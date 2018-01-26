package com.human.resume.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 简历教育经历信息POJO
 * @author liuwei
 *
 */
public class ResumeEducationHistory {
    private Long id;

    private Long resumeId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String schoolName;

    private Boolean is211;

    private Boolean is985;

    private String education;

    private String major;

    private String describes;

    private Boolean isValid;
    
    public ResumeEducationHistory() {
        super();
    }

    public ResumeEducationHistory(Long id, Long resumeId, Date startTime, Date endTime, String schoolName, Boolean is211, Boolean is985, String education, String major, String describes,
            String entranceType) {
        super();
        this.id = id;
        this.resumeId = resumeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.schoolName = schoolName;
        this.is211 = is211;
        this.is985 = is985;
        this.education = education;
        this.major = major;
        this.describes = describes;
        this.entranceType = entranceType;
    }

    /*
     * 统招类型
     */
    private String entranceType;

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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getStartTime(){
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Boolean getIs211() {
        return is211;
    }

    public void setIs211(Boolean is211) {
        this.is211 = is211;
    }

    public Boolean getIs985() {
        return is985;
    }

    public void setIs985(Boolean is985) {
        this.is985 = is985;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getEntranceType() {
        return entranceType;
    }

    public void setEntranceType(String entranceType) {
        this.entranceType = entranceType;
    }
    
}