package com.human.jobs;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import com.human.mail.entity.AcceptMail;
import com.human.mail.service.AcceptMailService;
import com.human.resume.service.AnalysisResumeService;


@Component("DownLoadAnalysisFailMailJob")
public class DownLoadAnalysisFailMailJob{
	
    private final Logger logger = LogManager.getLogger(DownLoadAnalysisFailMailJob.class);
    
    @Resource
    private AcceptMailService acceptMailService;
    
    @Resource
    private AnalysisResumeService analysisResumeService;
    
    
    public void downLoadAnalysisFailMail(JobExecutionContext context) {  
        try {
        	logger.info("开始解析招聘接收邮箱中解析失败的邮件");
        	ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        	String path=servletContext.getRealPath("/static/temp/");
        	List<AcceptMail>list=acceptMailService.queryFailAnalysis();
        	if(list!=null && list.size()>0){
        	   for(AcceptMail am:list){
        	       am.setPath(path);
        	       analysisResumeService.analysisResume(am);
        	   }
        	}   
			logger.info("解析招聘接收邮箱中解析失败的邮件完成");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("解析招聘接收邮箱中解析失败的邮件错误");
		}
    }  

}
