package com.human.jobs;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.human.jobs.service.JobsService;


/**
 * 每天发送反馈信息
 * @author Administrator
 *
 */
@Component("feedbackJob")
public class FeedbackJob {
    
    private final Logger logger = LogManager.getLogger(FeedbackJob.class);
    
    @Resource
    private JobsService  jobService;;
    
    public void sendFeedbackEmail(JobExecutionContext context) {  
        logger.info("=============发送每日反馈记录邮件===============");
        try {
            jobService.sendFeedbackEmail();
        }
        catch (Exception e) {
            logger.error("=============发送每日反馈记录邮件任务异常=============",e);
        }
        logger.info("刷新个性化老师数据任务开始结束");
    }
}
