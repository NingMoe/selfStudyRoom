package com.human.teacherservice.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.manager.dao.UserDao;
import com.human.teacherservice.dao.LibBookMailRecordDao;
import com.human.teacherservice.dao.LibBookSmsRecordDao;
import com.human.teacherservice.dao.LibBorrowListDao;
import com.human.teacherservice.entity.LibBookMailRecord;
import com.human.teacherservice.entity.LibBookSmsRecord;
import com.human.teacherservice.entity.LibBorrowList;
import com.human.teacherservice.entity.LibBorrowListDto;
import com.human.teacherservice.service.LibraryBoorowService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.ExchangeMailReceiverInfo;
import com.human.utils.mailUtils.ExchangeMailUtil;

@Service
public class LibraryBoorowServiceImpl implements LibraryBoorowService {
    
    private  final  Logger logger = LogManager.getLogger(LibraryBoorowServiceImpl.class);

    @Resource
    private UserDao userDao;
    
    @Resource
    private LibBorrowListDao libBorrowListDao;
    
    @Resource
    private LibBookMailRecordDao libBookMailRecordDao;
    
    @Resource
    private LibBookSmsRecordDao libBookSmsRecordDao; 
    
    @Resource
    private SmsTempService smsService;
    
    @Value("${feedback_email_username}")
    private String emailUsername;
    
    @Value("${feedback_email_password}")
    private String emailPassword;
    
    @Value("${feedback_email_addr}")
    private String emailAddr;
    
    /**
     * 分页获取借阅记录
     */
    public PageView query(LibBorrowList libBorrowList, PageView pageView) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        libBorrowList.setBorrow_schoolid(Common.getMyUser().getCompanyId());        
        map.put("paging", pageView); 
        map.put("t", libBorrowList);        
        List<LibBorrowList> list = libBorrowListDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public List<LibBorrowListDto> selectByBorrowTime(String borrowTime) {
        return libBorrowListDao.selectByBorrowTime(borrowTime);
    }

    
    @Override
    public void sendNodifyMailAndSms() {
        try{                      
            sendAsyncNodifyMail();
            sendAsyncNodifySms();
        }catch(Exception e){
            e.printStackTrace();
            logger.error("图书提醒异常!", e.getMessage());             
        }               
    }

