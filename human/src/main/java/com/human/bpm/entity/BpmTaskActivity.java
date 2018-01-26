package com.human.bpm.entity;

import java.util.List;

/**
 * 任务节点信息
 *
 */
public class BpmTaskActivity {
	
	private String activityId;
	private int x;
	private int y;
	private int width;
	private int height;
	private List<BpmTaskInfo> tasks;
	private boolean curTask;
	private String type;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public List<BpmTaskInfo> getTasks() {
		return tasks;
	}
	public void setTasks(List<BpmTaskInfo> tasks) {
		this.tasks = tasks;
	}
	public boolean isCurTask() {
		return curTask;
	}
	public void setCurTask(boolean curTask) {
		this.curTask = curTask;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
