package com.human.resume.entity;

import java.sql.Timestamp;

public class InterAppr {
    
    private Long resumeId;
    
    private String procInstId;
    
    private String flowCode;
    
    private String nodeName;
    
    private Timestamp receiveTime;
    
    private Timestamp endTime;
    
    private String resumeName;
    
    private String sex;
    
    private String telePhone;
    
    private String email;
    
    private String workTime;
    
    private String graSchool;
    
    private String highEdu;
    
    private String workName;
    
    private String companyName;
    
    private String deptName;
    
    private String apprUser;

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Timestamp getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Timestamp receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }


    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool;
    }

    public String getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
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

    public String getApprUser() {
        return apprUser;
    }

    public void setApprUser(String apprUser) {
        this.apprUser = apprUser;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }
    
}
