package cn.xdf.pay.job;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import cn.xdf.pay.service.OrderBillService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;


/**
 * 下载微信对账单定时任务
 * @author liuwei63
 *
 */
@Component
public class DownLoadWxOrderBillJob {
	
	private static Logger logger = LoggerFactory.getLogger(DownLoadWxOrderBillJob.class);
    
	@Autowired
	private OrderBillService orderBillService;
	    
	public HttpServletRequest request;
	 
	public HttpServletResponse response;
	 
    @Scheduled(cron="0 0 10 * * ?")
    public void downLoadWxOrderBill() {  
        try {
        	logger.info("------开始下载微信每日对账单------");
        	orderBillService.downLoadWxOrderBill(request,response);
			logger.info("------下载微信每日对账单完成------");
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("------下载微信每日对账单异常------",e.getMessage());
		}
    }  
    
}
