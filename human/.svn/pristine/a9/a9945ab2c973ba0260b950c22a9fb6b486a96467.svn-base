package com.human.bpm.entity;

import java.sql.Timestamp;
import java.util.List;

public class ActCustomComment {
    private Integer id;

    private String flowCode;

    private String nodeId;
    
    private String nodeName;
    
    /**
     * 1同意 0不同意 2退回 3直接通过
     */
    private Integer result;

    private String comment;
    /**
     * 退回 OR 不同意的原因
     */
    private String backReason;

    /**
     * 审批人帐号
     */
    private String approver;
    
    /**
     * 审批人名称
     */
    private String approverName;

    /**
     * 审批时间
     */
    private Timestamp approveTime;
    
    private List<ActCustomScorce> scorceList;

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
        this.flowCode = flowCode == null ? null : flowCode.trim();
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId == null ? null : nodeId.trim();
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver == null ? null : approver.trim();
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Timestamp getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(Timestamp approveTime) {
        this.approveTime = approveTime;
    }

    public List<ActCustomScorce> getScorceList() {
        return scorceList;
    }

    public void setScorceList(List<ActCustomScorce> scorceList) {
        this.scorceList = scorceList;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
    }
    
}