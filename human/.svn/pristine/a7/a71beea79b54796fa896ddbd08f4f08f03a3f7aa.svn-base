package com.human.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.jobs.MyDetailQuartzJobBean;
import com.human.manager.entity.QuartzJobBean;
import com.human.manager.service.TaskJobService;
import com.human.utils.PageView;
import com.human.utils.TaskUtils;


@Service
public class TaskJobServiceImpl implements TaskJobService {
	
	private final  Logger logger = LogManager.getLogger(TaskJobServiceImpl.class);
	@Resource     
	private SchedulerFactoryBean schedulerFactoryBean;
	
/*	@Reference(registry = "real-registry")    
	private DataExchangeModuleService moduleService; 
*/	/**    
	 * 获取所有任务    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public PageView  getAllJobs(PageView pager) throws SchedulerException {    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();      
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();    
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);    
		List<QuartzJobBean> jobList = new ArrayList<QuartzJobBean>();    
		for (JobKey jobKey : jobKeys) {    
		    List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
		    JobDetail jd=scheduler.getJobDetail(jobKey);
		    for (Trigger trigger : triggers) {    
		        QuartzJobBean job = new QuartzJobBean(); 
		        job.setJobId(jobKey.getGroup()+","+jobKey.getName());
		        job.setJobName(jobKey.getName());    
		        job.setJobGroup(jobKey.getGroup());    
		        job.setTriggerGroup(trigger.getKey().getGroup());
		        job.setTriggerName(trigger.getKey().getName());
		        job.setDescription(jd.getDescription());    
		        job.setNextTime(trigger.getNextFireTime()); //下次触发时间	
		        job.setTargetObject(jd.getJobDataMap().get("targetObject").toString());
		        job.setTargetMethod(jd.getJobDataMap().get("targetMethod").toString());
		        trigger.getFinalFireTime();//最后一次执行时间    
		        job.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间    
		        job.setStartTime(trigger.getStartTime());//开始时间    
		        trigger.getEndTime();//结束时间		            
		        //触发器当前状态    
		        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
		        job.setJobStatus(triggerState.name());    
		        //    
		        if (trigger instanceof CronTrigger) {    
		            CronTrigger cronTrigger = (CronTrigger) trigger;    
		            String cronExpression = cronTrigger.getCronExpression();    
		            job.setCronExpression(cronExpression);    
		        }    
		        jobList.add(job);    
		    }    
		}  
		pager.setRecords(jobList);
		return pager;    
	}   
	
	/**    
	 * 获取单个任务    
	 * @param jobName    
	 * @param jobGroup    
	 * @return    
	 * @throws SchedulerException    
	 */    
	public QuartzJobBean getJob(String jobName,String jobGroup) throws SchedulerException{    
		QuartzJobBean job = null;    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);    
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);      
		if (null != trigger) {    
			job = new QuartzJobBean();    
	        job.setJobName(jobName);    
	        job.setJobGroup(jobGroup);    
	        job.setDescription(trigger.getDescription());    
	        job.setNextTime(trigger.getNextFireTime()); //下次触发时间    
	        job.setPreviousTime(trigger.getPreviousFireTime());//上次触发时间    
	        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
	        job.setJobStatus(triggerState.name());    
	        if (trigger instanceof CronTrigger) {    
	            CronTrigger cronTrigger = (CronTrigger) trigger;    
	            String cronExpression = cronTrigger.getCronExpression();    
	            job.setCronExpression(cronExpression);    
	        }    
		}		    
        return job;    
	}   
	
	/**    
	 * 所有正在运行的job    
	 *     
	 * @return    
	 * @throws SchedulerException    
	 */    
	public List<QuartzJobBean> getRunningJob() throws SchedulerException {    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();    
		List<QuartzJobBean> jobList = new ArrayList<QuartzJobBean>(executingJobs.size());    
		for (JobExecutionContext executingJob : executingJobs) {    
			QuartzJobBean job = new QuartzJobBean();    
			JobDetail jobDetail = executingJob.getJobDetail();    
			JobKey jobKey = jobDetail.getKey(); 
			Trigger trigger = executingJob.getTrigger();    
			job.setJobName(jobKey.getName());    
			job.setJobGroup(jobKey.getGroup());    
			job.setDescription("触发器:" + trigger.getKey());    
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());    
			job.setJobStatus(triggerState.name());    
			if (trigger instanceof CronTrigger) {    
				CronTrigger cronTrigger = (CronTrigger) trigger;    
				String cronExpression = cronTrigger.getCronExpression();    
				job.setCronExpression(cronExpression);    
			}    
			jobList.add(job);    
		}    
		return jobList;    
	} 
	
	/**    
	 * 查询任务列表    
	 * @return    
	 */    
	/*public List<QuartzJobBean> getTaskList(){    
		List<QuartzJobBean> jobs = new ArrayList<QuartzJobBean>();    
		List<DataExchangeModuleBO> taskList = moduleService.findAll();    
		QuartzJobBean job = null;    
		for(DataExchangeModuleBO bo:taskList){    
			job = getTask(bo);    
			if(job!=null){    
				jobs.add(job);    
			}    
		}    
		return jobs;    
	}    */
	/**    
	 * 查询任务列表    
	 * @return    
	 */    
	/*public QuartzJobBean getTask(DataExchangeModuleBO bo){    
		QuartzJobBean job = null;    
		if(bo!=null){    
			job = new QuartzJobBean();    
			job.setJobId(String.valueOf(bo.getId()));    
			job.setJobName(bo.getModuleName());    
			job.setJobGroup(bo.getSystemName());    
			job.setJobStatus(bo.getStatus());//初始状态    
			job.setCronExpression(bo.getCron());    
			job.setSpringId(bo.getSpringId());    
			job.setConcurrent(bo.getConcurrent());    
			job.setJobClass(bo.getClazzName());    
			job.setMethodName(bo.getMethodName());    
			job.setDescription(bo.getSystemName()+"->"+bo.getModuleName()+"->"+bo.getInterfaceInfo());    
		}    
		return job;    
	}   */
	
	/**    
	 * 添加任务    
	 *     
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */
	@Transactional
	public Map<String, Object> addJob(QuartzJobBean job)  {    
		Map<String, Object> map=new HashMap<String,Object>();
		if(!TaskUtils.isValidExpression(job.getCronExpression())){    
			logger.error("时间表达式错误（"+job.getJobName()+","+job.getJobGroup()+"）,"+job.getCronExpression());    
			map.put("flag", false);
			map.put("message", "时间表达式错误");
		}else{    
			try{
				Scheduler scheduler = schedulerFactoryBean.getScheduler();    
				// 任务名称和任务组设置规则：    // 名称：task_1 ..    // 组 ：group_1 ..        
				TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());    
				CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);        
				// 不存在，创建一个       
				if (null == trigger) {    
					//是否允许并发执行    
					JobDetail jobDetail = JobBuilder.newJob(MyDetailQuartzJobBean.class).requestRecovery(true).storeDurably(true).withDescription(job.getDescription()).withIdentity(job.getJobName(), job.getJobGroup()).build();   
					jobDetail.getJobDataMap().put("targetObject", job.getTargetObject());     
					jobDetail.getJobDataMap().put("targetMethod", job.getTargetMethod());     
					jobDetail.getJobDataMap().put("concurrent", job.getIsConcurrent()); 
					// 表达式调度构建器         
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());    
					// 按新的表达式构建一个新的trigger         
					trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();   
					scheduler.scheduleJob(jobDetail, trigger);    
					map.put("flag", true);
					map.put("message", "任务新增成功!");
				} else {     // trigger已存在，则更新相应的定时设置         
					CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());         
					// 按新的cronExpression表达式重新构建trigger         
					trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();        
					// 按新的trigger重新设置job执行         
					scheduler.rescheduleJob(triggerKey, trigger); 
					map.put("flag", true);
					map.put("message", "任务时间已重新设定!");
				}	
				
			}catch(Exception e){
				logger.error("任务名:"+job.getJobName()+",分组:"+job.getJobGroup()+"）,表达式:"+job.getCronExpression()+"新增失败",e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				map.put("flag", false);
				map.put("message", "任务新增失败，请稍后再试或联系管理员!");
			}
		}    
		return map;    
	}    
    
	/**    
	 * 暂停任务    
	 * @param scheduleJob    
	 * @return    
	 */    
	public boolean pauseJob(QuartzJobBean scheduleJob){    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try {    
			scheduler.pauseJob(jobKey);    
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 恢复任务    
	 * @param scheduleJob    
	 * @return    
	 */    
	public boolean resumeJob(QuartzJobBean scheduleJob){    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try {    
			scheduler.resumeJob(jobKey);    
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 删除任务    
	 */    
	public boolean deleteJob(QuartzJobBean scheduleJob){    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());    
		try{    
			scheduler.deleteJob(jobKey);    
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 立即执行一个任务    
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */    
	public boolean testJob(QuartzJobBean scheduleJob) {    
		Scheduler scheduler = schedulerFactoryBean.getScheduler();    
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());  
		try{    
			scheduler.triggerJob(jobKey);   
			return true;    
		} catch (SchedulerException e) {			    
		}    
		return false;    
	}    
	    
	/**    
	 * 更新任务时间表达式    
	 * @param scheduleJob    
	 * @throws SchedulerException    
	 */  
	@Transactional
	public boolean updateCronExpression(QuartzJobBean scheduleJob) {
		try {
			if(!TaskUtils.isValidExpression(scheduleJob.getCronExpression())){
				logger.error("时间表达式错误（"+scheduleJob.getJobName()+","+scheduleJob.getJobGroup()+"）,"+scheduleJob.getCronExpression());    
				return false;    
			}
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getTriggerName(), scheduleJob.getTriggerGroup());
			// 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			// 表达式调度构建器
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
			return true;
		} catch (SchedulerException e) {
		    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("时间更新错误（"+scheduleJob.getJobName()+","+scheduleJob.getJobGroup()+"）,"+scheduleJob.getCronExpression(),e);    
		}
		return false;
	}
}
