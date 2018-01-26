package com.human.jzbTest.entity;

public class JzbPaperMainMessage {
    private Integer id;

    private Integer mainConfigId;

    private Integer dicId;

    private String message;
    
    private String name;
    
    private Integer dataValue;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDataValue() {
        return dataValue;
    }

    public void setDataValue(Integer dataValue) {
        this.dataValue = dataValue;
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

    public Integer getDicId() {
        return dicId;
    }

    public void setDicId(Integer dicId) {
        this.dicId = dicId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}