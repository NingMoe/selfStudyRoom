package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 班级信息实体
 * @author liuwei63
 *
 */
public class ClassInformation {
    private Long id;

    private Long ruleId;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="班号")
    private String classCode;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="班级名称")
    private String className;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="校区")
    private String schoolAreaName;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="年级")
    private String grade;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="科目")
    private String subject;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="教师")
    private String teacherName;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="部门")
    private String deptName;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="是否扩科")
    private String kuokeFlag;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="是否尖子")
    private String topFlag;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    private int type;
    
    private String classStartDate;

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

    public String getSchoolAreaName() {
        return schoolAreaName;
    }

    public void setSchoolAreaName(String schoolAreaName) {
        this.schoolAreaName = schoolAreaName == null ? null : schoolAreaName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
   

    public String getKuokeFlag() {
        return kuokeFlag;
    }

    public void setKuokeFlag(String kuokeFlag) {
        this.kuokeFlag = kuokeFlag== null ? null : kuokeFlag.trim();
    }

    public String getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(String topFlag) {
        this.topFlag = topFlag== null ? null : topFlag.trim();
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClassStartDate() {
        return classStartDate;
    }

    public void setClassStartDate(String classStartDate) {
        this.classStartDate = classStartDate;
    }
    
    
}