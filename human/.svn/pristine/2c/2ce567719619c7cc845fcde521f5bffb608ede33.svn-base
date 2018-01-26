package com.human.teacherservice.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 意见反馈
 * @author liuwei63
 *
 */
public class LibFeedBack {
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
    private String content;
    
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
}