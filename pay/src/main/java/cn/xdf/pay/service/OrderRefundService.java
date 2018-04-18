package cn.xdf.pay.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import cn.xdf.pay.annotation.ReadDataSource;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.constant.CommonStatus;
import cn.xdf.pay.dao.OrderRefundInfoDao;
import cn.xdf.pay.domain.OrderInfo;
import cn.xdf.pay.domain.OrderRefundInfo;
import cn.xdf.pay.domain.PayMerchant;
import cn.xdf.pay.util.CommonUtil;
import cn.xdf.pay.util.SpringContextUtil;
import cn.xdf.pay.util.TimeUtil;
import cn.xdf.pay.util.XMLUtil;
import cn.xdf.pay.util.WechatPayUtil.WechatPayInterfaceUtil;

@Service
public class OrderRefundService {
	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundService.class);
	
	@Autowired
	private OrderRefundInfoDao orderRefundInfoDao;
	
	private OrderRefundService getService(){		
		return SpringContextUtil.getBean(this.getClass());
	}
	
	/**
	 * 保存退款单
	 * @param orderInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int saveOrderRefundInfo(OrderRefundInfo orderRefundInfo){
		return this.orderRefundInfoDao.insertSelective(orderRefundInfo);
	}
		
	/**
	 * 通过订单号查询退款单
	 * @param orderNo
	 * @return
	 */
	@ReadDataSource
	public List<OrderRefundInfo> selectByOrderNo(String orderNo){
		return this.orderRefundInfoDao.selectByOrderNo(orderNo);
	}
	
	/**
	 * 查询某订单号对应的退款总金额
	 * @param orderNo
	 * @return
	 */
	@ReadDataSource
	public  BigDecimal selectTotalRefundByOrderNo(String orderNo){
		return this.orderRefundInfoDao.selectTotalRefundByOrderNo(orderNo);
	}

	/**
	 * 更新退款单
	 * @param orderRefundInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int updateOrderRefundInfo(OrderRefundInfo orderRefundInfo){
		return this.orderRefundInfoDao.updateByPrimaryKeySelective(orderRefundInfo);
	}
	
	/**
	 * 查询系统所有处于退款中的退款单
	 * @param orderNo
	 * @return
	 */
	@ReadDataSource
	public List<OrderRefundInfo> selectAll(){
		return this.orderRefundInfoDao.selectAll();
	}
	
	/**
	 * 通过退款单号查询退款单
	 * @param orderNo
	 * @return
	 */
	@ReadDataSource
	public OrderRefundInfo selectByOrderRefundNo(String orderRefundNo){
		return this.orderRefundInfoDao.selectByOrderRefundNo(orderRefundNo);
	}
	
			
	/**
	 * 微信支付申请退款
	 * @param appId
	 * @param appKey
	 * @param orderRefundJson
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({"rawtypes" })
	public Map<String,Object> refundOrder(String appId,String appKey,String orderRefundJson,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(orderRefundJson)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "退款单json参数必传！");
                return map;
			}
			OrderRefundInfo orderRefundInfo=JSONObject.parseObject(orderRefundJson, OrderRefundInfo.class);
			//校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}			
			//校验退款单必填参数是否填写			
			BigDecimal refundCost=orderRefundInfo.getRefundCost();
			if(refundCost==null || refundCost.compareTo(new BigDecimal(0.00))<0){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "退款单参数中退款金额必传且为正数！");
                return map;
			}
			String orderNo=orderRefundInfo.getOrderNo();
			OrderInfoService orderInfoService=SpringContextUtil.getBean(OrderInfoService.class);
			map=orderInfoService.checkOrderInfo(orderNo);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			OrderInfo orderInfo=(OrderInfo) map.get("orderInfo");
			//判断订单是否已支付
			if(orderInfo.getOrderState()!=1){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单未支付！");
                return map;
			}
			//判断单笔退款金额是否大于订单金额
			if(refundCost.compareTo(orderInfo.getOrderCost())==1){
                 map.put("flag", false);
                 map.put("code", CommonStatus.FORBIDDEN);
                 map.put("message", "退款金额大于订单金额!");
                 return map;
            }
			//判断累计退款金额是否大于订单金额
			BigDecimal totalRefundCost=getService().selectTotalRefundByOrderNo(orderNo);
			if((refundCost.add(totalRefundCost)).compareTo(orderInfo.getOrderCost())==1){
				map.put("flag", false);
                map.put("code", CommonStatus.FORBIDDEN);
                map.put("message", "累计退款金额大于订单金额!");
                return map;
			}
			//查询该笔订单的微信支付商户
			PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
			PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderNo);
			//生成退款订单信息
			String out_refund_no =CommonUtil.getOrderNo() ;
			orderRefundInfo.setTransactionId(orderInfo.getTransactionId());//微信支付订单号
            orderRefundInfo.setOrderRefundNo(out_refund_no);//商户退款单号
            orderRefundInfo.setOrderCost(orderInfo.getOrderCost());//订单金额			
			//调用微信申请退款接口
			Map msgMap=WechatPayInterfaceUtil.getRefundOrderMap(request,response, payMerchant,orderRefundInfo);
			String return_code = (String) msgMap.get("return_code");
            String result_code = msgMap.get("result_code") + "";
            logger.info("return_code===="+return_code+",result_code====="+result_code);
            if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){ 
            	 String refund_id = (String) msgMap.get("refund_id");
            	 orderRefundInfo.setCreateTime(TimeUtil.getCurrentTimestamp());//申请退款时间
                 orderRefundInfo.setOrderRefundState(1);//退款中
                 orderRefundInfo.setRefundId(refund_id);// 微信退款单号
                 orderRefundInfo.setResultCode(result_code);
                 int i=getService().saveOrderRefundInfo(orderRefundInfo);
                 if(i==1){
                	map.put("flag", true);
     				map.put("orderRefundInfo", orderRefundInfo);
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
 				map.put("message", "调用微信申请退款接口业务错误！");
                return map;
            }else{
            	Object object=msgMap.get("return_msg");
                if(object!=null){
                    String return_msg =(String) object;
                    logger.info("------return_msg ------"+return_msg);
                }
            	map.put("flag", false);
            	map.put("code", CommonStatus.EXCEPTION);
 				map.put("message", "调用微信申请退款接口通信错误！");
                return map;	
            }            
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信申请退款异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;
	}
	
	
	
	/**
	 * 微信支付查询退款
	 * @param appId
	 * @param appKey
	 * @param orderNo
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<String,Object> queryRefundOrderOrder(String appId,String appKey,String orderNo,HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			//校验调用系统
			CallSystemService callSystemService=SpringContextUtil.getBean(CallSystemService.class);
			map=callSystemService.checkCallSystem(appId, appKey);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//校验订单
			OrderInfoService orderInfoService=SpringContextUtil.getBean(OrderInfoService.class);
			map=orderInfoService.checkOrderInfo(orderNo);
			if(!(Boolean) map.get("flag")){
				return map;
			}
			//校验订单是否发起过退款
			List<OrderRefundInfo> refundList=getService().selectByOrderNo(orderNo);
			if(CollectionUtils.isEmpty(refundList)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "该订单号未申请过微信退款！");
                return map;
			}
			//查询该笔订单的微信支付商户
			PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
			PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderNo);
			for(OrderRefundInfo orderRefundInfo:refundList){
				if(StringUtils.isEmpty(orderRefundInfo.getRefundRecvAccout())){
					//调用微信查询退款接口
					String msgxml = WechatPayInterfaceUtil.getQueryRefundOrderXml(request, response, payMerchant,orderRefundInfo.getOrderRefundNo());
					if(StringUtils.isNotEmpty(msgxml)){
						Map msgMap = XMLUtil.parseXmlToMap(msgxml);
		                String return_code =(String) msgMap.get("return_code");
		                String result_code =msgMap.get("result_code")+"";
		                if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
		                	String refund_channel=(String) msgMap.get("refund_channel_0");//退款渠道
		                    String refund_count=(String) msgMap.get("refund_count");//退款笔数
		                    String refund_status =(String) msgMap.get("refund_status_0");//退款状态 
		                    String refund_recv_accout  =(String) msgMap.get("refund_recv_accout_0");//退款入账账户
		                    String return_msg=(String) msgMap.get("return_msg");//返回信息
		                    orderRefundInfo.setRefundChannel(refund_channel);
		                    orderRefundInfo.setRefundCount(Integer.valueOf(refund_count));
		                    orderRefundInfo.setResultDesc(return_msg);//结果说明
		                    orderRefundInfo.setResultCode(result_code);//业务结果
		                    orderRefundInfo.setRefundDesc(return_code);
		                    Integer order_refund_state=1;
		                    if("SUCCESS".equals(refund_status)){//退款成功 
		                    	 order_refund_state=2;
		                    	 orderRefundInfo.setRefundRecvAccout(refund_recv_accout);
		                    }else if("FAIL".equals(refund_status)){//退款失败
		                        order_refund_state=3;
		                    }else if("PROCESSING".equals(refund_status)){//退款中 
		                        order_refund_state=1;
		                    }else if("CHANGE".equals(refund_status)){//转入代发
		                        order_refund_state=4;
		                    }		                    
		                    orderRefundInfo.setOrderRefundState(order_refund_state);
		                    int i= getService().updateOrderRefundInfo(orderRefundInfo);
		                    if(i!=1){
		                    	map.put("flag", false);
				            	map.put("code", CommonStatus.EXCEPTION);
				 				map.put("message", "更新退款单状态异常！");
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
		     				map.put("message", "调用微信查询退款接口业务错误！");
		                    return map;
		                }else{
		                	Object object=msgMap.get("return_msg");
		                    if(object!=null){
		                        String return_msg =(String) object;
		                        logger.info("------return_msg ------"+return_msg);
		                    }
		                	map.put("flag", false);
			            	map.put("code", CommonStatus.EXCEPTION);
			 				map.put("message", "微信查询退款接口返回通信错误！");
			                return map;	
		                }
					}else{
						map.put("flag", false);
		            	map.put("code", CommonStatus.EXCEPTION);
		 				map.put("message", "调用微信查询退款接口异常！");
		                return map;	
					}
				}	
			}
			map.put("flag", true);
			map.put("refundList", refundList);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信查询退款异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}		
		return map;	
	}
	
	
	/**
	 * 更新系统里所有处于退款中的退款单
	 * @param request
	 * @param response
	 */
	public void updateOrderRefundInfo(HttpServletRequest request, HttpServletResponse response) {      
        //查出审核通过并且处于退款中的退款订单
        List<OrderRefundInfo> list=getService().selectAll();
        if(!CollectionUtils.isEmpty(list)){
        	 for(OrderRefundInfo orderRefundInfo:list){
                 updateRefundDetail(orderRefundInfo,request,response);
             }        	
        }
    }
	
	
	/**
	 * 更新退款单
	 * @param orderRefundInfo
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@Async
	public void updateRefundDetail(OrderRefundInfo orderRefundInfo,HttpServletRequest request,HttpServletResponse response){
		try{
			//查询订单的微信支付商户
			PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
			PayMerchant payMerchant=payMerchantService.selectByOrderNo(orderRefundInfo.getOrderNo());
			//调用微信查询退款接口
			String msgxml = WechatPayInterfaceUtil.getQueryRefundOrderXml(request, response, payMerchant,orderRefundInfo.getOrderRefundNo());
			if(StringUtils.isNotEmpty(msgxml)){
				Map msgMap = XMLUtil.parseXmlToMap(msgxml);
	            String return_code =(String) msgMap.get("return_code");
	            String result_code =msgMap.get("result_code")+"";
	            if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
	            	String refund_channel=(String) msgMap.get("refund_channel_0");//退款渠道
	                String refund_count=(String) msgMap.get("refund_count");//退款笔数
	                String refund_status =(String) msgMap.get("refund_status_0");//退款状态 
	                String refund_recv_accout  =(String) msgMap.get("refund_recv_accout_0");//退款入账账户
	                String return_msg=(String) msgMap.get("return_msg");//返回信息
	                orderRefundInfo.setRefundChannel(refund_channel);
	                orderRefundInfo.setRefundCount(Integer.valueOf(refund_count));
	                orderRefundInfo.setResultDesc(return_msg);//结果说明
	                orderRefundInfo.setResultCode(result_code);//业务结果
	                orderRefundInfo.setRefundDesc(return_code);
	                Integer order_refund_state=1;
	                if("SUCCESS".equals(refund_status)){//退款成功 
	                	 order_refund_state=2;
	                	 orderRefundInfo.setRefundRecvAccout(refund_recv_accout);
	                }else if("FAIL".equals(refund_status)){//退款失败
	                    order_refund_state=3;
	                }else if("PROCESSING".equals(refund_status)){//退款中 
	                    order_refund_state=1;
	                }else if("CHANGE".equals(refund_status)){//转入代发
	                    order_refund_state=4;
	                }		                    
	                orderRefundInfo.setOrderRefundState(order_refund_state);
	                int i= getService().updateOrderRefundInfo(orderRefundInfo);
	                if(i==1){
	                	logger.info("------更新退款单成功------");
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
                    logger.error("------调用微信查询退款接口业务错误------");
                }else{
                	Object object=msgMap.get("return_msg");
                    if(object!=null){
                        String return_msg =(String) object;
                        logger.info("------return_msg ------"+return_msg);
                    } 
	            	logger.error("------调用微信查询退款接口通信错误------");
	            }
			}else{				
				logger.error("------调用微信查询退款接口异常------");
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("------更新退款单异常------",e.getMessage());
		}				
	}
}
