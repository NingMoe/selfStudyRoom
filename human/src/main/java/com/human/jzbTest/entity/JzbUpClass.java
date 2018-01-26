package com.human.jzbTest.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JzbUpClass {
    private Long id;

    private Long gradeSubjectId;

    private Integer nschoolid;

    private String swindowperiodid;

    private String swindowperiodname;

    private String sstageid;

    private String sstagename;
    
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

    public Long getGradeSubjectId() {
        return gradeSubjectId;
    }

    public void setGradeSubjectId(Long gradeSubjectId) {
        this.gradeSubjectId = gradeSubjectId;
    }

    public Integer getNschoolid() {
        return nschoolid;
    }

    public void setNschoolid(Integer nschoolid) {
        this.nschoolid = nschoolid;
    }

    public String getSwindowperiodid() {
        return swindowperiodid;
    }

    public void setSwindowperiodid(String swindowperiodid) {
        this.swindowperiodid = swindowperiodid == null ? null : swindowperiodid.trim();
    }

    public String getSwindowperiodname() {
        return swindowperiodname;
    }

    public void setSwindowperiodname(String swindowperiodname) {
        this.swindowperiodname = swindowperiodname == null ? null : swindowperiodname.trim();
    }

    public String getSstageid() {
        return sstageid;
    }

    public void setSstageid(String sstageid) {
        this.sstageid = sstageid == null ? null : sstageid.trim();
    }

    public String getSstagename() {
        return sstagename;
    }

    public void setSstagename(String sstagename) {
        this.sstagename = sstagename == null ? null : sstagename.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }
}