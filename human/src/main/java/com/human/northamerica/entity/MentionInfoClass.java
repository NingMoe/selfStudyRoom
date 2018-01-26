package com.human.northamerica.entity;

import java.util.List;

public class MentionInfoClass {
    
    private Long id;
    
    private Long infoId;
    
    private String roomCode;
    
    private Integer type;
    
    private String startTime;
    
    private String endTime;
    
    private List<ClassTeach> ctList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<ClassTeach> getCtList() {
        return ctList;
    }

    public void setCtList(List<ClassTeach> ctList) {
        this.ctList = ctList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
