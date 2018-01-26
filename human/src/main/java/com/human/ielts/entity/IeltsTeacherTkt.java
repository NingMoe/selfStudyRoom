package com.human.ielts.entity;

import java.util.Date;

public class IeltsTeacherTkt {
    
    private Integer id;
    
    private Integer teacher_id;

    private Double tkt_scoreone;

    private Double tkt_scoretwo;

    private Double tkt_scorethree;

    private Double tkt_scorefour;

    private Date create_time;

    private Integer valid;
    
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

    public Double getTkt_scoreone() {
        return tkt_scoreone;
    }

    public void setTkt_scoreone(Double tkt_scoreone) {
        this.tkt_scoreone = tkt_scoreone;
    }

    public Double getTkt_scoretwo() {
        return tkt_scoretwo;
    }

    public void setTkt_scoretwo(Double tkt_scoretwo) {
        this.tkt_scoretwo = tkt_scoretwo;
    }

    public Double getTkt_scorethree() {
        return tkt_scorethree;
    }

    public void setTkt_scorethree(Double tkt_scorethree) {
        this.tkt_scorethree = tkt_scorethree;
    }

    public Double getTkt_scorefour() {
        return tkt_scorefour;
    }

    public void setTkt_scorefour(Double tkt_scorefour) {
        this.tkt_scorefour = tkt_scorefour;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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