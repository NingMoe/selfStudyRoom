package com.human.activity.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.activity.dao.BuyerInfoDao;
import com.human.activity.dao.OrderInfoDao;
import com.human.activity.dao.OrderRefundInfoDao;
import com.human.activity.dao.ProductDao;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.OrderRefundInfo;
import com.human.activity.entity.Product;
import com.human.activity.service.OrderRefundInfoService;
import com.human.order.entity.PrepayIdRequestHandler;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.WXUtil;
import com.human.order.utils.XMLUtil;


@Service
public class OrderRefundInfoServiceImpl implements OrderRefundInfoService {
    
    private final Logger logger = LogManager.getLogger(OrderRefundInfoServiceImpl.class);
    
    @Resource
    private OrderInfoDao orderInfoDao;
    
    @Resource
    private OrderRefundInfoDao orderRefundInfoDao;
    
    @Resource
    private BuyerInfoDao buyerInfoDao;
    
    @Resource
    private ProductDao productDao;
    

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void checkValidOrderRefundInfo(HttpServletRequest request, HttpServletResponse response) {      
        logger.info("更新退款单退款进度信息定时任务开始！");
        //查出审核通过并且处于退款中的退款订单
        List<OrderRefundInfo>list=orderRefundInfoDao.getOrderRefundList();
        if(list==null||list.size()==0){
            logger.info("更新退款单退款进度信息定时任务结束！");
            return;
        }else{
            for(OrderRefundInfo orderRefundInfo:list){
                updateRefundDetail(orderRefundInfo,request,response);
            }
        }
        logger.info("更新退款单退款进度信息定时任务结束！");
    }
    
    /**
     * 更新退款单信息
     * @param orderRefundInfo
     */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void updateRefundDetail(OrderRefundInfo orderRefundInfo,HttpServletRequest request,HttpServletResponse response){
        PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
        String noncestr = WXUtil.getNonceStr();
        // 设置查询退款订单参数
        prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
        prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
        prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
        prepayReqHandler.setParameter("out_refund_no", orderRefundInfo.getOrderRefundNo());// 商户退款单号
        String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
        prepayReqHandler.setParameter("sign", sign);
        String gateUrl = ConstantUtil2.CHECK_REFUND_URL;
        prepayReqHandler.setGateUrl(gateUrl);
        //获取退款查询接口微信返回的XML
        try {       
            String msgxml = prepayReqHandler.sendAndGetXml();
            System.out.println("微信退款查询接口返回的xml数据------"+msgxml);
            if(!"".equals(msgxml)){
                Map msgMap = XMLUtil.parseXmlToList2(msgxml);
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
                        // 更新买家交易信息
                        BuyerInfo  buyerInfo=buyerInfoDao.selectByOrderRefundId(orderRefundInfo.getId());
                        if (buyerInfo != null) {
                            buyerInfo.setBuyState(5);
                            int z = buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
                            if (z != 1) {
                                logger.error("调用微信退款查询接口更新买家交易信息失败!");
                            }else{
                                // 更新商品已卖数量
                                Product product=productDao.selectByPrimaryKey(buyerInfo.getProductId());
                                product.setSaleTotal(product.getSaleTotal()-buyerInfo.getBuyTotal());
                                z =productDao.updateByPrimaryKeySelective(product);
                                if (z != 1) {
                                    logger.error("调用微信退款查询接口更新商品已卖数量失败!");
                                }
                            }
                        }
                        orderRefundInfo.setRefundRecvAccout(refund_recv_accout);
                    }else if("FAIL".equals(refund_status)){//退款失败
                        order_refund_state=3;
                    }else if("PROCESSING".equals(refund_status)){//退款中 
                        order_refund_state=1;
                    }else if("CHANGE".equals(refund_status)){//转入代发
                        order_refund_state=4;
                    }
                    orderRefundInfo.setOrderRefundState(order_refund_state);
                    orderRefundInfoDao.updateByPrimaryKeySelective(orderRefundInfo);                                                            
              }         
            }else{
                logger.info("调用微信退款查询接口返回XML数据失败！");    
            }   
        }catch(Exception e){
            logger.info(e.getMessage());
            logger.info("调用微信退款查询接口更新退款单信息失败！");    
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }           
    }

    @Override
    public OrderRefundInfo selectByOrderNo(String orderNo) {
        return orderRefundInfoDao.selectByOrderNo(orderNo);
    }
    
    
    
    
    
    
    
    
}
