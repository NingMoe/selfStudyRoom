package com.ls.spt.question.entity;

public class QuestionVoiceParam {
    
    private long currentTime;
    
    private Integer currentIndex;
    
    private Integer currentSeqNo;
    
    
    /**
     * SOGOU接口题型
     */
    private String scoreType;
    
    /**
     * 单词、短语、句子、短文朗诵为题干   情景对话、话题简述传解析
     */
    private String refText;
    
    /**
     * 情景对话传的KEY
     */
    private String key;
    
    /**
     * 情景对话传的源问题
     */
    private String content;
    
    
    /**
     * 情景对话传的短文
     */
    private String topic;
    
    /**
     * 学生ID
     */
    private Integer student_id;
    
    /**
     * lst_student_test表ID
     */
    private Integer student_test_id;
    
    /**
     * 考试ID
     */
    private Integer test_id;
    
    /**
     * 作业ID
     */
    private Integer zuoye_id;
    
    /**
     * 班级编码
     */
    private String class_code;
    
    /**
     * 题目ID
     */
    private Integer question_id;
    
    /**
     * 是否结束（最后一题）
     */
    private String isEnd;
    
    /**
     * 此题是否结束
     */
    private String isQuestionEnd;
    
    
    

    public Integer getCurrentSeqNo() {
        return currentSeqNo;
    }

    public void setCurrentSeqNo(Integer currentSeqNo) {
        this.currentSeqNo = currentSeqNo;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Integer getStudent_test_id() {
        return student_test_id;
    }

    public void setStudent_test_id(Integer student_test_id) {
        this.student_test_id = student_test_id;
    }

    public String getIsQuestionEnd() {
        return isQuestionEnd;
    }

    public void setIsQuestionEnd(String isQuestionEnd) {
        this.isQuestionEnd = isQuestionEnd;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }

    public Integer getZuoye_id() {
        return zuoye_id;
    }

    public void setZuoye_id(Integer zuoye_id) {
        this.zuoye_id = zuoye_id;
    }

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }

    public String getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getRefText() {
        return refText;
    }

    public void setRefText(String refText) {
        this.refText = refText;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
