package com.human.bpm.entity;

import java.util.List;

import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessUser;

public class BpmNodeConfig {
    /**
     * 普通节点
     */
    public static final String NODETYPE_P = "1";
    
    /**
     * 用户组
     */
    public static final String NODETYPE_G = "2";
    
    /**
     * 会签
     */
    public static final String NODETYPE_M = "3";
    
    private String nodeId;

    private String processdefId;

    private String processName;

    private String nodeName;

    private String nodeType;

    private String assigneeExp;

    private Integer sort;
    
    private String positionName;
    
    private List<PositionProcessUser> users;

    private PositionProcessNodeConfig positionNodeConfig;

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public String getProcessdefId() {
        return processdefId;
    }

    public void setProcessdefId(String processdefId) {
        this.processdefId = processdefId == null ? null : processdefId.trim();
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName == null ? null : processName.trim();
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType == null ? null : nodeType.trim();
    }

    public String getAssigneeExp() {
        return assigneeExp;
    }

    public void setAssigneeExp(String assigneeExp) {
        this.assigneeExp = assigneeExp == null ? null : assigneeExp.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<PositionProcessUser> getUsers() {
        return users;
    }

    public void setUsers(List<PositionProcessUser> users) {
        this.users = users;
    }

    public PositionProcessNodeConfig getPositionNodeConfig() {
        return positionNodeConfig;
    }

    public void setPositionNodeConfig(PositionProcessNodeConfig positionNodeConfig) {
        this.positionNodeConfig = positionNodeConfig;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
}