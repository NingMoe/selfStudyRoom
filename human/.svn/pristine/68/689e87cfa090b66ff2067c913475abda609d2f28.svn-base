package com.human.order.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.activity.dao.BuyerInfoDao;
import com.human.activity.dao.OrderRefundInfoDao;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.OrderRefundInfo;
import com.human.order.dao.OrderBillDao;
import com.human.order.entity.OrderBill;
import com.human.order.entity.PrepayIdRequestHandler;
import com.human.order.service.OrderBillService;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.TenpayUtil;
import com.human.order.utils.WXUtil;
import com.human.order.utils.XMLUtil;
@Service
public class OrderBillServiceImpl implements OrderBillService {
	
	private final Logger logger = LogManager.getLogger(OrderBillServiceImpl.class);
	
	@Resource
	private OrderBillDao orderBillDao;
	
	@Resource
	private OrderRefundInfoDao orderRefundInfoDao;
	
	@Resource
    private BuyerInfoDao buyerInfoDao;
			
		
	@Override
	public int insert(OrderBill record) {		
		return orderBillDao.insert(record) ;
	}
		
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void downLoadWxOrderBill(HttpServletRequest request,HttpServletResponse response) {
		String bill_type="SUCCESS";//账单类型		
		try {
			String msgxml=getWxMsgxml(request, response, bill_type);			 
			logger.info("调用微信对账单接口获取支付成功订单返回数据------"+msgxml);
			if(msgxml.startsWith("<xml>")){
				Map msgMap = XMLUtil.parseXmlToList2(msgxml);
				String return_msg =(String) msgMap.get("return_msg");
				if("No Bill Exist".equals(return_msg)){
					logger.info("无支付成功订单");
				}else{
					logger.info("微信调用对账单接口获取支付成功订单失败！");
				}
			}else{
				//读取文本文件
				String[]t=msgxml.split("\n");
				List<String> list = new ArrayList<String>(Arrays.asList(t));
				list=list.subList(1, list.size()-2);
				logger.info("已支付成功订单数："+list.size());
				for(String s:list){
					String[] t1=s.replaceAll("`", "").split(",");
					insertSucessesOrderBill(t1);					
				}					
			}
			bill_type="REFUND";
			msgxml=getWxMsgxml(request, response, bill_type);
			logger.info("调用微信对账单接口获取退款订单返回数据------"+msgxml);
			if(msgxml.startsWith("<xml>")){
				Map msgMap = XMLUtil.parseXmlToList2(msgxml);
				String return_msg =(String) msgMap.get("return_msg");
				if("No Bill Exist".equals(return_msg)){
					logger.info("无退款订单");
				}else{
					logger.info("微信调用对账单接口获取退款订单失败！");
				}
			}else{
				//读取文本文件
				String[]t=msgxml.split("\n");
				List<String> list = new ArrayList<String>(Arrays.asList(t));
				list=list.subList(1, list.size()-2);
				logger.info("退款订单数："+list.size());
				for(String s:list){
					String[] t1=s.replaceAll("`", "").split(",");
					insertRefundOrderBill(t1);					
				}					
			}	
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}	
	 }
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String getWxMsgxml(HttpServletRequest request,HttpServletResponse response,String bill_type){
		PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
		//获取前一天日期字符串
		String bill_date=TenpayUtil.getPrevDate();
		String noncestr = WXUtil.getNonceStr();
		// 设置查询订单参数
		prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
		prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
		prepayReqHandler.setParameter("bill_date", bill_date); //对账单日期
		prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
		prepayReqHandler.setParameter("bill_type", bill_type);//账单类型
		String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
		prepayReqHandler.setParameter("sign", sign);
		String gateUrl = ConstantUtil2.DOWNLOAD_BILL_URL;
		prepayReqHandler.setGateUrl(gateUrl);				
		String msgxml = prepayReqHandler.sendAndGetXml();
		return msgxml;
		
	}

	/**
	 * 往对账表中插入支付成功的对账单
	 * @param t
	 */
	@Transactional(rollbackFor=Exception.class)
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
	        logger.info("对账表中插入支付成功的对账单开始！");
	        int i=orderBillDao.insert(orderBill);
	        if(i==1){
	            logger.info("对账表中插入支付成功的对账单成功结束！");
	        }  
	    }catch(Exception e){
	        e.printStackTrace();
	        logger.info("对账表中插入支付成功的对账单失败！",e.getMessage());
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    }		
	}

		
	/**
	 * 往对账表中插入退款的对账单
	 * @param t
	 */
	@Transactional(rollbackFor=Exception.class)
	public  void  insertRefundOrderBill(String[] t){
	    try{
	        List<String> list = new ArrayList<String>(Arrays.asList(t));
	        BigDecimal money;
	        OrderBill orderBill = extracted(list);      
	        orderBill.setRefundCreateTime(TenpayUtil.getTimestamp2(list.get(14)));//退款申请时间
	        orderBill.setRefundSucessesTime(TenpayUtil.getTimestamp2(list.get(15)));//退款成功时间
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
	        logger.info("对账表中插入退款的对账单开始！");
	        int i=orderBillDao.insert(orderBill);
	        if(i==1){
	            //更新退款表
	            OrderRefundInfo orderRefundInfo=orderRefundInfoDao.selectByOrderRefundNo(list.get(17));
	            if(orderRefundInfo!=null){
	                orderRefundInfo.setSucessesTime(TenpayUtil.getTimestamp2(list.get(15)));//退款成功时间
	                orderRefundInfo.setRefundChannel(list.get(20));//退款渠道
	                orderRefundInfo.setRefundCount(1);//退款笔数
	                Integer order_refund_state=1;
	                if("SUCCESS".equals(list.get(21))){//退款成功 
	                    order_refund_state=2;
	                    //更新买家交易信息
	                    BuyerInfo buyerInfo=buyerInfoDao.selectByOrderNo(orderRefundInfo.getOrderNo());
	                    buyerInfo.setBuyState(5);
	                    int j=buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
	                    if(j==1){
	                        logger.info("更新买家交易信息成功！");
	                    }  
	                }else if("FAIL".equals(list.get(21))){//退款失败
	                    order_refund_state=3;
	                }else if("PROCESSING".equals(list.get(21))){//退款中 
	                    order_refund_state=1;
	                }else if("CHANGE".equals(list.get(21))){//转入代发
	                    order_refund_state=4;
	                }
	                orderRefundInfo.setOrderRefundState(order_refund_state);
	                i=orderRefundInfoDao.updateByPrimaryKeySelective(orderRefundInfo);
	                if(i==1){
	                    logger.info("更新退款表成功！");
	                }  
	            } 
	            logger.info("对账表中插入退款的对账单成功结束！");
	        }	        
	    }catch(Exception e){
	        e.printStackTrace();
	        logger.info("对账表中插入退款的对账单失败！",e.getMessage());
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
		orderBill.setTradeTime(TenpayUtil.getTimestamp2(list.get(0)));//交易时间
		orderBill.setWxorder(list.get(5));//微信订单号
		orderBill.setBzorder(list.get(6));//商户订单号
		orderBill.setOpenid(list.get(7));//用户标识
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




 }


