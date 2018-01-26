package com.human.resume.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 简历项目经验信息POJO
 * @author liuwei
 *
 */
public class ResumeProjectExperience {
    public ResumeProjectExperience() {
        super();
    }

    public ResumeProjectExperience(Long id, Long resumeId, Date startTime, Date endTime, String projectName, String responsibilityDescribe, String projectDescribe, String companyName) {
        super();
        this.id = id;
        this.resumeId = resumeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectName = projectName;
        this.responsibilityDescribe = responsibilityDescribe;
        this.projectDescribe = projectDescribe;
        this.companyName = companyName;
    }

    private Long id;

    private Long resumeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String projectName;

    private String responsibilityDescribe;

    private String projectDescribe;

    private String projectPosition;

    private Boolean isValid;
    
    private String companyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getResponsibilityDescribe() {
        return responsibilityDescribe;
    }

    public void setResponsibilityDescribe(String responsibilityDescribe) {
        this.responsibilityDescribe = responsibilityDescribe == null ? null : responsibilityDescribe.trim();
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe == null ? null : projectDescribe.trim();
    }

    public String getProjectPosition() {
        return projectPosition;
    }

    public void setProjectPosition(String projectPosition) {
        this.projectPosition = projectPosition == null ? null : projectPosition.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    
}