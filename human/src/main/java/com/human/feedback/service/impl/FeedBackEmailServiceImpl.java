package com.human.feedback.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.human.basic.entity.MailMessage;
import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackDetail;
import com.human.feedback.bean.FeedBackOper;
import com.human.feedback.dao.FbSubDao;
import com.human.feedback.service.FeedBackEmailService;
import com.human.manager.dao.MailSendRecordDao;
import com.human.manager.entity.MailSendRecord;
import com.human.manager.entity.Users;
import com.human.utils.SecurityHelper;
import com.human.utils.mailUtils.ExchangeMailReceiverInfo;
import com.human.utils.mailUtils.ExchangeMailUtil;


@Service
public class FeedBackEmailServiceImpl implements FeedBackEmailService {

    @Resource
    private FbSubDao fbDao;
    
    @Value("${feedback_email_username}")
    private String emailUsername;
    
    @Value("${feedback_email_password}")
    private String emailPassword;
    
    @Value("${feedback_email_addr}")
    private String emailAddr;
    
    @Resource
    private SecurityHelper sh;
    
    private final Logger logger = LogManager.getLogger(FeedBackEmailServiceImpl.class);
    
    @Resource
    private MailSendRecordDao mailSendRecordDao;
    
    @Override
    @Async
    public void sendOperMsgMail(Long id){
        FeedBackBase fbb=fbDao.getBaseById(id);
        if(fbb==null){
            return;
        }
        String mailServerHost="https://"+"mailbj.xdf.cn"+"/EWS/exchange.asmx";
        ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,emailUsername,emailPassword,"staff");
        // 发送文本邮件  
        MailMessage mailMessage=new MailMessage();
        mailMessage.setSubject("反馈提醒_"+fbb.getTitle());
        mailMessage.setFrom(emailAddr);
        mailMessage.setTo(new String[]{fbb.getUserName()+"@xdf.cn"});
        StringBuffer s = new StringBuffer();
        s.append("<body  style='font-size: 16;'>");
        s.append("<div style='width: 100%;'> "+fbb.getCreateUsername()+"老师,您好！</div>");
        s.append("<div style='width: 100%;'>");
        s.append("&nbsp;&nbsp;&nbsp;您提交的<font style='color: red;'>"+fbb.getTitle()+"</font>的反馈建议已处理，详情请点击[<a href='"+sh.CreateUserFeedBackDetailURL(fbb.getUserName(),fbb.getId())+"'>反馈详情</a>]。");
        s.append("</div>");
        s.append("</body>");
        mailMessage.setMessage(s.toString());
        boolean flag;
        try {
            flag = ExchangeMailUtil.send(receiverInfo,mailMessage);
            MailSendRecord mailSendRecord=new MailSendRecord();
            mailSendRecord.setCompanyId("128");
            mailSendRecord.setSender(emailAddr);
            mailSendRecord.setRecipientsTo(fbb.getUserName()+"@xdf.cn");
            mailSendRecord.setSendTime(new Date());
            mailSendRecord.setSendComment(mailMessage.getMessage());
            if(flag){
                logger.info(fbb.getUserName()+"发送反馈邮件成功");
                mailSendRecord.setState("0");   
                mailSendRecord.setResultDesc("邮件发送成功"); 
            }else{
                logger.error(fbb.getUserName()+"发送反馈邮件失败");
                mailSendRecord.setState("1"); 
                mailSendRecord.setResultDesc("邮件发送失败"); 
            }
            mailSendRecordDao.insertSelective(mailSendRecord);
        }
        catch (Exception e) {
            logger.error(e);
        }   
        
    }

    /**
     * 
     * @param id
     * @param type 1.新反馈,2.继续反馈,3.关闭
     */
    @Override
    @Async
    public void sendFeedbackMsgMail(Long id,Integer type) {
        FeedBackBase fbb=fbDao.getBaseDetailById(id);
        if(fbb==null||fbb.getFbdetail().size()==0){
            return;
        }
        FeedBackDetail fbd=fbb.getFbdetail().get(0);
        String mailServerHost="https://"+"mailbj.xdf.cn"+"/EWS/exchange.asmx";
        ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,emailUsername,emailPassword,"staff");
        // 发送文本邮件  
        MailMessage mailMessage=new MailMessage();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        mailMessage.setSubject("校长信箱新反馈_"+sdf.format(new Date()));
        mailMessage.setFrom(emailAddr);
        FeedBackOper fbo=new FeedBackOper();
        fbo.setDeptId(fbb.getDeptId());
        List<Users> userList= fbDao.queryEmailUserList(fbo);
        for(Users u:userList){
            try{
                mailMessage.setTo(new String[]{u.getLoginName()+"@xdf.cn"});
                StringBuffer s = new StringBuffer();
                s.append("<body  style='font-size: 16;'>");
                s.append("<div style='width: 100%;'> "+u.getName()+"老师,您好！</div>");
                s.append("<div style='width: 100%;'>");
                SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                if(type==1) {
                s.append("&nbsp;&nbsp;&nbsp;&nbsp;校长信箱新收到新的反馈建议<font style='font-size: 26;color: green;font-weight: bold;'>"+fbb.getTitle()+"</font>，您可直接[<a href='"+sh.CreateFeedBackDetailURL(u.getLoginName(),fbb.getId())+"'>点击回复</a>]或者进入[<a href='"+sh.CreateFeedBackListURL(u.getLoginName())+"'>反馈待办</a>]查看具体详情。");
                s.append("</div>");
                s.append("<div style='width: 100%;margin-top:5px;'>");
                s.append("<table style='width:96%;margin:0 auto;'>");
                s.append("<tr><td style='width: 90px;font-weight: bold;'>反馈人:</td>");
                if(fbb.getIsHide()==1){
                    s.append("<td>匿名</td>");
                }else{
                    s.append("<td>"+fbd.getOperUser()+"</td>");
                }
                s.append("</tr>");
                s.append("<tr><td style='width: 90px;font-weight: bold;'>反馈对象:</td><td>"+fbb.getDeptName()+"</td></tr>");
                s.append("<tr><td style='width: 90px;font-weight: bold;'>反馈时间:</td><td>"+sdf1.format(fbd.getOperTime())+"</td></tr>");
                s.append("<tr><td style='width: 90px;font-weight: bold;'>反馈内容:</td><td ></td></tr>");
                s.append("<tr><td colspan='2'>"+fbd.getDesc()+" </td></tr>");
                s.append("</table></div><br>");
                s.append("</body>");
                }
                if(type==2) {
                    s.append("&nbsp;&nbsp;&nbsp;&nbsp;校长信箱中<font style='font-size: 26;color: green;font-weight: bold;'>"+fbb.getTitle()+"</font>有了新的回复，您可直接[<a href='"+sh.CreateFeedBackDetailURL(u.getLoginName(),fbb.getId())+"'>点击回复</a>]或者进入[<a href='"+sh.CreateFeedBackListURL(u.getLoginName())+"'>反馈待办</a>]查看具体详情。");
                            s.append("</div>");
                            s.append("<div style='width: 100%;margin-top:5px;'>");
                            s.append("<table style='width:96%;margin:0 auto;'>");
                            s.append("<tr><td style='width: 90px;font-weight: bold;'>回复人:</td>");
                            if(fbb.getIsHide()==1){
                                s.append("<td>匿名</td>");
                            }else{
                                s.append("<td>"+fbd.getOperUser()+"</td>");
                            }
                            s.append("</tr>");
                            s.append("<tr><td style='width: 90px;font-weight: bold;'>回复对象:</td><td>"+fbb.getDeptName()+"</td></tr>");
                            s.append("<tr><td style='width: 90px;font-weight: bold;'>回复时间:</td><td>"+sdf1.format(fbd.getOperTime())+"</td></tr>");
                            s.append("<tr><td style='width: 90px;font-weight: bold;'>回复内容:</td><td ></td></tr>");
                            s.append("<tr><td colspan='2'>"+fbd.getDesc()+" </td></tr>");
                            s.append("</table></div><br>");
                            s.append("</body>");
                }
                if(type==3) {
                    s.append("&nbsp;&nbsp;&nbsp;&nbsp;校长信箱中<font style='font-size: 26;color: green;font-weight: bold;'>"+fbb.getTitle()+"</font>已经被确认关闭，得分为"+fbb.getScore()+"分,您可直接点击[<a href='"+sh.CreateFeedBackDetailURL(u.getLoginName(),fbb.getId())+"'>查看详情</a>]查看具体详情。");
                    s.append("</div>");
                    s.append("<div style='width: 100%;margin-top:5px;'>");
                    s.append("<table style='width:96%;margin:0 auto;'>");
                    s.append("<tr><td style='width: 90px;font-weight: bold;'>关闭人:</td>");
                    if(fbb.getIsHide()==1){
                        s.append("<td>匿名</td>");
                    }else{
                        s.append("<td>"+fbb.getCloseUser()+"</td>");
                    }
                    s.append("</tr>");
                    s.append("<tr><td style='width: 90px;font-weight: bold;'>反馈对象:</td><td>"+fbb.getDeptName()+"</td></tr>");
                    s.append("<tr><td style='width: 90px;font-weight: bold;'>关闭时间:</td><td>"+sdf1.format(fbb.getCloseTime())+"</td></tr>");
                    s.append("<tr><td style='width: 90px;font-weight: bold;'>关闭评价:</td><td ></td></tr>");
                    if(StringUtils.isBlank(fbb.getCloseDesc())) {
                        s.append("<tr><td colspan='2'>无 </td></tr>");
                    }else {
                        s.append("<tr><td colspan='2'>"+fbb.getCloseDesc()+" </td></tr>");
                    }
                  
                    s.append("</table></div><br>");
                    s.append("</body>");
                }
                mailMessage.setMessage(s.toString());
                boolean flag=ExchangeMailUtil.send(receiverInfo,mailMessage); 
                MailSendRecord mailSendRecord=new MailSendRecord();
                mailSendRecord.setCompanyId("128");
                mailSendRecord.setSender(emailAddr);
                mailSendRecord.setRecipientsTo(u.getLoginName()+"@xdf.cn");
                mailSendRecord.setSendTime(new Date());
                mailSendRecord.setSendComment(mailMessage.getMessage());
                if(flag){
                    logger.info(u.getName()+"发送反馈邮件成功");
                    mailSendRecord.setState("0");   
                    mailSendRecord.setResultDesc("邮件发送成功"); 
                }else{
                    logger.info(u.getName()+"发送反馈邮件失败");
                    mailSendRecord.setState("1"); 
                    mailSendRecord.setResultDesc("邮件发送失败"); 
                }
                mailSendRecordDao.insertSelective(mailSendRecord);
            }catch(Exception e){
                logger.error(u.getName()+"发送反馈邮件失败",e);
            }
        }
        
    }
}
