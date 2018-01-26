package com.human.resume.entity;

import java.util.Date;
/**
 * 简历校内职务信息POJO
 * @author liuwei
 *
 */
public class ResumeSchoolPost {
    public ResumeSchoolPost(Long id, Long resumeId, Date startTime, Date endTime, String postName, String describes) {
        super();
        this.id = id;
        this.resumeId = resumeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.postName = postName;
        this.describes = describes;
    }

    public ResumeSchoolPost() {
        super();
    }

    private Long id;

    private Long resumeId;

    private Date startTime;

    private Date endTime;

    private String postName;

    private String describes;

    private Boolean isValid;

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

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
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
}