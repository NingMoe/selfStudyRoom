package com.human.jzbTest.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class JzbQuestionPaper implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String openId;

    private String name;

    private String phone;

    private Integer grade;
    
    private String gradeName;

    private String classtype;

    private String subject;
    
    private String subjectName;

    private Integer area;

    private Integer school;

    private Integer monthConfigId;
    
    private Integer mainConfigId;
    
    private String paperName;
    
    private Integer paperTime;
    
    private Integer totalNum;
    
    private Integer dayTimes;
    
    private Integer monthTimes;
    
    private Integer totalTimes;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    private Integer isPass;
    
    private String isPassName;
    
    private String status;
    
    private String isExistClass;
    
    private Integer xdf;
    
    private String xdfNo;
    
    private String bmCodes;
    
    private String realBmCodes;
    
    
    
    public String getBmCodes() {
        return bmCodes;
    }

    public void setBmCodes(String bmCodes) {
        this.bmCodes = bmCodes;
    }

    public String getRealBmCodes() {
        return realBmCodes;
    }

    public void setRealBmCodes(String realBmCodes) {
        this.realBmCodes = realBmCodes;
    }

    public String getXdfNo() {
        return xdfNo;
    }

    public void setXdfNo(String xdfNo) {
        this.xdfNo = xdfNo;
    }

    private String messageId;
    
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Integer getXdf() {
        return xdf;
    }

    public void setXdf(Integer xdf) {
        this.xdf = xdf;
    }

    public String getIsExistClass() {
        return isExistClass;
    }

    public void setIsExistClass(String isExistClass) {
        this.isExistClass = isExistClass;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Integer getDayTimes() {
        return dayTimes;
    }

    public void setDayTimes(Integer dayTimes) {
        this.dayTimes = dayTimes;
    }

    public Integer getMonthTimes() {
        return monthTimes;
    }

    public void setMonthTimes(Integer monthTimes) {
        this.monthTimes = monthTimes;
    }

    public Integer getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(Integer totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Integer paperTime) {
        this.paperTime = paperTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public Integer getMainConfigId() {
        return mainConfigId;
    }

    public void setMainConfigId(Integer mainConfigId) {
        this.mainConfigId = mainConfigId;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getClasstype() {
        return classtype;
    }

    public void setClasstype(String classtype) {
        this.classtype = classtype == null ? null : classtype.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getSchool() {
        return school;
    }

    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getMonthConfigId() {
        return monthConfigId;
    }

    public void setMonthConfigId(Integer monthConfigId) {
        this.monthConfigId = monthConfigId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsPassName() {
        return isPassName;
    }

    public void setIsPassName(String isPassName) {
        this.isPassName = isPassName;
    }
    
    
}