package com.human.basic.entity;

public class SmsParam {
    
    private Long id;
    
    private String paraCode;
    
    private String paraName;
    
    private Long smsTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParaCode() {
        return paraCode;
    }

    public void setParaCode(String paraCode) {
        this.paraCode = paraCode;
    }

    public String getParaName() {
        return paraName;
    }

    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public Long getSmsTypeId() {
        return smsTypeId;
    }

    public void setSmsTypeId(Long smsTypeId) {
        this.smsTypeId = smsTypeId;
    }

}
