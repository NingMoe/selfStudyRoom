package com.human.teacherservice.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class LibBookMailRecord {
    private Long id;

    private String companyId;

    private Integer bookeId;

    private String sender;

    private String recipientsTo;

    private String recipientsName;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private String sendComment;

    private Integer state;

    private String resultDesc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Integer getBookeId() {
        return bookeId;
    }

    public void setBookeId(Integer bookeId) {
        this.bookeId = bookeId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getRecipientsTo() {
        return recipientsTo;
    }

    public void setRecipientsTo(String recipientsTo) {
        this.recipientsTo = recipientsTo == null ? null : recipientsTo.trim();
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName == null ? null : recipientsName.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendComment() {
        return sendComment;
    }

    public void setSendComment(String sendComment) {
        this.sendComment = sendComment == null ? null : sendComment.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }
}