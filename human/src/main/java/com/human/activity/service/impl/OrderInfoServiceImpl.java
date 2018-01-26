package com.human.activity.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import com.human.activity.dao.ActivityInfoDao;
import com.human.activity.dao.BuyerInfoDao;
import com.human.activity.dao.OrderInfoDao;
import com.human.activity.dao.OrderRefundInfoDao;
import com.human.activity.dao.ProductDao;
import com.human.activity.entity.ActivityInfo;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.FrontOrderInfoParams;
import com.human.activity.entity.OrderInfo;
import com.human.activity.entity.OrderRefundInfo;
import com.human.activity.entity.PayInfoDto;
import com.human.activity.entity.Product;
import com.human.activity.service.OrderInfoService;
import com.human.basic.dao.SmsTempDao;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.order.entity.ClientRequestHandler;
import com.human.order.entity.PrepayIdRequestHandler;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.TenpayUtil;
import com.human.order.utils.WXUtil;
import com.human.order.utils.XMLUtil;
import com.human.utils.Common;



@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    
    private final Logger logger = LogManager.getLogger(OrderInfoServiceImpl.class);
          
    @Resource
    private OrderInfoDao orderInfoDao;
    
    @Resource
    private BuyerInfoDao buyerInfoDao;
    
    @Resource
    private OrderRefundInfoDao orderRefundInfoDao;
    
    @Resource
    private ActivityInfoDao aIDao;
    
    @Resource
    private SmsTempDao smsTempDao;
    
    @Resource
    private ProductDao productDao;
    
    @Resource
    private SmsTempService smsService;
    

    @Override
    public Map<String, Object> checkOrderInfo(FrontOrderInfoParams fops, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String telephone=fops.getTelephone();
            Long activityId=fops.getActivityId();
            Long productId=fops.getProductId();
            String code=fops.getCode();
            Long buySum =fops.getBuySum();
            ActivityInfo activityInfo=aIDao.selectByPrimaryKey(activityId);
            //验证活动有效期
            Date startTime=activityInfo.getStartTime();
            Date endTime=activityInfo.getEndTime();
            //当前时间
            Date currDate=new Date();            
            if(currDate.before(startTime)){
                map.put("flag", false);
                map.put("message", "活动还未开始!");
                return map;
            }
            if(endTime.before(currDate)){
                map.put("flag", false);
                map.put("message", "活动已经结束!");
                return map;
            }
            // 验证用户账号手机格式
            if(!Common.isMobile(telephone)){
                map.put("flag", false);
                map.put("message", "手机号格式不正确!");
                return map;
            }
            //验证手机验证码是否正确
            String code1=getMsg(telephone);
            if(!code1.equals(code)){
                map.put("flag", false);
                map.put("message", "验证码输入错误!");
                return map;
            }
            //判断该活动是否配置单个手机号只能购买一次          
            String tollLimit =activityInfo.getTollLimit();
            if("1".equals(tollLimit)){
                //判断用户是否已经购买过该商品
                BuyerInfo  buyerInfo=new BuyerInfo();
                buyerInfo.setActivityId(activityId);
                buyerInfo.setTelephone(telephone);
                int i=buyerInfoDao.selectByParams(buyerInfo);
                if(i!=0){
                    map.put("flag", false);
                    map.put("message", "单个手机号只能购买一次,你已经购买过一次!");
                    return map; 
                }                
            }
            //判断购买数量是否已经超过库存
            Product product= productDao.selectByPrimaryKey(productId);
            Long saleTotal=product.getSaleTotal();
            Long total=product.getTotal();
            if(total!=0){//商品数量有限制
                if(buySum+saleTotal>total){
                    map.put("flag", false);
                    map.put("message", "购买数量已经超过库存，请减少购买数量!");
                    return map; 
                }
            }
            map.put("flag", true);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "校验信息失败,请稍后重试!");            
        }
        return map;
    }

    
    /**
     * 获取手机验证码
     * @param phone
     * @return
     */
    public String getMsg(String phone) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE, -30);
        Date dt1=rightNow.getTime();
        String time = sdf.format(dt1);
        System.out.println(time);         
        String code="";
        String sendComment = smsTempDao.getMsg(phone,time);
        if(StringUtils.isNotEmpty(sendComment)){
            int start=sendComment.indexOf("(");
            int end=sendComment.indexOf(")");
            code=sendComment.substring(start+1, end);
        }
        return code;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ModelAndView insertOrderInfo(FrontOrderInfoParams fops, HttpServletRequest request) {    
        Long activityId=fops.getActivityId();
        ModelAndView mav = new ModelAndView("/activity/frontWeixin/orderInfo"+activityId);
        try{
            String telephone=fops.getTelephone();            
            Long productId=fops.getProductId();
            Long buySum =fops.getBuySum();
            //判断购买数量是否已经超过库存
            Product product= productDao.selectByPrimaryKey(productId);
            Long saleTotal=product.getSaleTotal();
            Long total=product.getTotal();
            if(total!=0){//商品数量有限制
                if(buySum+saleTotal>total){
                    mav.addObject("flag", false);
                    mav.addObject("message", "购买数量已经超过库存，请减少购买数量!");
                    mav.addObject("activityId", activityId);
                    return mav; 
                }
            }
            BuyerInfo buyerInfo=new BuyerInfo();
            buyerInfo.setName(fops.getName());
            buyerInfo.setTelephone(telephone);
            buyerInfo.setActivityId(activityId);
            buyerInfo.setProductId(productId);
            buyerInfo.setBuyTotal(buySum);
            //生成订单号
            String orderNo=TenpayUtil.getOrderNo();
            buyerInfo.setOrderNo(orderNo);
            buyerInfo.setText1(fops.getText1());
            buyerInfo.setText2(fops.getText2());
            buyerInfo.setText3(fops.getText3());
            buyerInfo.setText4(fops.getText4());
            buyerInfo.setBuyState(0);//待支付
            // 生成业务订单信息
            OrderInfo orderInfo = new OrderInfo();                 
            ActivityInfo activityInfo =aIDao.selectByPrimaryKey(activityId);
            String orderName = activityInfo.getActivityName()+"-"+product.getName()+ "费用";
            orderInfo.setOrderName(orderName);// 订单名称
            BigDecimal orderCost=product.getPrice().multiply(new BigDecimal(buySum)).setScale(1, BigDecimal.ROUND_HALF_UP);
            orderInfo.setOrderCost(orderCost);// 订单金额
            orderInfo.setRealCost(orderCost);// 实际金额
            orderInfo.setOrderState(0);// 待支付
            orderInfo.setOrderNo(orderNo);
            // 保存业务订单信息
            int i=orderInfoDao.insertSelective(orderInfo);
            if(i==1){
                buyerInfoDao.insertSelective(buyerInfo);
                addParam(orderInfo,activityId, mav);
            }else{
                mav.addObject("flag", false);
                mav.addObject("message", "提交订单失败！");
                mav.addObject("activityId", activityId);
            }  
        }catch(Exception e){
            e.printStackTrace();
            mav.addObject("flag", false);
            mav.addObject("message", "提交订单失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }        
        return mav;
    }
    
    
    
    /**
     * 向页面传参
     */
    @SuppressWarnings("deprecation")
    public void addParam(OrderInfo orderInfo,long activityId,ModelAndView mav){     
        // 拼接重定向的URL
        String redirect_uri = ConstantUtil2.REDIRECT_URI + "?orderInfoId=" + orderInfo.getId();
        redirect_uri = URLEncoder.encode(redirect_uri);
        mav.addObject("redirect_uri", redirect_uri);
        mav.addObject("appid", ConstantUtil2.APP_ID);
        mav.addObject("orderInfo", orderInfo);
        mav.addObject("index", "1");
        mav.addObject("activityId", activityId);
        mav.addObject("flag", true);    
    }
    
    

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> refundOrderInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String buyInfoIds = ServletRequestUtils.getStringParameter(request, "buyInfoIds"); 
            String[] ids = buyInfoIds.split(",");
            System.out.println("买家退款Id==========="+Arrays.toString(ids));
            //查询是否封账
            String buyInfoId=ids[0];
            String accountValid=aIDao.selectByBuyInfoId(Long.valueOf(buyInfoId));
            if("2".equals(accountValid)){
                map.put("flag", false);
                map.put("message", "该活动已经封账，不允许退款!");
                return map;
            }
            String refundMoney=ServletRequestUtils.getStringParameter(request, "refundMoney"); 
            BigDecimal refundOrderMoney=new BigDecimal(refundMoney);
            String userName = Common.getAuthenticatedUsername();
            OrderInfo orderInfo=null;
            for (String id : ids){
                //查询买家交易信息
                BuyerInfo buyerInfo=buyerInfoDao.selectByPrimaryKey(Long.valueOf(id));
                String orderNo=buyerInfo.getOrderNo();
                //查询订单
                orderInfo=orderInfoDao.getOrderInfoByOrderNo(orderNo);
                //判断退款金额是否超过订单金额
                if(refundOrderMoney.compareTo(orderInfo.getOrderCost())==1){
                    map.put("flag", false);
                    map.put("message", "买家退款Id="+id+"的退款金额大于订单金额,请重新输入退款金额!");
                    return map;
                }
                //调用微信退款接口发起退款
                PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
                String noncestr = WXUtil.getNonceStr();
                String out_refund_no =TenpayUtil.getOrderNo() ;
                String total_fee=Integer.toString(orderInfo.getOrderCost().multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());
                String refund_fee=Integer.toString(refundOrderMoney.multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());
                System.out.println("订单金额===="+total_fee+",退款金额====="+refund_fee);
                // 设置申请退款订单参数
                prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
                prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
                prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
                prepayReqHandler.setParameter("transaction_id", orderInfo.getTransactionId()); // 微信订单号
                prepayReqHandler.setParameter("out_refund_no", out_refund_no);// 商户退款单号
                prepayReqHandler.setParameter("total_fee", total_fee);// 订单金额
                prepayReqHandler.setParameter("refund_fee", refund_fee);// 退款金额
                String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
                prepayReqHandler.setParameter("sign", sign);
                String gateUrl = ConstantUtil2.REFUND_URL;
                prepayReqHandler.setGateUrl(gateUrl);
                // 发送申请退款
                Map msgMap = prepayReqHandler.sendRefundAndGetXml();
                System.out.println("微信退款接口返回===="+msgMap==null?"为空":"不为空");
                String return_code = (String) msgMap.get("return_code");
                String result_code = msgMap.get("result_code") + "";
                System.out.println("return_code===="+return_code+",result_code====="+result_code);
                if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){                   
                    String refund_id = (String) msgMap.get("refund_id");
                    //生成退款订单信息
                    OrderRefundInfo orderRefundInfo=new OrderRefundInfo();
                    orderRefundInfo.setOrderNo(orderInfo.getOrderNo());//商户订单号
                    orderRefundInfo.setTransactionId(orderInfo.getTransactionId());//微信支付订单号
                    orderRefundInfo.setOrderRefundNo(out_refund_no);//商户退款单号
                    orderRefundInfo.setOrderCost(orderInfo.getOrderCost());//订单金额
                    orderRefundInfo.setRefundCost(refundOrderMoney);//申请退款金额
                    orderRefundInfo.setRefundUser(userName);//申请退款人
                    orderRefundInfo.setCreateTime(TenpayUtil.getTimestamp(TenpayUtil.getCurrTime()));//申请退款时间
                    orderRefundInfo.setOrderRefundState(1);//退款中
                    orderRefundInfo.setRefundId(refund_id);// 微信退款单号
                    int z=orderRefundInfoDao.insertSelective(orderRefundInfo);
                    if (z == 1) {
                        buyerInfo.setBuyState(4);
                        buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
                        map.put("flag", true);
                        map.put("message", "退款申请微信已接收成功,请耐心等候!");
                    }else {
                        map.put("flag", false);
                        map.put("message", "退款申请微信已接收成功但插入退款表记录失败!");
                    }
                }else if ("SUCCESS".equals(return_code) && "FAIL".equals(result_code)){
                    Object object=msgMap.get("err_code");
                    if(object!=null){
                        String err_code=(String) object;
                        System.out.println("err_code===="+err_code);
                        map.put("err_code",err_code);
                    }   
                    object=msgMap.get("err_code_des");
                    if(object!=null){
                        String err_code_des=(String) object;
                        System.out.println("err_code_des===="+err_code_des);
                        map.put("err_code_des",err_code_des);
                    }   
                    map.put("flag", false);
                    map.put("message", "提交退款申请微信接受业务失败!");
                }else {
                    map.put("flag", false);
                    map.put("message", "调用微信退款申请接口失败!");
                }
            }                    
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "发起退款异常!");           
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public String weixinNotify(HttpServletRequest request, HttpServletResponse response) {
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
            String msgxml = new String(out.toByteArray(), "utf-8");// xml数据
            out.close();
            in.close();
            System.out.println("微信支付通知回调返回的xml数据------" + msgxml);
            if (StringUtils.isNotEmpty(msgxml)){
                Map msgMap = XMLUtil.parseXmlToList2(msgxml);
                String return_code = (String) msgMap.get("return_code");
                String result_code = (String) msgMap.get("result_code");
                if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)) {
                    String bank_type = (String) msgMap.get("bank_type");// 付款银行
                    String transaction_id = (String) msgMap.get("transaction_id");// 微信支付订单号
                    String time_end = (String) msgMap.get("time_end");// 支付完成时间
                    String trade_type = (String) msgMap.get("trade_type");// 交易类型
                    String openid = (String) msgMap.get("openid");// 用户标识
                    String orderNo = (String) msgMap.get("out_trade_no");// 商户订单号
                    // 通过订单号查询业务订单
                    OrderInfo orderInfo = orderInfoDao.getOrderInfoByOrderNo(orderNo);
                    int orderState = orderInfo.getOrderState();
                    if (orderState == 0) {// 未处理业务逻辑
                        // 更新业务订单信息
                        orderInfo.setTransactionId(transaction_id);
                        orderInfo.setOrderState(1);
                        orderInfo.setPayTime(TenpayUtil.getTimestamp(time_end));
                        orderInfo.setBankType(bank_type);
                        orderInfo.setResultCode(result_code);
                        orderInfo.setOpenid(openid);
                        orderInfo.setTradeType(trade_type);
                        int z = orderInfoDao.updateByPrimaryKeySelective(orderInfo);
                        if (z != 1) {
                            logger.error("微信支付结果通知回调更新业务订单信息失败!");
                        }
                        // 更新买家交易信息
                        BuyerInfo  buyerInfo=buyerInfoDao.selectByOrderNo(orderNo);
                        if (buyerInfo != null) {
                            buyerInfo.setBuyState(1);
                            z = buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
                            if (z != 1) {
                                logger.error("微信支付结果通知回调更新买家交易信息失败!");
                            }
                        }
                        // 更新商品已卖数量
                        Product product=productDao.selectByPrimaryKey(buyerInfo.getProductId());
                        product.setSaleTotal(product.getSaleTotal()+buyerInfo.getBuyTotal());
                        z =productDao.updateByPrimaryKeySelective(product);
                        if (z != 1) {
                            logger.error("微信支付结果通知回调更新商品已卖数量失败!");
                        }
                        sb.append("<return_code><![CDATA[SUCCESS]]></return_code>" + "\r\n");
                        sb.append("<return_msg><![CDATA[OK]]></return_msg>" + "\r\n");
                    } else if (orderState == 1) {// 已处理业务逻辑直接返回结果成功
                        sb.append("<return_code><![CDATA[SUCCESS]]></return_code>" + "\r\n");
                        sb.append("<return_msg><![CDATA[OK]]></return_msg>" + "\r\n");
                    }
                } else if ("SUCCESS".equals(return_code) && "FAIL".equals(result_code)) {
                    sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
                    logger.error("微信支付结果通知回调业务结果失败!");
                } else if ("FAIL".equals(return_code)) {
                    sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
                    logger.error("微信支付结果通知回调通信失败!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            sb.append("<return_code><![CDATA[FAIL]]></return_code>" + "\r\n");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        params += sb.toString();
        params += "</xml>";
        return params;
    }

        
    @Override
    public OrderInfo selectByPrimaryKey(Long id) {
        return orderInfoDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(OrderInfo record) {     
        return orderInfoDao.updateByPrimaryKeySelective(record);
    }

    
    @Override
    public Map<String, Object> getWxPayParamById(HttpServletRequest request, HttpServletResponse response, long orderInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            System.out.println("orderInfoId==="+orderInfoId);
            OrderInfo orderInfo=orderInfoDao.selectByPrimaryKey(orderInfoId);
            System.out.println("prepay_id==="+orderInfo.getPrepayId());
            if(!"".equals(orderInfo.getPrepayId())&& orderInfo.getPrepayId()!=null){
                //参数                
                String timeStamp=WXUtil.getTimeStamp();
                String packageStr="prepay_id="+orderInfo.getPrepayId();
                String noncestr2 = WXUtil.getNonceStr();
                ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);//返回客户端支付参数的请求类
                clientHandler.setParameter("appId", ConstantUtil2.APP_ID);//公众账号ID
                clientHandler.setParameter("timeStamp",timeStamp );//时间戳
                clientHandler.setParameter("nonceStr", noncestr2);//随机字符串
                clientHandler.setParameter("package",packageStr);//订单详情扩展字符串
                clientHandler.setParameter("signType", "MD5"); //签名算法   
                String paySign = clientHandler.createMd5Sign(); //生成获取支付签名                  
                map.put("appId", ConstantUtil2.APP_ID);
                map.put("timeStamp",timeStamp);
                map.put("nonceStr",noncestr2);
                map.put("packageStr",packageStr);
                map.put("paySign",paySign);
                map.put("flag",true);
            }else{
                map.put("flag",false);
                map.put("flagIndex",2);
                map.put("message", "获取微信预支付prepay_id失败！");
            }               
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag",false);
            map.put("flagIndex",2);
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> queryOrderInfoById(HttpServletRequest request, HttpServletResponse response, long orderInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(orderInfoId);
            if (orderInfo.getOrderState() == 0) {
                String msgxml = TenpayUtil.getPaySuccessXml(request, response, orderInfo.getOrderNo());
                if (!"".equals(msgxml)){
                    Map msgMap = XMLUtil.parseXmlToList2(msgxml);
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
                            orderInfo.setPayTime(TenpayUtil.getTimestamp(time_end));
                            orderInfo.setBankType(bank_type);
                            orderInfo.setResultCode(result_code);
                            orderInfo.setResultDesc(trade_state_desc);
                            orderInfo.setOpenid(openid);
                            int z = orderInfoDao.updateByPrimaryKeySelective(orderInfo);
                            if (z != 1) {
                                map.put("flag", false);
                                map.put("message", "更新业务订单信息失败!");
                                return map;
                            }
                            // 更新买家交易信息
                            BuyerInfo  buyerInfo=buyerInfoDao.selectByOrderNo(orderInfo.getOrderNo());
                            if (buyerInfo != null){
                                buyerInfo.setBuyState(1);
                                z = buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
                                if (z != 1) {
                                    map.put("flag", false);
                                    map.put("message", "更新买家交易信息失败!");
                                    return map;
                                }
                            }else{
                                throw new Exception();
                            } 
                            // 更新商品已卖数量
                            Product product=productDao.selectByPrimaryKey(buyerInfo.getProductId());
                            product.setSaleTotal(product.getSaleTotal()+buyerInfo.getBuyTotal());
                            z =productDao.updateByPrimaryKeySelective(product);
                            if (z != 1) {
                                map.put("flag", false);
                                map.put("message", "更新商品已卖数量失败!");
                                return map;
                            }
                        }else if ("NOTPAY".equals(trade_state)) {// 未支付
                            map.put("flag", false);
                            map.put("flagIndex", 1);
                            map.put("message", trade_state_desc);
                            return map;
                        }else if ("PAYERROR".equals(trade_state)) {// 支付失败
                            map.put("flag", false);
                            map.put("flagIndex", 2);
                            map.put("message", trade_state_desc);
                            return map;
                        }
                    }else if ("FAIL".equals(return_code)) {
                        map.put("flag", false);
                        map.put("message", "返回状态码失败!");
                        return map;
                    }
                }else{
                    map.put("flag", false);
                    map.put("message", "调用微信查询订单接口失败!");
                    return map;
                }
            }
            map.put("flag", true);
            map.put("message", "订单支付成功,您可以继续购买!");
            //支付成功后异步发送短信
            sendPaySuccessMessage(orderInfoId);
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "查询订单是否支付成功失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> closeOrderInfoById(HttpServletRequest request, HttpServletResponse response, long orderInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("orderInfoId==="+orderInfoId);
        boolean hasException=false;
        try{
            OrderInfo orderInfo=orderInfoDao.selectByPrimaryKey(orderInfoId);
            String msgxml = TenpayUtil.getPayFailXml(request, response,orderInfo.getOrderNo());
            if (!"".equals(msgxml)){
                Map msgMap = XMLUtil.parseXmlToList2(msgxml);
                String return_code =(String) msgMap.get("return_code");
                String result_code =msgMap.get("result_code")+"";
                if("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
                    //更新订单信息
                    orderInfo.setOrderState(2);//支付失败
                    int i=orderInfoDao.updateByPrimaryKeySelective(orderInfo);
                    if(i==1){
                        // 更新买家交易信息
                        BuyerInfo  buyerInfo=buyerInfoDao.selectByOrderNo(orderInfo.getOrderNo());
                        if (buyerInfo != null){
                            buyerInfo.setBuyState(2);//支付失败
                            i = buyerInfoDao.updateByPrimaryKeySelective(buyerInfo);
                            if (i != 1) {
                                map.put("flag", false);
                                map.put("message", "更新买家交易信息失败!");
                                return map;
                            }
                            map.put("flag", true);
                            map.put("message", "关闭原订单成功,请重新购买支付!");
                        }else{
                            throw new Exception();
                        } 
                    }else{
                        map.put("flag", false);
                        map.put("message", "更新订单信息失败!");
                        throw new Exception();
                    }   
                }else{
                    map.put("flag", false);
                    map.put("message", "关闭原订单失败!");
                }                                       
            }
        }catch(Exception e){
            e.printStackTrace();
            hasException=true; 
            map.put("flag", false);
            map.put("message", "调用微信关闭订单接口关闭订单失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }finally{
            if(hasException){
                map.put("flag", false);
                map.put("message", "关闭原订单失败!");
            }
        } 
      return map;
    }

    @Override
    public OrderInfo getOrderInfoByOrderNo(String orderNo) {       
        return orderInfoDao.getOrderInfoByOrderNo(orderNo);
    }

    
    /**
     * 支付成功发送短信
     * @param orderInfoId
     */
    @Async
    public void sendPaySuccessMessage(long id){        
        try{
            PayInfoDto payInfoDto=buyerInfoDao.sendPaySuccessMessage(id);
            if(payInfoDto!=null){
                SmsRecord smsRecord = new SmsRecord();
                smsRecord.setSendTel(payInfoDto.getTelephone());
                smsRecord.setCompany("25");
                smsRecord.setSmsType("2"); 
                String message=payInfoDto.getName()+"同学，您成功购买了（"+payInfoDto.getActivityName()+"）的（"+payInfoDto.getProductName()
                               +"），购买数量（"+payInfoDto.getBuyTotal()+"），支付金额（"+payInfoDto.getRealCost()+"）元.关注公众号新东方合肥学校微服务（公众号hfxdfkf）可了解更多活动详情.";
                smsRecord.setSendComment(message);
                smsService.sendMessage(smsRecord);                
            }
        }catch(Exception e){
            e.printStackTrace();
            logger.error("支付成功发送短信失败!", e.getMessage());
        }
    }


    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> payOrderInfoByH5(FrontOrderInfoParams fops, HttpServletRequest request,HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
           //第一步：校验订单有效性
           map=checkOrderInfo(fops,request);
           if((boolean) map.get("flag")){
               //第二步：保存订单
               map=saveOrderInfo(fops,request);
               if((boolean) map.get("flag")){
                   //第三步：调用微信H5支付接口下单
                   String msgxml=TenpayUtil.getUnifiedorderXmlByH5(request,response,(OrderInfo)map.get("orderInfo"));
                   if(StringUtils.isNotEmpty(msgxml)){
                       Map msgMap = XMLUtil.parseXmlToList2(msgxml);
                       String return_code = (String) msgMap.get("return_code");
                       String result_code = msgMap.get("result_code") + "";
                       if ("SUCCESS".equals(return_code) && "SUCCESS".equals(result_code)){
                         String mweb_url=(String) msgMap.get("mweb_url");
                         System.out.println("mweb_url===="+mweb_url);
                         map.put("flag",true);
                         map.put("mweb_url",mweb_url);  
                       }else if("SUCCESS".equals(return_code) && "FAIL".equals(result_code)) {
                           String err_code=msgMap.get("err_code")+"";
                           String err_code_des=msgMap.get("err_code_des")+"";
                           System.out.println("err_code===="+err_code+",err_code_des==="+err_code_des);
                           map.put("flag", false);
                           map.put("message", "调用微信H5支付接口下单业务失败!"+err_code_des);
                           return map;
                       }else{
                           map.put("flag", false);
                           map.put("message", "调用微信H5支付接口通信失败!");
                           return map;
                       }
                   }else{
                       map.put("flag", false);
                       map.put("message", "调用微信H5支付接口下单失败!");
                       return map;
                   }                   
               }
           }
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "调用浏览器微信H5支付接口下单失败!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
    /**
     * 浏览器H5支付保存订单
     * @param fops
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> saveOrderInfo(FrontOrderInfoParams fops, HttpServletRequest request) { 
        Map<String, Object> map = new HashMap<String, Object>();
        Long activityId=fops.getActivityId();     
        try{
            String telephone=fops.getTelephone();            
            Long productId=fops.getProductId();
            Long buySum =fops.getBuySum();
            //判断购买数量是否已经超过库存
            Product product= productDao.selectByPrimaryKey(productId);
            Long saleTotal=product.getSaleTotal();
            Long total=product.getTotal();
            if(total!=0){//商品数量有限制
                if(buySum+saleTotal>total){
                    map.put("flag", false);
                    map.put("message", "购买数量已经超过库存，请减少购买数量!");
                    return map; 
                }
            }
            BuyerInfo buyerInfo=new BuyerInfo();
            buyerInfo.setName(fops.getName());
            buyerInfo.setTelephone(telephone);
            buyerInfo.setActivityId(activityId);
            buyerInfo.setProductId(productId);
            buyerInfo.setBuyTotal(buySum);
            //生成订单号
            String orderNo=TenpayUtil.getOrderNo();
            buyerInfo.setOrderNo(orderNo);
            buyerInfo.setText1(fops.getText1());
            buyerInfo.setText2(fops.getText2());
            buyerInfo.setText3(fops.getText3());
            buyerInfo.setText4(fops.getText4());
            buyerInfo.setBuyState(0);//待支付
            // 生成业务订单信息
            OrderInfo orderInfo = new OrderInfo();                 
            ActivityInfo activityInfo =aIDao.selectByPrimaryKey(activityId);
            String orderName = activityInfo.getActivityName()+"-"+product.getName()+ "费用";
            orderInfo.setOrderName(orderName);// 订单名称
            BigDecimal orderCost=product.getPrice().multiply(new BigDecimal(buySum)).setScale(1, BigDecimal.ROUND_HALF_UP);
            orderInfo.setOrderCost(orderCost);// 订单金额
            orderInfo.setRealCost(orderCost);// 实际金额
            orderInfo.setOrderState(0);// 待支付
            orderInfo.setOrderNo(orderNo);
            // 保存业务订单信息
            int i=orderInfoDao.insertSelective(orderInfo);
            if(i==1){
                i=buyerInfoDao.insertSelective(buyerInfo);   
                if(i==1){
                    map.put("flag", true);
                    map.put("orderInfo", orderInfo);
                }else{
                    map.put("flag", false);
                    map.put("message", "向用户订单关联表插入数据失败！");               
                } 
            }else{
                map.put("flag", false);
                map.put("message", "向订单表插入订单失败！");               
            }  
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "保存订单失败！");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }        
        return map;
    }
    
    
    
}
