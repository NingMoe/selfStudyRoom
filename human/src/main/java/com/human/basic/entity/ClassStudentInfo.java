package com.human.basic.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassStudentInfo {
    private Integer orderInfoId;
    
    private String sStudentCode;
    
    private String sBusinessItemCode;
    
    @JsonProperty(value = "IsHighEndClass")  
    private String isHighEndClass;
    
    private String sCourseCode;
    
    private String sClassCode;
    
    private String sCardCode;
    
    private String sOldItemCode;
    
    private String dAmountReceivable;

    public String getsStudentCode() {
        return sStudentCode;
    }

    public void setsStudentCode(String sStudentCode) {
        this.sStudentCode = sStudentCode;
    }

    public String getsBusinessItemCode() {
        return sBusinessItemCode;
    }

    public void setsBusinessItemCode(String sBusinessItemCode) {
        this.sBusinessItemCode = sBusinessItemCode;
    }

    public String getIsHighEndClass() {
        return isHighEndClass;
    }

    public void setIsHighEndClass(String isHighEndClass) {
        this.isHighEndClass = isHighEndClass;
    }

    public String getsCourseCode() {
        return sCourseCode;
    }

    public void setsCourseCode(String sCourseCode) {
        this.sCourseCode = sCourseCode;
    }

    public String getsClassCode() {
        return sClassCode;
    }

    public void setsClassCode(String sClassCode) {
        this.sClassCode = sClassCode;
    }

    public String getsCardCode() {
        return sCardCode;
    }

    public void setsCardCode(String sCardCode) {
        this.sCardCode = sCardCode;
    }

    public String getsOldItemCode() {
        return sOldItemCode;
    }

    public void setsOldItemCode(String sOldItemCode) {
        this.sOldItemCode = sOldItemCode;
    }

    public Integer getOrderInfoId() {
        return orderInfoId;
    }

    public void setOrderInfoId(Integer orderInfoId) {
        this.orderInfoId = orderInfoId;
    }

    public String getdAmountReceivable() {
        return dAmountReceivable;
    }

    public void setdAmountReceivable(String dAmountReceivable) {
        this.dAmountReceivable = dAmountReceivable;
    }
    
    
}
