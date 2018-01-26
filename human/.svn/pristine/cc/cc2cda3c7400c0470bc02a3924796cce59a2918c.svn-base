package com.human.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.human.basic.dao.MailTempDao;
import com.human.basic.dao.RecruitMailDao;
import com.human.basic.dao.SmsTempDao;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.MailTem;
import com.human.basic.entity.RecruitMail;
import com.human.manager.dao.MailSendRecordDao;
import com.human.manager.entity.MailSendRecord;
import com.human.manager.service.MailSendRecordService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.mailUtils.ExchangeMailReceiverInfo;
import com.human.utils.mailUtils.ExchangeMailUtil;

@Service
public class MailSendRecordServiceImpl implements MailSendRecordService {
    
    
    private final  Logger logger = LogManager.getLogger(MailSendRecordServiceImpl.class);
    
    @Resource
    private MailSendRecordDao mailSendRecordDao;
    
    @Resource
    private RecruitMailDao recruitMailDao ;
    
    @Resource
    private MailTempDao mailTempDao;
    
    @Resource
    private SmsTempDao smsTempDao;
    
    @Override
    public PageView query(PageView pageView, MailSendRecord mailSendRecord) {      
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", mailSendRecord);
        List<MailSendRecord> list = mailSendRecordDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Async
    public Map<String, Object> sendMail(String hrCompanyId, MailMessage mailMessage,String mailTempId) {        
        Map<String, Object> map = new HashMap<String, Object>();
        MailSendRecord mailSendRecord=new MailSendRecord();
        MailTem mailTem=null;
        try{
            RecruitMail recruitMail =recruitMailDao.selectByCompanyId(hrCompanyId);
            String sendMailServerHost=recruitMail.getSendMailServerHost();
            String userName=recruitMail.getMailUser();
            String password=recruitMail.getMailPassword();
            String domain=recruitMail.getDomain();
            if(!Common.isEmpty(mailTempId)){
                mailTem=mailTempDao.queryById(Long.valueOf(mailTempId));
            }              
            map=ExchangeMailUtil.checkRecruitMail(sendMailServerHost, userName, password,domain);
            if((boolean) map.get("flag")){
                String sender=userName+"@xdf.cn";
                mailMessage.setFrom(sender);
                //保存邮件发送记录               
                mailSendRecord.setCompanyId(hrCompanyId);
                mailSendRecord.setSender(sender);
                if(mailTem!=null){
                    mailSendRecord.setDeptId(mailTem.getTemDept());
                    mailSendRecord.setType(mailTem.getTemType().toString()); 
                }
                //收件人
                String[] to=mailMessage.getTo();
                String toUser="";
                if(to!=null && to.length>0){        
                    for(String accept:to){
                        toUser+=accept+",";
                    }
                    toUser=toUser.substring(0,toUser.length()-1);
                }
                mailSendRecord.setRecipientsTo(toUser);
                //抄送 人
                String[] cc=mailMessage.getCc();
                String toCcUser="";
                if(cc!=null && cc.length>0){       
                    for(String accept:cc){
                        toCcUser+=accept+",";
                    }
                    toCcUser=toCcUser.substring(0,toCcUser.length()-1);
                    mailSendRecord.setRecipientsCc(toCcUser);
                }                
                //密送人 (不会在邮件收件人名单中显示出来)  
                String[] bcc=mailMessage.getBcc();
                String toBccUser="";
                if(bcc!=null && bcc.length>0){       
                    for(String accept:bcc){
                        toBccUser+=accept+",";
                    }
                    toBccUser=toBccUser.substring(0,toBccUser.length()-1);
                    mailSendRecord.setRecipientsBcc(toBccUser);
                } 
                mailSendRecord.setSendTime(new Date());
                mailSendRecord.setSendComment(mailMessage.getMessage());
                //发送邮件
                String mailServerHost="https://"+sendMailServerHost+"/EWS/exchange.asmx";
                ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,userName,password,domain);
                boolean flag=ExchangeMailUtil.send(receiverInfo, mailMessage);   
                if(flag){
                    mailSendRecord.setState("0");   
                    mailSendRecord.setResultDesc("邮件发送成功"); 
                }else{
                    mailSendRecord.setState("1"); 
                    mailSendRecord.setResultDesc("邮件发送失败"); 
                }               
            }else{
                map.put("flag", false);
            }  
        }catch(Exception e){
            e.printStackTrace();
            logger.error("发送邮件失败!", e.getMessage());
            map.put("flag", false);
            mailSendRecord.setState("1"); 
            mailSendRecord.setResultDesc("邮件发送失败!失败原因:"+e.getMessage());
        }
        mailSendRecordDao.insertSelective(mailSendRecord);
        return map;
    }
    
    @Override
    public String getSendMsg(String telNo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());
        rightNow.add(Calendar.MINUTE, -30);
        Date dt1=rightNow.getTime();
        String time = sdf.format(dt1);
        System.out.println(time);
        return smsTempDao.getMsg(telNo,time);
    }

}
