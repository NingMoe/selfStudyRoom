package com.human.recruitment.entity;

import java.util.List;

public class PositionProcess {
    private Integer id;

    private Integer positionId;

    private String processDef;
    
    private String oldProcessDef;

    private Integer status;

    private Integer type;
    
    private List<PositionProcessNodeConfig> configList;
    
    private List<PositionProcessScoreItem> scoreItemList;
    
    private String positionProcessName;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getProcessDef() {
        return processDef;
    }

    public void setProcessDef(String processDef) {
        this.processDef = processDef == null ? null : processDef.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<PositionProcessNodeConfig> getConfigList() {
        return configList;
    }

    public void setConfigList(List<PositionProcessNodeConfig> configList) {
        this.configList = configList;
    }

    public List<PositionProcessScoreItem> getScoreItemList() {
        return scoreItemList;
    }

    public void setScoreItemList(List<PositionProcessScoreItem> scoreItemList) {
        this.scoreItemList = scoreItemList;
    }

    public String getPositionProcessName() {
        return positionProcessName;
    }

    public void setPositionProcessName(String positionProcessName) {
        this.positionProcessName = positionProcessName;
    }

    public String getOldProcessDef() {
        return oldProcessDef;
    }

    public void setOldProcessDef(String oldProcessDef) {
        this.oldProcessDef = oldProcessDef;
    }
    
    
    
    
}