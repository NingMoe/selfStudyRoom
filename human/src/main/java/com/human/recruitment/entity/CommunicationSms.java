package com.human.recruitment.entity;

import java.sql.Timestamp;

public class CommunicationSms {

    private Long smsId;
    
    private String smsTo;
    
    private String smsUser;
    
    private String smsUserName;
    
    private String smsComment;
    
    private Integer smsStatus;
    
    private Long seekerId;
    
    private String seekerName;
    
    private String memo;
    
    private Timestamp sendTime;

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getSmsTo() {
        return smsTo;
    }

    public void setSmsTo(String smsTo) {
        this.smsTo = smsTo;
    }

    public String getSmsUser() {
        return smsUser;
    }

    public void setSmsUser(String smsUser) {
        this.smsUser = smsUser;
    }

    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }

    public String getSmsComment() {
        return smsComment;
    }

    public void setSmsComment(String smsComment) {
        this.smsComment = smsComment;
    }

    public Integer getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(Integer smsStatus) {
        this.smsStatus = smsStatus;
    }

    public Long getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(Long seekerId) {
        this.seekerId = seekerId;
    }

    public String getSeekerName() {
        return seekerName;
    }

    public void setSeekerName(String seekerName) {
        this.seekerName = seekerName;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    
}
