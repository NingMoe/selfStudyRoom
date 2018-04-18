package cn.xdf.pay.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.alibaba.fastjson.JSONObject;
import cn.xdf.pay.annotation.ReadDataSource;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.constant.CommonStatus;
import cn.xdf.pay.constant.WechatPay;
import cn.xdf.pay.dao.OrderInfoDao;
import cn.xdf.pay.domain.CallSystem;
import cn.xdf.pay.domain.OrderInfo;
import cn.xdf.pay.domain.PayMerchant;
import cn.xdf.pay.domain.SystemOrder;
import cn.xdf.pay.domain.WechatPayParams;
import cn.xdf.pay.util.CommonUtil;
import cn.xdf.pay.util.SpringContextUtil;
import cn.xdf.pay.util.TimeUtil;
import cn.xdf.pay.util.XMLUtil;
import cn.xdf.pay.util.WechatPayUtil.WechatPayInterfaceUtil;
import cn.xdf.pay.util.WechatPayUtil.WechatPayRequestHandler;

@Service
public class OrderInfoService {

	private static Logger logger = LoggerFactory.getLogger(OrderInfoService.class);
	
	@Autowired
	private OrderInfoDao orderInfoDao;
	
	private OrderInfoService getService(){		
		return SpringContextUtil.getBean(this.getClass());
	}
	
	/**
	 * 保存订单
	 * @param orderInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int saveOrderInfo(OrderInfo orderInfo){
		return this.orderInfoDao.insertSelective(orderInfo);
	}
	
	/**
	 * 通过订单号查询订单
	 * @param orderNo
	 * @return
	 */
	@ReadDataSource
	public OrderInfo selectOrderInfoByOrderNo(String orderNo){
		return this.orderInfoDao.selectOrderByOrderNo(orderNo);
	}
	
