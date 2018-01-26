package com.human.bpm.entity;

import java.util.Date;

/**
 * 审批意见
 *
 */
public class BpmComment{
	
	private String id;
	/**
	 * 业务单据KEY
	 */
	private String businessKey;
	/**
	 * 任务ID
	 */
	private String taskId;
	/**
	 * 任务名称
	 */
	private String taskName;
	
	/**
	 * 流程实例ID
	 */
	private String processInstanceId;
	
	/**
	 * 审批结果
	 */
	private String approve;
	/**
	 * 审批意见
	 */
	private String comment;
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 姓名
	 */
	private String userName;
	
	/**
	 * 任务查看时间
	 */
	private Date taskShowTime;
	
	/**
	 * 任务接收时间
	 */
	private Date taskStartTime;
	
	/**
	 * 任务结束时间
	 */
	private Date taskEndTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getTaskShowTime() {
        return taskShowTime;
    }
    public void setTaskShowTime(Date taskShowTime) {
        this.taskShowTime = taskShowTime;
    }
    public Date getTaskStartTime() {
		return taskStartTime;
	}
	public void setTaskStartTime(Date taskStartTime) {
		this.taskStartTime = taskStartTime;
	}
	public Date getTaskEndTime() {
		return taskEndTime;
	}
	public void setTaskEndTime(Date taskEndTime) {
		this.taskEndTime = taskEndTime;
	}
	public String getBusinessKey() {
		return businessKey;
	}
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}
