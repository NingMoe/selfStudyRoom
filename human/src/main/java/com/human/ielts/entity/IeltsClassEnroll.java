package com.human.ielts.entity;

import java.util.Date;

public class IeltsClassEnroll {
    private Integer id;

    private Integer student_id;

    private Integer class_type_id;
    
    private String class_type_name;

    private Integer valid;
    
    private Integer student_count;
    
    private Date left_test_time;
    
    private Date right_test_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getClass_type_id() {
        return class_type_id;
    }

    public void setClass_type_id(Integer class_type_id) {
        this.class_type_id = class_type_id;
    }

    public String getClass_type_name() {
        return class_type_name;
    }

    public void setClass_type_name(String class_type_name) {
        this.class_type_name = class_type_name;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getStudent_count() {
        return student_count;
    }

    public void setStudent_count(Integer student_count) {
        this.student_count = student_count;
    }

    public Date getLeft_test_time() {
        return left_test_time;
    }

    public void setLeft_test_time(Date left_test_time) {
        this.left_test_time = left_test_time;
    }

    public Date getRight_test_time() {
        return right_test_time;
    }

    public void setRight_test_time(Date right_test_time) {
        this.right_test_time = right_test_time;
    }
}