package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class CombinationClass {
    private Long id;

    private Long ruleId;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="校区")
    private String schoolAreaName;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="年级")
    private String grade;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="套餐组合")
    private String combinationData;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;

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

    public String getSchoolAreaName() {
        return schoolAreaName;
    }

    public void setSchoolAreaName(String schoolAreaName) {
        this.schoolAreaName = schoolAreaName == null ? null : schoolAreaName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getCombinationData() {
        return combinationData;
    }

    public void setCombinationData(String combinationData) {
        this.combinationData = combinationData == null ? null : combinationData.trim();
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
}