package com.ls.spt.question.entity;

public class LstQuestionType {
    private Integer id;

    private String name;

    private String subject;

    private String lType;

    private String remark;

    private String isNeedGuide;

    private String isNeedParse;

    private String isNeedEssay;

    private Integer questionNum;

    private Integer status;
    
    private int mrContentTime;
    
    private int mrTopicTime;
    
    private int mrAnswerTime;
    private String mrZdmessage;
   
    public int getMrContentTime() {
        return mrContentTime;
    }

    public void setMrContentTime(int mrContentTime) {
        this.mrContentTime = mrContentTime;
    }

    public int getMrTopicTime() {
        return mrTopicTime;
    }

    public void setMrTopicTime(int mrTopicTime) {
        this.mrTopicTime = mrTopicTime;
    }

    public int getMrAnswerTime() {
        return mrAnswerTime;
    }

    public void setMrAnswerTime(int mrAnswerTime) {
        this.mrAnswerTime = mrAnswerTime;
    }

    public String getMrZdmessage() {
        return mrZdmessage;
    }

    public void setMrZdmessage(String mrZdmessage) {
        this.mrZdmessage = mrZdmessage;
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
        this.name = name == null ? null : name.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getlType() {
        return lType;
    }

    public void setlType(String lType) {
        this.lType = lType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsNeedGuide() {
        return isNeedGuide;
    }

    public void setIsNeedGuide(String isNeedGuide) {
        this.isNeedGuide = isNeedGuide == null ? null : isNeedGuide.trim();
    }

    public String getIsNeedParse() {
        return isNeedParse;
    }

    public void setIsNeedParse(String isNeedParse) {
        this.isNeedParse = isNeedParse == null ? null : isNeedParse.trim();
    }

    public String getIsNeedEssay() {
        return isNeedEssay;
    }

    public void setIsNeedEssay(String isNeedEssay) {
        this.isNeedEssay = isNeedEssay == null ? null : isNeedEssay.trim();
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}