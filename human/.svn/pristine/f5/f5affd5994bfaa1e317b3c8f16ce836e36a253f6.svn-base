package com.human.activity.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderRefundInfo {
    private Long id;

    private String orderNo;//商户订单号

    private String orderRefundNo;//微信支付订单号

    private String transactionId;//商户退款单号

    private String refundId;//微信退款单号

    private BigDecimal orderCost;//订单金额

    private BigDecimal refundCost;//申请退款金额

    private String refundChannel;//退款渠道
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//退款申请时间

    private String refundDesc;//退款说明

    private String resultCode;//业务结果

    private String resultDesc;//结果说明

    private String refundUser;//申请退款人

    private Integer orderRefundState;//退款状态：0：申请退款1：待退款2 退款成功 3 退款失败

    private String refundRecvAccout;//退款入账账户

    private Integer refundCount;//退款笔数
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sucessesTime;//退款成功时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getOrderRefundNo() {
        return orderRefundNo;
    }

    public void setOrderRefundNo(String orderRefundNo) {
        this.orderRefundNo = orderRefundNo == null ? null : orderRefundNo.trim();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId == null ? null : transactionId.trim();
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    public BigDecimal getOrderCost() {
        return orderCost;
    }

    public void setOrderCost(BigDecimal orderCost) {
        this.orderCost = orderCost;
    }

    public BigDecimal getRefundCost() {
        return refundCost;
    }

    public void setRefundCost(BigDecimal refundCost) {
        this.refundCost = refundCost;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel == null ? null : refundChannel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc == null ? null : refundDesc.trim();
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode == null ? null : resultCode.trim();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    public String getRefundUser() {
        return refundUser;
    }

    public void setRefundUser(String refundUser) {
        this.refundUser = refundUser == null ? null : refundUser.trim();
    }

    public Integer getOrderRefundState() {
        return orderRefundState;
    }

    public void setOrderRefundState(Integer orderRefundState) {
        this.orderRefundState = orderRefundState;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout == null ? null : refundRecvAccout.trim();
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public Date getSucessesTime() {
        return sucessesTime;
    }

    public void setSucessesTime(Date sucessesTime) {
        this.sucessesTime = sucessesTime;
    }
}