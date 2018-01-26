package com.human.order.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.activity.entity.OrderInfo;
import com.human.order.entity.ClientRequestHandler;
import com.human.order.entity.PrepayIdRequestHandler;



public class TenpayUtil {
	
	
	/**
	 * 把对象转换成字符串
	 * 
	 * @param obj
	 * @return String 转换成字符串,若对象为null,则返回空字符串.
	 */
	public static String toString(Object obj) {
		if (obj == null)
			return "";

		return obj.toString();
	}

	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}

	/**
	 * 获取当前日期 yyyyMMdd
	 * @param date
	 * @return String
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(date);
		return strDate;
	}

	/**
     * 获取当前日期 yyyy-MM-dd
     * @param date
     * @return String
     */
    public static String formatDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = formatter.format(date);
        return strDate;
    }
	
	/**
     * 获取当前日期 前一天yyyyMMdd
     * @param date
     * @return String
     */
    public static String getPrevDate() {
        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1); 
        Date prevDate=calendar.getTime();   
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strDate = formatter.format(prevDate);
        return strDate;
    }
    
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 获取编码字符集
	 * 
	 * @param request
	 * @param response
	 * @return String
	 */
	public static String getCharacterEncoding(HttpServletRequest request, HttpServletResponse response) {

		if (null == request || null == response) {
			return "UTF-8";
		}

		String enc = request.getCharacterEncoding();
		if (null == enc || "".equals(enc)) {
			enc = response.getCharacterEncoding();
		}

		if (null == enc || "".equals(enc)) {
			enc = "UTF-8";
		}
		return enc;
	}

	/**
	 * 获取unix时间，从1970-01-01 00:00:00开始的秒数
	 * 
	 * @param date
	 * @return long
	 */
	public static long getUnixTime(Date date) {
		if (null == date) {
			return 0;
		}

		return date.getTime() / 1000;
	}

	/**
	 * 时间转换成字符串
	 * 
	 * @param date
	 *            时间
	 * @param formatType
	 *            格式化类型
	 * @return String
	 */
	public static String date2String(Date date, String formatType) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatType);
		return sdf.format(date);
	}

	/**
	 * 根据订单生成时间生成订单失效时间，默认30分钟
	 */
	public static String getExpireTime(String startTime) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String expireTime = "";
		try {
			Date date = outFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, 30);
			Date expireDate = calendar.getTime();
			expireTime = outFormat.format(expireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return expireTime;
	}

	
	public static Date getDate(String time) {
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=new Date();
        try {
           date = outFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }       
        return date;
    }
	
	/**
	 * 将字符串转为Timestamp
	 */
	public static Timestamp getTimestamp(String time) {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Timestamp result = null;
		try {
			Date date = outFormat.parse(time);
			result = new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
     * 将字符串转为Timestamp2
     */
    public static Timestamp getTimestamp2(String time) {
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp result = null;
        try {
            Date date = outFormat.parse(time);
            result = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
	
		
	
	/**
	 * 将Timestamp转为字符串
	 */
	public static String changeTimestampToString(Timestamp tmp) {
		String tsStr="";
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			 tsStr = outFormat.format(tmp);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tsStr;
	}
	
	/**
	 * 获取当前日期 后一天yyyyMMdd
	 * 
	 * @param date
	 * @return String
	 */
	public static String getNextDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		Date nextDate = calendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(nextDate);
		return strDate;
	}

	/**
	 * 生成订单号
	 * 
	 * @return
	 */
	public static String getOrderNo() {
		// ---------------生成订单号 开始------------------------
		// 当前时间 yyyyMMddHHmmss
		String currTime = getCurrTime();
		String strTime = currTime;
		// 四位随机数
		String strRandom = buildRandom(4) + "";
		// 14位序列号,可以自行调整
		String strReq = strTime + strRandom;
		// 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		String out_trade_no = strReq;
		// ---------------生成订单号 结束------------------------
		return out_trade_no;

	}

    /**
     * 微信公众号支付统一下单
     */
    public static String getUnifiedorderXml(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo, String openid) {
        String msgxml = "";
        try {
            // 商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调起支付
            PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);// 获取prepayid的请求类
            String noncestr = WXUtil.getNonceStr();
            String body = "新东方优能中学教育-" + orderInfo.getOrderName();
            String total_fee = Integer.toString(orderInfo.getOrderCost().multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());
            String spbill_create_ip = WXUtil.toIpAddr(request);
            String time_start = date2String(orderInfo.getTimeStart(), "yyyyMMddHHmmss");// 订单生成时间
            String out_trade_no = orderInfo.getOrderNo();
            // 设置获取prepayid支付参数
            prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
            prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
            prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
            prepayReqHandler.setParameter("body", body);// 商品描述
            prepayReqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
            prepayReqHandler.setParameter("total_fee", total_fee);// 总金额
            prepayReqHandler.setParameter("spbill_create_ip", spbill_create_ip);// 终端IP
            prepayReqHandler.setParameter("time_start", time_start);// 订单生成时间
            prepayReqHandler.setParameter("notify_url", ConstantUtil2.NOTIFY_URL);// 通知地址
            prepayReqHandler.setParameter("trade_type", "JSAPI");// 交易类型
            prepayReqHandler.setParameter("openid", openid);// 微信用户openid
            String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
            prepayReqHandler.setParameter("sign", sign);
            String gateUrl = ConstantUtil2.GATEURL;
            prepayReqHandler.setGateUrl(gateUrl);
            msgxml = prepayReqHandler.sendAndGetXml();
            System.out.println("微信下单成功后返回的xml数据------" + msgxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return msgxml;
    }
	
	/**
	 * 生成获取支付签名
	 * @param request
	 * @param response
	 * @param prepayid
	 * @return
	 */
	public static String getPaySign(HttpServletRequest request, HttpServletResponse response,String prepayid){		
		String timeStamp=WXUtil.getTimeStamp();
		String packageStr="prepay_id="+prepayid;
		String noncestr = WXUtil.getNonceStr();
	    ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);//返回客户端支付参数的请求类
	    clientHandler.setParameter("appId", ConstantUtil2.APP_ID);//公众账号ID
	    clientHandler.setParameter("timeStamp",timeStamp );//时间戳
	    clientHandler.setParameter("nonceStr", noncestr);//随机字符串
	    clientHandler.setParameter("package",packageStr);//订单详情扩展字符串
	    clientHandler.setParameter("signType", "MD5"); //签名算法 	
	    String paySign = clientHandler.createMd5Sign();	//生成获取支付签名
	    return paySign;
	}
	
	
	
	/**
	 * 支付成功查询订单
	 */
	public static String getPaySuccessXml(HttpServletRequest request, HttpServletResponse response,String orderNo) {
		String msgxml = "";
		try {
			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
			String noncestr = WXUtil.getNonceStr();
			String out_trade_no=orderNo;
			// 设置查询订单参数
			prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
			prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
			prepayReqHandler.setParameter("out_trade_no", out_trade_no); //商家订单号  
			prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
			String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = ConstantUtil2.CHECK_ORDER_URL;
			prepayReqHandler.setGateUrl(gateUrl);
			//获取查询接口微信返回的XML
			msgxml = prepayReqHandler.sendAndGetXml();
			System.out.println("微信支付后调用查询订单接口返回的xml数据------"+msgxml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgxml;

	}
	
	
	/**
	 * 支付失败关闭订单
	 */
	public static String getPayFailXml(HttpServletRequest request, HttpServletResponse response,String orderNo) {
		String msgxml = "";
		try {
			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);
			String noncestr = WXUtil.getNonceStr();
			String out_trade_no=orderNo;
			// 设置查询订单参数
			prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
			prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
			prepayReqHandler.setParameter("out_trade_no", out_trade_no); //商家订单号  
			prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
			String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
			prepayReqHandler.setParameter("sign", sign);
			String gateUrl = ConstantUtil2.CLOSE_ORDER_URL;
			prepayReqHandler.setGateUrl(gateUrl);
			//获取查询接口微信返回的XML
			msgxml = prepayReqHandler.sendAndGetXml();
			System.out.println("微信支付失败后返回的xml数据------"+msgxml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgxml;

	}
	

    /**
     * 微信H5支付统一下单
     * @param request
     * @param response
     * @param orderInfo
     * @return
     */
    public static String getUnifiedorderXmlByH5(HttpServletRequest request, HttpServletResponse response, OrderInfo orderInfo) {
        String msgxml = "";
        try {
            PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//
            String noncestr = WXUtil.getNonceStr();
            String body = "合肥新东方H5支付-" + orderInfo.getOrderName();
            String total_fee = Integer.toString(orderInfo.getOrderCost().multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());
            String spbill_create_ip = WXUtil.toIpAddr(request);
            System.out.println("spbill_create_ip==="+spbill_create_ip);
            String out_trade_no = orderInfo.getOrderNo();
            String scene_info="{\"h5_info\":{\"type\":\"Wap\",\"wap_url\": \"http://sw.hf.xdf.cn\",\"wap_name\": \"新东方购买优惠券\"}}";
            // 设置获取prepayid支付参数
            prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);// 公众账号ID
            prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);// 商户号
            prepayReqHandler.setParameter("nonce_str", noncestr);// 随机字符串
            prepayReqHandler.setParameter("body", body);// 商品描述
            prepayReqHandler.setParameter("out_trade_no", out_trade_no); // 商家订单号
            prepayReqHandler.setParameter("total_fee", total_fee);// 总金额
            prepayReqHandler.setParameter("spbill_create_ip", spbill_create_ip);// 终端IP
            prepayReqHandler.setParameter("notify_url", ConstantUtil2.NOTIFY_URL);// 通知地址
            prepayReqHandler.setParameter("trade_type", "MWEB");//H5支付的交易类型为MWEB
            prepayReqHandler.setParameter("scene_info", scene_info);//场景信息
            String sign = prepayReqHandler.createMd5Sign(); // 生成获取预支付签名
            prepayReqHandler.setParameter("sign", sign);
            String gateUrl = ConstantUtil2.GATEURL;
            prepayReqHandler.setGateUrl(gateUrl);
            msgxml = prepayReqHandler.sendAndGetXml();
            System.out.println("微信浏览器H5支付下单成功后返回的xml数据------" + msgxml);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return msgxml;
    }
	
}
