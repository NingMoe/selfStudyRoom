package cn.xdf.pay.util.WechatPayUtil;

import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.xdf.pay.constant.WechatPay;
import cn.xdf.pay.domain.OrderInfo;
import cn.xdf.pay.domain.PayMerchant;
import cn.xdf.pay.util.CommonUtil;
import cn.xdf.pay.util.TimeUtil;


/**
 * 微信接口工具类
 * @author liuwei63
 *
 */
public class WechatPayInterfaceUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WechatPayInterfaceUtil.class);
	
	/**
	 * 微信支付统一下单
	 * @param request
	 * @param response
	 * @param orderInfo
	 * @return
	 */
    public static String getUnifiedorderXml(HttpServletRequest request, HttpServletResponse response,PayMerchant payMerchant,OrderInfo orderInfo) {
        String msgxml = "";
        try {
        	WechatPayRequestHandler prepayReqHandler = new WechatPayRequestHandler(request, response);
        	prepayReqHandler.setKey(payMerchant.getMchSecret());
            String noncestr = CommonUtil.getNonceStr();
            String tradeType=orderInfo.getTradeType();
            String body="新东方优能中学教育-" + orderInfo.getOrderName();
            if(WechatPay.JSAPI.equals(tradeType)){// 公众号支付或小程序支付         	
            	prepayReqHandler.setParameter("openid",orderInfo.getOpenId());// 微信用户openId
            }else if(WechatPay.NATIVE.equals(tradeType)){ // PC扫码支付         	
            	prepayReqHandler.setParameter("product_id",orderInfo.getProductId());//商品ID
            }else if(WechatPay.MWEB.equals(tradeType)){ // H5支付          	
            	String sceneInfo="{\"h5_info\":{\"type\":\"Wap\",\"wap_url\": \""+orderInfo.getSceneInfo()+"\" ,\"wap_name\": \"合肥新东方H5支付\"}}";
            	prepayReqHandler.setParameter("scene_info",sceneInfo);//场景信息
            }            
            String total_fee = Integer.toString(orderInfo.getOrderCost().multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());
            String spbill_create_ip = CommonUtil.getIpAddress(request);
            String time_start = TimeUtil.date2String(orderInfo.getTimeStart(), "yyyyMMddHHmmss");// 订单生成时间
            String out_trade_no = orderInfo.getOrderNo();
            prepayReqHandler.setParameter("appid", payMerchant.getAppId());// 公众账号ID
            prepayReqHandler.setParameter("mch_id",payMerchant.getMchId());// 商户号
            prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
            prepayReqHandler.setParameter("body", body);// 商品描述
            prepayReqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
            prepayReqHandler.setParameter("total_fee", total_fee);// 总金额
            prepayReqHandler.setParameter("spbill_create_ip", spbill_create_ip);// 终端IP
            prepayReqHandler.setParameter("time_start", time_start);// 订单生成时间
            prepayReqHandler.setParameter("notify_url", WechatPay.NOTIFY_URL);// 通知地址
            prepayReqHandler.setParameter("trade_type",tradeType);// 交易类型
            String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
            prepayReqHandler.setParameter("sign", sign);
            String gateUrl = WechatPay.GATEURL;
            prepayReqHandler.setGateUrl(gateUrl);
            msgxml = prepayReqHandler.sendAndGetXml();
            logger.info("-----微信支付统一下单返回的xml数据------" + msgxml);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("------调用微信支付统一下单接口异常------", e.getMessage());
        }
        return msgxml;
    }
    

    /**
     * 微信查询订单
     * @param request
     * @param response
     * @param payMerchant
     * @param orderNo
     * @return
     */
    public static String getQueryOrderXml(HttpServletRequest request, HttpServletResponse response,PayMerchant payMerchant,String orderNo) {
		String msgxml = "";
		try {
			WechatPayRequestHandler prepayReqHandler = new WechatPayRequestHandler(request, response);
			prepayReqHandler.setKey(payMerchant.getMchSecret());
			String noncestr = CommonUtil.getNonceStr();
			String out_trade_no = orderNo;
			// 设置查询订单参数
			prepayReqHandler.setParameter("appid", payMerchant.getAppId());// 公众账号ID
			prepayReqHandler.setParameter("mch_id", payMerchant.getMchId());// 商户号
			prepayReqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
			prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
			String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = WechatPay.CHECK_ORDER_URL;
			prepayReqHandler.setGateUrl(gateUrl);
			// 获取查询接口微信返回的XML
			msgxml = prepayReqHandler.sendAndGetXml();
			logger.info("-----微信查询订单接口返回的xml数据------" + msgxml);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("------调用微信查询订单接口异常------", e.getMessage());
		}
		return msgxml;
    }
    
        
    /**
     * 微信关闭订单
     * @param request
     * @param response
     * @param payMerchant
     * @param orderNo
     * @return
     */
	public static String getCloseOrderXml(HttpServletRequest request, HttpServletResponse response,PayMerchant payMerchant,String orderNo) {
		String msgxml = "";
		try {
			WechatPayRequestHandler prepayReqHandler = new WechatPayRequestHandler(request, response);
			prepayReqHandler.setKey(payMerchant.getMchSecret());
			String noncestr=CommonUtil.getNonceStr();
			String out_trade_no=orderNo;
			// 设置关闭订单参数
			prepayReqHandler.setParameter("appid", payMerchant.getAppId());// 公众账号ID
			prepayReqHandler.setParameter("mch_id", payMerchant.getMchId());// 商户号
			prepayReqHandler.setParameter("out_trade_no", out_trade_no); //商家订单号  
			prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
			String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = WechatPay.CLOSE_ORDER_URL;
			prepayReqHandler.setGateUrl(gateUrl);
			msgxml = prepayReqHandler.sendAndGetXml();
			// 获取关闭订单接口微信返回的XML
			logger.info("-----微信关闭订单接口返回的xml数据------" + msgxml);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("------调用微信关闭订单接口异常------", e.getMessage());
		}
		return msgxml;
	}
    
    /**
     * 微信对账单接口获取对账单
     * @param request
     * @param response
     * @param bill_type
     * @param payMerchant
     * @return
     */
	public static String getWxMsgxml(HttpServletRequest request,HttpServletResponse response,String bill_type,PayMerchant payMerchant){
		String msgxml="";
		try{
			WechatPayRequestHandler prepayReqHandler = new WechatPayRequestHandler(request, response);
			prepayReqHandler.setKey(payMerchant.getMchSecret());
			//获取前一天日期字符串
			String bill_date=TimeUtil.getPrevDate();
			String noncestr = CommonUtil.getNonceStr();
			// 设置查询对账单参数
			prepayReqHandler.setParameter("appid",payMerchant.getAppId() );// 公众账号ID
			prepayReqHandler.setParameter("mch_id",payMerchant.getMchId() );// 商户号
			prepayReqHandler.setParameter("bill_date", bill_date); //对账单日期
			prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
			prepayReqHandler.setParameter("bill_type", bill_type);//账单类型
			String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = WechatPay.DOWNLOAD_BILL_URL;
			prepayReqHandler.setGateUrl(gateUrl);				
			msgxml = prepayReqHandler.sendAndGetXml();
			logger.info("------微信对账单接口返回的xml数据------" + msgxml);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("------调用微信对账单接口获取对账单异常------", e.getMessage());
		}
		return msgxml;		
	}
}
