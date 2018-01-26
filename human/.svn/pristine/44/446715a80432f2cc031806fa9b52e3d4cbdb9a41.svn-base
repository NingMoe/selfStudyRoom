package com.human.xdfStudent.entity;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

/**
 * 新东方学员信息实体
 * @author liuwei63
 */
public class XdfStudentInfo implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 571666792700933398L;

    private Long id;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学员号")
    private String studentCode;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="所属学校")
    private String schoolName;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手机号码1")
    private String telephone1;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手机号码2")
    private String telephone2;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createUser;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    private String studentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode == null ? null : studentCode.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getTelephone1() {
        return telephone1;
    }

    public void setTelephone1(String telephone1) {
        this.telephone1 = telephone1 == null ? null : telephone1.trim();
    }

    public String getTelephone2() {
        return telephone2;
    }

    public void setTelephone2(String telephone2) {
        this.telephone2 = telephone2 == null ? null : telephone2.trim();
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
            
    
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public int hashCode() {
        int hash = 7;  
        hash = 31 * hash + (null == studentCode ? 0 : studentCode.hashCode());  
        return hash; 
    }

    @Override
    public boolean equals(Object obj) {        
        if((obj == null) || (obj.getClass() != this.getClass()))  
             return false; 
        if(this == obj)  
            return true;  
        if (obj instanceof XdfStudentInfo) {
            XdfStudentInfo cs=(XdfStudentInfo)obj;
            return this.studentCode.equals(cs.studentCode);
        }
        return false;
    } 
}