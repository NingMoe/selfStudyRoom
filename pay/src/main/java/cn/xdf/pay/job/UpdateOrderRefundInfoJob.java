package cn.xdf.pay.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.xdf.pay.service.OrderRefundService;


/**
 * 更新退款单定时任务
 * @author liuwei63
 *
 */
@Component
public class UpdateOrderRefundInfoJob {
	
	private static Logger logger = LoggerFactory.getLogger(UpdateOrderRefundInfoJob.class);
    
	@Autowired
	private OrderRefundService orderRefundService;
    
    public HttpServletRequest request;
    
    public HttpServletResponse response;
    
    @Scheduled(cron="0 0 0/1 * * ?")
    public void updateOrderRefundInfo() {  
        try {
        	logger.info("------更新退款单定时任务开始------");
        	orderRefundService.updateOrderRefundInfo(request,response);
        	logger.info("------更新退款单定时任务结束------");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("------更新退款单定时任务异常------",e.getMessage());
		}
    }  

}
