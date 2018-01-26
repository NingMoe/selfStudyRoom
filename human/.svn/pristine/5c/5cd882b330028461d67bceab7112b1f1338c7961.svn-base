package com.human.jobs;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class MyDetailQuartzJobBean extends QuartzJobBean {
	private final Logger logger = LogManager.getLogger(MyDetailQuartzJobBean.class);
	private String targetObject;
	private String targetMethod;
	private ApplicationContext ctx;
	
	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		try {
			logger.info("execute [" + targetObject + "] at once>>>>>>");
			Object otargetObject = ctx.getBean(targetObject);
			Method m = null;

			try {
				m = otargetObject.getClass().getMethod(targetMethod, new Class[] {JobExecutionContext.class});
				m.invoke(otargetObject, new Object[] {context});
			} catch (SecurityException e) {
				logger.error(e);
			} catch (NoSuchMethodException e) {
				logger.error(e);
			}
		} catch (Exception e) {
			throw new JobExecutionException(e);
		}
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.ctx = applicationContext;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
}
