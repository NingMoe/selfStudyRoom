package com.human.rank.entity;

import java.util.Date;

import com.human.pulgin.excel.ExcelAnnotation;
import com.human.pulgin.excel.ExportTitleAnnotation;

public class RankClasses {
    private Integer id;

    private Integer rankinfo_id;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="班号")
    private String class_code;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="班级名称")
    private String class_name;

    @ExportTitleAnnotation()
    @ExcelAnnotation(exportName="教师名称")
    private String teacher_name;
    
    private String class_type_name;
    
    private String subject;
    
    private String quarter;
    
    private String grade;
    
    private Integer max_count;
    
    private Integer current_count;
    
    private Integer last_count;
    
    private Integer now_count;
    
    private Date update_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRankinfo_id() {
        return rankinfo_id;
    }

    public void setRankinfo_id(Integer rankinfo_id) {
        this.rankinfo_id = rankinfo_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code == null ? null : class_code.trim();
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name == null ? null : class_name.trim();
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name == null ? null : teacher_name.trim();
    }

    public String getClass_type_name() {
        return class_type_name;
    }

    public void setClass_type_name(String class_type_name) {
        this.class_type_name = class_type_name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getMax_count() {
        return max_count;
    }

    public void setMax_count(Integer max_count) {
        this.max_count = max_count;
    }

    public Integer getCurrent_count() {
        return current_count;
    }

    public void setCurrent_count(Integer current_count) {
        this.current_count = current_count;
    }

    public Integer getLast_count() {
        return last_count;
    }

    public void setLast_count(Integer last_count) {
        this.last_count = last_count;
    }

    public Integer getNow_count() {
        return now_count;
    }

    public void setNow_count(Integer now_count) {
        this.now_count = now_count;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((class_code == null) ? 0 : class_code.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RankClasses other = (RankClasses) obj;
        if (class_code == null) {
            if (other.class_code != null)
                return false;
        }
        else if (!class_code.equals(other.class_code))
            return false;
        return true;
    }
    
}