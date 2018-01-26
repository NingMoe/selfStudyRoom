package com.human.jw.entity;

import java.io.Serializable;

public class JwCourse implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 500399235325227541L;

    private String sClassCode;
    
    private String sClassName;
    
    private String SectBegin;
    
    private String SectEnd;
    
    private String sPrintAddress;
    
    //开课时间
    private String dtBeginDate;
    
    //结课时间
    private String dtEndDate;
    
    /**
     * 班次名称
     */
    private String sCourseName;
    
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

    public String getSectBegin() {
        return SectBegin;
    }

    public void setSectBegin(String sectBegin) {
        SectBegin = sectBegin;
    }

    public String getSectEnd() {
        return SectEnd;
    }

    public void setSectEnd(String sectEnd) {
        SectEnd = sectEnd;
    }

    public String getsPrintAddress() {
        return sPrintAddress;
    }

    public void setsPrintAddress(String sPrintAddress) {
        this.sPrintAddress = sPrintAddress;
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
        if (obj instanceof JwCourse) {
            JwCourse jc=(JwCourse)obj;
            return this.sClassCode.equals(jc.sClassCode);
        }
        return false;
    }

    public String getsCourseName() {
        return sCourseName;
    }

    public void setsCourseName(String sCourseName) {
        this.sCourseName = sCourseName;
    } 
    
}
