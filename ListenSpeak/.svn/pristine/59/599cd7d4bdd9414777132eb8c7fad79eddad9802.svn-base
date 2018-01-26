package com.ls.spt.utils;

import java.text.ParseException;
import java.util.Date;

import org.quartz.impl.triggers.CronTriggerImpl;


public class TaskUtils {
	/**     
	 * 判断cron时间表达式正确性     
	 * @param cronExpression     
	 * @return      
	 */     
	public static boolean isValidExpression(final String cronExpression){     
//		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);     
		CronTriggerImpl trigger = new CronTriggerImpl();        
        try {     
			trigger.setCronExpression(cronExpression);     
			Date date = trigger.computeFirstFireTime(null);       
	        return date != null && date.after(new Date());        
		} catch (ParseException e) {     
		}      
        return false;     
	}     
	     
	/*     
	 * 任务运行状态     
	 */     
	public enum TASK_STATE{     
		NONE("NONE","未知"),     
		NORMAL("NORMAL", "正常运行"),     
		PAUSED("PAUSED", "暂停状态"),      
		COMPLETE("COMPLETE",""),     
		ERROR("ERROR","错误状态"),     
		BLOCKED("BLOCKED","锁定状态");     
		     
		private TASK_STATE(String index,String name) {        
	        this.name = name;        
	        this.index = index;        
	    }       
		private String index;       
		private String name;       
		public String getName() {        
	        return name;        
	    }        
	    public String getIndex() {        
	        return index;        
	    }     
	}     
}
