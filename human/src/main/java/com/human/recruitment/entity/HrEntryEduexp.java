package com.human.recruitment.entity;

import java.util.Date;

public class HrEntryEduexp {
    private Integer id;

    private Integer entryBaseId;

    private Date eduStartDate;

    private Date eduEndDate;

    private Integer eduIsGraduated;

    private String eduNationality;

    private String eduCollage;

    private Integer eduType;

    private Integer eduDocotor;

    private String eduMajor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntryBaseId() {
        return entryBaseId;
    }

    public void setEntryBaseId(Integer entryBaseId) {
        this.entryBaseId = entryBaseId;
    }

    public Date getEduStartDate() {
        return eduStartDate;
    }

    public void setEduStartDate(Date eduStartDate) {
        this.eduStartDate = eduStartDate;
    }

    public Date getEduEndDate() {
        return eduEndDate;
    }

    public void setEduEndDate(Date eduEndDate) {
        this.eduEndDate = eduEndDate;
    }

    public Integer getEduIsGraduated() {
        return eduIsGraduated;
    }

    public void setEduIsGraduated(Integer eduIsGraduated) {
        this.eduIsGraduated = eduIsGraduated;
    }

    public String getEduNationality() {
        return eduNationality;
    }

    public void setEduNationality(String eduNationality) {
        this.eduNationality = eduNationality == null ? null : eduNationality.trim();
    }

    public String getEduCollage() {
        return eduCollage;
    }

    public void setEduCollage(String eduCollage) {
        this.eduCollage = eduCollage == null ? null : eduCollage.trim();
    }

    public Integer getEduType() {
        return eduType;
    }

    public void setEduType(Integer eduType) {
        this.eduType = eduType;
    }

    public Integer getEduDocotor() {
        return eduDocotor;
    }

    public void setEduDocotor(Integer eduDocotor) {
        this.eduDocotor = eduDocotor;
    }

    public String getEduMajor() {
        return eduMajor;
    }

    public void setEduMajor(String eduMajor) {
        this.eduMajor = eduMajor == null ? null : eduMajor.trim();
    }
}