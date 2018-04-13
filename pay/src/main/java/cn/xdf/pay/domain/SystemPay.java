package cn.xdf.pay.domain;
/**
 * 调用系统-支付商户
 * @author liuwei63
 *
 */
public class SystemPay {
    private Long id;

    private Long callSystemId;

    private Long payId;

    private String isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallSystemId() {
        return callSystemId;
    }

    public void setCallSystemId(Long callSystemId) {
        this.callSystemId = callSystemId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }
}