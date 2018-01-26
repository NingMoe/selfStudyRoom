package com.human.jzbTest.entity;

public class JzbQuestionAnswer {
	private Integer id;
    /**
     * 对应题目ID
     */
    private Integer questionId;
    /**
     * 题干
     */
    private String qContent;
    
    /**
     * 答案
     */
    private String aContent;
    
    /**
     * 序号
     */
    private String xh;
    
    private String aImg;
    
    /**
     * 是否正确答案
     */
    private String isCorrect;
    
    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getaImg() {
        return aImg;
    }

    public void setaImg(String aImg) {
        this.aImg = aImg;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
