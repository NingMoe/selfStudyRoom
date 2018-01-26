package com.human.utils.mailUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import org.springframework.stereotype.Component;
import com.human.basic.entity.AttachMent;
import com.human.basic.entity.MailMessage;

/** 
 * 使用SMTP协议发送电子邮件 
 */
@Component
public class SendMailUtil implements Serializable{
   
    /**
     * 序列号
     */
    private static final long serialVersionUID = -570430241548010814L;


    /**
     * 发邮件的实体类
     * @param mailServerHost
     * @param userName
     * @param password
     * @param attachmentDir
     * @return
     */
    public  static  MailSendInfo getMailSendInfo(String mailServerHost,String userName,String password,String attachmentDir){
        MailSendInfo sendInfo = new MailSendInfo(mailServerHost,userName,password,attachmentDir);
        return sendInfo;
    }
    
    /** 
     * 连接邮件服务器 
     */ 
    public static Session connectToServer(MailSendInfo sendInfo) {
        MyAuthenticator authenticator = new MyAuthenticator(sendInfo.getUserName(), sendInfo.getPassword()); 
        //创建session 
        Session session = Session.getInstance(sendInfo.getProperties(), authenticator); 
        return session;
    }
    
    
    /**
     * 检查发件服务器能否正常连接
     * @param mailServerHost
     * @param userName
     * @param password
     * @return
     */
    public static Map<String, Object> checkRecruitMail(String mailServerHost,String userName,String password){
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            MailSendInfo sendInfo=getMailSendInfo(mailServerHost,userName, password,"");
            Session session=connectToServer(sendInfo);
            if(session!=null){
                map.put("flag", true);
                map.put("message", "连接发件服务器成功！");
            }else{
                map.put("flag", false);
                map.put("message", "连接服务器失败！请检查发件服务器地址、用户名或密码是否正确!");
            }
        }catch(Exception e){
            map.put("flag", false);
            map.put("message", "连接服务器失败！请检查发件服务器地址、用户名或密码是否正确!");
        }        
        return map;
    }
        
    /** 
     * 发送简单的文本邮件 
     */  
    public static Boolean sendTextEmail(MailSendInfo sendInfo,MailMessage mailMessage){
        Boolean result = false;  
        try{
         // 创建Session实例对象  
            Session session = connectToServer(sendInfo);            
            // 创建MimeMessage实例对象  
            MimeMessage message = new MimeMessage(session);
            // 设置发件人  
            message.setFrom(new InternetAddress(mailMessage.getFrom()));  
            // 设置邮件主题  
            message.setSubject(mailMessage.getSubject(), "utf-8");  
            // 设置收件人  
            String[] to=mailMessage.getTo();
            String toUser="";
            if(to!=null && to.length>0){        
                for(String accept:to){
                    toUser+=accept+",";
                }
                toUser=toUser.substring(0,toUser.length()-1);
            }
            InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);        
            message.setRecipients(RecipientType.TO, internetAddressTo);  
            // 抄送  
            String[] cc=mailMessage.getCc();
            String toCcUser="";
            if(cc!=null && cc.length>0){       
                for(String accept:cc){
                    toCcUser+=accept+",";
                }
                toCcUser=toCcUser.substring(0,toCcUser.length()-1);
                InternetAddress[] internetAddressCc= new InternetAddress().parse(toCcUser);
                message.setRecipients(RecipientType.CC, internetAddressCc); 
            }
            // 密送 (不会在邮件收件人名单中显示出来)  
            String[] bcc=mailMessage.getBcc();
            String toBccUser="";
            if(bcc!=null && bcc.length>0){       
                for(String accept:bcc){
                    toBccUser+=accept+",";
                }
                toBccUser=toBccUser.substring(0,toBccUser.length()-1);
                InternetAddress[] internetAddressBcc= new InternetAddress().parse(toBccUser);
                message.setRecipients(RecipientType.BCC, internetAddressBcc); 
            } 
            // 设置发送时间  
            message.setSentDate(new Date());  
            // 设置纯文本内容为邮件正文  
            message.setText(mailMessage.getMessage());  
            // 保存并生成最终的邮件内容  
            message.saveChanges();            
            // 获得Transport实例对象  
            Transport transport = session.getTransport();  
            // 打开连接  
            transport.connect(sendInfo.getUserName(), sendInfo.getPassword());  
            // 将message对象传递给transport对象，将邮件发送出去  
            transport.sendMessage(message, message.getAllRecipients());  
            // 关闭连接  
            transport.close(); 
            result = true; 
        }catch(Exception e){
            e.printStackTrace();  
        }
        return result;  
    }  
      
    /** 
     * 发送简单的html邮件 
     */  
    public static Boolean sendHtmlEmail(MailSendInfo sendInfo,MailMessage mailMessage){ 
        Boolean result = false;  
        try{
         // 创建Session实例对象  
            Session session = connectToServer(sendInfo);            
            // 创建MimeMessage实例对象  
            MimeMessage message = new MimeMessage(session);  
            // 设置邮件主题  
            message.setSubject(mailMessage.getSubject(), "utf-8");  
            // 设置发送人  
            message.setFrom(new InternetAddress(mailMessage.getFrom()));  
            // 设置发送时间  
            message.setSentDate(new Date());  
            // 设置收件人  
            String[] to=mailMessage.getTo();
            String toUser="";
            if(to!=null && to.length>0){        
                for(String accept:to){
                    toUser+=accept+",";
                }
                toUser=toUser.substring(0,toUser.length()-1);
            }
            InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);        
            message.setRecipients(RecipientType.TO, internetAddressTo);  
            // 抄送  
            String[] cc=mailMessage.getCc();
            String toCcUser="";
            if(cc!=null && cc.length>0){       
                for(String accept:cc){
                    toCcUser+=accept+",";
                }
                toCcUser=toCcUser.substring(0,toCcUser.length()-1);
                InternetAddress[] internetAddressCc= new InternetAddress().parse(toCcUser);
                message.setRecipients(RecipientType.CC, internetAddressCc); 
            }
            // 密送 (不会在邮件收件人名单中显示出来)  
            String[] bcc=mailMessage.getBcc();
            String toBccUser="";
            if(bcc!=null && bcc.length>0){       
                for(String accept:bcc){
                    toBccUser+=accept+",";
                }
                toBccUser=toBccUser.substring(0,toBccUser.length()-1);
                InternetAddress[] internetAddressBcc= new InternetAddress().parse(toBccUser);
                message.setRecipients(RecipientType.BCC, internetAddressBcc); 
            } 
            // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为gbk  
            message.setContent(mailMessage.getMessage(),"text/html;charset=gbk");           
            // 保存并生成最终的邮件内容  
            message.saveChanges();            
            // 发送邮件  
            Transport.send(message);    
            result = true;  
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;  
    }  
      
    /** 
     * 发送带内嵌图片的HTML邮件 
     */  
    public static Boolean sendHtmlWithInnerImageEmail(MailSendInfo sendInfo,MailMessage mailMessage){
        Boolean result = false;
        try{
         // 创建Session实例对象  
            Session session = connectToServer(sendInfo);            
            // 创建邮件内容  
            MimeMessage message = new MimeMessage(session);  
            // 邮件主题,并指定编码格式  
            message.setSubject(mailMessage.getSubject(), "utf-8");      
            // 发件人          
            message.setFrom(new InternetAddress(mailMessage.getFrom()));  
            // 收件人  
            String[] to=mailMessage.getTo();
            String toUser="";
            if(to!=null && to.length>0){        
                for(String accept:to){
                    toUser+=accept+",";
                }
                toUser=toUser.substring(0,toUser.length()-1);
            }
            InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);        
            message.setRecipients(RecipientType.TO, internetAddressTo);
            // 抄送  
            String[] cc=mailMessage.getCc();
            String toCcUser="";
            if(cc!=null && cc.length>0){       
                for(String accept:cc){
                    toCcUser+=accept+",";
                }
                toCcUser=toCcUser.substring(0,toCcUser.length()-1);
                InternetAddress[] internetAddressCc= new InternetAddress().parse(toCcUser);
                message.setRecipients(RecipientType.CC, internetAddressCc); 
            }
            // 密送 (不会在邮件收件人名单中显示出来)  
            String[] bcc=mailMessage.getBcc();
            String toBccUser="";
            if(bcc!=null && bcc.length>0){       
                for(String accept:bcc){
                    toBccUser+=accept+",";
                }
                toBccUser=toBccUser.substring(0,toBccUser.length()-1);
                InternetAddress[] internetAddressBcc= new InternetAddress().parse(toBccUser);
                message.setRecipients(RecipientType.BCC, internetAddressBcc); 
            } 
            // 发送时间  
            message.setSentDate(new Date());            
            // 创建一个MIME子类型为“related”的MimeMultipart对象  
            MimeMultipart mp = new MimeMultipart("related");  
            // 创建一个表示正文的MimeBodyPart对象，并将它加入到前面创建的MimeMultipart对象中  
            MimeBodyPart htmlPart = new MimeBodyPart();  
            mp.addBodyPart(htmlPart);         
            // 图片  
            String[] photos=mailMessage.getPhoto();
            if(photos!=null && photos.length>0){
                for(String photo:photos){
                    MimeBodyPart imgPart = new MimeBodyPart();
                    DataSource ds = new FileDataSource(new File(photo));  
                    DataHandler dh = new DataHandler(ds);  
                    imgPart.setDataHandler(dh);  
                    imgPart.setContentID(photo.substring(photo.lastIndexOf("/")+1)); 
                    mp.addBodyPart(imgPart);   
                }
            }         
            // 将MimeMultipart对象设置为整个邮件的内容  
            message.setContent(mp);          
            // 创建一个MIME子类型为"alternative"的MimeMultipart对象，并作为前面创建的htmlPart对象的邮件内容  
            MimeMultipart htmlMultipart = new MimeMultipart("alternative");  
            // 创建一个表示html正文的MimeBodyPart对象  
            MimeBodyPart htmlBodypart = new MimeBodyPart();  
            // 其中cid=firefoxlogo.png是引用邮件内部的图片，即imagePart.setContentID("firefoxlogo.png");方法所保存的图片  
            htmlBodypart.setContent(mailMessage.getMessage(),"text/html;charset=utf-8");  
            htmlMultipart.addBodyPart(htmlBodypart);
            htmlPart.setContent(htmlMultipart);            
            // 保存并生成最终的邮件内容  
            message.saveChanges();            
            // 发送邮件  
            Transport.send(message); 
            result = true;  
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return result;   
    }  
      
    /** 
     * 发送带内嵌图片、附件、多收件人(显示邮箱姓名)、邮件优先级、阅读回执的完整的HTML邮件 
     */  
    public static Boolean sendMultipleEmail(MailSendInfo sendInfo,MailMessage mailMessage) throws Exception {
        Boolean result = false;
        try{
            String charset = "utf-8"; // 指定中文编码格式  
            // 创建Session实例对象  
            Session session = connectToServer(sendInfo);            
            // 创建MimeMessage实例对象  
            MimeMessage message = new MimeMessage(session);  
            // 设置主题  
            message.setSubject(mailMessage.getSubject(), charset);  
            // 设置发送人  
            message.setFrom(new InternetAddress(mailMessage.getFrom(),"新东方合肥学校",charset));  
            // 设置收件人 
            String[] to=mailMessage.getTo();
            String toUser="";
            if(to!=null && to.length>0){        
                for(String accept:to){
                    toUser+=accept+",";
                }
                toUser=toUser.substring(0,toUser.length()-1);
            }
            InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);        
            message.setRecipients(RecipientType.TO, internetAddressTo);
            // 抄送  
            String[] cc=mailMessage.getCc();
            String toCcUser="";
            if(cc!=null && cc.length>0){       
                for(String accept:cc){
                    toCcUser+=accept+",";
                }
                toCcUser=toCcUser.substring(0,toCcUser.length()-1);
                InternetAddress[] internetAddressCc= new InternetAddress().parse(toCcUser);
                message.setRecipients(RecipientType.CC, internetAddressCc); 
            }
            // 密送 (不会在邮件收件人名单中显示出来)  
            String[] bcc=mailMessage.getBcc();
            String toBccUser="";
            if(bcc!=null && bcc.length>0){       
                for(String accept:bcc){
                    toBccUser+=accept+",";
                }
                toBccUser=toBccUser.substring(0,toBccUser.length()-1);
                InternetAddress[] internetAddressBcc= new InternetAddress().parse(toBccUser);
                message.setRecipients(RecipientType.BCC, internetAddressBcc); 
            } 
            // 设置发送时间  
            message.setSentDate(new Date());  
            // 设置回复人(收件人回复此邮件时,默认收件人)  
            //message.setReplyTo(InternetAddress.parse("\"" + MimeUtility.encodeText("合肥新东方培训学校人力资源部") + "\" <hfhr@xdf.cn>"));  
            // 设置优先级(1:紧急   3:普通    5:低)  
            message.setHeader("X-Priority", "1");  
            // 要求阅读回执(收件人阅读邮件时会提示回复发件人,表明邮件已收到,并已阅读)  
            //message.setHeader("Disposition-Notification-To",mailMessage.getFrom());          
            // 创建一个MIME子类型为"mixed"的MimeMultipart对象，表示这是一封混合组合类型的邮件  
            MimeMultipart mailContent = new MimeMultipart("mixed");
            message.setContent(mailContent);            
            // 附件  
            List<AttachMent>attachments=mailMessage.getAttachments();
            if(attachments!=null && attachments.size()>0){
                for(AttachMent att:attachments){
                    MimeBodyPart attach = new MimeBodyPart(); 
                    DataHandler dh =null;
                    if("1".equals(att.getAttachmentPathType())){
                        URLDataSource ds=new URLDataSource(new URL(att.getAttachmentPath())); 
                        dh = new DataHandler(ds); 
                    }else{
                        DataSource ds = new FileDataSource(att.getAttachmentPath()); 
                        dh = new DataHandler(ds);
                    }
                    attach.setFileName(MimeUtility.encodeText(att.getName()));  
                    attach.setDataHandler(dh);
                    // 将附件添加到邮件当中  
                    mailContent.addBodyPart(attach);  
                }
            }
            // 内容  
            MimeBodyPart mailBody = new MimeBodyPart();           
            // 将内容添加到邮件当中  
            mailContent.addBodyPart(mailBody);                 
            // 邮件正文(内嵌图片+html文本)  
            MimeMultipart body = new MimeMultipart("related");  //邮件正文也是一个组合体,需要指明组合关系  
            mailBody.setContent(body);           
            // 邮件正文由html和图片构成   
            MimeBodyPart htmlPart = new MimeBodyPart();        
            body.addBodyPart(htmlPart); 
            // 图片  
            String[] photos=mailMessage.getPhoto();
            if(photos!=null && photos.length>0){
                for(String photo:photos){
                    MimeBodyPart imgPart = new MimeBodyPart();
                    DataHandler dh =null;
                    if(photo.contains("http:")|| photo.contains("https:")){
                        URLDataSource ds=new URLDataSource(new URL(photo)); 
                        dh = new DataHandler(ds); 
                    }else{
                        DataSource ds = new FileDataSource(photo); 
                        dh = new DataHandler(ds);
                    } 
                    imgPart.setDataHandler(dh);  
                    String contentID=photo.substring(photo.lastIndexOf(File.separator)+1);
                    imgPart.setContentID(contentID); 
                    body.addBodyPart(imgPart);   
                }
            }         
            // 正文html邮件内容  
            MimeMultipart htmlMultipart = new MimeMultipart("related");   
            htmlPart.setContent(htmlMultipart);  
            MimeBodyPart htmlContent = new MimeBodyPart();  
            htmlContent.setContent(mailMessage.getMessage(), "text/html;charset=gbk");  
            htmlMultipart.addBodyPart(htmlContent);            
            // 保存邮件内容修改  
            message.saveChanges();         
            // 发送邮件  
            Transport.send(message);  
            result = true; 
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }  
      
    /** 
     * 将邮件内容生成eml文件 
     * @param message 邮件内容 
     */  
    public static File buildEmlFile(Message message) throws MessagingException, FileNotFoundException, IOException {  
        File file = new File("c:\\" + MimeUtility.decodeText(message.getSubject())+".eml");  
        message.writeTo(new FileOutputStream(file));  
        return file;  
    }  
      
    /** 
     * 发送本地已经生成好的email文件 
     */  
    public static void sendMailForEml(File eml,MailSendInfo sendInfo) throws Exception {  
        // 获得邮件会话  
        Session session = connectToServer(sendInfo);  
        // 获得邮件内容,即发生前生成的eml文件  
        InputStream is = new FileInputStream(eml);  
        MimeMessage message = new MimeMessage(session,is);  
        //发送邮件  
        Transport.send(message);  
    } 
    
    // 异步执行  
    @SuppressWarnings("unused")
    public  static Boolean asynchronizedExecutor(MailSendInfo sendInfo,MailMessage mailMessage) {  
        Boolean flag = Boolean.FALSE;          
        FutureTask<Boolean> futureTask = null;  
        ExecutorService excutorService = Executors.newCachedThreadPool(); 
        try { 
            // 执行任务  
            futureTask = new FutureTask<Boolean>(new Callable<Boolean>() {  
                @Override  
                public Boolean call() throws Exception {  
                    return sendMultipleEmail(sendInfo,mailMessage);// 发送邮件;  
                }  
                  
            });  
            excutorService.submit(futureTask); 
            // 任务没超时说明发送成功  
            flag = futureTask.get(5L, TimeUnit.SECONDS);   
        }catch (Exception e) {  
            futureTask.cancel(true);  
            e.printStackTrace();  
        }finally {
            excutorService.shutdown();  
        }  
        return flag;  
    }  
    
    
    public static void main(String[] args) throws Exception {
        MailSendInfo sendInfo=new MailSendInfo("smtp.xdf.cn","hfhr","NewOrientalHR6810000","");
        // 发送文本邮件  
        MailMessage mailMessage=new MailMessage();
        mailMessage.setSubject("测试邮件");
        mailMessage.setFrom("hfhr@xdf.cn");
        //mailMessage.setTo(new String[]{"liuwei63@xdf.cn"});
        //mailMessage.setCc(new String[]{"chenyunsong@xdf.cn"});
        mailMessage.setTo(new String[]{"xiazhenyou@xdf.cn"});
   //     
                StringBuffer s = new StringBuffer();
        s.append("<body  style='font-size: 16;'>");
        s.append("<div style='width: 100%;'> 王玮老师,您好！</div>");
        s.append("<div style='width: 100%;'>");
        s.append("&nbsp;&nbsp;&nbsp;截至目前，校长信箱共收到<font style='font-size: 26;color: red;font-weight: bold;'>128</font>条反馈建议，已处理<font style='font-size: 26;color: green;font-weight: bold;'>128</font>条，详情请点击[<a href='#'>反馈待办</a>]。"
                + "<br>昨日收到的最新反馈<font style='font-size: 26;color: red;font-weight: bold;'>128</font>条，具体详情如下：");
        s.append("</div>");
        s.append("<div style='width: 100%;margin-top:10px;'>");
        s.append("<table style='width:90%;margin:0 auto;'>");
        s.append("<tr><td  colspan='2' >反馈人：XXXX&nbsp;&nbsp;反馈时间：2017-08-05 24:00:00</td></tr>");
        s.append("<tr><td style='white-space: nowrap;'>意见回复:</td><td ><a href='#'>点击回复</a></td></tr>");
        s.append("<tr><td valign='top'>反馈内容:</td><td >反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容:反馈内容: </td></tr>");
        s.append("</table><div>");
        s.append("</body>");

        mailMessage.setMessage(s.toString());
        //sendTextEmail(sendInfo,mailMessage);  
          
        // 发送简单的html邮件  
        //sendHtmlEmail(sendInfo,mailMessage);  
          
        // 发送带内嵌图片的HTML邮件  
        //sendHtmlWithInnerImageEmail(sendInfo,mailMessage);  
          
        // 发送混合组合邮件  

        System.out.println("发送状态:" + asynchronizedExecutor(sendInfo,mailMessage));   

        //sendMultipleEmail(sendInfo,mailMessage);  
          
        // 发送已经生成的eml邮件  
        //sendMailForEml();  
    }  
}
