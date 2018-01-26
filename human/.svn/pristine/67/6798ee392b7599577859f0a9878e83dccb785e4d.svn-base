package com.human.order.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConstantUtil2 {
	static ResourceBundle bundle;
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String getString(String s) {
		try {
			return getResourceBundle().getString(s);
		} catch (Exception e) {			
           e.printStackTrace();
		}
		return "";
	}
	/**
	 * 
	 * @return
	 */
	private static ResourceBundle getResourceBundle() {
		try {
			if (bundle == null){
				bundle = ResourceBundle.getBundle("properties/weixinConfig",Locale.getDefault());	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return bundle;
	}
	
	/**
	 * 服务号相关信息
	 */
	public static String APP_ID = getString("APP_ID");// 微信开发平台应用id
	public static String APP_SECRET = getString("APP_SECRET");// 应用对应的凭证
	public static String PARTNER = getString("PARTNER");// 微信支付商户号
	public static String PARTNER_KEY = getString("PARTNER_KEY");// 商户号对应的密钥
    public static String NOTIFY_URL =getString("NOTIFY_URL");// 微信支付统一接口的回调
	public static String SUCCESS_URL = "";// 微信支付成功支付后跳转的地址
	public static String REDIRECT_URI = getString("REDIRECT_URI");// oauth2授权时回调
		
	/**
	 * 微信基础接口地址
	 */	
	public static String TOKEN_URL = "";// 获取token接口
	public static String REFRESH_TOKEN_URL = getString("REFRESH_TOKEN_URL");// 刷新access_token接口
	public static String TOKENURL = getString("TOKENURL");//oauth2授权获取access_token对应的url
	public static String GRANT_TYPE = getString("GRANT_TYPE");// 常量固定值
	public static String OPENID = getString("OPENID");
	public static String PREPAY_ID = getString("PREPAY_ID");
	public static String AUTHORIZE_URL=getString("AUTHORIZE_URL");//微信静默授权接口地址
	
	/**
	 * 微信支付接口地址
	 */
	public static String GATEURL = getString("GATEURL");//获取预支付id的接口
	public  static String REFUND_URL =getString("REFUND_URL");// 微信退款接口
	public  static String CHECK_ORDER_URL = getString("CHECK_ORDER_URL");// 订单查询接口
	public  static String CLOSE_ORDER_URL = getString("CLOSE_ORDER_URL");// 关闭订单接口
	public  static String CHECK_REFUND_URL = getString("CHECK_REFUND_URL");// 退款查询接口
	public  static String DOWNLOAD_BILL_URL =getString("DOWNLOAD_BILL_URL");// 对账单接口

}
