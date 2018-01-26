package com.human.recruitment.entity;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class ResumeTalent {
    
    private Integer id;
    /*
     * 简历ID
     */
    private Integer resumeId;
    
    /**
     * 求职者ID
     */
    private String seekerId;
    /*
     * 机构名称Id
     */
    private String hrCompanyId;
    
    /*
     * 机构名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="机构名称")
    private String companyName;
    

    /*
     * 部门名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="部门名称")
    private String deptName;
    
    /*
     *  部门Id
     */
    private String dept;
    
    /*
     *  职位Id
     */
    private Integer position;
    
    /*
     * 职位名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="职位名称")
    private String positionName;

    
    /*
     * 投递职位
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="投递职位")
    private String applyPosition;
    
    
    /*
     * 人才库时间
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="人才库时间")
    private Date createTime;
    
    
    /**
     * 用于查询的人才库时间
     * @return
     */
    private String talentTimeStart;
    
    private String talentTimeEnd;
    
    
    private String createUser;

    private Integer status;
    
    
    /*
     * 淘汰环节
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="淘汰环节")
    private String circulationName;
    
    /*
     * 求职者姓名
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="求职者")
    private String seekerName;
    
    /*
     * 手机号
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="手机号")
    private String telephone;
    
    
    /*
     * 学校名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学校名称")
    private String graSchool;
                
    /*
     * 学历
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="学历")
    private String highEdu;
    
    /*
     * 专业
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="专业")
    private String major;
    
    
    /*
     * 登陆用户的ID    
     */
    private Long userId;
    
    /*
     * 流程单号
     */
    private String flowCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") 
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getHrCompanyId() {
        return hrCompanyId;
    }

    public void setHrCompanyId(String hrCompanyId) {
        this.hrCompanyId = hrCompanyId;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getTalentTimeStart() {
        return talentTimeStart;
    }

    public void setTalentTimeStart(String talentTimeStart) {
        this.talentTimeStart = talentTimeStart;
    }

    public String getTalentTimeEnd() {
        return talentTimeEnd;
    }

    public void setTalentTimeEnd(String talentTimeEnd) {
        this.talentTimeEnd = talentTimeEnd;
    }

    public String getCirculationName() {
        return circulationName;
    }

    public void setCirculationName(String circulationName) {
        this.circulationName = circulationName;
    }

    public String getSeekerName() {
        return seekerName;
    }

    public void setSeekerName(String seekerName) {
        this.seekerName = seekerName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool;
    }

    public String getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getApplyPosition() {
        return applyPosition;
    }

    public void setApplyPosition(String applyPosition) {
        this.applyPosition = applyPosition;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getSeekerId() {
        return seekerId;
    }

    public void setSeekerId(String seekerId) {
        this.seekerId = seekerId;
    }
    
    
    
}