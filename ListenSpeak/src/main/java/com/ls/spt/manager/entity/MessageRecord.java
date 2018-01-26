package com.ls.spt.manager.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MessageRecord {
    private Long id;

    private String toUser;

    private String code;

    private String sendComment;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private String state;

    private String stateDesc;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser == null ? null : toUser.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSendComment() {
        return sendComment;
    }

    public void setSendComment(String sendComment) {
        this.sendComment = sendComment == null ? null : sendComment.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getStateDesc() {
        return stateDesc;
    }

    public void setStateDesc(String stateDesc) {
        this.stateDesc = stateDesc == null ? null : stateDesc.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}