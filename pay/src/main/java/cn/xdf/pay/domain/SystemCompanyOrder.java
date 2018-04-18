package cn.xdf.pay.domain;

/**
 * 调用系统-企业支付订单
 * @author liuwei63
 *
 */
public class SystemCompanyOrder {
    private Long id;

    private Long callSystemId;

    private Long payMerchantId;

    private Long companyOrderId;

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

    public Long getPayMerchantId() {
        return payMerchantId;
    }

    public void setPayMerchantId(Long payMerchantId) {
        this.payMerchantId = payMerchantId;
    }

    public Long getCompanyOrderId() {
        return companyOrderId;
    }

    public void setCompanyOrderId(Long companyOrderId) {
        this.companyOrderId = companyOrderId;
    }
}