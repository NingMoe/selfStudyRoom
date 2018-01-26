package com.human.activity.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 导出活动支付明细Dto
 * @author liuwei63
 *
 */
public class PayInfoDto {
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="姓名")
    private String name;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手机号码")
    private String telephone;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员号")
    private String studentCode;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="支付时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;//支付完成时间
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="支付金额")
    private BigDecimal realCost;//实际金额
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="退款金额")
    private BigDecimal refundCost;//实际金额
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手续费")
    private BigDecimal fee;//手续费
    
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="商品名称")
    private String productName;//商品名称
    
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="购买数量")
    private Long buyTotal;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="微信订单号")
    private String transactionId;//微信支付单号
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="交易状态")
    private String payStatus;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="文本1")
    private String text1;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="文本2")
    private String text2;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="文本3")
    private String text3;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="文本4")
    private String text4;
    
    private String activityName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    
    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

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
    
    public BigDecimal getRefundCost() {
        return refundCost;
    }

    public void setRefundCost(BigDecimal refundCost) {
        this.refundCost = refundCost;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Long buyTotal) {
        this.buyTotal = buyTotal;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3;
    }

    public String getText4() {
        return text4;
    }

    public void setText4(String text4) {
        this.text4 = text4;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
    
    
    
    
    
    
}
