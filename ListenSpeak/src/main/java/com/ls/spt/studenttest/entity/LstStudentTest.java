package com.ls.spt.studenttest.entity;

import java.math.BigDecimal;
import java.util.Date;

public class LstStudentTest {
    private Integer id;

    private Integer student_id;
    
    private String student_name;

    private Integer test_id;

    private Integer paper_id;
    
    private String class_code;
    
    private String class_name;

    private String test_name;
    
    private Integer dati_num;

    private Integer test_num;

    private Integer test_end_num;

    private Date end_time;
    
    private Date open_time;

    private Date submission_time;

    private Integer continue_time;
    
    private String create_user;
    
    private Date creaate_time;
    
    private BigDecimal accuracy_sogou;

    private BigDecimal fluency_sogou;

    private BigDecimal integrity_sogou;

    private BigDecimal overall_sogou;

    private BigDecimal accuracy_teacher;

    private BigDecimal fluency_teacher;

    private BigDecimal integrity_teacher;

    private BigDecimal overall_teacher;
    
    private String status;
    
    private String isEnd;
    
    private String isQuestionEnd;
    
    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public String getIsQuestionEnd() {
        return isQuestionEnd;
    }

    public void setIsQuestionEnd(String isQuestionEnd) {
        this.isQuestionEnd = isQuestionEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name == null ? null : test_name.trim();
    }

    public Integer getTest_num() {
        return test_num;
    }

    public void setTest_num(Integer test_num) {
        this.test_num = test_num;
    }

    public Integer getTest_end_num() {
        return test_end_num;
    }

    public void setTest_end_num(Integer test_end_num) {
        this.test_end_num = test_end_num;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getOpen_time() {
        return open_time;
    }

    public void setOpen_time(Date open_time) {
        this.open_time = open_time;
    }

    public Date getSubmission_time() {
        return submission_time;
    }

    public void setSubmission_time(Date submission_time) {
        this.submission_time = submission_time;
    }

    public Integer getContinue_time() {
        return continue_time;
    }

    public void setContinue_time(Integer continue_time) {
        this.continue_time = continue_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Date getCreaate_time() {
        return creaate_time;
    }

    public void setCreaate_time(Date creaate_time) {
        this.creaate_time = creaate_time;
    }

    public BigDecimal getAccuracy_sogou() {
        return accuracy_sogou;
    }

    public void setAccuracy_sogou(BigDecimal accuracy_sogou) {
        this.accuracy_sogou = accuracy_sogou;
    }

    public BigDecimal getFluency_sogou() {
        return fluency_sogou;
    }

    public void setFluency_sogou(BigDecimal fluency_sogou) {
        this.fluency_sogou = fluency_sogou;
    }

    public BigDecimal getIntegrity_sogou() {
        return integrity_sogou;
    }

    public void setIntegrity_sogou(BigDecimal integrity_sogou) {
        this.integrity_sogou = integrity_sogou;
    }

    public BigDecimal getOverall_sogou() {
        return overall_sogou;
    }

    public void setOverall_sogou(BigDecimal overall_sogou) {
        this.overall_sogou = overall_sogou;
    }

    public BigDecimal getAccuracy_teacher() {
        return accuracy_teacher;
    }

    public void setAccuracy_teacher(BigDecimal accuracy_teacher) {
        this.accuracy_teacher = accuracy_teacher;
    }

    public BigDecimal getFluency_teacher() {
        return fluency_teacher;
    }

    public void setFluency_teacher(BigDecimal fluency_teacher) {
        this.fluency_teacher = fluency_teacher;
    }

    public BigDecimal getIntegrity_teacher() {
        return integrity_teacher;
    }

    public void setIntegrity_teacher(BigDecimal integrity_teacher) {
        this.integrity_teacher = integrity_teacher;
    }

    public BigDecimal getOverall_teacher() {
        return overall_teacher;
    }

    public void setOverall_teacher(BigDecimal overall_teacher) {
        this.overall_teacher = overall_teacher;
    }

    public Integer getDati_num() {
        return dati_num;
    }

    public void setDati_num(Integer dati_num) {
        this.dati_num = dati_num;
    }
    
    
}