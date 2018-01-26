package com.human.continuation.entity;

import java.util.List;

public class TeacherContinuationTeachername {
    private Integer id;

    private String class_code;
    
    private String class_name;
    
    private String email_addr;
    
    private String all_teacher_name;

    private String teacher_name;
    
    private List<String> teacher_name_list;
    
    private String is_add;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        this.class_name = class_name;
    }

    public String getEmail_addr() {
        return email_addr;
    }

    public void setEmail_addr(String email_addr) {
        this.email_addr = email_addr;
    }

    public String getAll_teacher_name() {
        return all_teacher_name;
    }

    public void setAll_teacher_name(String all_teacher_name) {
        this.all_teacher_name = all_teacher_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name == null ? null : teacher_name.trim();
    }

    public List<String> getTeacher_name_list() {
        return teacher_name_list;
    }

    public void setTeacher_name_list(List<String> teacher_name_list) {
        this.teacher_name_list = teacher_name_list;
    }

    public String getIs_add() {
        return is_add;
    }

    public void setIs_add(String is_add) {
        this.is_add = is_add;
    }
}