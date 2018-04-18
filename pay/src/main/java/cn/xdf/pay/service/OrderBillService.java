package cn.xdf.pay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.dao.OrderBillDao;
import cn.xdf.pay.domain.OrderBill;
import cn.xdf.pay.domain.OrderRefundInfo;
import cn.xdf.pay.domain.PayMerchant;
import cn.xdf.pay.util.SpringContextUtil;
import cn.xdf.pay.util.TimeUtil;
import cn.xdf.pay.util.XMLUtil;
import cn.xdf.pay.util.WechatPayUtil.WechatPayInterfaceUtil;

@Service
public class OrderBillService {

	private static Logger logger = LoggerFactory.getLogger(OrderBillService.class);
		
	@Autowired
	private OrderBillDao orderBillDao;
			
	
	/**
	 * 下载微信每日对账单
	 * @param request
	 * @param response
	 */
	public void downLoadWxOrderBill(HttpServletRequest request,HttpServletResponse response){
		try{
			PayMerchantService payMerchantService=SpringContextUtil.getBean(PayMerchantService.class);
			List<PayMerchant> list=payMerchantService.selectAllPayMerchant();
			if(!CollectionUtils.isEmpty(list)){
				for(PayMerchant payMerchant:list){
					asyncDownLoadWxOrderBill(request,response,payMerchant);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("------下载微信每日对账单异常------", e.getMessage());
		}		
	}
	
	/**
	 * 异步下载微信每日对账单
	 * @param request
	 * @param response
	 * @param payMerchant
	 */
	@SuppressWarnings({"rawtypes"})
	@Async
	public void asyncDownLoadWxOrderBill(HttpServletRequest request,HttpServletResponse response,PayMerchant payMerchant){
		String bill_type="SUCCESS";//账单类型		
		try {
			String msgxml=WechatPayInterfaceUtil.getWxMsgxml(request, response,bill_type,payMerchant);			 
			logger.info("------调用微信对账单接口获取支付成功订单返回数据------"+msgxml);
			if(msgxml.startsWith("<xml>")){
				Map msgMap = XMLUtil.parseXmlToMap(msgxml);
				String return_msg =(String) msgMap.get("return_msg");
				if("No Bill Exist".equals(return_msg)){
					logger.info("------无支付成功订单------");
				}else{
					logger.info("------调用微信对账单接口获取支付成功订单失败------");
				}
			}else{
				//读取文本文件
				String[]t=msgxml.split("\n");
				List<String> list = new ArrayList<String>(Arrays.asList(t));
				list=list.subList(1, list.size()-2);
				logger.info("------已支付成功订单数------"+list.size());
				for(String s:list){
					String[] t1=s.replaceAll("`", "").split(",");
					getService().insertSucessesOrderBill(t1);					
				}					
			}
			bill_type="REFUND";
			msgxml=WechatPayInterfaceUtil.getWxMsgxml(request, response, bill_type,payMerchant);
			logger.info("------调用微信对账单接口获取退款订单返回数据------"+msgxml);
			if(msgxml.startsWith("<xml>")){
				Map msgMap = XMLUtil.parseXmlToMap(msgxml);
				String return_msg =(String) msgMap.get("return_msg");
				if("No Bill Exist".equals(return_msg)){
					logger.info("------无退款订单------");
				}else{
					logger.info("------调用微信对账单接口获取退款订单失败------");
				}
			}else{
				//读取文本文件
				String[]t=msgxml.split("\n");
				List<String> list = new ArrayList<String>(Arrays.asList(t));
				list=list.subList(1, list.size()-2);
				logger.info("------退款订单数------"+list.size());
				for(String s:list){
					String[] t1=s.replaceAll("`", "").split(",");
					getService().insertRefundOrderBill(t1);					
				}					
			}	
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("------下载微信每日对账单异常------",e.getMessage());
		}	
	}
	
	
	/**
	 * 往对账表中插入支付成功的对账单
	 * @param t
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public  void  insertSucessesOrderBill(String[] t){
	    try{
	        List<String> list = new ArrayList<String>(Arrays.asList(t));
	        BigDecimal money;
	        OrderBill orderBill = extracted(list);
	        orderBill.setProductName(list.get(14));//商品名称(订单名称）
	        orderBill.setBzdataPacket(list.get(15));//商户数据包
	        money=new BigDecimal(list.get(16));
	        orderBill.setFee(money);//手续费
	        orderBill.setRate(list.get(17));//费率
	        orderBill.setBillType(0);
	        logger.info("------对账表中插入支付成功的对账单开始------");
	        int i=this.orderBillDao.insertSelective(orderBill);
	        if(i==1){
	            logger.info("------对账表中插入支付成功的对账单成功结束------");
	        }  
	    }catch(Exception e){
	        e.printStackTrace();
	        logger.info("------对账表中插入支付成功的对账单失败------",e.getMessage());
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    }		
	}
	
	/**
	 * 往对账表中插入退款的对账单
	 * @param t
	 */
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public  void  insertRefundOrderBill(String[] t){
	    try{
	        List<String> list = new ArrayList<String>(Arrays.asList(t));
	        BigDecimal money;
	        OrderBill orderBill = extracted(list);      
	        orderBill.setRefundCreateTime(TimeUtil.getTimestamp(list.get(14)));//退款申请时间
	        orderBill.setRefundSucessesTime(TimeUtil.getTimestamp(list.get(15)));//退款成功时间
	        orderBill.setWxrefund(list.get(16));//微信退款单号
	        orderBill.setBzrefund(list.get(17));//商户退款单号
	        money=new BigDecimal(list.get(18));
	        orderBill.setRefundMoney(money);//退款金额
	        money=new BigDecimal(list.get(19));
	        orderBill.setRedpacketRefundMoney(money);//代金券或立减优惠退款金额
	        orderBill.setRefundType(list.get(20));//退款类型
	        orderBill.setRefundStatus(list.get(21));//退款状态      
	        orderBill.setProductName(list.get(22));//商品名称(订单名称）
	        orderBill.setBzdataPacket(list.get(23));//商户数据包
	        money=new BigDecimal(list.get(24));
	        orderBill.setFee(money);//手续费
	        orderBill.setRate(list.get(25));//费率
	        orderBill.setBillType(1);
	        logger.info("------对账表中插入退款的对账单开始------");
	        int i=this.orderBillDao.insertSelective(orderBill);
	        if(i==1){	 
	            logger.info("------对账表中插入退款的对账单成功结束------");
	            //更新退款表
	            OrderRefundService orderRefundService=SpringContextUtil.getBean(OrderRefundService.class);
	            OrderRefundInfo orderRefundInfo=orderRefundService.selectByOrderRefundNo(list.get(17));
	            if(orderRefundInfo!=null){
	                orderRefundInfo.setSucessesTime(TimeUtil.getTimestamp(list.get(15)));//退款成功时间
	                orderRefundInfo.setRefundChannel(list.get(20));//退款渠道
	                orderRefundInfo.setRefundCount(1);//退款笔数
	                Integer order_refund_state=1;
	                if("SUCCESS".equals(list.get(21))){//退款成功 
	                    order_refund_state=2;  
	                }else if("FAIL".equals(list.get(21))){//退款失败
	                    order_refund_state=3;
	                }else if("PROCESSING".equals(list.get(21))){//退款中 
	                    order_refund_state=1;
	                }else if("CHANGE".equals(list.get(21))){//转入代发
	                    order_refund_state=4;
	                }
	                orderRefundInfo.setOrderRefundState(order_refund_state);
	                i=orderRefundService.updateOrderRefundInfo(orderRefundInfo);
	                if(i==1){
	                    logger.info("------更新退款表成功------");
	                }  
	            }  
	        }	        
	    }catch(Exception e){
	        e.printStackTrace();
	        logger.info("------对账表中插入退款的对账单失败------",e.getMessage());
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    }

	}
	
	/**
     * 公用方法
     * @param list
     * @return
     */
	public OrderBill extracted(List<String> list) {
		OrderBill orderBill=new OrderBill();
		orderBill.setTradeTime(TimeUtil.getTimestamp(list.get(0)));//交易时间
		orderBill.setWxorder(list.get(5));//微信订单号
		orderBill.setBzorder(list.get(6));//商户订单号
		orderBill.setOpenId(list.get(7));//用户标识
		orderBill.setTradeType(list.get(8));//交易类型
		orderBill.setTradeStatus(list.get(9));//交易状态
		orderBill.setBank(list.get(10));//付款银行
		orderBill.setCurrency(list.get(11));//货币种类
		BigDecimal money=new BigDecimal(list.get(12));
		orderBill.setTotalMoney(money);//总金额
		money=new BigDecimal(list.get(13));
		orderBill.setRedpacketMoney(money);//代金券或立减优惠金额
		return orderBill;
	}

	
	private OrderBillService getService(){
		return SpringContextUtil.getBean(this.getClass());
	}
}
