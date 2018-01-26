package com.human.recruitment.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;
/**
 * 校园招聘或活动实体
 * @author liuwei
 *
 */
public class SchoolRecruitment {
    
    private Long id;

    private String hrCompanyId;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="机构名称")
    private String companyName;

    private String deptId;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="部门名称")
    private String deptName;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="活动名称")
    private String name;

    private String codeUrl;
   
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="添加人")
    private String createUser;
    
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="添加时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;
    
    /*
     * 累计简历数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="累计简历数")  
    private long totalCount;
    
    
    /*
     * 待处理简历数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="待处理")  
    private long noDealCount;
    
    
    /*
     * 面试中简历数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="面试中")  
    private long interviewCount;
    
    /*
     * 淘汰简历数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="淘汰")  
    private long eliminateCount;
    
    
    /*
     * 入职简历数
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="入职")  
    private long entryCount;
    
    /*
     * 登陆用户的ID    
     */
    private Long userId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHrCompanyId() {
        return hrCompanyId;
    }

    public void setHrCompanyId(String hrCompanyId) {
        this.hrCompanyId = hrCompanyId == null ? null : hrCompanyId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl == null ? null : codeUrl.trim();
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

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getNoDealCount() {
        return noDealCount;
    }

    public void setNoDealCount(long noDealCount) {
        this.noDealCount = noDealCount;
    }

    public long getInterviewCount() {
        return interviewCount;
    }

    public void setInterviewCount(long interviewCount) {
        this.interviewCount = interviewCount;
    }

    public long getEliminateCount() {
        return eliminateCount;
    }

    public void setEliminateCount(long eliminateCount) {
        this.eliminateCount = eliminateCount;
    }

    public long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(long entryCount) {
        this.entryCount = entryCount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    
    
    
    
}