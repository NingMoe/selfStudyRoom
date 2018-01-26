package com.human.recruitment.entity;

import java.sql.Timestamp;

public class CommunicationDesc {
    
    private Long descId;
    
    private String communication;
    
    private String communicationName;
    
    private Timestamp linkTime;
    
    private String nextTime;
    
    private String comment;
    
    private Long communicationId;
    
    private  Integer status;

    public Long getDescId() {
        return descId;
    }

    public void setDescId(Long descId) {
        this.descId = descId;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public Timestamp getLinkTime() {
        return linkTime;
    }

    public void setLinkTime(Timestamp linkTime) {
        this.linkTime = linkTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCommunicationId() {
        return communicationId;
    }

    public void setCommunicationId(Long communicationId) {
        this.communicationId = communicationId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCommunicationName() {
        return communicationName;
    }

    public void setCommunicationName(String communicationName) {
        this.communicationName = communicationName;
    }

}
