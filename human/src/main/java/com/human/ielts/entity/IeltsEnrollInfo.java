package com.human.ielts.entity;

import java.util.Date;

public class IeltsEnrollInfo {
    private Integer id;

    private Integer student_id;

    private Double total;

    private Double listening;

    private Double reading;

    private Double writing;

    private Double oral;

    private Date test_time;
    
    private Date left_test_time;
    
    private Date right_test_time;
    
    private Integer is_target;

    private Integer valid;

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getListening() {
        return listening;
    }

    public void setListening(Double listening) {
        this.listening = listening;
    }

    public Double getReading() {
        return reading;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }

    public Double getWriting() {
        return writing;
    }

    public void setWriting(Double writing) {
        this.writing = writing;
    }

    public Double getOral() {
        return oral;
    }

    public void setOral(Double oral) {
        this.oral = oral;
    }

    public Date getTest_time() {
        return test_time;
    }

    public void setTest_time(Date test_time) {
        this.test_time = test_time;
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

    public Integer getIs_target() {
        return is_target;
    }

    public void setIs_target(Integer is_target) {
        this.is_target = is_target;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}