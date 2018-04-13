package cn.xdf.pay.domain;
/**
 * 微信支付商户
 * @author liuwei63
 *
 */
public class PayMerchant {
    private Long id;

    private String appId;//公众账号Id

    private String appSecret;//公众账号秘钥

    private String mchId;//商户号Id

    private String mchSecret;//商户号秘钥

    private String path;//退款所需证书路径

    private String isValid;//状态 1 有效 2 无效
    
    private Long callSystemId;//调用系统Id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    public String getMchSecret() {
        return mchSecret;
    }

    public void setMchSecret(String mchSecret) {
        this.mchSecret = mchSecret == null ? null : mchSecret.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

	public Long getCallSystemId() {
		return callSystemId;
	}

	public void setCallSystemId(Long callSystemId) {
		this.callSystemId = callSystemId;
	}
    
    
}