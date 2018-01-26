package com.human.jzbTest.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JzbSchool {
    private Long id;

    private String schoolCompanyId;

    private String schoolName;

    private Long schoolProvince;
    
    private String schoolProvinceName;

    private Long schoolCity;
    
    private String schoolCityName;

    private Long schoolArea;
    
    private String schoolAreaName;
    
    private Integer schoolType;

    private String schoolAddr;

    private String schoolUrl;

    private String schoolTel;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    private Boolean isValid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolCompanyId() {
        return schoolCompanyId;
    }

    public void setSchoolCompanyId(String schoolCompanyId) {
        this.schoolCompanyId = schoolCompanyId == null ? null : schoolCompanyId.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Long getSchoolProvince() {
        return schoolProvince;
    }

    public void setSchoolProvince(Long schoolProvince) {
        this.schoolProvince = schoolProvince;
    }

    public Long getSchoolCity() {
        return schoolCity;
    }

    public void setSchoolCity(Long schoolCity) {
        this.schoolCity = schoolCity;
    }

    public Long getSchoolArea() {
        return schoolArea;
    }

    public void setSchoolArea(Long schoolArea) {
        this.schoolArea = schoolArea;
    }

    public Integer getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(Integer schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolAddr() {
        return schoolAddr;
    }

    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr == null ? null : schoolAddr.trim();
    }

    public String getSchoolUrl() {
        return schoolUrl;
    }

    public void setSchoolUrl(String schoolUrl) {
        this.schoolUrl = schoolUrl == null ? null : schoolUrl.trim();
    }

    public String getSchoolTel() {
        return schoolTel;
    }

    public void setSchoolTel(String schoolTel) {
        this.schoolTel = schoolTel == null ? null : schoolTel.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
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

    public String getSchoolProvinceName() {
        return schoolProvinceName;
    }

    public void setSchoolProvinceName(String schoolProvinceName) {
        this.schoolProvinceName = schoolProvinceName;
    }

    public String getSchoolCityName() {
        return schoolCityName;
    }

    public void setSchoolCityName(String schoolCityName) {
        this.schoolCityName = schoolCityName;
    }

    public String getSchoolAreaName() {
        return schoolAreaName;
    }

    public void setSchoolAreaName(String schoolAreaName) {
        this.schoolAreaName = schoolAreaName;
    }
    
    
    
    
    
    
}