    /**
     * 异步发送图书归还邮件提醒
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void sendAsyncNodifyMail(){        
        try{
            //获取当前时间一个月前的今天
            String borrowTime=TimeUtil.getIntervalMonthDay(); 
            //查询未归还并延期的图书
            List<LibBorrowListDto> list=selectByBorrowTime(borrowTime);
            if(CollectionUtils.isNotEmpty(list)){
                for(LibBorrowListDto info:list){
                    sendNodifyMail(info);
                }
            }  
        }catch(Exception e){
           e.printStackTrace(); 
        }       
    }
    
    /**
     * 异步发送图书归还短信提醒
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void sendAsyncNodifySms(){        
        try{
            //获取当前时间一个月前的今天
            String borrowTime=TimeUtil.getIntervalMonthDay(); 
            //查询未归还并延期的图书
            List<LibBorrowListDto> list=libBorrowListDao.selectForSmsByBorrowTime(borrowTime);
            if(CollectionUtils.isNotEmpty(list)){
                for(LibBorrowListDto info:list){
                    sendNodifySms(info);
                }
            }  
        }catch(Exception e){
           e.printStackTrace(); 
        }       
    }
    
    /**
     * 图书归还邮件提醒
     * @param info
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void sendNodifyMail(LibBorrowListDto info){
        try{
            String mailServerHost="https://"+"mailbj.xdf.cn"+"/EWS/exchange.asmx";
            ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,emailUsername,emailPassword,"staff");
            //构造邮件信息
            MailMessage mailMessage=new MailMessage();
            String subject="图书归还提醒-"+info.getBookName();
            mailMessage.setSubject(subject);//主题
            mailMessage.setFrom(emailAddr);//发件人
            mailMessage.setTo(new String[]{info.getEmailAddr()+"@xdf.cn"});//收件人
            //获取逾期天数
            int outDays=getOutDays(info.getBorrowTime());
            String message="<p>亲爱的<strong>"+info.getName()+"</strong>老师，您好!</p><p>&nbsp;&nbsp;&nbsp;您借阅的&lt;&lt;<strong>"+info.getBookName()+"</strong>&gt;&gt;书(编号：<strong>"+info.getBookCode()+"</strong>、书架位置：<strong>"+info.getBookAddress()+"</strong>)已经超过借阅期限<strong>"+outDays+"</strong>天，该归还啦。要抓紧哦！其他同事也想涨涨姿势呢，憋让他们等太久！</p>";//邮件内容
            mailMessage.setMessage(message);
            boolean flag=ExchangeMailUtil.send(receiverInfo,mailMessage);
            //构造邮件记录
            LibBookMailRecord mailSendRecord=new LibBookMailRecord();
            mailSendRecord.setCompanyId(info.getCompanyId());
            mailSendRecord.setBookeId(info.getBookId());
            mailSendRecord.setSender(emailAddr);
            mailSendRecord.setRecipientsTo(info.getEmailAddr()+"@xdf.cn");
            mailSendRecord.setSendTime(new Date());
            mailSendRecord.setSendComment(mailMessage.getMessage());
            mailSendRecord.setRecipientsName(info.getName());
            if(flag){               
                mailSendRecord.setState(1);   
                mailSendRecord.setResultDesc("邮件发送成功"); 
            }else{               
                mailSendRecord.setState(2); 
                mailSendRecord.setResultDesc("邮件发送失败"); 
            }
            libBookMailRecordDao.insertSelective(mailSendRecord);
        }catch(Exception e){
            e.printStackTrace();   
            logger.error("图书归还邮件提醒发送异常!", e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }    
    }
    
    /**
     * 获取延期天数
     * @param borrowTime
     * @return
     */
    public int getOutDays(Date borrowTime){
        int outDays=0;
        try{
            Calendar c = Calendar.getInstance();
            c.setTime(borrowTime);
            c.add(Calendar.MONTH,1); 
            Date smdate=c.getTime();
            Date bdate=new Date();
            outDays=TimeUtil.daysBetween(smdate, bdate); 
        }catch(Exception e){
           e.printStackTrace(); 
        }
        return outDays;
    }
    
    /**
     * 图书归还短信提醒
     * @param info
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void sendNodifySms(LibBorrowListDto info){
        try{
            //构造短信记录
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setCompany(info.getMessageId());
            smsRecord.setSendTel(info.getPhone());
            //获取逾期天数
            int outDays=getOutDays(info.getBorrowTime());
            String message="亲爱的"+info.getName()+"老师，您借阅的《"+info.getBookName()+"》书，编号："+info.getBookCode()+"，已超过借阅期限"+outDays+"天，请及时归还到书架"+info.getBookAddress()+"位置，谢谢配合！";
            smsRecord.setSendComment(message);
            Integer sendResult = smsService.sendMessage(smsRecord);
            LibBookSmsRecord libBookSmsRecord=new LibBookSmsRecord();
            libBookSmsRecord.setCompanyId(info.getCompanyId());
            libBookSmsRecord.setBookId(info.getBookId());
            libBookSmsRecord.setEmailAddr(info.getEmailAddr());
            libBookSmsRecord.setTelephone(info.getPhone());
            libBookSmsRecord.setSendTime(new Date());
            libBookSmsRecord.setSendComment(message);
            libBookSmsRecord.setBorrowId(info.getBorrowId());
            if(sendResult==1){               
                libBookSmsRecord.setState(1);   
                libBookSmsRecord.setStateDesc("短信发送成功");
            }else{
                libBookSmsRecord.setState(99);   
                libBookSmsRecord.setStateDesc("短信发送失败");
            }
            //判断该借阅记录是否发送过短信
            LibBookSmsRecord oldLibBookSmsRecord=libBookSmsRecordDao.selectByBorrowId(info.getBorrowId());
            if(oldLibBookSmsRecord==null){
                libBookSmsRecordDao.insertSelective(libBookSmsRecord);
            }else{
                libBookSmsRecord.setId(oldLibBookSmsRecord.getId());
                libBookSmsRecordDao.updateByPrimaryKeySelective(libBookSmsRecord);
            }            
        }catch(Exception e){
           e.printStackTrace(); 
           logger.error("图书归还短信提醒发送异常!", e.getMessage());
           TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }
    
}
