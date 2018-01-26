package com.ls.spt.studentpc.student.entity;

import java.math.BigDecimal;

public class ZuoyeQuestion {
    
    private Integer studentId;
    
    private String classCode;
    
    private Integer zuoyeId;
    
    private Integer tmNum;
    
    private Integer questionId;
    
    private String questionType;
    
    private String zdmessage;
    
    private String topic;
    
    private Integer topicTime;
    
    private String content;
    
    private Integer contentTime;
    
    private Integer xh;
    
    private BigDecimal accuracy;
    
    private BigDecimal fluency;
    
    private BigDecimal integrity;
    
    private BigDecimal overall;
    
    private String isEnd;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public Integer getZuoyeId() {
        return zuoyeId;
    }

    public void setZuoyeId(Integer zuoyeId) {
        this.zuoyeId = zuoyeId;
    }

    public Integer getTmNum() {
        return tmNum;
    }

    public void setTmNum(Integer tmNum) {
        this.tmNum = tmNum;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getZdmessage() {
        return zdmessage;
    }

    public void setZdmessage(String zdmessage) {
        this.zdmessage = zdmessage;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getTopicTime() {
        return topicTime;
    }

    public void setTopicTime(Integer topicTime) {
        this.topicTime = topicTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentTime() {
        return contentTime;
    }

    public void setContentTime(Integer contentTime) {
        this.contentTime = contentTime;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public BigDecimal getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(BigDecimal accuracy) {
        this.accuracy = accuracy;
    }

    public BigDecimal getFluency() {
        return fluency;
    }

    public void setFluency(BigDecimal fluency) {
        this.fluency = fluency;
    }

    public BigDecimal getIntegrity() {
        return integrity;
    }

    public void setIntegrity(BigDecimal integrity) {
        this.integrity = integrity;
    }

    public BigDecimal getOverall() {
        return overall;
    }

    public void setOverall(BigDecimal overall) {
        this.overall = overall;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

}
