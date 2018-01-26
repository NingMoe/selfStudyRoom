package com.ls.spt.lstClassTest.entity;

public class LstTestStudentAnswer {
    private Integer id;

    private Integer studentId;

    private Integer testId;
    private String name;
    private Integer questionId;

    private String studentAnswer;

    private Double accuracySogou;

    private Double fluencySogou;

    private Double integritySogou;

    private Double overallSogou;

    private Double accuracyTeacher;

    private Double fluencyTeacher;

    private Double integrityTeacher;

    private Double overallTeacher;
    //班号
    private String classCode;
    private String flag;
    private String testName;
    private String className;
    private int status;
    private int xh;
    private int paperId;
    private int Total_question_num;
    private Double accuracyPercent;

    private Double fluencyPercent;

    private Double integrityPercent;
    
    private String answerComment;
    
    public String getAnswerComment() {
        return answerComment;
    }

    public void setAnswerComment(String answerComment) {
        this.answerComment = answerComment;
    }

    public Double getAccuracyPercent() {
        return accuracyPercent;
    }

    public void setAccuracyPercent(Double accuracyPercent) {
        this.accuracyPercent = accuracyPercent;
    }

    public Double getFluencyPercent() {
        return fluencyPercent;
    }

    public void setFluencyPercent(Double fluencyPercent) {
        this.fluencyPercent = fluencyPercent;
    }

    public Double getIntegrityPercent() {
        return integrityPercent;
    }

    public void setIntegrityPercent(Double integrityPercent) {
        this.integrityPercent = integrityPercent;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getTotal_question_num() {
        return Total_question_num;
    }

    public void setTotal_question_num(int total_question_num) {
        Total_question_num = total_question_num;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer == null ? null : studentAnswer.trim();
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

}