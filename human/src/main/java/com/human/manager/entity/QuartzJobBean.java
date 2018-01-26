package com.human.manager.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuartzJobBean {
	public static final String STATUS_RUNNING = "1";     
	public static final String STATUS_NOT_RUNNING = "0";     
	public static final String CONCURRENT_IS = "1";     
	public static final String CONCURRENT_NOT = "0";     
	     
	/** 任务id */     
    private String jobId;     
      
    /** 任务名称 */     
    private String jobName;     
      
    /** 任务分组，任务名称+组名称应该是唯一的 */     
    private String jobGroup;     
    
    /** trigger名称 */     
    private String triggerName;     
      
    /** trigger分组，trigger名称+组名称应该是唯一的 */     
    private String triggerGroup;     
      
    /** 任务初始状态 0禁用 1启用 2删除*/     
    private String jobStatus;     
      
    /** 任务是否有状态（并发） */     
	private String isConcurrent = "1";     
	     
    /** 任务运行时间表达式 */     
    private String cronExpression;     
      
    /** 任务描述 */     
    private String description;     
     
    /** 任务调用类在spring中注册的bean id，如果spingId不为空，则按springId查找 */     
    private String targetObject;     
         
    /** 任务调用类名，包名+类名，通过类反射调用 ，如果spingId为空，则按jobClass查找   */     
    private String jobClass;     
         
    /** 任务调用的方法名 */     
	private String targetMethod;     
         
    /** 启动时间 */ 
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Date startTime;     
         
    /** 前一次运行时间 */ 
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Date previousTime;     
         
    /** 下次运行时间 */ 
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    private Date nextTime;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(Date previousTime) {
		this.previousTime = previousTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getTriggerGroup() {
		return triggerGroup;
	}

	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}

	public String getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}
