package com.human.jobs;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;
import com.human.activity.service.OrderRefundInfoService;


@Component("UpdateOrderRefundInfoJob")
public class UpdateOrderRefundInfoJob {
	
    private final Logger logger = LogManager.getLogger(UpdateOrderRefundInfoJob.class);
    
	@Resource
	private OrderRefundInfoService orderRefundInfoService;
    
    public HttpServletRequest request;
    public HttpServletResponse response;
    
    public void updateOrderRefundInfo(JobExecutionContext context) {  
        try {
            orderRefundInfoService.checkValidOrderRefundInfo(request,response);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("更新退款单退款进度定时任务异常---",e.getMessage());
		}
    }  

}
