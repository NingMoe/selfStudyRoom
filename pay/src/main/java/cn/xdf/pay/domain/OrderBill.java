package cn.xdf.pay.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
/**
 * 对账单
 * @author liuwei63
 *
 */
public class OrderBill {
    private Long id;

    private Timestamp tradeTime;//交易时间

    private String wxorder;//微信订单号

    private String bzorder;//商户订单号

    private String openId;//用户微信标识

    private String tradeType;//交易类型

    private String tradeStatus;//交易状态

    private String bank;//付款银行

    private String currency;//货币种类

    private BigDecimal totalMoney;//总金额

    private BigDecimal redpacketMoney;//代金券或立减优惠金额

    private Timestamp refundCreateTime;//退款申请时间

    private Timestamp refundSucessesTime;//退款成功时间

    private String wxrefund;//微信退款单号

    private String bzrefund;//商户退款单号

    private BigDecimal refundMoney;//退款金额

    private BigDecimal redpacketRefundMoney;//代金券或立减优惠退款金额

    private String refundType;//退款类型

    private String refundStatus;//退款状态

    private String productName;//商品名称(订单名称）

    private String bzdataPacket;//商户数据包

    private BigDecimal fee;//手续费

    private String rate;//费率

    private Integer billType;//对账单类型：0：当日成功支付的订单、1：当日退款订单

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Timestamp tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getWxorder() {
        return wxorder;
    }

    public void setWxorder(String wxorder) {
        this.wxorder = wxorder == null ? null : wxorder.trim();
    }

    public String getBzorder() {
        return bzorder;
    }

    public void setBzorder(String bzorder) {
        this.bzorder = bzorder == null ? null : bzorder.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

    public BigDecimal getRedpacketMoney() {
        return redpacketMoney;
    }

    public void setRedpacketMoney(BigDecimal redpacketMoney) {
        this.redpacketMoney = redpacketMoney;
    }

    public Timestamp getRefundCreateTime() {
		return refundCreateTime;
	}

	public void setRefundCreateTime(Timestamp refundCreateTime) {
		this.refundCreateTime = refundCreateTime;
	}

	public Timestamp getRefundSucessesTime() {
		return refundSucessesTime;
	}

	public void setRefundSucessesTime(Timestamp refundSucessesTime) {
		this.refundSucessesTime = refundSucessesTime;
	}

	public String getWxrefund() {
        return wxrefund;
    }

    public void setWxrefund(String wxrefund) {
        this.wxrefund = wxrefund == null ? null : wxrefund.trim();
    }

    public String getBzrefund() {
        return bzrefund;
    }

    public void setBzrefund(String bzrefund) {
        this.bzrefund = bzrefund == null ? null : bzrefund.trim();
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public BigDecimal getRedpacketRefundMoney() {
        return redpacketRefundMoney;
    }

    public void setRedpacketRefundMoney(BigDecimal redpacketRefundMoney) {
        this.redpacketRefundMoney = redpacketRefundMoney;
    }

    public String getRefundType() {
        return refundType;
    }

    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getBzdataPacket() {
        return bzdataPacket;
    }

    public void setBzdataPacket(String bzdataPacket) {
        this.bzdataPacket = bzdataPacket == null ? null : bzdataPacket.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public Integer getBillType() {
        return billType;
    }

    public void setBillType(Integer billType) {
        this.billType = billType;
    }
}