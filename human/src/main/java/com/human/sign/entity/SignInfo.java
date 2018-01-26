package com.human.sign.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 签到明细pojo
 * @author liuwei63
 *
 */
public class SignInfo {
    private Long id;

    private Long activityId;

    @ExportTitleAnnotation()
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date signTime;
    
    @ExportTitleAnnotation()
    private String studentCode;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="姓名")
    private String name;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手机号码")
    private String telephone;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="身份证号码")
    private String cardNo;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="性别")
    private String sex;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="邮箱")
    private String email;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="部门/学校")
    private String deptorschool;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="日期")
    private String exportDate;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="备用文本1")
    private String text1;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="备用文本2")
    private String text2;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="备用文本3")
    private String text3;

    private String isSign;
    
    @ExportTitleAnnotation()
    private String classCode;
    
    @ExportTitleAnnotation()
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @ExportTitleAnnotation()
    private String createUser;

    private String updateUser;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String isValid;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDeptorschool() {
        return deptorschool;
    }

    public void setDeptorschool(String deptorschool) {
        this.deptorschool = deptorschool == null ? null : deptorschool.trim();
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate == null ? null : exportDate.trim();
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1 == null ? null : text1.trim();
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2 == null ? null : text2.trim();
    }

    public String getText3() {
        return text3;
    }

    public void setText3(String text3) {
        this.text3 = text3 == null ? null : text3.trim();
    }

    public String getIsSign() {
        return isSign;
    }

    public void setIsSign(String isSign) {
        this.isSign = isSign == null ? null : isSign.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    
    
}