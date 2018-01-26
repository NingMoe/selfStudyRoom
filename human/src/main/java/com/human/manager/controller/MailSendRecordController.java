package com.human.manager.controller;

import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.basic.service.DictionaryService;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.MailSendRecord;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.MailSendRecordService;
import com.human.manager.service.OpLogService;
import com.human.utils.PageView;


@Controller
@RequestMapping("/manager/mailSendRecord/")
public class MailSendRecordController {
    
    private final  Logger logger = LogManager.getLogger(MailSendRecordController.class);
    
	@Resource  
	private MailSendRecordService mailSendRecordService; 
	
	@Resource
	private HrCompanyService hrCompanyService;
	
	@Resource
    private  DictionaryService dictionaryService;
	 
	@Resource
	private OpLogService opLogService;
	
		
	/**
     * 邮件发送日志管理列表页面
     * @return
     */
	@RequestMapping(value="list")
	public  ModelAndView view(){
	    ModelAndView mav=new ModelAndView("/manager/mailSendRecord/list");
        List<HrCompany> companyList=hrCompanyService.findAll();          
        mav.addObject("companyList", companyList);
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
	public PageView query(PageView pageView,MailSendRecord mailSendRecord){
		return  mailSendRecordService.query(pageView, mailSendRecord);
	}
	

	
	
}
