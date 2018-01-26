package com.human.jobs;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import com.human.teacherservice.service.LibraryBoorowService;

/**
 * 图书馆提醒功能定时任务
 * @author liuwei63
 *
 */
@Component("LibraryNodifyJob")
public class LibraryNodifyJob{
    
    public static final Logger logger = LogManager.getLogger(LibraryNodifyJob.class);
    
    @Resource
    private LibraryBoorowService libraryBoorowService;
    
    /**
     * 图书馆提醒功能定时任务
     * @param context
     */
    public void sendNodifyMailAndSms(JobExecutionContext context) {  
        logger.info("图书馆提醒功能定时任务开始");
        try{
            libraryBoorowService.sendNodifyMailAndSms();
        }catch(Exception e){
            e.printStackTrace();
            logger.error("图书馆提醒功能定时任务异常", e.getMessage());
        }
        logger.info("图书馆提醒功能定时任务结束");
    }

    
}
