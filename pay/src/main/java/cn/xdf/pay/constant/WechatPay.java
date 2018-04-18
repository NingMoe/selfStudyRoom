package cn.xdf.pay.constant;
/**
 * 微信支付接口常量
 * @author liuwei63
 *
 */
public class WechatPay {
	
	
	public  static final String JSAPI="JSAPI";// 公众号支付
	
	public  static final String NATIVE="NATIVE";// PC扫码支付
	
	public  static final String APP="APP";// APP支付
	
	public  static final String MWEB="MWEB";// H5支付
	
	public  static final String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";//统一下单接口
	
	public  static final String REFUND_URL ="https://api.mch.weixin.qq.com/secapi/pay/refund";// 申请退款接口
	
	public  static final String CHECK_ORDER_URL = "https://api.mch.weixin.qq.com/pay/orderquery";// 订单查询接口
	
	public  static final String CLOSE_ORDER_URL = "https://api.mch.weixin.qq.com/pay/closeorder";// 关闭订单接口
	
	public  static final String CHECK_REFUND_URL = "https://api.mch.weixin.qq.com/pay/refundquery";// 退款查询接口
	
	public  static final String DOWNLOAD_BILL_URL ="https://api.mch.weixin.qq.com/pay/downloadbill";// 对账单接口
	
	public  static final String DOWNLOAD_FUND_URL ="https://api.mch.weixin.qq.com/pay/downloadfundflow";// 资金账单接口
	
	public  static final String NOTIFY_URL ="https://hfhd.xdf.cn/pay/weChatPay/notify";// 支付结果通过回调地址
	
	public  static final String COMPANY_PAY_URL ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";// 企业付款到零钱接口
	
	public  static final String CHECK_COMPANY_PAY_URL="https://api.mch.weixin.qq.com/mmpaymkttransfers/gettransferinfo";// 企业付款到零钱查询接口
}
