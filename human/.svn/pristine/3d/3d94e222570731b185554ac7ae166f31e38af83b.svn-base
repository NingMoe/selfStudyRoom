package com.human.resume.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

/**
 * 简历基本信息POJO
 * @author liuwei
 *
 */
public class ResumeBase {
    private Long id;
    
    /*
     *  机构名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="机构名称")
    private String companyName;
    
    /*
     *  部门名称
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="部门名称")
    private String deptName;
    
    /*
     *  职位名称
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
     * 求职者姓名
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="求职者")
    private String name;
    
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
     *  简历状态
     */
    @ExportTitleAnnotation()     
    @ExcelAnnotation(exportName="简历状态")
    private String resumeStatus;
    
    /*
     * 内部推荐
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="内部推荐")
    private String insideRecommend;
    
    /*
     * 投递时间
     */
    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="投递时间")
    private String deliveryDate;
    
    /*
     *  最后一次流转时间
     */
    @ExportTitleAnnotation()     
    @ExcelAnnotation(exportName="最后一次流转时间")
    private String approveTime;
        
    private String sex;

    private String nationality;
    
    private String email;

    private String nation;

    private String workingTime;

    private String source;

    private String type;

    private String locationCity;

    private String householdRegister;

    private String politicalStatus;

    private String certificatesType;

    private String certificatesNumber;

    private String postAdjustment;

    private String phoneBack;
    

    
    /**
     * 用于查询的投递时间
     * @return
     */
    private String deliveryDateStart;
    
    private String deliveryDateEnd;
    
    /**
     * 是否内推
     */
    private String isInside;
    

    //推荐人关系
    private String insideRelation;
    

    private String resumeLink;

    private String evaluation;

    private Long analysisMailId;

    private Long resumeSeekerId;
    

    private String graduationDate;

    
    private String nodeName;
    

    
    /*
     * 头像地址
     */
    private String headUrl;
    
    /*
     * 匹配职位
     */
    private String matchingPosition;
    
    private String flowStatus;
    
    /*
     * 机构名称Id
     */
    private String hrCompanyId;
    

    
    /*
     *  部门Id
     */
    private String deptId;
    

    
    /*
     *  职位Id
     */
    private String positionId;
    
    
    /*
     * 流程单号
     */
    private String flowCode;
    
    /*
     * 流程ID
     */
    private String flowId;

    /*
     * 流程是否正常结束
     */
    private String status;
    
    /**
     * 最后一次沟通记录
     */
    private String lastComment;
    
    /**简历详情所需集合**/
    /**
     * 求职意向
     */
    private ResumeIntention resumeInterntion;
    
    /**
     * 语言能力
     */
    private List<ResumeLanguage> languageList;
    
    /**
     * 专业技能
     */
    private List<ResumeMajorSkill> resumeMajorList;
    
    /**
     * 简历快照
     */
    private List<ResumeSnapshot> resumeSnapshot;
    
    /**
     * 简历图片
     */
    private List<ResumePhoto> resumePhoto;
    
    /**
     * 工作经历
     */
    private List<ResumeWorkHistory> resumeWorkList;
    
    /**
     * 教育经历
     */
    private List<ResumeEducationHistory> resumeEduList;
    
    /**
     * 培训经历
     */
    private List<ResumeTrainHistory> trainList;
    
    /**
     * 校内职务
     */
    private List<ResumeSchoolPost> schoolPostList;
    
    /**
     * 项目经验
     */
    private List<ResumeProjectExperience> projectList;
    
    /**
     * 实践经验
     */
    private List<ResumePracticeExperience> practiceList;
    
    /**
     * 简历属性
     */
    private String originalFlag;
    
    private String marriage;
    
    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    private String birthDate;
    
    /**
     * 保存标志
     */
    private String saveFlag;
    
    /**
     * 是否显示手机号  1显示  2不显示
     */
    private String flowFlag;
    
    /*
     * 是否211 0：否 1：是
     */
    private Integer is211;
    /*
     * 是否985 0：否 1：是
     */
    private Integer is985;
    
    /*
     * 校园招聘活动Id
     */
    private Long schoolRecruitmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime == null ? null : workingTime.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity == null ? null : locationCity.trim();
    }

    public String getHouseholdRegister() {
        return householdRegister;
    }

    public void setHouseholdRegister(String householdRegister) {
        this.householdRegister = householdRegister == null ? null : householdRegister.trim();
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType == null ? null : certificatesType.trim();
    }

    public String getCertificatesNumber() {
        return certificatesNumber;
    }

    public void setCertificatesNumber(String certificatesNumber) {
        this.certificatesNumber = certificatesNumber == null ? null : certificatesNumber.trim();
    }

    public String getPostAdjustment() {
        return postAdjustment;
    }

    public void setPostAdjustment(String postAdjustment) {
        this.postAdjustment = postAdjustment == null ? null : postAdjustment.trim();
    }

    public String getPhoneBack() {
        return phoneBack;
    }

    public void setPhoneBack(String phoneBack) {
        this.phoneBack = phoneBack == null ? null : phoneBack.trim();
    }


    public String getInsideRecommend() {
        return insideRecommend;
    }

    public void setInsideRecommend(String insideRecommend) {
        this.insideRecommend = insideRecommend == null ? null : insideRecommend.trim();
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink == null ? null : resumeLink.trim();
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation == null ? null : evaluation.trim();
    }

    public Long getAnalysisMailId() {
        return analysisMailId;
    }

    public void setAnalysisMailId(Long analysisMailId) {
        this.analysisMailId = analysisMailId;
    }

    public Long getResumeSeekerId() {
        return resumeSeekerId;
    }

    public void setResumeSeekerId(Long resumeSeekerId) {
        this.resumeSeekerId = resumeSeekerId;
    }

    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool == null ? null : graSchool.trim();
    }


    public String getHighEdu() {
        return highEdu;
    }

    public void setHighEdu(String highEdu) {
        this.highEdu = highEdu == null ? null : highEdu.trim();
    }

    public String getApplyPosition() {
        return applyPosition;
    }

    public void setApplyPosition(String applyPosition) {
        this.applyPosition = applyPosition == null ? null : applyPosition.trim();
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }


    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getMatchingPosition() {
        return matchingPosition;
    }

    public void setMatchingPosition(String matchingPosition) {
        this.matchingPosition = matchingPosition;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(String flowStatus) {
        this.flowStatus = flowStatus;
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

    public String getResumeStatus() {
        return resumeStatus;
    }

    public void setResumeStatus(String resumeStatus) {
        this.resumeStatus = resumeStatus;
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
    }

    public String getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(String deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public String getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(String deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }

    public String getIsInside() {
        return isInside;
    }

    public void setIsInside(String isInside) {
        this.isInside = isInside;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getInsideRelation() {
        return insideRelation;
    }

    public void setInsideRelation(String insideRelation) {
        this.insideRelation = insideRelation;
    }

    public ResumeIntention getResumeInterntion() {
        return resumeInterntion;
    }

    public void setResumeInterntion(ResumeIntention resumeInterntion) {
        this.resumeInterntion = resumeInterntion;
    }

    public List<ResumeLanguage> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<ResumeLanguage> languageList) {
        this.languageList = languageList;
    }

    public List<ResumeMajorSkill> getResumeMajorList() {
        return resumeMajorList;
    }

    public void setResumeMajorList(List<ResumeMajorSkill> resumeMajorList) {
        this.resumeMajorList = resumeMajorList;
    }

    public List<ResumeSnapshot> getResumeSnapshot() {
        return resumeSnapshot;
    }

    public void setResumeSnapshot(List<ResumeSnapshot> resumeSnapshot) {
        this.resumeSnapshot = resumeSnapshot;
    }

    public List<ResumePhoto> getResumePhoto() {
        return resumePhoto;
    }

    public void setResumePhoto(List<ResumePhoto> resumePhoto) {
        this.resumePhoto = resumePhoto;
    }

    public List<ResumeWorkHistory> getResumeWorkList() {
        return resumeWorkList;
    }

    public void setResumeWorkList(List<ResumeWorkHistory> resumeWorkList) {
        this.resumeWorkList = resumeWorkList;
    }

    public List<ResumeEducationHistory> getResumeEduList() {
        return resumeEduList;
    }

    public void setResumeEduList(List<ResumeEducationHistory> resumeEduList) {
        this.resumeEduList = resumeEduList;
    }

    public List<ResumeTrainHistory> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<ResumeTrainHistory> trainList) {
        this.trainList = trainList;
    }

    public List<ResumeSchoolPost> getSchoolPostList() {
        return schoolPostList;
    }

    public void setSchoolPostList(List<ResumeSchoolPost> schoolPostList) {
        this.schoolPostList = schoolPostList;
    }

    public List<ResumeProjectExperience> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ResumeProjectExperience> projectList) {
        this.projectList = projectList;
    }

    public List<ResumePracticeExperience> getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(List<ResumePracticeExperience> practiceList) {
        this.practiceList = practiceList;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOriginalFlag() {
        return originalFlag;
    }

    public void setOriginalFlag(String originalFlag) {
        this.originalFlag = originalFlag;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }

    public String getFlowFlag() {
        return flowFlag;
    }

    public void setFlowFlag(String flowFlag) {
        this.flowFlag = flowFlag;
    }

    public Integer getIs211() {
        return is211;
    }

    public void setIs211(Integer is211) {
        this.is211 = is211;
    }

    public Integer getIs985() {
        return is985;
    }

    public void setIs985(Integer is985) {
        this.is985 = is985;
    }

    public Long getSchoolRecruitmentId() {
        return schoolRecruitmentId;
    }

    public void setSchoolRecruitmentId(Long schoolRecruitmentId) {
        this.schoolRecruitmentId = schoolRecruitmentId;
    }

    public String getLastComment() {
        return lastComment;
    }

    public void setLastComment(String lastComment) {
        this.lastComment = lastComment;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }  
    

}