package com.human.ielts.entity;

import java.util.Date;

public class IeltsTeacherFeedback {
    
    private Integer id;
    private Integer teacher_id;

    private Integer feedback_num;
    
    private Integer time_month;

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

    public Integer getFeedback_num() {
        return feedback_num;
    }

    public void setFeedback_num(Integer feedback_num) {
        this.feedback_num = feedback_num;
    }

    public Integer getTime_month() {
        return time_month;
    }

    public void setTime_month(Integer time_month) {
        this.time_month = time_month;
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