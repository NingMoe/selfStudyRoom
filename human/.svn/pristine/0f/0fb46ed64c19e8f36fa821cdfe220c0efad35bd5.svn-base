package com.human.manager.service;


import java.util.Map;

import org.quartz.SchedulerException;

import com.human.manager.entity.QuartzJobBean;
import com.human.utils.PageView;

public interface TaskJobService {

	PageView  getAllJobs(PageView pager) throws SchedulerException;
	
	 boolean pauseJob(QuartzJobBean jobBean);
	 
	 boolean resumeJob(QuartzJobBean jobBean);
	 
	 boolean testJob(QuartzJobBean scheduleJob);
	 
	 boolean updateCronExpression(QuartzJobBean scheduleJob);

	Map<String, Object> addJob(QuartzJobBean jobBean);

	boolean deleteJob(QuartzJobBean jobBean);
}
