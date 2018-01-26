package com.human.activity.entity;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;


public class ActivityInfoDto {
    
    private Long id;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="活动名称")
    private String activityName;//活动名称

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="所属部门")
    private String deptName;//所属部门
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="活动有效期")
    private String validTime;//活动有效期
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="创建人")
    private String createUser;//创建人
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="交易笔数")
    private Integer prosceniumNum;//交易笔数
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="累计收款总金额")
    private BigDecimal totalPayMoney;//累计收款总金额
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="退款笔数")
    private Integer refundNum;//退款笔数
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="累计退款总金额")
    private BigDecimal totalRefundMoney;//累计退款总金额
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手续费")
    private BigDecimal servicePayMoney;//手续费
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="结余总金额")
    private BigDecimal totalSurplusMoney;//结余总金额
        
    private String codeUrl;
    
    private String accountValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getValidTime() {
        return validTime;
    }

    public void setValidTime(String validTime) {
        this.validTime = validTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getProsceniumNum() {
        return prosceniumNum;
    }

    public void setProsceniumNum(Integer prosceniumNum) {
        this.prosceniumNum = prosceniumNum;
    }

    public BigDecimal getTotalPayMoney() {
        return totalPayMoney;
    }

    public void setTotalPayMoney(BigDecimal totalPayMoney) {
        this.totalPayMoney = totalPayMoney;
    }

    public Integer getRefundNum() {
        return refundNum;
    }

    public void setRefundNum(Integer refundNum) {
        this.refundNum = refundNum;
    }

    public BigDecimal getTotalRefundMoney() {
        return totalRefundMoney;
    }

    public void setTotalRefundMoney(BigDecimal totalRefundMoney) {
        this.totalRefundMoney = totalRefundMoney;
    }

    public BigDecimal getServicePayMoney() {
        return servicePayMoney;
    }

    public void setServicePayMoney(BigDecimal servicePayMoney) {
        this.servicePayMoney = servicePayMoney;
    }

    public BigDecimal getTotalSurplusMoney() {
        return totalSurplusMoney;
    }

    public void setTotalSurplusMoney(BigDecimal totalSurplusMoney) {
        this.totalSurplusMoney = totalSurplusMoney;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getAccountValid() {
        return accountValid;
    }

    public void setAccountValid(String accountValid) {
        this.accountValid = accountValid;
    }
    
    
    
    
}