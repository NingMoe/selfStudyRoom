package com.human.jzbTest.entity;

/**
 * 报名参数对象
 * @author liuwei
 *
 */
public class JzbGradeSubjectDto {

    private Long gradeId;

    private String subjectCode;
    
    private Integer classType;
    
    private Integer isPass;
    
    private String sStudentName;
    
    private String sMobile;
    
    /**
     * 校区编码
     */
    private String areaCode;

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getClassType() {
        return classType;
    }

    public void setClassType(Integer classType) {
        this.classType = classType;
    }

    

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public String getsStudentName() {
        return sStudentName;
    }

    public void setsStudentName(String sStudentName) {
        this.sStudentName = sStudentName;
    }

    public String getsMobile() {
        return sMobile;
    }

    public void setsMobile(String sMobile) {
        this.sMobile = sMobile;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    
   
   
    
}

