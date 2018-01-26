package com.human.bpm.entity;

import java.util.List;

/**
 * 流程节点用户(选择分支和人员使用)
 *
 */
public class BpmOutgo {
	/**
	 * 退回操作标识
	 */
	public static final String OPER_TYPE_BACK = "back";
	private String outgoId;
	private String outgoName;
	private String destination;
	private String destinationName;
	
	private String userVar;//用户变量
	private String userVarVal;//用户变量的值
	private boolean sel;//是否选择人员
	private int userType;//1:个人;2:候选人;3:组
	private boolean end;//是否是归档
	private List<BpmTaskUser> taskUsers;
	
	private String actType;//activity类型
	
	private List<BpmOutgo> outgos;//需要后续确定人员的环节，一般在等待节点和java节点之后才会出现
	
	private String operType;//back等
	public BpmOutgo(){
	}
	
	public String getOutgoId() {
		return outgoId;
	}

	public void setOutgoId(String outgoId) {
		this.outgoId = outgoId;
	}

	public String getOutgoName() {
		return outgoName;
	}

	public void setOutgoName(String outgoName) {
		this.outgoName = outgoName;
	}

	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isSel() {
		return sel;
	}
	public void setSel(boolean sel) {
		this.sel = sel;
	}
	public List<BpmTaskUser> getTaskUsers() {
		return taskUsers;
	}
	public void setTaskUsers(List<BpmTaskUser> taskUsers) {
		this.taskUsers = taskUsers;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getUserVarVal() {
		return userVarVal;
	}

	public void setUserVarVal(String userVarVal) {
		this.userVarVal = userVarVal;
	}

	public String getUserVar() {
		return userVar;
	}

	public void setUserVar(String userVar) {
		this.userVar = userVar;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public List<BpmOutgo> getOutgos() {
		return outgos;
	}

	public void setOutgos(List<BpmOutgo> outgos) {
		this.outgos = outgos;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}
}
