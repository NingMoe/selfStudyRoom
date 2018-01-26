package com.human.jobs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.human.basic.entity.MailMessage;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.Users;
import com.human.manager.service.HrCompanyService;
import com.human.manager.service.HrOrganizationService;
import com.human.manager.service.HrUserService;
import com.human.manager.service.MailSendRecordService;
import com.human.manager.service.UserService;
import com.human.recruitment.service.HrPositiveRecordService;

/**
 * 每天同步组织机构及人员信息
 * @author HF-JAVA-02
 *
 */

@Component("companySyncJob")
public class CompanySyncJob {
    
    private final Logger logger = LogManager.getLogger(CompanySyncJob.class);
    
    @Autowired
    private HrCompanyService companyService;
    
    @Autowired
    private HrOrganizationService organizationService;
    
    @Autowired
    private HrUserService userService;
    
    @Autowired
    private UserService uService;
    
    @Autowired
    private MailSendRecordService mailSendRecordService;
    
    @Autowired
    private HrPositiveRecordService positiveRecordService;
    
    public void syscOrgAndEmployee(JobExecutionContext context) {  
        logger.info("数据同步开始");
        List<HrCompany> companys = companyService.findAll();
        for(HrCompany c:companys){
            organizationService.refreshOrgsByCompanyId(c.getCompanyId());
            userService.refreshUsersByCompanyId(c.getCompanyId());
        }
        //初始化转正记录
        positiveRecordService.initPositiveRecord();
        
        //资产管理人员发送转正记录
        List<Users> list = uService.getNowPositiveUsers();
        if(list!=null && list.size()>0){
            MailMessage mailMessage = new MailMessage();
            SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd");
            String dateStr = smf.format(new Date());
            StringBuffer sb = new StringBuffer("<div align=\"justify\" style=\"margin:0;\"><font face=\"Calibri,sans-serif\" size=\"3\"><span style=\"font-size:10.5pt;\"><font face=\"华文细黑\" color=\"gray\"><b>余老师你好，下面为今天转正的员工名单：</b></font></span></font></div>");
            sb.append("&nbsp;&nbsp;&nbsp;&nbsp;<table cellpadding=\"8\"><tr><td><font face=\"华文细黑\" color=\"gray\"><b>姓名</b></font></td><td><font face=\"华文细黑\" color=\"gray\"><b>部门</b></font></td><td><font face=\"华文细黑\" color=\"gray\"><b>手机号码</b></font></td><td><font face=\"华文细黑\" color=\"gray\"><b>身份证号码</b></font></td><td><font face=\"华文细黑\" color=\"gray\"><b>转正日期</b></font></td></tr>");
            for(Users u:list){
                mailMessage.setTo(new String[]{"yushiling@xdf.cn"});
                //mailMessage.setTo(new String[]{"chenyunsong@xdf.cn"});
                sb.append("<tr><td><font face=\"华文细黑\" color=\"gray\">"+u.getName()+"</font></td><td><font face=\"华文细黑\" color=\"gray\">"+u.getDeptName()+"</font></td><td><font face=\"华文细黑\" color=\"gray\">"+u.getEmpPhone()+"</font></td><td><font face=\"华文细黑\" color=\"gray\">"+u.getNationalId()+"</font></td><td><font face=\"华文细黑\" color=\"gray\">"+u.getPositiveTime()+"</font></td></tr>");
            }
            sb.append("</table>");
            mailMessage.setMessage(sb.toString());
            mailMessage.setSubject("转正员工名单"+dateStr);
            mailSendRecordService.sendMail("128", mailMessage,null);
        }
        logger.info("数据同步结束");
    }
}
