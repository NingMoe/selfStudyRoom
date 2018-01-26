package com.human.continuedClass.controller;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.continuedClass.entity.SendcardMailRecord;
import com.human.continuedClass.service.SendcardMailRecordService;
import com.human.utils.PageView;


@Controller
@RequestMapping("/continued_class/mail_record/")
public class ClassCardMailSendRecordController {
    
    private final  Logger logger = LogManager.getLogger(ClassCardMailSendRecordController.class);
    
	@Resource  
	private SendcardMailRecordService smrService; 
	
			
	/**
     * 续班卡邮件发送日志管理列表页面
     * @return
     */
	@RequestMapping(value="index")
	public  ModelAndView view(){
	    ModelAndView mav=new ModelAndView("/ContinuedClass/mailSendRecord/list");
        return mav;
	}
	
	/**
	 * 分页查询
	 * @param model
	 * @param resources
	 * @param pageNow
	 * @return
	 * @throws SchedulerException 
	 */
	@RequestMapping(value="query")
	@ResponseBody
	public PageView query(PageView pageView,SendcardMailRecord smr){
	    logger.info("分页查询续班卡邮件发送日志");
		return  smrService.query(pageView, smr);
	}
	

	
	
}
