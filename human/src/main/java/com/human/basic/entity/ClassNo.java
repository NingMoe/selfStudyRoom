package com.human.basic.entity;

public class ClassNo {
    private String nSchoolId;
    
    private String sCode;
    
    private String nAudit;//1审核  0未审核

    public String getnSchoolId() {
        return nSchoolId;
    }

    public void setnSchoolId(String nSchoolId) {
        this.nSchoolId = nSchoolId;
    }

    public String getsCode() {
        return sCode;
    }

    public void setsCode(String sCode) {
        this.sCode = sCode;
    }

    public String getnAudit() {
        return nAudit;
    }

    public void setnAudit(String nAudit) {
        this.nAudit = nAudit;
    }
    
    
}
