package com.human.jobs;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.human.rank.service.RankClassesService;

@Component("RankClassesNumJob")
public class RankClassesNumJob{
    
    @Resource
    private RankClassesService rankClassesService;
    
    public void updaterankclassesinfo(JobExecutionContext context) {
        rankClassesService.getClassesNum();
    }  

}
