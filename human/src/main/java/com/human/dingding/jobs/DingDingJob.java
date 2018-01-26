package com.human.dingding.jobs;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.human.dingding.service.DingDingService;


@Component("dingdingJob")
public class DingDingJob {
   
    private final Logger logger = LogManager.getLogger(DingDingJob.class);
    
    @Resource
    private DingDingService  dingdingService;
    
    public void dingdingSync(JobExecutionContext context) {  
            logger.info("开始处理同步钉钉与本地数据！");
            dingdingService.dingdingSync();
            logger.info("钉钉数据处理完成！");
    }
}
