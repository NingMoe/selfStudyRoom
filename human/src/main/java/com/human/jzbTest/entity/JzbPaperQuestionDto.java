package com.human.jzbTest.entity;

public class JzbPaperQuestionDto {
    
    private Integer id;
    //对应答卷Id
    private Integer paperId;
    //题目Id
    private Integer questionId;
    //正确答案Id
    private Integer answerId;
    //排序
    private Integer sort;
    //学生回答Id
    private Integer stuAnswer;
    //题目类型 1 单选  2为带大标题的（完形填空、阅读理解）
    private String qType;
    //题目内容
    private String qContent;
    //当知识点为阅读理解、完型填空时录入的短文
    private String qMainDesc;
    //题目编码
    private String qCode;
    //正确答案内容
    private String aContent1;
    //正确答案序号
    private String xh1;
    //学生答案内容
    private String aContent2;
    //学生答案序号
    private String xh2;
    
    private String aImg1;
    
    private String aImg2;
    
    

    public String getaImg1() {
        return aImg1;
    }

    public void setaImg1(String aImg1) {
        this.aImg1 = aImg1;
    }

    public String getaImg2() {
        return aImg2;
    }

    public void setaImg2(String aImg2) {
        this.aImg2 = aImg2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStuAnswer() {
        return stuAnswer;
    }

    public void setStuAnswer(Integer stuAnswer) {
        this.stuAnswer = stuAnswer;
    }
            
    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType;
    }

    public String getqContent() {
        return qContent;
    }

    public void setqContent(String qContent) {
        this.qContent = qContent;
    }

    public String getqMainDesc() {
        return qMainDesc;
    }

    public void setqMainDesc(String qMainDesc) {
        this.qMainDesc = qMainDesc;
    }
                
    public String getqCode() {
        return qCode;
    }

    public void setqCode(String qCode) {
        this.qCode = qCode;
    }

    public String getaContent1() {
        return aContent1;
    }

    public void setaContent1(String aContent1) {
        this.aContent1 = aContent1;
    }

    public String getXh1() {
        return xh1;
    }

    public void setXh1(String xh1) {
        this.xh1 = xh1;
    }

    public String getaContent2() {
        return aContent2;
    }

    public void setaContent2(String aContent2) {
        this.aContent2 = aContent2;
    }

    public String getXh2() {
        return xh2;
    }

    public void setXh2(String xh2) {
        this.xh2 = xh2;
    }
    
    
    
    
    
    
    
    
}