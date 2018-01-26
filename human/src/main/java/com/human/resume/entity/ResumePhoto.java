package com.human.resume.entity;
/**
 * 简历照片信息POJO
 * @author liuwei
 *
 */
public class ResumePhoto {
    private Long id;

    private Long resumeId;

    private String name;

    private String path;

    private Boolean isValid;
    
    private Boolean fastFlag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean getFastFlag() {
        return fastFlag;
    }

    public void setFastFlag(Boolean fastFlag) {
        this.fastFlag = fastFlag;
    }
    
    
    
    
    
}