package cn.xdf.pay.domain;

/**
 * 微信公众号支付、小程序支付参数
 * @author liuwei63
 *
 */
public class WechatPayParams {

	private String appId;//公众号id
	
	private String timeStamp;//时间戳
	
	private String nonceStr;//随机字符串
	
	private String packageInfo;//订单详情扩展字符串
	
	private String paySign;//签名

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getPackageInfo() {
		return packageInfo;
	}

	public void setPackageInfo(String packageInfo) {
		this.packageInfo = packageInfo;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
	
	
	
	
	
}
