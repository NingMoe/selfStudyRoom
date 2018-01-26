package com.human.teacherservice.entity;

import java.util.Date;

public class LibBookError {
    private Integer id;

    private String school_id;

    private Integer book_id;

    private String book_name;
    
    private Integer book_type;
    
    private String type_name;

    private Integer report_empl_id;

    private String report_name;

    private Date report_time;

    private String report_phone;
    
    private Integer valid;
    
    private Date left_report_time;
    
    private Date right_report_time;

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

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name == null ? null : book_name.trim();
    }

    public Integer getBook_type() {
        return book_type;
    }

    public void setBook_type(Integer book_type) {
        this.book_type = book_type;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getReport_empl_id() {
        return report_empl_id;
    }

    public void setReport_empl_id(Integer report_empl_id) {
        this.report_empl_id = report_empl_id;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name == null ? null : report_name.trim();
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public String getReport_phone() {
        return report_phone;
    }

    public void setReport_phone(String report_phone) {
        this.report_phone = report_phone == null ? null : report_phone.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getLeft_report_time() {
        return left_report_time;
    }

    public void setLeft_report_time(Date left_report_time) {
        this.left_report_time = left_report_time;
    }

    public Date getRight_report_time() {
        return right_report_time;
    }

    public void setRight_report_time(Date right_report_time) {
        this.right_report_time = right_report_time;
    }

    public String toString() {
        return "id=" + id + ", school_id=" + school_id + ", book_id=" + book_id + ", book_name=" + book_name + ", book_type=" + book_type + ", type_name=" + type_name
                + ", report_empl_id=" + report_empl_id + ", report_name=" + report_name + ", report_time=" + report_time + ", report_phone=" + report_phone + ", valid=" + valid + ", left_report_time="
                + left_report_time + ", right_report_time=" + right_report_time;
    }
    
}