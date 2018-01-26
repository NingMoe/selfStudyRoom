package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 续班规则实体
 * @author liuwei63
 *
 */
public class ContinuedClassRule {
    private Long id;

    private String ruleName;

    private String deptName;

    private Integer ruleSort;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    /**
     * 用于查询的时间
     * @return
     */
    private String deliveryDateStart;
    
    private String deliveryDateEnd;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getRuleSort() {
        return ruleSort;
    }

    public void setRuleSort(Integer ruleSort) {
        this.ruleSort = ruleSort;
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
    
    
}