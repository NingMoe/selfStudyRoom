package com.human.manager.service;

import java.util.Map;
import com.human.basic.entity.MailMessage;
import com.human.manager.entity.MailSendRecord;
import com.human.utils.PageView;

public interface MailSendRecordService {
    
    /*
     * 分页查询邮件发送日志
     */
    PageView query(PageView pageView, MailSendRecord mailSendRecord);
    /*
     * 发送邮件并保存邮件发送记录
     */
    Map<String,Object>sendMail(String hrCompanyId,MailMessage mailMessage,String mailTempId);
    
    /**
     * 获取短信发送记录
     */
    String getSendMsg(String telNo);
}
