package com.human.jobs;

import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import com.human.basic.entity.XdfClassInfo;
import com.human.jw.service.JwService;
import com.human.jw.service.JwXdfClassInfoService;


@Component("UpdateXdfClassInfoNum")
public class UpdateXdfClassInfoNum {
	
    private final Logger logger = LogManager.getLogger(UpdateXdfClassInfoNum.class);
    
    @Resource
    private JwXdfClassInfoService jwXdfClassInfoService;
    
    @Resource
    private JwService jwService;
    
    public void updateNCurrentNum(JobExecutionContext context) {  
        try {
        	logger.info("开始查询需要更新的数据");
        	List<XdfClassInfo> list=jwXdfClassInfoService.queryTotalNum();
        	int i =list.size();
        	jwXdfClassInfoService.updateNCurrentNum(list);
        	logger.info("共更新数据"+i+"条");
			logger.info("查询需要更新数据结束");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("更新xdfclassinfo错误");
		}
    }  
    
}
