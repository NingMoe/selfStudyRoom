package com.ls.spt.zuoye.entity;

import java.math.BigDecimal;

public class LstZuoyeStudentAnswer {
    private Integer id;

    private Integer student_id;

    private Integer zuoye_id;
    
    private String zuoye_name;
    
    private String class_code;
    
    private Integer question_id;

    private String student_answer;
    
    private String parse_text;
    
    private String content_keys;
    
    private String isEnd;

    private BigDecimal accuracy_sogou;

    private BigDecimal fluency_sogou;

    private BigDecimal integrity_sogou;

    private BigDecimal overall_sogou;

    private BigDecimal accuracy_teacher;

    private BigDecimal fluency_teacher;

    private BigDecimal integrity_teacher;

    private BigDecimal overall_teacher;
    
    private String question_code;
    
    private Integer xh;
    
    private String difficulty;
    
    private String name;
    
    private String parse_audio;
    
    private String content;
    
    private String answer_comment;
    
    private Integer total_question_num;
    
    
    
    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public Integer getTotal_question_num() {
        return total_question_num;
    }

    public void setTotal_question_num(Integer total_question_num) {
        this.total_question_num = total_question_num;
    }

    public String getZuoye_name() {
        return zuoye_name;
    }

    public void setZuoye_name(String zuoye_name) {
        this.zuoye_name = zuoye_name;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
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

    public Integer getZuoye_id() {
        return zuoye_id;
    }

    public void setZuoye_id(Integer zuoye_id) {
        this.zuoye_id = zuoye_id;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getStudent_answer() {
        return student_answer;
    }

    public void setStudent_answer(String student_answer) {
        this.student_answer = student_answer;
    }

    public String getParse_text() {
        return parse_text;
    }

    public void setParse_text(String parse_text) {
        this.parse_text = parse_text;
    }

    public String getContent_keys() {
        return content_keys;
    }

    public void setContent_keys(String content_keys) {
        this.content_keys = content_keys;
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

    public String getQuestion_code() {
        return question_code;
    }

    public void setQuestion_code(String question_code) {
        this.question_code = question_code;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParse_audio() {
        return parse_audio;
    }

    public void setParse_audio(String parse_audio) {
        this.parse_audio = parse_audio;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer_comment() {
        return answer_comment;
    }

    public void setAnswer_comment(String answer_comment) {
        this.answer_comment = answer_comment;
    }
}