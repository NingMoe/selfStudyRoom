package com.ls.spt.manager.entity;


import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Users {
    
    private Long id;

    private String userName;//用户登录账号(手机号)

    private String userPassword;//用户的密码

    private String userRealName;//用户的中文姓名

    private Integer status;//状态

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Timestamp createTime;

    private Long createUserId;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Timestamp updateStatusTime;;
    
    private Long updateStatusUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getUpdateStatusTime() {
        return updateStatusTime;
    }

    public void setUpdateStatusTime(Timestamp updateStatusTime) {
        this.updateStatusTime = updateStatusTime;
    }

    public Long getUpdateStatusUserId() {
        return updateStatusUserId;
    }

    public void setUpdateStatusUserId(Long updateStatusUserId) {
        this.updateStatusUserId = updateStatusUserId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
    
}