package com.human.continuedClass.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class SendcardMailRecord {
    private Long id;

    private Long ruleId;

    private String companyId;

    private String sender;

    private String recipientsTo;

    private String attchment;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    
    /**
     * 下面两个工查询使用
     */
    private String startTime;
    
    private String endTime;

    private String sendComment;

    private String classCode;

    private String state;

    private String resultDesc;
    
    private String ruleName;
    
    private String teacherName;
    
    private String schoolAreaName;
    
    private String grade;
    
    private String subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getRecipientsTo() {
        return recipientsTo;
    }

    public void setRecipientsTo(String recipientsTo) {
        this.recipientsTo = recipientsTo == null ? null : recipientsTo.trim();
    }

    public String getAttchment() {
        return attchment;
    }

    public void setAttchment(String attchment) {
        this.attchment = attchment == null ? null : attchment.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendComment() {
        return sendComment;
    }

    public void setSendComment(String sendComment) {
        this.sendComment = sendComment == null ? null : sendComment.trim();
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSchoolAreaName() {
        return schoolAreaName;
    }

    public void setSchoolAreaName(String schoolAreaName) {
        this.schoolAreaName = schoolAreaName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    
        
}