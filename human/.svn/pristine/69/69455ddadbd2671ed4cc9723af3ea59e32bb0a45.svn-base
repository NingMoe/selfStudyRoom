package com.human.activity.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class BuyerInfoDto extends BuyerInfo{

    public BuyerInfoDto() {
        super();
       
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;//支付完成时间
    
    private BigDecimal realCost;//实际金额
    
    private String productName;//商品名称
    
    private String transactionId;//微信支付单号
       
    private Long orderInfoId;//订单id
    
    private BigDecimal fee;//手续费
    
    private String studentCode;
    
    private BigDecimal refundCost;//退款金额

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public BigDecimal getRealCost() {
        return realCost;
    }

    public void setRealCost(BigDecimal realCost) {
        this.realCost = realCost;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Long getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Long orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public BigDecimal getRefundCost() {
        return refundCost;
    }

    public void setRefundCost(BigDecimal refundCost) {
        this.refundCost = refundCost;
    }
    
    
    
    
    
    
}