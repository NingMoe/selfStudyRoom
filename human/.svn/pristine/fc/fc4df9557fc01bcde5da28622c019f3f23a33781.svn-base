package com.human.manager.controller;

import java.util.Map;
import javax.annotation.Resource;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.manager.entity.QuartzJobBean;
import com.human.manager.service.OpLogService;
import com.human.manager.service.TaskJobService;
import com.human.utils.PageView;


@Controller
@RequestMapping("/manager/trigger/")
public class ManagerTaskController {
	
	@Resource  
	private TaskJobService taskJobService; 
	 
	@Resource
	private OpLogService opLogService;
	 
	@RequestMapping(value="list")
	public  ModelAndView view(){
		return new ModelAndView("/manager/trigger/list");
	}
	
	/**
	 * 分页查询
	 * @param model
	 * @param resources
	 * @param pageNow
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="query")
	@ResponseBody
	public PageView query(PageView pageView) throws SchedulerException{
		return  taskJobService.getAllJobs(pageView);
	}
	
	/**
	 * 暂停定时任务
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="pauseJob")
	@ResponseBody
	public boolean pauseJob(QuartzJobBean jobBean){
	    opLogService.insertOpLog(2, "任务管理:用户暂停了["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.pauseJob(jobBean);
	}
	
	/**
	 * 恢复暂停定时任务
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="resumeJob")
	@ResponseBody
	public boolean resumeJob(QuartzJobBean jobBean){
	    opLogService.insertOpLog(2, "任务管理:用户恢复了["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.resumeJob(jobBean);
	}

	/**
	 * 立即触发定时任务
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="testJob")
	@ResponseBody
	public boolean testJob(QuartzJobBean jobBean){
	    opLogService.insertOpLog(2, "任务管理:手动触发了["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.testJob(jobBean);
	}

	/**
	 * 更新任务表达式
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="updateCronExpression")
	@ResponseBody
	public boolean updateCronExpression(QuartzJobBean jobBean){
	    opLogService.insertOpLog(2, "任务管理:更新了["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.updateCronExpression(jobBean);
	}
	
	/**
	 * 新增任务页面
	 * @return
	 */
	@RequestMapping(value="addJobView")
	public ModelAndView addJobView(){
		return new ModelAndView("/manager/trigger/triggerAdd");
	}
	
	/**
	 * 更新任务表达式
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="addJob")
	@ResponseBody
	public Map<String,Object> addJob(QuartzJobBean jobBean){
	    opLogService.insertOpLog(1, "任务管理:新增了名称为["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.addJob(jobBean);
	}
	
	/**
	 * 更新任务表达式
	 * @param jobBean
	 * @return
	 */
	@RequestMapping(value="deleteJob")
	@ResponseBody
	public boolean deleteJob(QuartzJobBean jobBean){
	    opLogService.insertOpLog(3, "任务管理:删除了名称为["+jobBean.getJobName()+"]的定时任务");
		return taskJobService.deleteJob(jobBean);
	}
}
