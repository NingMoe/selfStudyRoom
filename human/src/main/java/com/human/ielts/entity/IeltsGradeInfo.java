package com.human.ielts.entity;

import java.util.Date;

public class IeltsGradeInfo {
    private Integer id;

    private Date create_time;

    private Integer valid;

    private byte[] grade_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public byte[] getGrade_name() {
        return grade_name;
    }

    public void setGrade_name(byte[] grade_name) {
        this.grade_name = grade_name;
    }
}