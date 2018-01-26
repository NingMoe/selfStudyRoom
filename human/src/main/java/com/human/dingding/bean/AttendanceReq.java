package com.human.dingding.bean;

public class AttendanceReq {
    
    private String userId;
    
    //考情查询开始时间：yyyy-MM-dd hh:mm:ss
    private String workDateFrom;
    
    //考情查询结束时间：yyyy-MM-dd hh:mm:ss
    private String workDateTo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkDateFrom() {
        return workDateFrom;
    }

    public void setWorkDateFrom(String workDateFrom) {
        this.workDateFrom = workDateFrom;
    }

    public String getWorkDateTo() {
        return workDateTo;
    }

    public void setWorkDateTo(String workDateTo) {
        this.workDateTo = workDateTo;
    }

}
