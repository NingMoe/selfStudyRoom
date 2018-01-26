package com.human.recruitment.entity;

import java.util.Date;

public class HrEntryFamily {
    private Integer id;

    private Integer entryBaseId;

    private Integer memberRelation;

    private String firstName;

    private String secondName;

    private Integer memberSex;

    private Date borthDate;

    private String memberPhone;

    private String workUnit;

    private String memberJob;

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

    public Integer getMemberRelation() {
        return memberRelation;
    }

    public void setMemberRelation(Integer memberRelation) {
        this.memberRelation = memberRelation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName == null ? null : firstName.trim();
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName == null ? null : secondName.trim();
    }

    public Integer getMemberSex() {
        return memberSex;
    }

    public void setMemberSex(Integer memberSex) {
        this.memberSex = memberSex;
    }

    public Date getBorthDate() {
        return borthDate;
    }

    public void setBorthDate(Date borthDate) {
        this.borthDate = borthDate;
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit == null ? null : workUnit.trim();
    }

    public String getMemberJob() {
        return memberJob;
    }

    public void setMemberJob(String memberJob) {
        this.memberJob = memberJob == null ? null : memberJob.trim();
    }
}