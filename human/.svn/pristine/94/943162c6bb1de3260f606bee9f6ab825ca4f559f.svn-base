package com.human.jobs;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import com.human.mail.service.AcceptMailService;


@Component("DownLoadRecruitMailJob")
public class DownLoadRecruitMailJob{
	
    private final Logger logger = LogManager.getLogger(DownLoadRecruitMailJob.class);
    
    @Resource
    private AcceptMailService acceptMailService;
    
    
    public void downLoadAcceptMail(JobExecutionContext context) {  
        try {
        	logger.info("开始下载招聘接收邮箱每日邮件");
        	ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        	String path=servletContext.getRealPath("/static/temp/");
        	acceptMailService.downLoadAcceptMail(path);
			logger.info("下载招聘接收邮箱每日邮件完成");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("下载招聘接收邮箱每日邮件错误");
		}
    }  

}
