package com.human.resume.entity;

import java.sql.Timestamp;
import java.util.List;

public class ActFlow {
    
    private Integer id;

    private String flowCode;
    
    private String companyName;
    
    private Timestamp nodeStartTime;
    
    private String positionName;

    private List<ActComent> actComment;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<ActComent> getActComment() {
        return actComment;
    }

    public void setActComment(List<ActComent> actComment) {
        this.actComment = actComment;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Timestamp getNodeStartTime() {
        return nodeStartTime;
    }

    public void setNodeStartTime(Timestamp nodeStartTime) {
        this.nodeStartTime = nodeStartTime;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
}
