package com.human.questionnaire.entity;

import java.sql.Timestamp;

public class DisableIP {
    private Long id;
    
    private Long formId;
    
    private String ipAddr;
    
    private Timestamp opTime;
    
    private Long opUser;
    
    private String userName;

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    } 

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOpTime() {
        return opTime;
    }

    public void setOpTime(Timestamp opTime) {
        this.opTime = opTime;
    }

    public Long getOpUser() {
        return opUser;
    }

    public void setOpUser(Long opUser) {
        this.opUser = opUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    

}
