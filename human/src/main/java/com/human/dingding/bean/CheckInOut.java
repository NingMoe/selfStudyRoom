package com.human.dingding.bean;

public class CheckInOut {
    
    private Integer userid;
    
    private String checktime;
    
    private String checktype;
    
    private Integer verifycode;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getChecktype() {
        return checktype;
    }

    public void setChecktype(String checktype) {
        this.checktype = checktype;
    }

    public Integer getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(Integer verifycode) {
        this.verifycode = verifycode;
    }

}
