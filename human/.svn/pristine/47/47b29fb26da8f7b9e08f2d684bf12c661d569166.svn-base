package com.human.teacherservice.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 教师反馈pojo
 * @author liuwei63
 *
 */
public class LibBookBuy {
    private Long id;

    private String companyId;

    private String emailAddr;
   
    private Integer isValid;
    
    @ExportTitleAnnotation()
    private String companyName;
    
    @ExportTitleAnnotation()
    private String deptName;
    
    @ExportTitleAnnotation()
    private String name;
    
    @ExportTitleAnnotation()
    private String bookName;

    @ExportTitleAnnotation()
    private String bookAuthor;
    
    @ExportTitleAnnotation()
    private String reason;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ExportTitleAnnotation()
    private Date createTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr == null ? null : emailAddr.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }
        
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
    
}