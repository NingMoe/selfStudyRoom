package com.human.bpm.entity;

import java.util.Date;

public class BpmProcessInstance {
	protected String name;
	protected String procInstId;
	protected String businessKey;
	protected String procDefId;
	protected Date startTime;
	protected Date endTime;
	protected int duration;
	protected String deleteReason;
	protected String viewUrl;
	protected String curUser;

	protected Date draftDate;// 起草时间
	protected Date createTime;// 任务创建时间即到达时间

	protected String businessDesc;
	protected String businessModule;

	protected String curActName;

	protected boolean end;
	
	private String busiView;
	public Date getDraftDate() {
		return draftDate;
	}

	public void setDraftDate(Date draftDate) {
		this.draftDate = draftDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public String getBusinessModule() {
		return businessModule;
	}

	public void setBusinessModule(String businessModule) {
		this.businessModule = businessModule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getStatus() {
		if (deleteReason != null) {
			return "运行中被删除";
		}
		if (endTime != null) {
			return "已办结";
		}

		return "运行中";
	}

	public boolean isEnd() {
		if (endTime != null || end) {
			return true;
		}
		return false;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public String getViewUrl() {
		return viewUrl;
	}

	public void setViewUrl(String viewUrl) {
		this.viewUrl = viewUrl;
	}

	public String getCurUser() {
		return curUser;
	}

	public void setCurUser(String curUser) {
		this.curUser = curUser;
	}

	public String getCurActName() {
		return curActName;
	}

	public void setCurActName(String curActName) {
		this.curActName = curActName;
	}

	public String getBusiView() {
		return busiView;
	}

	public void setBusiView(String busiView) {
		this.busiView = busiView;
	}

}
