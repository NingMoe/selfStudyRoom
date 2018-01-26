package com.human.manager.entity;

import java.util.Date;

/**
 *  操作日志实体
 * @author HF-121093-01
 *
 */
public class OplogEntity {
    
    /**
     * 主键
     */
    private Long id;

    /**
     * 操作用户帐号
     */
    private String opUser;
    
    /**
     * 操作用户名称
     */
    private String opUserName;
    
    /**
     * 操作时间
     */
    private Date opTime;

    /**
     * 操作IP
     */
    private String opIp;
    
    /**
     * 操作类型:0.查询，1.新增，2.修改，3.删除,4.导入，5.导出，6，上传，7.下载
     */
    private Integer opType;
    
    /**
     * 操作说明
     */
    private String opDesc;
    
    /**
     * 下面两个工查询使用
     */
    /**
     * 查询开始时间
     */
    private String startTime;
    
    /**
     * 查询结束时间
     */
    private String endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpUser() {
        return opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    public String getOpUserName() {
        return opUserName;
    }

    public void setOpUserName(String opUserName) {
        this.opUserName = opUserName;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public Integer getOpType() {
        return opType;
    }

    public void setOpType(Integer opType) {
        this.opType = opType;
    }

    public String getOpDesc() {
        return opDesc;
    }

    public void setOpDesc(String opDesc) {
        this.opDesc = opDesc;
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