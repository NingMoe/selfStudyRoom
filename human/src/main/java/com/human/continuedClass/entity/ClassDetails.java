package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ClassDetails {
    private Long id;

    private Integer schoolId;

    private String classCode;

    private String className;

    private Integer studentMaxCount;

    private Integer studentCurrentCount;

    private String teacherCode;

    private String teacherName;

    private String classSprintTime;

    private String classAddress;

    private String classStartDate;

    private String classEndDate;

    private String openTime;

    private String roomCode;

    private String fee;

    private String quarterCode;

    private String gradeName;

    private String subjectName;

    private Integer souke;

    private Integer classStatus;

    private Boolean isnet;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public Integer getStudentMaxCount() {
        return studentMaxCount;
    }

    public void setStudentMaxCount(Integer studentMaxCount) {
        this.studentMaxCount = studentMaxCount;
    }

    public Integer getStudentCurrentCount() {
        return studentCurrentCount;
    }

    public void setStudentCurrentCount(Integer studentCurrentCount) {
        this.studentCurrentCount = studentCurrentCount;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode == null ? null : teacherCode.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getClassSprintTime() {
        return classSprintTime;
    }

    public void setClassSprintTime(String classSprintTime) {
        this.classSprintTime = classSprintTime == null ? null : classSprintTime.trim();
    }

    public String getClassAddress() {
        return classAddress;
    }

    public void setClassAddress(String classAddress) {
        this.classAddress = classAddress == null ? null : classAddress.trim();
    }

    public String getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(String classStartDate) {
        this.classStartDate = classStartDate == null ? null : classStartDate.trim();
    }

    public String getClassEndDate() {
        return classEndDate;
    }

    public void setClassEndDate(String classEndDate) {
        this.classEndDate = classEndDate == null ? null : classEndDate.trim();
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee == null ? null : fee.trim();
    }

    public String getQuarterCode() {
        return quarterCode;
    }

    public void setQuarterCode(String quarterCode) {
        this.quarterCode = quarterCode == null ? null : quarterCode.trim();
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName == null ? null : gradeName.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public Integer getSouke() {
        return souke;
    }

    public void setSouke(Integer souke) {
        this.souke = souke;
    }

    public Integer getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(Integer classStatus) {
        this.classStatus = classStatus;
    }

    public Boolean getIsnet() {
        return isnet;
    }

    public void setIsnet(Boolean isnet) {
        this.isnet = isnet;
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
}