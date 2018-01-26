package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SchoolAreaMatch {
    private Long id;

    private Long ruleId;
    
    private Long schoolAreaId;

    private Long matchId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    private String name;
    
    private String matchName;//临近校区
    
    /**
     * 用于查询的时间
     * @return
     */
    private String deliveryDateStart;
    
    private String deliveryDateEnd;
    
    private long[]matchIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    
    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getSchoolAreaId() {
        return schoolAreaId;
    }

    public void setSchoolAreaId(Long schoolAreaId) {
        this.schoolAreaId = schoolAreaId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(String deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public String getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(String deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public long[] getMatchIds() {
        return matchIds;
    }

    public void setMatchIds(long[] matchIds) {
        this.matchIds = matchIds;
    }
    
    
    
}