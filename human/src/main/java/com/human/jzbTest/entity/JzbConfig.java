package com.human.jzbTest.entity;

import java.util.List;

import com.human.front.entity.FastDeliveryEditBase.EducationHistory;

public class JzbConfig {
    private int id;
    /**
     * 试卷月份
     */
    private String paperMonth;
    
    private int mainConfigId;
    /**
     * 答题时间
     */
    private int paperTime;
    /**
     *     题目总数
     */
    private int totalQNum;
    /**
     * 通过线
     */
    private int minTime;
    
    private String zsdName;
    
    private int zsd;
    
    private int diflevel;
    
    private int difficultnum;
    
    private int knowledgeId;
    
    private String tkMonth;
    
   private List<JzbPaperMonthLevel> monthLevel;
    
    public List<JzbPaperMonthLevel> getMonthLevel() {
        return monthLevel;
    }

    public void setMonthLevel(List<JzbPaperMonthLevel> monthLevel) {
        this.monthLevel = monthLevel;
    }

    public String getTkMonth() {
        return tkMonth;
    }

    public void setTkMonth(String tkMonth) {
        this.tkMonth = tkMonth;
    }

    public int getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(int knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public int getId() {
        return id;
    }
    
    public int getMainConfigId() {
        return mainConfigId;
    }

    public void setMainConfigId(int mainConfigId) {
        this.mainConfigId = mainConfigId;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getPaperMonth() {
        return paperMonth;
    }

    public void setPaperMonth(String paperMonth) {
        this.paperMonth = paperMonth;
    }

    public int getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(int paperTime) {
        this.paperTime = paperTime;
    }

    public int getTotalQNum() {
        return totalQNum;
    }

    public void setTotalQNum(int totalQNum) {
        this.totalQNum = totalQNum;
    }

    public int getMinTime() {
        return minTime;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public String getZsdName() {
        return zsdName;
    }

    public void setZsdName(String zsdName) {
        this.zsdName = zsdName;
    }

    public int getZsd() {
        return zsd;
    }

    public void setZsd(int zsd) {
        this.zsd = zsd;
    }

    public int getDiflevel() {
        return diflevel;
    }

    public void setDiflevel(int diflevel) {
        this.diflevel = diflevel;
    }

    public int getDifficultnum() {
        return difficultnum;
    }

    public void setDifficultnum(int difficultnum) {
        this.difficultnum = difficultnum;
    }
    

}
