package com.human.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.human.jw.service.JwService;

/**
 * 每天同步个性化老师的教师编码以及当前1对 1对6带班量数据
 * @author HF-JAVA-02
 *
 */
@Component("jwTeacherDblJob")
public class JwTeacherDblJob {

    private final Logger logger = LogManager.getLogger(JwTeacherDblJob.class);
    
    @Autowired
    private JwService jwService;
    

    public void syncTeacherData(JobExecutionContext context) {  
        logger.info("刷新个性化老师数据任务开始");
        try {
            jwService.jobRefresh();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("刷新个性化老师数据任务异常");
        }
        logger.info("刷新个性化老师数据任务开始结束");
    }
    
    /**
     * 每周刷新一次课时统计
     * @param context
     */
    public void refreshKsTj(JobExecutionContext context) {  
        logger.info("刷新个性化老师数据任务开始");
        try {
            jwService.kstjRefresh();
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("刷新个性化老师数据任务异常");
        }
        logger.info("刷新个性化老师数据任务开始结束");
    }
}
