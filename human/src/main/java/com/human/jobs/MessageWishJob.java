package com.human.jobs;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.manager.entity.MessageWish;
import com.human.manager.service.HrCompanyService;

/**
 * 每天同步组织机构及人员信息
 * @author HF-JAVA-02
 *
 */

@Component("messageWishJob")
public class MessageWishJob {
    
    private final Logger logger = LogManager.getLogger(MessageWishJob.class);
    
    @Autowired
    private HrCompanyService companyService;
    
    @Autowired
    private SmsTempService smsService;
    
    
    public void messageWish(JobExecutionContext context) {  
        logger.info("发送祝福任务开始");
        List<MessageWish> employees = companyService.selectWishEmployee();
        List<SmsRecord> smsRecords;
        try {
            smsRecords = getSmsRecords(employees);
            smsService.sendMessage(smsRecords);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.info("发送祝福任务异常");
        }
        logger.info("发送祝福任务结束");
    }
    
    private List<SmsRecord> getSmsRecords(List<MessageWish> employees) throws Exception{
        List<SmsRecord> smsRecords = new ArrayList<SmsRecord>();
        for(MessageWish mw : employees){
            SmsRecord s = new SmsRecord();
            s.setCompany(mw.getCompany());
            s.setDeptId(mw.getDeptId());
            s.setSendTel(mw.getTel());
            s.setMemo("wish");
            String message = "";
            Integer num = getWishNum(mw);
            if("1".equals(mw.getType())){
                message = mw.getName()+"老师您好，今天是您入职"+mw.getCompanyName()+num+"周年的日子，感谢您一直以来的坚持与付出!\n来自SMARTWORK的祝福\n";
            }
            if("2".equals(mw.getType())){
                message = mw.getName()+"老师您好，这是你在公司度过的第"+num+"个生日，感谢您在学校一直以来的出色表现，在您生日来临之际，送上最诚挚的祝福：生日快乐!\n来自SMARTWORK的祝福\n";
            }
            s.setSendComment(message);
            smsRecords.add(s);
        }
        return smsRecords;
    }
    
    private boolean isEarly(String date1,String date2) throws Exception{
        date1 = "2000"+date1.substring(4);
        date2 = "2000"+date2.substring(4);
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = smf.parse(date1);
        Date d2 = smf.parse(date2);
        return !d1.after(d2);
    };
    
    
    @SuppressWarnings("deprecation")
    private Integer getWishNum(MessageWish mw) throws Exception{
        SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
        if("1".equals(mw.getType())){
            return (new Date()).getYear() - smf.parse(mw.getJoinDate()).getYear();
        }
        if("2".equals(mw.getType())){
            int ruzhi = (new Date()).getYear() - smf.parse(mw.getJoinDate()).getYear();
            if(ruzhi==0){
                return 1;
            }else{
                if(isEarly(mw.getBirthDate(),mw.getJoinDate())){
                    return ruzhi;
                }else{
                    return ruzhi + 1;
                }
            }
            
        }
        return null;
    }
    
}
