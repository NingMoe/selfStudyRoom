package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class ClassMatch {
    
    private Long id;

    private Long ruleId;
   
    /*
     * 学员姓名
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员姓名")
    private String name;

    /*
     * 学员号
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员号")
    private String code;
    
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班班号")
    private String cClassCode;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="原班班号")
    private String oClassCode;

    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班班级名称")
    private String cClassName;
    

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班校区")
    private String cSchoolAreaName;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班年级")
    private String cGrade;
       
           
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班科目")
    private String cSubject;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="续班教师")
    private String cTeacherName;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="原班班级名称")
    private String oClassName;
    

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="原班校区")
    private String oSchoolAreaName;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="原班科目")
    private String oSubject;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="原班教师")
    private String oTeacherName;
    
    /*
     * 原班年级
     */
    private String oGrade;
            
    /*
     * 原班部门
     */
    private String oDeptName;

    /*
     * 原班是否扩科
     */
    private String oKuokeFlag;

    /*
     * 原班是否是否尖子
     */
    private String oTopFlag;
    
    /*
     * 原班上课时间
     */
    private String oClassSprintTime;

    /*
     * 原班课程开始时间
     */
    private String oClassStartDate;
    /*
     * 原班课程结束时间
     */
    private String oClassEndDate;
    
       
    /*
     * 续班部门
     */
    private String cDeptName;

    /*
     * 续班是否扩科
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="是否续班")
    private String cKuokeFlag;

    /*
     * 续班是否是否尖子
     */
    private String cTopFlag;
    
    /*
     * 续班上课时间
     */
    private String cClassSprintTime;

    /*
     * 续班课程开始时间
     */
    private String cClassStartDate;
    /*
     * 续班课程结束时间
     */
    private String cClassEndDate;
    
        
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="操作时间")
    private Date createTime;
    
    
    private String createUser; 
    /**
     * 配课成功与否  1 成功  2 失败
     */
    private  String distributionFlag;
    
    /**
     * 配课情况
     */
    private String courseAllocation;
    
    /**
     * 错误/警告原因
     */
    private String distributionReason;
    
    
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getoClassCode() {
        return oClassCode;
    }
    public void setoClassCode(String oClassCode) {
        this.oClassCode = oClassCode;
    }

    public String getoClassName() {
        return oClassName;
    }
    public void setoClassName(String oClassName) {
        this.oClassName = oClassName;
    }
    public String getcClassName() {
        return cClassName;
    }
    public void setcClassName(String cClassName) {
        this.cClassName = cClassName;
    }
    public String getoSchoolAreaName() {
        return oSchoolAreaName;
    }
    public void setoSchoolAreaName(String oSchoolAreaName) {
        this.oSchoolAreaName = oSchoolAreaName;
    }
    public String getoTeacherName() {
        return oTeacherName;
    }
    public void setoTeacherName(String oTeacherName) {
        this.oTeacherName = oTeacherName;
    }
    public String getoGrade() {
        return oGrade;
    }
    public void setoGrade(String oGrade) {
        this.oGrade = oGrade;
    }
    public String getoSubject() {
        return oSubject;
    }
    public void setoSubject(String oSubject) {
        this.oSubject = oSubject;
    }
    public String getoDeptName() {
        return oDeptName;
    }
    public void setoDeptName(String oDeptName) {
        this.oDeptName = oDeptName;
    }
    public String getoKuokeFlag() {
        return oKuokeFlag;
    }
    public void setoKuokeFlag(String oKuokeFlag) {
        this.oKuokeFlag = oKuokeFlag;
    }
    public String getoTopFlag() {
        return oTopFlag;
    }
    public void setoTopFlag(String oTopFlag) {
        this.oTopFlag = oTopFlag;
    }
    public String getoClassSprintTime() {
        return oClassSprintTime;
    }
    public void setoClassSprintTime(String oClassSprintTime) {
        this.oClassSprintTime = oClassSprintTime;
    }
    public String getoClassStartDate() {
        return oClassStartDate;
    }
    public void setoClassStartDate(String oClassStartDate) {
        this.oClassStartDate = oClassStartDate;
    }
    public String getoClassEndDate() {
        return oClassEndDate;
    }
    public void setoClassEndDate(String oClassEndDate) {
        this.oClassEndDate = oClassEndDate;
    }
    public String getcClassCode() {
        return cClassCode;
    }
    public void setcClassCode(String cClassCode) {
        this.cClassCode = cClassCode;
    }

    public String getcSchoolAreaName() {
        return cSchoolAreaName;
    }
    public void setcSchoolAreaName(String cSchoolAreaName) {
        this.cSchoolAreaName = cSchoolAreaName;
    }
    public String getcTeacherName() {
        return cTeacherName;
    }
    public void setcTeacherName(String cTeacherName) {
        this.cTeacherName = cTeacherName;
    }
    public String getcGrade() {
        return cGrade;
    }
    public void setcGrade(String cGrade) {
        this.cGrade = cGrade;
    }
    public String getcSubject() {
        return cSubject;
    }
    public void setcSubject(String cSubject) {
        this.cSubject = cSubject;
    }
    public String getcDeptName() {
        return cDeptName;
    }
    public void setcDeptName(String cDeptName) {
        this.cDeptName = cDeptName;
    }
    public String getcKuokeFlag() {
        return cKuokeFlag;
    }
    public void setcKuokeFlag(String cKuokeFlag) {
        this.cKuokeFlag = cKuokeFlag;
    }
    public String getcTopFlag() {
        return cTopFlag;
    }
    public void setcTopFlag(String cTopFlag) {
        this.cTopFlag = cTopFlag;
    }
    public String getcClassSprintTime() {
        return cClassSprintTime;
    }
    public void setcClassSprintTime(String cClassSprintTime) {
        this.cClassSprintTime = cClassSprintTime;
    }
    public String getcClassStartDate() {
        return cClassStartDate;
    }
    public void setcClassStartDate(String cClassStartDate) {
        this.cClassStartDate = cClassStartDate;
    }
    public String getcClassEndDate() {
        return cClassEndDate;
    }
    public void setcClassEndDate(String cClassEndDate) {
        this.cClassEndDate = cClassEndDate;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getDistributionFlag() {
        return distributionFlag;
    }
    public void setDistributionFlag(String distributionFlag) {
        this.distributionFlag = distributionFlag;
    }
    public String getCreateUser() {
        return createUser;
    }
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    public String getCourseAllocation() {
        return courseAllocation;
    }
    public void setCourseAllocation(String courseAllocation) {
        this.courseAllocation = courseAllocation;
    }
    public String getDistributionReason() {
        return distributionReason;
    }
    public void setDistributionReason(String distributionReason) {
        this.distributionReason = distributionReason;
    }

    
    
}
