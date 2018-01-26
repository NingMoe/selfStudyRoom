package com.human.resume.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 简历工作经历信息POJO
 * @author liuwei
 *
 */
public class ResumeWorkHistory {
    
    public ResumeWorkHistory(Long id, Long resumeId, Date startTime, Date endTime, String companyName, String workTime, String workProperty, String companyScale, String department, String position,
            String salary, String describes, String leaveReason) {
        this.id = id;
        this.resumeId = resumeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.companyName = companyName;
        this.workTime = workTime;
        this.workProperty = workProperty;
        this.companyScale = companyScale;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.describes = describes;
        this.leaveReason = leaveReason;
    }

    public ResumeWorkHistory() {
    }

    private Long id;

    private Long resumeId;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String companyName;

    private String workTime;

    private String workProperty;

    private String companyScale;

    private String department;

    private String position;

    private String salary;

    private String describes;

    private String leaveReason;

    private Boolean isValid;
    
    private String workType;
    
    private Long minSalary;
    
    private Long maxSalary;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    
    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getWorkProperty() {
        return workProperty;
    }

    public void setWorkProperty(String workProperty) {
        this.workProperty = workProperty == null ? null : workProperty.trim();
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale == null ? null : companyScale.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason == null ? null : leaveReason.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }
    
    
}