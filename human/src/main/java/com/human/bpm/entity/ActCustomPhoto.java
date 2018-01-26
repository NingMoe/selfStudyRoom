package com.human.bpm.entity;

import java.sql.Timestamp;

public class ActCustomPhoto {
   
    private Long id;

    private String flowCode;

    private String nodeId;

    /**
     * 审批人帐号
     */
    private String approver;
    
    /**
     * 审批时间
     */
    private Timestamp createTime;
    
    private String photoUrl;
    
    private String miniPhotoUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getMiniPhotoUrl() {
        return miniPhotoUrl;
    }

    public void setMiniPhotoUrl(String miniPhotoUrl) {
        this.miniPhotoUrl = miniPhotoUrl;
    }
}
