package com.human.basic.entity;

import java.io.Serializable;

public class XdfClassInfo implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 2680009748837188004L;
    
    private String sClassCode;
    private String sClassName;
    private String routeKey;
    private String dtBeginDate;
    private String dtEndDate;
    private String bIsNet;
    private String bIsEnd;
    private String bSouke;
    private String ClassBookDeliveryType;
    private String dFee;
    private String sClassTypeCode;
    private String sClassTypeFCode;
    private String sClassTypeName;
    private String sProjectCode;
    private String sProjectFCode;
    private String sProjectName;
    private String sDeptCode;
    private String sDeptFCode;
    private String sDeptName;
    private String bInsurance;
    private String nUseCard;
    private String bIsResideClass;
    private String sAreaAddress;
    private String sAreaCode;
    private String CourseCode;
    private String nLesson;
    private String nMaxCount;
    private String nCurrentCount;
    private String sPrintAddress;
    private String sPrintTime;
    private String nMakePoint;
    private String dtOpenTime;
    private String sAllTeacherName;
    private String bAllFeeInsert;
    private String sClassSubject;
    private String sQuarter;
    private String Grade;
    private String Stage;
    private String dtRealBeginDate;
    private String dtRealEndDate;
    private String bCanRegister;
    private String sManageDeptCodes;
    private String nState;
    private String nAudit;
    private String bVirtual;
    private String remark;
    private String sLevel;
    private String area;
    private String beginGrade;
    private String endGrade;
    private String view;
    //多个年级查询
    private String[] sProjectCodes;
    
    //续班工具，教师已配置人数
    private Integer con_num;
    
    public String[] getsProjectCodes() {
        return sProjectCodes;
    }
    public void setsProjectCodes(String[] sProjectCodes) {
        this.sProjectCodes = sProjectCodes;
    }
    public String getView() {
        return view;
    }
    public void setView(String view) {
        this.view = view;
    }
    
    public String getBeginGrade() {
        return beginGrade;
    }
    public void setBeginGrade(String beginGrade) {
        this.beginGrade = beginGrade;
    }
    public String getEndGrade() {
        return endGrade;
    }
    public void setEndGrade(String endGrade) {
        this.endGrade = endGrade;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    private String sClassSubjectName;
    
    private String sManageDeptName;
    
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getsLevel() {
        return sLevel;
    }
    public void setsLevel(String sLevel) {
        this.sLevel = sLevel;
    }
    public String getsClassCode() {
        return sClassCode;
    }
    public void setsClassCode(String sClassCode) {
        this.sClassCode = sClassCode;
    }
    public String getsClassName() {
        return sClassName;
    }
    public void setsClassName(String sClassName) {
        this.sClassName = sClassName;
    }
    public String getDtBeginDate() {
        return dtBeginDate;
    }
    public void setDtBeginDate(String dtBeginDate) {
        this.dtBeginDate = dtBeginDate;
    }
    public String getDtEndDate() {
        return dtEndDate;
    }
    public void setDtEndDate(String dtEndDate) {
        this.dtEndDate = dtEndDate;
    }
    public String getbIsNet() {
        return bIsNet;
    }
    public void setbIsNet(String bIsNet) {
        this.bIsNet = bIsNet;
    }
    public String getbIsEnd() {
        return bIsEnd;
    }
    public void setbIsEnd(String bIsEnd) {
        this.bIsEnd = bIsEnd;
    }
    public String getbSouke() {
        return bSouke;
    }
    public void setbSouke(String bSouke) {
        this.bSouke = bSouke;
    }
    public String getClassBookDeliveryType() {
        return ClassBookDeliveryType;
    }
    public void setClassBookDeliveryType(String classBookDeliveryType) {
        ClassBookDeliveryType = classBookDeliveryType;
    }
    public String getdFee() {
        return dFee;
    }
    public void setdFee(String dFee) {
        this.dFee = dFee;
    }
    public String getsClassTypeCode() {
        return sClassTypeCode;
    }
    public void setsClassTypeCode(String sClassTypeCode) {
        this.sClassTypeCode = sClassTypeCode;
    }
    public String getsClassTypeFCode() {
        return sClassTypeFCode;
    }
    public void setsClassTypeFCode(String sClassTypeFCode) {
        this.sClassTypeFCode = sClassTypeFCode;
    }
    public String getsClassTypeName() {
        return sClassTypeName;
    }
    public void setsClassTypeName(String sClassTypeName) {
        this.sClassTypeName = sClassTypeName;
    }
    public String getsProjectCode() {
        return sProjectCode;
    }
    public void setsProjectCode(String sProjectCode) {
        this.sProjectCode = sProjectCode;
    }
    public String getsProjectFCode() {
        return sProjectFCode;
    }
    public void setsProjectFCode(String sProjectFCode) {
        this.sProjectFCode = sProjectFCode;
    }
    public String getsProjectName() {
        return sProjectName;
    }
    public void setsProjectName(String sProjectName) {
        this.sProjectName = sProjectName;
    }
    public String getsDeptCode() {
        return sDeptCode;
    }
    public void setsDeptCode(String sDeptCode) {
        this.sDeptCode = sDeptCode;
    }
    public String getsDeptFCode() {
        return sDeptFCode;
    }
    public void setsDeptFCode(String sDeptFCode) {
        this.sDeptFCode = sDeptFCode;
    }
    public String getsDeptName() {
        return sDeptName;
    }
    public void setsDeptName(String sDeptName) {
        this.sDeptName = sDeptName;
    }
    public String getbInsurance() {
        return bInsurance;
    }
    public void setbInsurance(String bInsurance) {
        this.bInsurance = bInsurance;
    }
    public String getnUseCard() {
        return nUseCard;
    }
    public void setnUseCard(String nUseCard) {
        this.nUseCard = nUseCard;
    }
    public String getbIsResideClass() {
        return bIsResideClass;
    }
    public void setbIsResideClass(String bIsResideClass) {
        this.bIsResideClass = bIsResideClass;
    }
    public String getsAreaAddress() {
        return sAreaAddress;
    }
    public void setsAreaAddress(String sAreaAddress) {
        this.sAreaAddress = sAreaAddress;
    }
    public String getsAreaCode() {
        return sAreaCode;
    }
    public void setsAreaCode(String sAreaCode) {
        this.sAreaCode = sAreaCode;
    }
    public String getCourseCode() {
        return CourseCode;
    }
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }
    public String getnLesson() {
        return nLesson;
    }
    public void setnLesson(String nLesson) {
        this.nLesson = nLesson;
    }
    public String getnMaxCount() {
        return nMaxCount;
    }
    public void setnMaxCount(String nMaxCount) {
        this.nMaxCount = nMaxCount;
    }
    public String getnCurrentCount() {
        return nCurrentCount;
    }
    public void setnCurrentCount(String nCurrentCount) {
        this.nCurrentCount = nCurrentCount;
    }
    public String getsPrintAddress() {
        return sPrintAddress;
    }
    public void setsPrintAddress(String sPrintAddress) {
        this.sPrintAddress = sPrintAddress;
    }
    public String getsPrintTime() {
        return sPrintTime;
    }
    public void setsPrintTime(String sPrintTime) {
        this.sPrintTime = sPrintTime;
    }
    public String getnMakePoint() {
        return nMakePoint;
    }
    public void setnMakePoint(String nMakePoint) {
        this.nMakePoint = nMakePoint;
    }
    public String getDtOpenTime() {
        return dtOpenTime;
    }
    public void setDtOpenTime(String dtOpenTime) {
        this.dtOpenTime = dtOpenTime;
    }
    public String getsAllTeacherName() {
        return sAllTeacherName;
    }
    public void setsAllTeacherName(String sAllTeacherName) {
        this.sAllTeacherName = sAllTeacherName;
    }
    public String getbAllFeeInsert() {
        return bAllFeeInsert;
    }
    public void setbAllFeeInsert(String bAllFeeInsert) {
        this.bAllFeeInsert = bAllFeeInsert;
    }
    public String getsClassSubject() {
        return sClassSubject;
    }
    public void setsClassSubject(String sClassSubject) {
        this.sClassSubject = sClassSubject;
    }
    public String getsQuarter() {
        return sQuarter;
    }
    public void setsQuarter(String sQuarter) {
        this.sQuarter = sQuarter;
    }
    public String getGrade() {
        return Grade;
    }
    public void setGrade(String grade) {
        Grade = grade;
    }
    public String getStage() {
        return Stage;
    }
    public void setStage(String stage) {
        Stage = stage;
    }
    public String getDtRealBeginDate() {
        return dtRealBeginDate;
    }
    public void setDtRealBeginDate(String dtRealBeginDate) {
        this.dtRealBeginDate = dtRealBeginDate;
    }
    public String getDtRealEndDate() {
        return dtRealEndDate;
    }
    public void setDtRealEndDate(String dtRealEndDate) {
        this.dtRealEndDate = dtRealEndDate;
    }
    public String getbCanRegister() {
        return bCanRegister;
    }
    public void setbCanRegister(String bCanRegister) {
        this.bCanRegister = bCanRegister;
    }
    public String getsManageDeptCodes() {
        return sManageDeptCodes;
    }
    public void setsManageDeptCodes(String sManageDeptCodes) {
        this.sManageDeptCodes = sManageDeptCodes;
    }
    public String getnState() {
        return nState;
    }
    public void setnState(String nState) {
        this.nState = nState;
    }
    public String getnAudit() {
        return nAudit;
    }
    public void setnAudit(String nAudit) {
        this.nAudit = nAudit;
    }
    public String getbVirtual() {
        return bVirtual;
    }
    public void setbVirtual(String bVirtual) {
        this.bVirtual = bVirtual;
    }
    public String getRouteKey() {
        return routeKey;
    }
    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }
    public String getsClassSubjectName() {
        return sClassSubjectName;
    }
    public void setsClassSubjectName(String sClassSubjectName) {
        this.sClassSubjectName = sClassSubjectName;
    }
    public String getsManageDeptName() {
        return sManageDeptName;
    }
    public void setsManageDeptName(String sManageDeptName) {
        this.sManageDeptName = sManageDeptName;
    }
    
    public Integer getCon_num() {
        return con_num;
    }
    public void setCon_num(Integer con_num) {
        this.con_num = con_num;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;  
        hash = 31 * hash + (null == sClassCode ? 0 : sClassCode.hashCode());  
        return hash; 
    }

    @Override
    public boolean equals(Object obj) {        
        if((obj == null) || (obj.getClass() != this.getClass()))  
             return false; 
        if(this == obj)  
            return true;  
        if (obj instanceof XdfClassInfo) {
            XdfClassInfo jc=(XdfClassInfo)obj;
            return this.sClassCode.equals(jc.sClassCode);
        }
        return false;
    } 
    
    

}
