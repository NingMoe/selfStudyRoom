package cn.xdf.pay.service;

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
import com.alibaba.fastjson.JSONObject;
import cn.xdf.pay.annotation.ReadDataSource;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.constant.CommonStatus;
import cn.xdf.pay.dao.CompanyOrderInfoDao;
import cn.xdf.pay.domain.CallSystem;
import cn.xdf.pay.domain.CompanyOrderInfo;
import cn.xdf.pay.domain.PayMerchant;
import cn.xdf.pay.domain.SystemCompanyOrder;
import cn.xdf.pay.util.CommonUtil;
import cn.xdf.pay.util.SpringContextUtil;
import cn.xdf.pay.util.TimeUtil;
import cn.xdf.pay.util.WechatPayUtil.WechatPayInterfaceUtil;

@Service
public class CompanyOrderInfoService {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyOrderInfoService.class);
	
	@Autowired
	private  CompanyOrderInfoDao  companyOrderInfoDao;
	
	private CompanyOrderInfoService getService(){		
		return SpringContextUtil.getBean(this.getClass());
	}
	
	/**
	 * 保存企业付款到零钱订单
	 * @param orderInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int saveCompanyOrderInfo(CompanyOrderInfo companyOrderInfo){
		return this.companyOrderInfoDao.insertSelective(companyOrderInfo);
	}
	
	
	/**
	 * 通过商户订单号查询企业付款订单
	 * @param partnerTradeNo
	 * @return
	 */
	@ReadDataSource
	public CompanyOrderInfo selectOrderInfoByOrderNo(String partnerTradeNo){
		return this.companyOrderInfoDao.selectOrderInfoByOrderNo(partnerTradeNo);
	}
	
	/**
	 * 更新企业付款到零钱订单
	 * @param orderInfo
	 * @return
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int updateOrderInfo(CompanyOrderInfo companyOrderInfo){
		return this.companyOrderInfoDao.updateByPrimaryKeySelective(companyOrderInfo);
	}
	
	
	/**
	 * 校验商户订单号参数
	 * @param orderNo
	 * @return
	 */
	public Map<String,Object> checkOrderInfo(String orderNo){
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(orderNo)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "商户订单号参数必传！");
                return map;
			}
			//查询商户订单号真实性
			CompanyOrderInfo orderInfo=getService().selectOrderInfoByOrderNo(orderNo);
			if(orderInfo==null){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "商户订单号错误！");
                return map;
			}
			map.put("flag", true);
			map.put("orderInfo", orderInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("--------校验商户订单号参数异常----------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;		
	}
		
	/**
	 * 微信企业付款到零钱
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
		try{
			if(StringUtils.isEmpty(orderJson)){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数必传！");
                return map;
			}
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
			CompanyOrderInfo companyOrderInfo=JSONObject.parseObject(orderJson, CompanyOrderInfo.class);
			//校验订单必填参数是否填写
			if(StringUtils.isEmpty(companyOrderInfo.getOpenId())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中收款用户openId必传！");
                return map;
			}
			BigDecimal orderCost=companyOrderInfo.getAmount();
			if(orderCost==null || orderCost.compareTo(new BigDecimal(0.00))<0){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中付款金额必传且为正数！");
                return map;
			}
			if(StringUtils.isEmpty(companyOrderInfo.getPayDesc())){
				map.put("flag", false);
				map.put("code", CommonStatus.FORBIDDEN);
				map.put("message", "订单参数中付款描述必传！");
                return map;
			}
			//生成订单号
	        String orderNo=CommonUtil.getOrderNo();
	        companyOrderInfo.setPartnerTradeNo(orderNo);
	        //订单生成时间
	        companyOrderInfo.setCreateTime(TimeUtil.getCurrentTimestamp());
	        //调用微信企业付款到零钱接口
	        Map msgMap= WechatPayInterfaceUtil.getTransfersOrderMap(request, response, payMerchant,companyOrderInfo);
	        String return_code = (String) msgMap.get("return_code");
            String result_code = msgMap.get("result_code") + "";
            logger.info("------微信企业付款到零钱接口return_code===="+return_code+",result_code====="+result_code);
            if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
            	String paymentNo=(String) msgMap.get("payment_no");
            	String paymentTime =(String) msgMap.get("payment_time");
            	companyOrderInfo.setPaymentNo(paymentNo);
            	companyOrderInfo.setTransferTime(TimeUtil.getTimestamp(paymentTime));
            	companyOrderInfo.setStatus("1");
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
            	//主动请求查询企业付款零钱接口确认
            	msgMap= WechatPayInterfaceUtil.getQueryTransfersOrderMap(request, response, payMerchant,companyOrderInfo);
    	        return_code = (String) msgMap.get("return_code");
                result_code = msgMap.get("result_code") + "";
                logger.info("------微信企业付款到零钱查询接口return_code===="+return_code+",result_code====="+result_code);
                if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
                	String status =(String) msgMap.get("status");
                	String paymentNo=(String) msgMap.get("detail_id");
                	String transferTime =(String) msgMap.get("transfer_time");
                	companyOrderInfo.setPaymentNo(paymentNo);
                	companyOrderInfo.setTransferTime(TimeUtil.getTimestamp(transferTime));
                	if("SUCCESS".equals(status)){//转账成功                     	
                    	companyOrderInfo.setStatus("1");
                	}else if("FAILED".equals(status)){//转账失败 
                		String failReason=(String) msgMap.get("reason");
                		companyOrderInfo.setFailReason(failReason);
                		companyOrderInfo.setStatus("2");            		
                	}else{//处理中
                		companyOrderInfo.setStatus("0");            		
                	}                	
                }else{
                	map.put("flag", false);
                	map.put("code", CommonStatus.EXCEPTION);
     				map.put("message", "调用微信企业付款到零钱查询接口错误！");
                    return map;
                }
            }else{
            	Object object=msgMap.get("return_msg");
                if(object!=null){
                    String return_msg =(String) object;
                    logger.info("------return_msg ------"+return_msg);
                }
            	map.put("flag", false);
            	map.put("code", CommonStatus.EXCEPTION);
 				map.put("message", "调用微信企业付款到零钱接口通信错误！");
                return map;
            }
			int i=getService().saveCompanyOrderInfo(companyOrderInfo);
			if(i==1){
				//保存调用系统-企业付款订单信息
            	SystemCompanyOrder systemCompanyOrder=new SystemCompanyOrder();                	
            	systemCompanyOrder.setCallSystemId(payMerchant.getCallSystemId());
            	systemCompanyOrder.setPayMerchantId(payMerchant.getId());
            	systemCompanyOrder.setCompanyOrderId(companyOrderInfo.getId());
            	SystemCompanyOrderService systemCompanyOrderService=SpringContextUtil.getBean(SystemCompanyOrderService.class);
            	systemCompanyOrderService.saveSystemCompanyOrder(systemCompanyOrder);
			}
			map.put("flag", true); 
		    map.put("companyOrderInfo", companyOrderInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信企业付款到零钱异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;		
	}
	
	/**
	 * 查询微信企业付款到零钱
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
			CompanyOrderInfo companyOrderInfo=(CompanyOrderInfo)map.get("orderInfo");
			String status=companyOrderInfo.getStatus();
			if("0".equals(status)){
				//查询该笔企业付款订单的微信支付商户
				PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
				PayMerchant payMerchant=payMerchantService.selectByPartnerTradeNo(orderNo);
				//调用查询企业付款零钱接口
				Map msgMap= WechatPayInterfaceUtil.getQueryTransfersOrderMap(request, response, payMerchant,companyOrderInfo);
				String return_code = (String) msgMap.get("return_code");
	            String result_code = msgMap.get("result_code") + "";
	            logger.info("------微信企业付款到零钱查询接口return_code===="+return_code+",result_code====="+result_code);
	            if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
                	status =(String) msgMap.get("status");
                	String paymentNo=(String) msgMap.get("detail_id");
                	String transferTime =(String) msgMap.get("transfer_time");
                	companyOrderInfo.setPaymentNo(paymentNo);
                	companyOrderInfo.setTransferTime(TimeUtil.getTimestamp(transferTime));
                	if("SUCCESS".equals(status)){//转账成功                     	
                    	companyOrderInfo.setStatus("1");
                	}else if("FAILED".equals(status)){//转账失败 
                		String failReason=(String) msgMap.get("reason");
                		companyOrderInfo.setFailReason(failReason);
                		companyOrderInfo.setStatus("2");            		
                	}else{//处理中
                		companyOrderInfo.setStatus("0");            		
                	}
                	getService().updateOrderInfo(companyOrderInfo);
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
     				map.put("message", "调用微信企业付款到零钱查询接口返回业务错误！");
                    return map;
                }else{
                	Object object=msgMap.get("return_msg");
                    if(object!=null){
                        String return_msg =(String) object;
                        logger.info("------return_msg ------"+return_msg);
                    }   
                	map.put("flag", false);
                	map.put("code", CommonStatus.EXCEPTION);
     				map.put("message", "调用微信企业付款到零钱查询接口通信错误！");
                    return map;
                }
			}
			map.put("flag", true);
			map.put("companyOrderInfo", companyOrderInfo);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------查询微信企业付款到零钱异常---------", e.getMessage());	
			map.put("flag", false);
			map.put("code", CommonStatus.EXCEPTION);
			map.put("message",e.getMessage());
		}
		return map;		
	}
}
