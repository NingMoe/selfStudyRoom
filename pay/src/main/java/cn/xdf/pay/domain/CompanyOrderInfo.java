package cn.xdf.pay.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 企业付款到用户微信零钱账单
 * @author liuwei63
 *
 */
public class CompanyOrderInfo {
    private Long id;

    private String partnerTradeNo;//商户订单号

    private String paymentNo;//微信订单号

    private String openId;//收款用户openId

    private String reUserName;//收款用户姓名

    private BigDecimal amount;//付款金额

    private String status;//付款状态：0.处理中,1.转账成功 ,2.转账失败

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date transferTime;//转账时间

    private String failReason;//转账失败原因

    private String payDesc;//付款描述

    private String resultCode;//业务结果编码

    private String resultDesc;//业务结果说明

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo == null ? null : partnerTradeNo.trim();
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo == null ? null : paymentNo.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getReUserName() {
        return reUserName;
    }

    public void setReUserName(String reUserName) {
        this.reUserName = reUserName == null ? null : reUserName.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
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
}