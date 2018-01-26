package com.human.coupon.entity;

import java.util.List;

public class StudentCoup {
    private String ExecutionType;
    
    private String SchoolID;
    
    private String StudentCode;
    
    private String NewStudentMobile;
    
    private String VoucherItemCodes;
    
    private String StaffID;
    
    private String CouponType;
    
    private String CouponSubType;
    
    private String PageSize;
    
    private String PageIndex;
    
    private List<Coupon> CouponList;

    public String getExecutionType() {
        return ExecutionType;
    }

    public void setExecutionType(String executionType) {
        ExecutionType = executionType;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public String getNewStudentMobile() {
        return NewStudentMobile;
    }

    public void setNewStudentMobile(String newStudentMobile) {
        NewStudentMobile = newStudentMobile;
    }

    public String getVoucherItemCodes() {
        return VoucherItemCodes;
    }

    public void setVoucherItemCodes(String voucherItemCodes) {
        VoucherItemCodes = voucherItemCodes;
    }

    public String getStaffID() {
        return StaffID;
    }

    public void setStaffID(String staffID) {
        StaffID = staffID;
    }

    public String getCouponType() {
        return CouponType;
    }

    public void setCouponType(String couponType) {
        CouponType = couponType;
    }

    public String getCouponSubType() {
        return CouponSubType;
    }

    public void setCouponSubType(String couponSubType) {
        CouponSubType = couponSubType;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(String pageIndex) {
        PageIndex = pageIndex;
    }

    public List<Coupon> getCouponList() {
        return CouponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        CouponList = couponList;
    }
    
    
    
}
