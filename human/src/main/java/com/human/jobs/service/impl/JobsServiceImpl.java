package com.human.jobs.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.human.basic.dao.DicDataDao;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.RecruitMail;
import com.human.feedback.bean.FeedBackBase;
import com.human.feedback.bean.FeedBackOper;
import com.human.feedback.dao.FbSubDao;
import com.human.jobs.TaskExecutorUtil;
import com.human.jobs.service.JobsService;
import com.human.mail.dao.AcceptMailDao;
import com.human.mail.entity.AcceptMail;
import com.human.manager.dao.MailSendRecordDao;
import com.human.manager.entity.MailSendRecord;
import com.human.manager.entity.Users;
import com.human.resume.service.AnalysisResumeService;
import com.human.utils.OSSUtil;
import com.human.utils.SecurityHelper;
import com.human.utils.mailUtils.ExchangeMailReceiverInfo;
import com.human.utils.mailUtils.ExchangeMailUtil;
import com.human.utils.mailUtils.MailUtil;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

@Service
public class JobsServiceImpl implements JobsService {
    
    private final Logger logger = LogManager.getLogger(JobsServiceImpl.class);
    
     @Value("${urlPreKey}")
     private String urlPreKey;
     
    @Resource
    private FbSubDao fsDao;
    
    @Resource
    private AcceptMailDao acceptMailDao;
    
    @Resource
    private DicDataDao dicDataDao;
       
    @Resource
    private AnalysisResumeService analysisResumeService;
    
