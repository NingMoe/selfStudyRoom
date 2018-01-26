package com.ls.spt.lstClassTest.entity;

import java.util.Date;

public class LstStudentTest {
    private Integer id;
    private String name;
    private Long studentId;

    private Integer testId;

    private Integer paperId;

    private String classCode;

    private String className;

    private String testName;

    private Integer testNum;

    private Integer testEndNum;

    private Date endTime;

    private Date openTime;

    private Date submissionTime;

    private Integer continueTime;

    private String createUser;

    private Date creaateTime;
    private int status;
    
    private Double accuracySogou;

    private Double fluencySogou;

    private Double integritySogou;

    private Double overallSogou;

    private Double accuracyTeacher;

    private Double fluencyTeacher;

    private Double integrityTeacher;

    private Double overallTeacher;
    
    private Double percent;
    private int datiNum ;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDatiNum() {
        return datiNum;
    }

    public void setDatiNum(int datiNum) {
        this.datiNum = datiNum;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getAccuracySogou() {
        return accuracySogou;
    }

    public void setAccuracySogou(Double accuracySogou) {
        this.accuracySogou = accuracySogou;
    }

    public Double getFluencySogou() {
        return fluencySogou;
    }

    public void setFluencySogou(Double fluencySogou) {
        this.fluencySogou = fluencySogou;
    }

    public Double getIntegritySogou() {
        return integritySogou;
    }

    public void setIntegritySogou(Double integritySogou) {
        this.integritySogou = integritySogou;
    }

    public Double getOverallSogou() {
        return overallSogou;
    }

    public void setOverallSogou(Double overallSogou) {
        this.overallSogou = overallSogou;
    }

    public Double getAccuracyTeacher() {
        return accuracyTeacher;
    }

    public void setAccuracyTeacher(Double accuracyTeacher) {
        this.accuracyTeacher = accuracyTeacher;
    }

    public Double getFluencyTeacher() {
        return fluencyTeacher;
    }

    public void setFluencyTeacher(Double fluencyTeacher) {
        this.fluencyTeacher = fluencyTeacher;
    }

    public Double getIntegrityTeacher() {
        return integrityTeacher;
    }

    public void setIntegrityTeacher(Double integrityTeacher) {
        this.integrityTeacher = integrityTeacher;
    }

    public Double getOverallTeacher() {
        return overallTeacher;
    }

    public void setOverallTeacher(Double overallTeacher) {
        this.overallTeacher = overallTeacher;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
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

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public Integer getTestNum() {
        return testNum;
    }

    public void setTestNum(Integer testNum) {
        this.testNum = testNum;
    }

    public Integer getTestEndNum() {
        return testEndNum;
    }

    public void setTestEndNum(Integer testEndNum) {
        this.testEndNum = testEndNum;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    public Integer getContinueTime() {
        return continueTime;
    }

    public void setContinueTime(Integer continueTime) {
        this.continueTime = continueTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreaateTime() {
        return creaateTime;
    }

    public void setCreaateTime(Date creaateTime) {
        this.creaateTime = creaateTime;
    }
}