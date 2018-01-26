package com.human.continuation.entity;

import java.util.Date;

public class TeacherContinuationClass {
    private Integer id;

    private String school_id;

    private String email_addr;

    private String student_name;

    private String phone;

    private String student_code;
    
    private String old_class_code;

    private String class_code;
    
    private String update_user;

    private Date update_time;
    
    private Integer update_type;
    
    private String update_time_left;
    
    private String update_time_right;

    private Integer is_add;

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

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr == null ? null : email_addr.trim();
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name == null ? null : student_name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code == null ? null : student_code.trim();
    }

    public String getOld_class_code() {
        return old_class_code;
    }

    public void setOld_class_code(String old_class_code) {
        this.old_class_code = old_class_code;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code == null ? null : class_code.trim();
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(Integer update_type) {
        this.update_type = update_type;
    }

    public String getUpdate_time_left() {
        return update_time_left;
    }

    public void setUpdate_time_left(String update_time_left) {
        this.update_time_left = update_time_left;
    }

    public String getUpdate_time_right() {
        return update_time_right;
    }

    public void setUpdate_time_right(String update_time_right) {
        this.update_time_right = update_time_right;
    }

    public Integer getIs_add() {
        return is_add;
    }

    public void setIs_add(Integer is_add) {
        this.is_add = is_add;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((student_code == null) ? 0 : student_code.hashCode());
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
        TeacherContinuationClass other = (TeacherContinuationClass) obj;
        if (student_code == null) {
            if (other.student_code != null)
                return false;
        }
        else if (!student_code.equals(other.student_code))
            return false;
        return true;
    }

}