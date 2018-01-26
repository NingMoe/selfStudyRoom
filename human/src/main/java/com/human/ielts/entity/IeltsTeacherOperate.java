package com.human.ielts.entity;

import java.util.Date;

public class IeltsTeacherOperate {
    
    private Integer id;
    
    private Integer teacher_id;

    private Integer operate_num;

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

    public Integer getOperate_num() {
        return operate_num;
    }

    public void setOperate_num(Integer operate_num) {
        this.operate_num = operate_num;
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