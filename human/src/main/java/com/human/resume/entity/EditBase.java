package com.human.resume.entity;

import java.util.List;

public class EditBase {
    private Long id;

    private String name;

    private String sex;

    private String nationality;

    private String telephone;

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

    private String deliveryDate;
    
    private String insideRecommend;
    
    //推荐人关系
    private String insideRelation;
    

    private String resumeLink;

    private String evaluation;

    private String graSchool;
    
    private String graduationDate;

    private String highEdu;
    
    /**
     * 专业
     */
    private String major;
    
    /**
     * 头像地址
     */
    private String headUrl;
    
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
    private List<WorkHistory> resumeWorkList;
    
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

        private Boolean is211;

        private Boolean is985;

        private String education;

        private String major;

        private String describes;

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

        public Boolean getIs211() {
            return is211;
        }

        public void setIs211(Boolean is211) {
            this.is211 = is211;
        }

        public Boolean getIs985() {
            return is985;
        }

        public void setIs985(Boolean is985) {
            this.is985 = is985;
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

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }
    }
    /**
     * 培训经历
     */
    private List<TrainHistory> trainList;
    
    public class TrainHistory{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String trainCompany;

        private String place;

        private String trainName;

        private String certificate;

        private String describes;

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

        public String getTrainCompany() {
            return trainCompany;
        }

        public void setTrainCompany(String trainCompany) {
            this.trainCompany = trainCompany;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getTrainName() {
            return trainName;
        }

        public void setTrainName(String trainName) {
            this.trainName = trainName;
        }

        public String getCertificate() {
            return certificate;
        }

        public void setCertificate(String certificate) {
            this.certificate = certificate;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
        }
    }
    
    /**
     * 校内职务
     */
    private List<SchoolPost> schoolPostList;
    
    public class SchoolPost{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String postName;

        private String describes;

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

        public String getPostName() {
            return postName;
        }

        public void setPostName(String postName) {
            this.postName = postName;
        }

        public String getDescribes() {
            return describes;
        }

        public void setDescribes(String describes) {
            this.describes = describes;
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
     * 实践经验
     */
    private List<PracticeExper> practiceList;
    
    public class PracticeExper{
        private Long id;

        private Long resumeId;

        private String startTime;

        private String endTime;

        private String practiceName;

        private String practiceDescribe;

        private String practicePosition;

        private String practiceCompany;

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

        public String getPracticeName() {
            return practiceName;
        }

        public void setPracticeName(String practiceName) {
            this.practiceName = practiceName;
        }

        public String getPracticeDescribe() {
            return practiceDescribe;
        }

        public void setPracticeDescribe(String practiceDescribe) {
            this.practiceDescribe = practiceDescribe;
        }

        public String getPracticePosition() {
            return practicePosition;
        }

        public void setPracticePosition(String practicePosition) {
            this.practicePosition = practicePosition;
        }

        public String getPracticeCompany() {
            return practiceCompany;
        }

        public void setPracticeCompany(String practiceCompany) {
            this.practiceCompany = practiceCompany;
        }
    }
    
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
    }

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }

    public String getHouseholdRegister() {
        return householdRegister;
    }

    public void setHouseholdRegister(String householdRegister) {
        this.householdRegister = householdRegister;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(String certificatesType) {
        this.certificatesType = certificatesType;
    }

    public String getCertificatesNumber() {
        return certificatesNumber;
    }

    public void setCertificatesNumber(String certificatesNumber) {
        this.certificatesNumber = certificatesNumber;
    }

    public String getPostAdjustment() {
        return postAdjustment;
    }

    public void setPostAdjustment(String postAdjustment) {
        this.postAdjustment = postAdjustment;
    }

    public String getPhoneBack() {
        return phoneBack;
    }

    public void setPhoneBack(String phoneBack) {
        this.phoneBack = phoneBack;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }



    public String getGraSchool() {
        return graSchool;
    }

    public void setGraSchool(String graSchool) {
        this.graSchool = graSchool;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
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

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
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

    public List<WorkHistory> getResumeWorkList() {
        return resumeWorkList;
    }

    public void setResumeWorkList(List<WorkHistory> resumeWorkList) {
        this.resumeWorkList = resumeWorkList;
    }

    public List<EducationHistory> getResumeEduList() {
        return resumeEduList;
    }

    public void setResumeEduList(List<EducationHistory> resumeEduList) {
        this.resumeEduList = resumeEduList;
    }

    public List<TrainHistory> getTrainList() {
        return trainList;
    }

    public void setTrainList(List<TrainHistory> trainList) {
        this.trainList = trainList;
    }

    public List<SchoolPost> getSchoolPostList() {
        return schoolPostList;
    }

    public void setSchoolPostList(List<SchoolPost> schoolPostList) {
        this.schoolPostList = schoolPostList;
    }

    public List<ProjectExper> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectExper> projectList) {
        this.projectList = projectList;
    }

    public List<PracticeExper> getPracticeList() {
        return practiceList;
    }

    public void setPracticeList(List<PracticeExper> practiceList) {
        this.practiceList = practiceList;
    }

}
