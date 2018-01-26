package com.human.order.entity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.order.utils.ConstantUtil;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.JsonUtil;
import com.human.order.utils.TenpayHttpClient;
import com.human.order.utils.WXUtil;


public class AccessTokenRequestHandler extends RequestHandler {

	public AccessTokenRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	private static String access_token = "";
	
	private static String openId = "";

	/**
	 * 获取凭证access_token
	 * @return
	 */
	public static String getAccessToken() {
		if ("".equals(access_token)) {// 如果为空直接获取
			return getTokenReal();
		}

		if (tokenIsExpire(access_token)) {// 如果过期重新获取
			return getTokenReal();
		}
		return access_token;
	}
	
	/**
	 * 获取OpenId
	 * @return
	 */
	public static String getOpenId(String code) {
           return getOpenID(code);
	}

	/**
	 * 实际获取access_token的方法
	 * @return
	 */
	protected static String getTokenReal() {
		String requestUrl = ConstantUtil.TOKENURL + "?grant_type=" + ConstantUtil.GRANT_TYPE + "&appid="
				+ ConstantUtil.APP_ID + "&secret=" + ConstantUtil.APP_SECRET;
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(ConstantUtil.ACCESS_TOKEN) > 0) {
				access_token = JsonUtil.getJsonValue(resContent, ConstantUtil.ACCESS_TOKEN);
			} else {
				System.out.println("获取access_token值返回错误！！！");
			}
		} else {
			System.out.println("后台调用通信失败");
			System.out.println(httpClient.getResponseCode());
			System.out.println(httpClient.getErrInfo());
			// 有可能因为网络原因，请求已经处理，但未收到应答。
		}

		return access_token;
	}

	/**
	 * 判断传递过来的参数access_token是否过期
	 * @param access_token
	 * @return
	 */
	private static boolean tokenIsExpire(String access_token) {
		boolean flag = false;
		PrepayIdRequestHandler wxReqHandler = new PrepayIdRequestHandler(null, null);
		wxReqHandler.setParameter("appid", ConstantUtil.APP_ID);
		wxReqHandler.setParameter("appkey",ConstantUtil.APP_KEY);
		wxReqHandler.setParameter("noncestr", WXUtil.getNonceStr());
		wxReqHandler.setParameter("package", ConstantUtil.packageValue);
		wxReqHandler.setParameter("timestamp", WXUtil.getTimeStamp());
		wxReqHandler.setParameter("traceid", ConstantUtil.traceid);

		// 生成支付签名
		String sign = wxReqHandler.createSHA1Sign();
		wxReqHandler.setParameter("app_signature", sign);
		wxReqHandler.setParameter("sign_method", ConstantUtil.SIGN_METHOD);
		String gateUrl = ConstantUtil.GATEURL + access_token;
		wxReqHandler.setGateUrl(gateUrl);

		// 发送请求
		String accesstoken = wxReqHandler.sendAccessToken();
		if (ConstantUtil.EXPIRE_ERRCODE.equals(accesstoken) || ConstantUtil.FAIL_ERRCODE.equals(accesstoken))
			flag = true;
		return flag;
	}
	
	/**
	 * 获取微信用户的openID
	 */
	protected static String getOpenID(String code) {		
		String requestUrl = ConstantUtil2.TOKENURL + "?appid="+ConstantUtil2.APP_ID +"&secret=" + ConstantUtil2.APP_SECRET
				+"&code=" + code+"&grant_type=" + ConstantUtil2.GRANT_TYPE;
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(ConstantUtil2.OPENID) > 0) {
				openId= JsonUtil.getJsonValue(resContent, ConstantUtil2.OPENID);
			} else {
				System.out.println("获取openId值返回错误！！！");
			}
		} else {
			System.out.println("后台调用通信失败");
			System.out.println(httpClient.getResponseCode());
			System.out.println(httpClient.getErrInfo());
		}
		return openId;
	}
	
		
	
	
	
	
	
	
	

}
