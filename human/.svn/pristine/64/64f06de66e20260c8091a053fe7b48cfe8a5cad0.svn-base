package com.human.jobs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import com.human.order.service.OrderBillService;


@Component("DownLoadWxOrderBillJob")
public class DownLoadWxOrderBillJob {
	
    private final Logger logger = LogManager.getLogger(DownLoadWxOrderBillJob.class);
    
    @Resource
    private OrderBillService orderBillService;
    
    public HttpServletRequest request;
    public HttpServletResponse response;
    
    public void downLoadWxOrderBill(JobExecutionContext context) {  
        try {
        	logger.info("开始下载微信每日对账单");
        	orderBillService.downLoadWxOrderBill(request,response);
			logger.info("下载微信每日对账单完成");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("下载微信每日对账单错误");
		}
    }  
    
}
