package com.human.front.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.human.resume.entity.ResumeIntention;
import com.human.resume.entity.ResumeLanguage;


public class ResumeDeliveryEditBase {
    
    private Long id;

    private String name;

    private String sex;

    private String telephone;

    private String email;
    
    private String locationCity;

    private String matchingPosition;
    
    /**
     * 头像地址
     */
    private String headUrl;
    
    
    private Long resumeSeekerId;
    

    private boolean hasRphotoFlag; 
    
    private boolean hasHeadPhotoFlag;
    
    private String marriage;
    
    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
    private String birthDate;
    
    /**
     * 推荐人邮箱
     */
    private String insideRecommend;
    
    /**
     * 推荐人关系
     */
    private String insideRelation;
    
    /**
     * 求职意向
     */
    private ResumeIntention resumeInterntion;
    
    /**
     * 教育经历
     */
    private List<EducationHistory> resumeEduList;
    
    public class EducationHistory{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String schoolName;

        private String education;

        private String major;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getResumeId() {
            return resumeId;
        }

        public void setResumeId(Long resumeId) {
            this.resumeId = resumeId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }


        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

    }
    
    /**
     * 工作经历
     */
    private List<WorkHistory> resumeWorkList;
    
    public class WorkHistory{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String companyName;

        private String workTime;

        private String workProperty;

        private String companyScale;

        private String department;

        private String position;

        private String salary;

        private String describes;

        private String leaveReason;
        
        private Long minSalary;
        
        private Long maxSalary;
        
        private String workType;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getResumeId() {
            return resumeId;
        }

        public void setResumeId(Long resumeId) {
            this.resumeId = resumeId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public String getWorkProperty() {
            return workProperty;
        }

        public void setWorkProperty(String workProperty) {
            this.workProperty = workProperty;
        }

        public String getCompanyScale() {
            return companyScale;
        }

        public void setCompanyScale(String companyScale) {
            this.companyScale = companyScale;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }

        public String getLeaveReason() {
            return leaveReason;
        }

        public void setLeaveReason(String leaveReason) {
            this.leaveReason = leaveReason;
        }

        public Long getMinSalary() {
            return minSalary;
        }

        public void setMinSalary(Long minSalary) {
            this.minSalary = minSalary;
        }

        public Long getMaxSalary() {
            return maxSalary;
        }

        public void setMaxSalary(Long maxSalary) {
            this.maxSalary = maxSalary;
        }

        public String getWorkType() {
            return workType;
        }

        public void setWorkType(String workType) {
            this.workType = workType;
        }
        
        
    }
    
    /**
     * 项目经验
     */
    private List<ProjectExper> projectList;
    
    public class ProjectExper{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String projectName;

        private String responsibilityDescribe;

        private String projectDescribe;

        private String companyName;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getResumeId() {
            return resumeId;
        }

        public void setResumeId(Long resumeId) {
            this.resumeId = resumeId;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getResponsibilityDescribe() {
            return responsibilityDescribe;
        }

        public void setResponsibilityDescribe(String responsibilityDescribe) {
            this.responsibilityDescribe = responsibilityDescribe;
        }

        public String getProjectDescribe() {
            return projectDescribe;
        }

        public void setProjectDescribe(String projectDescribe) {
            this.projectDescribe = projectDescribe;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
    
    /**
     * 语言能力
     */
    private List<ResumeLanguage> languageList;
    
        

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
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getMatchingPosition() {
        return matchingPosition;
    }

    public void setMatchingPosition(String matchingPosition) {
        this.matchingPosition = matchingPosition;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public Long getResumeSeekerId() {
        return resumeSeekerId;
    }

    public void setResumeSeekerId(Long resumeSeekerId) {
        this.resumeSeekerId = resumeSeekerId;
    }

    public boolean isHasRphotoFlag() {
        return hasRphotoFlag;
    }

    public void setHasRphotoFlag(boolean hasRphotoFlag) {
        this.hasRphotoFlag = hasRphotoFlag;
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

    public List<EducationHistory> getResumeEduList() {
        return resumeEduList;
    }

    public void setResumeEduList(List<EducationHistory> resumeEduList) {
        this.resumeEduList = resumeEduList;
    }

    public boolean isHasHeadPhotoFlag() {
        return hasHeadPhotoFlag;
    }

    public void setHasHeadPhotoFlag(boolean hasHeadPhotoFlag) {
        this.hasHeadPhotoFlag = hasHeadPhotoFlag;
    }

    public String getInsideRecommend() {
        return insideRecommend;
    }

    public void setInsideRecommend(String insideRecommend) {
        this.insideRecommend = insideRecommend;
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

    public List<WorkHistory> getResumeWorkList() {
        return resumeWorkList;
    }

    public void setResumeWorkList(List<WorkHistory> resumeWorkList) {
        this.resumeWorkList = resumeWorkList;
    }

    public List<ProjectExper> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectExper> projectList) {
        this.projectList = projectList;
    }

    public List<ResumeLanguage> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<ResumeLanguage> languageList) {
        this.languageList = languageList;
    }
    
    

}
