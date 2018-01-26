package com.human.continuation.entity;

public class TeacherContinuationConfig {
    private Integer id;

    private String school_id;

    private String manager_dept_code;
    
    private String manager_dept_name;

    private String old_fiscal_year;
    
    private String old_fiscal_name;

    private Integer old_class_quarter;

    private String new_fiscal_year;
    
    private String new_fiscal_name;

    private Integer new_class_quarter;

    private String continue_name;

    private String continue_id;
    
    private String sbq_config_name;
    
    private String sWindowPeriodName;

    private String sWindowPeriodId;
    
    private String sStageName;
    
    private String sStageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id == null ? null : school_id.trim();
    }

    public String getManager_dept_code() {
        return manager_dept_code;
    }

    public void setManager_dept_code(String manager_dept_code) {
        this.manager_dept_code = manager_dept_code;
    }

    public String getManager_dept_name() {
        return manager_dept_name;
    }

    public void setManager_dept_name(String manager_dept_name) {
        this.manager_dept_name = manager_dept_name;
    }

    public String getOld_fiscal_year() {
        return old_fiscal_year;
    }

    public void setOld_fiscal_year(String old_fiscal_year) {
        this.old_fiscal_year = old_fiscal_year == null ? null : old_fiscal_year.trim();
    }

    public String getOld_fiscal_name() {
        return old_fiscal_name;
    }

    public void setOld_fiscal_name(String old_fiscal_name) {
        this.old_fiscal_name = old_fiscal_name;
    }

    public Integer getOld_class_quarter() {
        return old_class_quarter;
    }

    public void setOld_class_quarter(Integer old_class_quarter) {
        this.old_class_quarter = old_class_quarter;
    }

    public String getNew_fiscal_year() {
        return new_fiscal_year;
    }

    public void setNew_fiscal_year(String new_fiscal_year) {
        this.new_fiscal_year = new_fiscal_year == null ? null : new_fiscal_year.trim();
    }
    
    public String getNew_fiscal_name() {
        return new_fiscal_name;
    }

    public void setNew_fiscal_name(String new_fiscal_name) {
        this.new_fiscal_name = new_fiscal_name;
    }

    public Integer getNew_class_quarter() {
        return new_class_quarter;
    }

    public void setNew_class_quarter(Integer new_class_quarter) {
        this.new_class_quarter = new_class_quarter;
    }

    public String getContinue_name() {
        return continue_name;
    }

    public void setContinue_name(String continue_name) {
        this.continue_name = continue_name;
    }

    public String getContinue_id() {
        return continue_id;
    }

    public void setContinue_id(String continue_id) {
        this.continue_id = continue_id == null ? null : continue_id.trim();
    }

    public String getsWindowPeriodName() {
        return sWindowPeriodName;
    }

    public void setsWindowPeriodName(String sWindowPeriodName) {
        this.sWindowPeriodName = sWindowPeriodName;
    }

    public String getsWindowPeriodId() {
        return sWindowPeriodId;
    }

    public void setsWindowPeriodId(String sWindowPeriodId) {
        this.sWindowPeriodId = sWindowPeriodId;
    }

    public String getsStageName() {
        return sStageName;
    }

    public void setsStageName(String sStageName) {
        this.sStageName = sStageName;
    }

    public String getsStageId() {
        return sStageId;
    }

    public void setsStageId(String sStageId) {
        this.sStageId = sStageId;
    }

    public String getSbq_config_name() {
        return sbq_config_name;
    }

    public void setSbq_config_name(String sbq_config_name) {
        this.sbq_config_name = sbq_config_name;
    }
}