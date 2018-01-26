package com.human.jzbTest.entity;

import java.util.List;

public class JzbPaperMonthConfig {
    private Integer id;

    private Integer mainConfigId;

    private String month;

    private Integer paperTime;

    private Integer totalQNum;

    private int minTime;
    
    private jzbPaperMainConfig mainConfig;
    
    private String preCode;
    
    private String examName;
    
    private String tkMonth;
    
    private int status;
    
    private int passQNum;
    
    private List<JzbPaperMonthLevel> level;
    
    public List<JzbPaperMonthLevel> getLevel() {
        return level;
    }

    public void setLevel(List<JzbPaperMonthLevel> level) {
        this.level = level;
    }

    public void setMinTime(int minTime) {
        this.minTime = minTime;
    }

    public int getPassQNum() {
        return passQNum;
    }

    public void setPassQNum(int passQNum) {
        this.passQNum = passQNum;
    }

    public Integer getMinTime() {
        return minTime;
    }

    public void setMinTime(Integer minTime) {
        this.minTime = minTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTkMonth() {
        return tkMonth;
    }

    public void setTkMonth(String tkMonth) {
        this.tkMonth = tkMonth;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainConfigId() {
        return mainConfigId;
    }

    public void setMainConfigId(Integer mainConfigId) {
        this.mainConfigId = mainConfigId;
    }
    

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getPaperTime() {
        return paperTime;
    }

    public void setPaperTime(Integer paperTime) {
        this.paperTime = paperTime;
    }

    public Integer getTotalQNum() {
        return totalQNum;
    }

    public void setTotalQNum(Integer totalQNum) {
        this.totalQNum = totalQNum;
    }

    
    public jzbPaperMainConfig getMainConfig() {
        return mainConfig;
    }

    public void setMainConfig(jzbPaperMainConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    public String getPreCode() {
        return preCode;
    }

    public void setPreCode(String preCode) {
        this.preCode = preCode;
    }
    
}