package com.human.recruitment.entity;

import java.sql.Timestamp;
import java.util.Date;

public class HrPositiveRecord {
    private Integer id;
    
    private String empId;

    private String userId;
    
    private String name;
    
    private String idCard;
    
    private String company;
    
    private String companyName;
    
    private String dept;
    
    private String deptName;

    private Integer positionId;
    
    private String positionName;

    private String graSchool;

    private String highEdu;

    private String major;

    private String phone;
    
    private String extendPhone;
    
    private Date joinDate;

    private Timestamp interviewTime;

    private String interviewer;
    
    private String interviewerName;

    private String interviewLocation;

    private String interviewRemark;

    private Integer msStatus;
    
    private String joinDate0;
    
    private String joinDate1;
    
    private String interviewTime0;
    
    private String interviewTime1;
    
    private String insideRecommend;
    
    private String recommendName;
    
    private String insideRelation;
    
    /**
     * 操作人所属学校
     */
    private String loginCompany;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool == null ? null : graSchool.trim();
    }

    public String getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu == null ? null : highEdu.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Timestamp getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Timestamp interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer == null ? null : interviewer.trim();
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation == null ? null : interviewLocation.trim();
    }

    public String getInterviewRemark() {
        return interviewRemark;
    }

    public void setInterviewRemark(String interviewRemark) {
        this.interviewRemark = interviewRemark == null ? null : interviewRemark.trim();
    }

    public Integer getMsStatus() {
        return msStatus;
    }

    public void setMsStatus(Integer msStatus) {
        this.msStatus = msStatus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinDate0() {
        return joinDate0;
    }

    public void setJoinDate0(String joinDate0) {
        this.joinDate0 = joinDate0;
    }

    public String getJoinDate1() {
        return joinDate1;
    }

    public void setJoinDate1(String joinDate1) {
        this.joinDate1 = joinDate1;
    }

    public String getInterviewTime0() {
        return interviewTime0;
    }

    public void setInterviewTime0(String interviewTime0) {
        this.interviewTime0 = interviewTime0;
    }

    public String getInterviewTime1() {
        return interviewTime1;
    }

    public void setInterviewTime1(String interviewTime1) {
        this.interviewTime1 = interviewTime1;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getInsideRecommend() {
        return insideRecommend;
    }

    public void setInsideRecommend(String insideRecommend) {
        this.insideRecommend = insideRecommend;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getInsideRelation() {
        return insideRelation;
    }

    public void setInsideRelation(String insideRelation) {
        this.insideRelation = insideRelation;
    }

    public String getInterviewerName() {
        return interviewerName;
    }

    public void setInterviewerName(String interviewerName) {
        this.interviewerName = interviewerName;
    }

    public String getLoginCompany() {
        return loginCompany;
    }

    public void setLoginCompany(String loginCompany) {
        this.loginCompany = loginCompany;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getExtendPhone() {
        return extendPhone;
    }

    public void setExtendPhone(String extendPhone) {
        this.extendPhone = extendPhone;
    }
    
}