	/**
	 * 更新订单
	 * @param orderInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int updateOrderInfo(OrderInfo orderInfo){
		return this.orderInfoDao.updateByPrimaryKeySelective(orderInfo);
	}
	
	
	/**
	 * 校验订单号参数
	 * @param orderNo
	 * @return
	 */
	public Map<String,Object> checkOrderInfo(String orderNo){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(orderNo)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单号参数必传！");
                return map;
			}
			//查询订单号真实性
			OrderInfo orderInfo=getService().selectOrderInfoByOrderNo(orderNo);
			if(orderInfo==null){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单号错误！");
                return map;
			}
			map.put("flag", true);
			map.put("orderInfo", orderInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("--------校验订单号参数异常----------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;		
	}
		
	/**
	 * 微信支付统一下单
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> unifiedOrder(String appId,String appKey,String orderJson,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String prepayId ="";
		String codeUrl="";
		String mwebUrl="";
		try{
			if(StringUtils.isEmpty(orderJson)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数必传！");
                return map;
			}
			OrderInfo orderInfo=JSONObject.parseObject(orderJson, OrderInfo.class);
            //校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//查询调用系统配置的微信支付商户
			CallSystem callSystem=new CallSystem();
			callSystem.setAppId(appId);
			callSystem.setAppKey(appKey);
			PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);			
			PayMerchant payMerchant=payMerchantService.selectByCallSystem(callSystem);
			if(payMerchant==null){
				map.put("flag", false);
				map.put("code", CommonStatus.UNAUTHORIZED);
				map.put("message", "调用系统未配置微信支付商户！");
                return map;
			}
			//校验订单必填参数是否填写
			if(StringUtils.isEmpty(orderInfo.getOrderName())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中订单名称必传！");
                return map;
			}
			String tradeType=orderInfo.getTradeType();
			if(StringUtils.isEmpty(tradeType)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中交易类型必传！");
                return map;
			}
			BigDecimal orderCost=orderInfo.getOrderCost();
			if(orderCost==null || orderCost.compareTo(new BigDecimal(0.00))<0){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中订单金额必传且为正数！");
                return map;
			}
			if(WechatPay.JSAPI.equals(tradeType) && StringUtils.isEmpty(orderInfo.getOpenId())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "公众号支付下订单参数缺少用户openId");
                return map;				
			}
			if(WechatPay.NATIVE.equals(tradeType) && StringUtils.isEmpty(orderInfo.getProductId())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "pc扫码支付下订单参数缺少商品Id");
                return map;				
			}
			if(WechatPay.MWEB.equals(tradeType) && StringUtils.isEmpty(orderInfo.getSceneInfo())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "H5支付下订单参数缺少场景信息");
                return map;				
			}
			orderInfo.setRealCost(orderCost);// 实际金额
	        orderInfo.setOrderState(0);// 待支付
	        //生成订单号
	        String orderNo=CommonUtil.getOrderNo();
	        orderInfo.setOrderNo(orderNo);
	        //订单生成时间
	        orderInfo.setTimeStart(TimeUtil.getCurrentTimestamp());
			//调用微信统一下单接口
	        String msgxml=WechatPayInterfaceUtil.getUnifiedorderXml(request, response, payMerchant, orderInfo);
        	if(StringUtils.isNotEmpty(msgxml)){
                 Map msgMap = XMLUtil.parseXmlToMap(msgxml);
                 String return_code = (String) msgMap.get("return_code");
                 String result_code = msgMap.get("result_code") + "";
                 if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
                	 prepayId =(String) msgMap.get("prepay_id");
                	 if(WechatPay.NATIVE.equals(tradeType)){
                		 codeUrl=(String) msgMap.get("code_url");
                	 }else if(WechatPay.MWEB.equals(tradeType)){
                		 mwebUrl=(String) msgMap.get("mweb_url");
                	 }
                 	 if(StringUtils.isNotEmpty(prepayId)){
                		orderInfo.setPayType(1);
                        orderInfo.setPrepayId(prepayId);	               
                        orderInfo.setCodeUrl(codeUrl);
                        orderInfo.setMwebUrl(mwebUrl);
                        //保存业务订单信息
                        int i=getService().saveOrderInfo(orderInfo);
                        if(i==1){
                        	//保存调用系统-订单信息
                        	SystemOrder systemOrder=new SystemOrder();                	
                        	systemOrder.setCallSystemId(payMerchant.getCallSystemId());
                        	systemOrder.setPayMerchantId(payMerchant.getId());
                        	systemOrder.setOrderId(orderInfo.getId());
                        	SystemOrderService systemOrderService=SpringContextUtil.getBean(SystemOrderService.class);
                        	systemOrderService.saveSystemOrder(systemOrder);
                        }
                	}
                 }else if("SUCCESS".equals(return_code) && "FAIL".equals(result_code)){
                	Object object=msgMap.get("err_code");
                    if(object!=null){
                        String err_code=(String) object;
                        logger.info("------err_code------"+err_code);
                    }   
                    object=msgMap.get("err_code_des");
                    if(object!=null){
                        String err_code_des=(String) object;
                        logger.info("-------err_code_des------"+err_code_des);
                    }
                    map.put("flag", false);
                	map.put("code", CommonStatus.EXCEPTION);
     				map.put("message", "调用微信统一下单接口业务错误！");
                    return map;
                }else{
                	Object object=msgMap.get("return_msg");
                    if(object!=null){
                        String return_msg =(String) object;
                        logger.info("------return_msg ------"+return_msg);
                    } 
                	map.put("flag", false);
                	map.put("code", CommonStatus.EXCEPTION);
     				map.put("message", "调用微信统一下单接口通信错误！");
                    return map;	
                 }	                          
            }else{
            	map.put("flag", false);
            	map.put("code", CommonStatus.EXCEPTION);
 				map.put("message", "调用微信统一下单接口异常！");
                return map;	
            }		        			        
	        map.put("flag", true); 
	        map.put("orderInfo", orderInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付统一下单异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;		
	}
	
	/**
	 * 微信支付查询订单
	 * @param appId
	 * @param appKey
	 * @param orderNo
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> queryOrder(String appId,String appKey,String orderNo,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//校验订单
			map=getService().checkOrderInfo(orderNo);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			OrderInfo orderInfo=(OrderInfo) map.get("orderInfo");
			int orderState=orderInfo.getOrderState();
			if(orderState==0){
				//查询该笔订单的微信支付商户
				PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
				PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderNo);
				//调用微信查询订单接口
				String msgxml=WechatPayInterfaceUtil.getQueryOrderXml(request, response, payMerchant, orderNo);
				if(StringUtils.isNotEmpty(msgxml)){
					Map msgMap = XMLUtil.parseXmlToMap(msgxml);
	                String return_code = (String) msgMap.get("return_code");
	                String result_code = msgMap.get("result_code") + "";
	                if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
	                	String trade_state = (String) msgMap.get("trade_state");// 交易状态
                        String trade_state_desc="";// 交易状态描述
                        if ("SUCCESS".equals(trade_state)) {// 支付成功
                        	String bank_type = (String) msgMap.get("bank_type");// 付款银行
                            String transaction_id = (String) msgMap.get("transaction_id");// 微信支付订单号
                            String time_end = (String) msgMap.get("time_end");// 支付完成时间
                            trade_state_desc = (String) msgMap.get("trade_state_desc");
                            String openid = (String) msgMap.get("openid");// 用户标识
                            // 更新业务订单信息
                            orderInfo.setTransactionId(transaction_id);
                            orderInfo.setOrderState(1);
                            orderInfo.setPayTime(TimeUtil.getTimestamp2(time_end));
                            orderInfo.setBankType(bank_type);
                            orderInfo.setResultCode(result_code);
                            orderInfo.setResultDesc(trade_state_desc);
                            orderInfo.setOpenId(openid);
                            int i=getService().updateOrderInfo(orderInfo);  
                            if(i==1){
                            	map.put("flag", true);
                				map.put("orderInfo", orderInfo);
                				return map;	
                            }
                        }else{
    						map.put("flag", false);
    		            	map.put("code", CommonStatus.EXCEPTION);
    		 				map.put("message", "订单未成功支付！");
    		                return map;	
    					}
	                }else if("SUCCESS".equals(return_code) && "FAIL".equals(result_code)){
	                	Object object=msgMap.get("err_code");
	                    if(object!=null){
	                        String err_code=(String) object;
	                        logger.info("------err_code------"+err_code);
	                    }   
	                    object=msgMap.get("err_code_des");
	                    if(object!=null){
	                        String err_code_des=(String) object;
	                        logger.info("-------err_code_des------"+err_code_des);
	                    }
	                    map.put("flag", false);
	                	map.put("code", CommonStatus.EXCEPTION);
	     				map.put("message", "调用微信查询订单接口业务错误！");
	                    return map;
	               }else{
	            	    Object object=msgMap.get("return_msg");
	                    if(object!=null){
	                        String return_msg =(String) object;
	                        logger.info("------return_msg ------"+return_msg);
	                    } 
						map.put("flag", false);
		            	map.put("code", CommonStatus.EXCEPTION);
		 				map.put("message", "调用微信查询订单接口通信错误！");
		                return map;	
					}
				}else{
					map.put("flag", false);
	            	map.put("code", CommonStatus.EXCEPTION);
	 				map.put("message", "调用微信查询订单接口异常！");
	                return map;	
				}
			}else{
				map.put("flag", true);
				map.put("orderInfo", orderInfo);
			}	
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付查询订单异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}		
		return map;
	}
	
	/**
	 * 微信支付关闭订单
	 * @param appId
	 * @param appKey
	 * @param orderNo
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> closeOrder(String appId,String appKey,String orderNo,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//校验订单
			map=getService().checkOrderInfo(orderNo);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			OrderInfo orderInfo=(OrderInfo) map.get("orderInfo");
			int orderState=orderInfo.getOrderState();
			if(orderState==0){
				//查询该笔订单的微信支付商户
				PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
				PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderNo);
				//调用微信关闭订单接口
				String msgxml=WechatPayInterfaceUtil.getCloseOrderXml(request, response, payMerchant, orderNo);
				if(StringUtils.isNotEmpty(msgxml)){
					Map msgMap = XMLUtil.parseXmlToMap(msgxml);
	                String return_code = (String) msgMap.get("return_code");
	                String result_code = msgMap.get("result_code") + "";
	                if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
	                	//更新订单支付状态
	                    orderInfo.setOrderState(2);//支付失败
	                    int i=getService().updateOrderInfo(orderInfo);
	                    if(i==1){
                        	map.put("flag", true);
            				map.put("orderInfo", orderInfo);
            				return map;	
                        }	                    
	                }else if("SUCCESS".equals(return_code) && "FAIL".equals(result_code)){
	                	Object object=msgMap.get("err_code");
	                    if(object!=null){
	                        String err_code=(String) object;
	                        logger.info("------err_code------"+err_code);
	                    }   
	                    object=msgMap.get("err_code_des");
	                    if(object!=null){
	                        String err_code_des=(String) object;
	                        logger.info("-------err_code_des------"+err_code_des);
	                    }
	                    map.put("flag", false);
	                	map.put("code", CommonStatus.EXCEPTION);
	     				map.put("message", "调用微信关闭订单接口业务错误！");
	                    return map;
	               }else{
	            	    Object object=msgMap.get("return_msg");
	                    if(object!=null){
	                        String return_msg =(String) object;
	                        logger.info("------return_msg ------"+return_msg);
	                    } 
						map.put("flag", false);
		            	map.put("code", CommonStatus.EXCEPTION);
		 				map.put("message", "调用微信关闭订单接口通信错误！");
		                return map;	
					}
				}else{
					map.put("flag", false);
	            	map.put("code", CommonStatus.EXCEPTION);
	 				map.put("message", "调用微信关闭订单接口异常！");
	                return map;	
				}
			}else if(orderState==1){
				map.put("flag", false);
            	map.put("code", CommonStatus.EXCEPTION);
 				map.put("message", "订单已支付，不能发起关单");
                return map;	
			}else if(orderState==2){
				map.put("flag", false);
            	map.put("code", CommonStatus.EXCEPTION);
 				map.put("message", "订单已关闭，无法重复关闭");
                return map;	
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付关闭订单异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}		
		return map;
	}
	
	/**
	 * 获取微信支付参数
	 * @param appId
	 * @param appKey
	 * @param orderNo
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String,Object> getWeChatPayParams(String appId,String appKey,String orderNo,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//校验订单
			map=getService().checkOrderInfo(orderNo);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			OrderInfo orderInfo=(OrderInfo) map.get("orderInfo");
			String prepayId=orderInfo.getPrepayId();
			if(StringUtils.isNotEmpty(prepayId)){
				//查询该笔订单的微信支付商户
				PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
				PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderNo);
				//参数                
                String timeStamp=TimeUtil.getSecond();
                String packageStr="prepay_id="+prepayId;
                String nonceStr = CommonUtil.getNonceStr();
                WechatPayRequestHandler prepayReqHandler = new WechatPayRequestHandler(request, response);
    			prepayReqHandler.setKey(payMerchant.getMchSecret());
    			prepayReqHandler.setParameter("appId",payMerchant.getAppId());//公众账号ID
    			prepayReqHandler.setParameter("timeStamp",timeStamp );//时间戳
    			prepayReqHandler.setParameter("nonceStr", nonceStr);//随机字符串
    			prepayReqHandler.setParameter("package",packageStr);//订单详情扩展字符串
    			prepayReqHandler.setParameter("signType", "MD5"); //签名算法   
                String paySign = prepayReqHandler.createMd5Sign(); //生成获取支付签名   
                WechatPayParams wechatPayParams=new WechatPayParams();
                wechatPayParams.setAppId(payMerchant.getAppId());
                wechatPayParams.setTimeStamp(timeStamp);
                wechatPayParams.setNonceStr(nonceStr);
                wechatPayParams.setPackageInfo(packageStr);
                wechatPayParams.setPaySign(paySign);
                map.put("flag",true);
                map.put("wechatPayParams",wechatPayParams);
			}else{
				map.put("flag",false);
				map.put("code", CommonStatus.EXCEPTION);
                map.put("message", "获取微信支付参数失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------获取微信支付参数异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;
	}
	
	
	/**
	 * 微信支付通知回调
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String weChatNotify(HttpServletRequest request, HttpServletResponse response) {
		StringBuffer sb = new StringBuffer();
		String params = "<xml>" + "\r\n";
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			InputStream in = request.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			String msgXml = new String(out.toByteArray(), "utf-8");// xml数据
			out.close();
			in.close();
			logger.info("------微信支付通知回调返回的xml数据------" + msgXml);
			if (StringUtils.isNotEmpty(msgXml)) {
				Map msgMap = XMLUtil.parseXmlToMap(msgXml);
				String return_code = (String) msgMap.get("return_code");
				String result_code = (String) msgMap.get("result_code");
				if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
					String bank_type = (String) msgMap.get("bank_type");// 付款银行
					String transaction_id = (String) msgMap.get("transaction_id");// 微信支付订单号
					String time_end = (String) msgMap.get("time_end");// 支付完成时间
					String trade_type = (String) msgMap.get("trade_type");// 交易类型
					String openid = (String) msgMap.get("openid");// 用户标识
					String orderNo = (String) msgMap.get("out_trade_no");// 商户订单号
					// 通过订单号查询订单
                    OrderInfo orderInfo = getService().selectOrderInfoByOrderNo(orderNo);
                    int orderState = orderInfo.getOrderState();
                    if (orderState == 0) {// 未处理业务逻辑
                    	// 更新业务订单信息
                        orderInfo.setTransactionId(transaction_id);
                        orderInfo.setOrderState(1);
                        orderInfo.setPayTime(TimeUtil.getTimestamp2(time_end));
                        orderInfo.setBankType(bank_type);
                        orderInfo.setResultCode(result_code);
                        orderInfo.setOpenId(openid);
                        orderInfo.setTradeType(trade_type);
                        getService().updateOrderInfo(orderInfo);
                    }
                    sb.append("<return_code><![CDATA[SUCCESS]]></return_code>" + "\r\n");
                    sb.append("<return_msg><![CDATA[OK]]></return_msg>" + "\r\n");
				}else if ("SUCCESS".equals(return_code) && "FAIL".equals(result_code)) {
                    sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
                    logger.error("------微信支付结果通知回调业务结果失败------");
                } else if ("FAIL".equals(return_code)) {
                    sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
                    logger.error("------微信支付结果通知回调通信失败------");
                }
			}
		}catch (Exception e) {
			e.printStackTrace();
			sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		params += sb.toString();
		params += "</xml>";
		return params;		
	}
}
