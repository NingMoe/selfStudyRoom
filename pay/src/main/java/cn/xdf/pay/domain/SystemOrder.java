package cn.xdf.pay.domain;
/**
 * 调用系统-订单
 * @author liuwei63
 *
 */
public class SystemOrder {
    private Long id;

    private Long callSystemId;
    
    private Long payMerchantId;

    private Long orderId;

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
    
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}