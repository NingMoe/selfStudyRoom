package com.human.continuedClass.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 推荐班级实体
 * @author liuwei63
 *
 */
public class RecommendClass {
    private Long id;

    private Long ruleId;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员姓名")
    private String name;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员号")
    private String code;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="推荐班号")
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