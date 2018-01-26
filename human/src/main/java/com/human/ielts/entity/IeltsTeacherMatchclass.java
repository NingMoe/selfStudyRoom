package com.human.ielts.entity;

import java.util.Date;

public class IeltsTeacherMatchclass {
    
    private Integer id;
    
    private Integer teacher_id;

    private String matchclass_name;

    private Integer matchclass_type;

    private Integer matchclass_grade;

    private Date create_time;

    private Date left_integral_time;
    
    private Date right_integral_time;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getMatchclass_name() {
        return matchclass_name;
    }

    public void setMatchclass_name(String matchclass_name) {
        this.matchclass_name = matchclass_name == null ? null : matchclass_name.trim();
    }

    public Integer getMatchclass_type() {
        return matchclass_type;
    }

    public void setMatchclass_type(Integer matchclass_type) {
        this.matchclass_type = matchclass_type;
    }

    public Integer getMatchclass_grade() {
        return matchclass_grade;
    }

    public void setMatchclass_grade(Integer matchclass_grade) {
        this.matchclass_grade = matchclass_grade;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLeft_integral_time() {
        return left_integral_time;
    }

    public void setLeft_integral_time(Date left_integral_time) {
        this.left_integral_time = left_integral_time;
    }

    public Date getRight_integral_time() {
        return right_integral_time;
    }

    public void setRight_integral_time(Date right_integral_time) {
        this.right_integral_time = right_integral_time;
    }
}