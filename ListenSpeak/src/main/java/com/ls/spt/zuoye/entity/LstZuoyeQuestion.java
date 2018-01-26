package com.ls.spt.zuoye.entity;

import java.util.List;

public class LstZuoyeQuestion {
    private Integer id;

    private Integer zuoye_id;

    private Integer question_id;

    private String question_code;
    
    private Integer xh;
    
    private Integer tid;
    
    private String tname;
    
    private String difficulty;
    
    private String topic;
    
    private Integer topic_time;
    
    private String type;
    
    private String sogou_score_type;
    
    private String zdmessage;
    
    private String zd_audio;
    
    private String content_audio;
    
    private List<LstZuoyeStudentAnswer> answer_list;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getQuestion_code() {
        return question_code;
    }

    public void setQuestion_code(String question_code) {
        this.question_code = question_code == null ? null : question_code.trim();
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getTopic_time() {
        return topic_time;
    }

    public void setTopic_time(Integer topic_time) {
        this.topic_time = topic_time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSogou_score_type() {
        return sogou_score_type;
    }

    public void setSogou_score_type(String sogou_score_type) {
        this.sogou_score_type = sogou_score_type;
    }

    public String getZdmessage() {
        return zdmessage;
    }

    public void setZdmessage(String zdmessage) {
        this.zdmessage = zdmessage;
    }

    public String getZd_audio() {
        return zd_audio;
    }

    public void setZd_audio(String zd_audio) {
        this.zd_audio = zd_audio;
    }

    public String getContent_audio() {
        return content_audio;
    }

    public void setContent_audio(String content_audio) {
        this.content_audio = content_audio;
    }

    public List<LstZuoyeStudentAnswer> getAnswer_list() {
        return answer_list;
    }

    public void setAnswer_list(List<LstZuoyeStudentAnswer> answer_list) {
        this.answer_list = answer_list;
    }
}