    @Resource
    private MailSendRecordDao mailSendRecordDao;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Resource
    private TaskExecutorUtil taskExecutorUtil;
        
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.attachmentPath}")
    private  String attachmentPath;
    
    @Value("${oss.emailPath}")
    private  String emailPath;
    
    @Value("${oss.htmlEmailPath}")
    private  String htmlEmailPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Value("${resumeUrlPre}")
    private String resumeUrlPre;

    @Value("${feedback_email_username}")
    private String emailUsername;
    
    @Value("${feedback_email_password}")
    private String emailPassword;
    
    @Value("${feedback_email_addr}")
    private String emailAddr;
    
    
    @Resource
    private SecurityHelper sh;
    /**
     * 异步读取各个学校招聘接收邮箱中的简历
     * @param recruitMail
     * @return
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void readMailBySchoolId(List<RecruitMail>list,String path){
        for(RecruitMail recruitMail:list){
            //读邮件
            readMail(recruitMail,dicDataDao,ossUtil,path);                      
        }
    }
    
    /**
     * 异步读取同一个学校招聘接收邮箱中的简历
     * @param recruitMail
     * @return
     * @throws ServiceLocalException 
     */
    @Async
    @Transactional(rollbackFor=Exception.class)
    public void readMail(RecruitMail recruitMail,DicDataDao dicDataDao,OSSUtil ossUtil,String path){
        String exchangeMailServerHost=recruitMail.getExchangeMailServerHost();
        String userName=recruitMail.getMailUser();
        String password=recruitMail.getMailPassword();
        String domain=recruitMail.getDomain();
        long id=recruitMail.getId();
        String hrCompanyId=recruitMail.getHrCompanyId();
        String attachmentDir=attachmentPath+recruitMail.getHrCompanyId()+"/";//附件保存路径
        String emailDir=emailPath+recruitMail.getHrCompanyId()+"/";//邮件原件保存路径
        String htmlEmailDir=htmlEmailPath+recruitMail.getHrCompanyId()+"/";//HTML格式邮件保存路径
        List<AcceptMail>AcceptMailList=new ArrayList<AcceptMail>();
        try{
            //第一步：先用Exchange协议去读取接收邮箱中的今天的邮件
            List<EmailMessage>list=ExchangeMailUtil.getEmailByParams(exchangeMailServerHost, userName, password, domain);
            int total=list.size();
            //第二步：过滤掉已经成功读取过的邮件
            List<EmailMessage>undoList= new ArrayList<EmailMessage>();//用来存放待读取的邮件
            for(EmailMessage em:list){
                String MessageId=em.getInternetMessageId();
                int count=acceptMailDao.queryByMessageId(MessageId);
                if(count==0){
                    undoList.add(em);
                }
            }
            //第三步：用Exchange协议读取邮件的内容包括附件
            if(undoList!=null && undoList.size()>0 ){
                ExchangeMailReceiverInfo receiverInfo=MailUtil.getExchangeMailReceiverInfo(exchangeMailServerHost, userName, password, domain, attachmentDir, emailDir, htmlEmailDir);
                AcceptMailList=MailUtil.readExchangeMailByParams(receiverInfo,undoList,total,dicDataDao,ossUtil,path);
            }
            //第四步：将邮件保存到数据库
            if(AcceptMailList!=null && AcceptMailList.size()>0){
                for(AcceptMail am:AcceptMailList){
                    am.setMessageAccept(Long.toString(id));
                    am.setHrCompanyId(hrCompanyId);
                    int row=acceptMailDao.insertSelective(am);
                    if(row==1){
                       //第五步：解析邮件
                        am.setId(am.getId());
                        am.setPath(path);
                        analysisResumeService.analysisResume(am);
                    }    
                }  
            }  
        }catch(Exception e){
            e.printStackTrace();
            logger.error("异步读取同一个学校招聘接收邮箱中的简历出错", e.getMessage());
        }

    }

    @Override
    public void sendFeedbackEmail() {
        FeedBackOper fbo=new FeedBackOper();
        List<Users> userList= fsDao.queryEmailUserList(fbo);
        logger.info("获取需要发送反馈邮件的人员成功,人员数量["+userList.size()+"]");
        
        String mailServerHost="https://"+"mailbj.xdf.cn"+"/EWS/exchange.asmx";
        ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,emailUsername,emailPassword,"staff");
        // 发送文本邮件  
        MailMessage mailMessage=new MailMessage();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        mailMessage.setSubject("校长信箱反馈_"+sdf.format(new Date()));
        mailMessage.setFrom(emailAddr);
        for(Users u:userList){
            try{
                List<FeedBackBase> list= fsDao.getMyYestodayOperFeedBack(u.getLoginName());
                //获取昨天新提交的待处理反馈
                logger.info("人员["+u.getName()+"]昨日尚未处理反馈数量["+list.size()+"]");
                if(list.size()==0){
                    logger.info("昨日未新增尚未处理的反馈，不发送邮件");
                    continue;
                }
                List<FeedBackBase> endList= fsDao.getMyOperFeedBackEd(u.getLoginName());
                logger.info("人员["+u.getName()+"]累计已处理反馈数量["+endList.size()+"]");
               
                mailMessage.setTo(new String[]{u.getLoginName()+"@xdf.cn"});
                StringBuffer s = new StringBuffer();
                s.append("<body  style='font-size: 16;'>");
                s.append("<div style='width: 100%;'> "+u.getName()+"老师,您好！</div>");
                s.append("<div style='width: 100%;'>");
                s.append("&nbsp;&nbsp;&nbsp;&nbsp;截至目前，校长信箱共收到<font style='font-size: 26;color: red;font-weight: bold;'>"+(list.size()+endList.size())+"</font>条反馈建议，已处理<font style='font-size: 26;color: green;font-weight: bold;'>"+endList.size()+"</font>条，详情请点击[<a href='"+sh.CreateFeedBackListURL(u.getLoginName())+"'>反馈待办</a>]。"
                        + "<br>&nbsp;&nbsp;&nbsp;&nbsp;收到的最新反馈<font style='font-size: 26;color: red;font-weight: bold;'>"+list.size()+"</font>条，具体详情如下：");
                s.append("</div>");
                SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for(FeedBackBase fb:list){
                    s.append("<div style='width: 100%;margin-top:5px;'>");
                    s.append("<table style='width:96%;margin:0 auto;'>");
                    if(fb.getFbdetail()!=null&&fb.getFbdetail().size()>0){
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈人:</td>");
                        if(fb.getIsHide()==1){
                            s.append("<td>匿名</td>");
                        }else{
                            s.append("<td>"+fb.getFbdetail().get(0).getOperUser()+"</td>");
                        }
                        s.append("</tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈对象:</td><td>"+fb.getDeptName()+"</td></tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈时间:</td><td>"+sdf1.format(fb.getFbdetail().get(0).getOperTime())+"</td></tr>");
                        s.append("<tr><td style='width: 10px;white-space: nowrap;font-weight: bold;'>意见回复:</td><td ><a href='"+sh.CreateFeedBackDetailURL(u.getLoginName(),fb.getId())+"'>点击回复</a></td></tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈内容:</td><td ></td></tr>");
                        s.append("<tr><td colspan='2'>"+fb.getFbdetail().get(0).getDesc()+" </td></tr>");
                    }
                       s.append("</table></div><br>");
                }
                s.append("</body>");
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
    
   /* public static void main(String[] args) {
        MailSendInfo sendInfo=new MailSendInfo("smtp.xdf.cn","hf_hfxcxx","hfxdf520!","");
        // 发送文本邮件  
        MailMessage mailMessage=new MailMessage();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        mailMessage.setSubject("校长信箱反馈_"+sdf.format(new Date()));
        mailMessage.setFrom("hf_hfxcxx@xdf.cn");
                mailMessage.setTo(new String[]{"xiazhenyou@xdf.cn"});
                StringBuffer s = new StringBuffer();
                s.append("<body  style='font-size: 16;'>");
                s.append("<div style='width: 100%;'> 夏振友老师,您好！</div>");
                s.append("<div style='width: 100%;'>");
                s.append("&nbsp;&nbsp;&nbsp;&nbsp;截至目前，校长信箱共收到<font style='font-size: 26;color: red;font-weight: bold;'>10</font>条反馈建议，已处理<font style='font-size: 26;color: green;font-weight: bold;'>21</font>条，详情请点击[<a href=''>反馈待办</a>]。"
                        + "<br>&nbsp;&nbsp;&nbsp;&nbsp;收到的最新反馈<font style='font-size: 26;color: red;font-weight: bold;'>5</font>条，具体详情如下：");
                s.append("</div>");
                SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    s.append("<div style='width: 100%;margin-top:5px;'>");
                    s.append("<table style='width:96%;margin:0 auto;'>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈人:</td>");
                        s.append("<td>匿名</td>");
                        s.append("</tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈对象:</td><td>信息管理部</td></tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈时间:</td><td>2017-10-11 12:00:00</td></tr>");
                        s.append("<tr><td style='width: 10px;white-space: nowrap;font-weight: bold;'>意见回复:</td><td ><a href=''>点击回复</a></td></tr>");
                        s.append("<tr><td style='width: 10px;font-weight: bold;'>反馈内容:</td><td ></td></tr>");
                        s.append("<tr><td colspan='2'>撒旦发射点发萨达发生大撒反对撒地方苏打粉苏打粉苏打粉散打范德萨发大水放大是否苏打粉苏打粉散打辐射大发是 </td></tr>");
                       s.append("</table></div><br>");
                s.append("</body>");
                mailMessage.setMessage(s.toString());
                try {
                    boolean flag=SendMailUtil.sendMultipleEmail(sendInfo,mailMessage);
                    System.out.println(flag);
                }
                catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } 
    }
    */
}