package com.human.utils.mailUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.aliyun.oss.OSSClient;
import com.human.basic.dao.DicDataDao;
import com.human.basic.entity.DicData;
import com.human.mail.entity.AcceptMail;
import com.human.order.utils.TenpayUtil;
import com.human.utils.OSSUtil;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

/** 
 * 邮件接收器，目前支持Exchange协议。 
 * 能够接收文本、HTML和带有附件的邮件 
*/
@Component
public class ExchangeMailReceiver{
    
    private final  Logger logger = LogManager.getLogger(ExchangeMailReceiver.class);
                  
    private static DicDataDao dicdataDao;
    
    private static OSSUtil oSSUtil;
       
    private static String bucketName;
    
    // 收邮件的参数配置 
    private ExchangeMailReceiverInfo receiverInfo; 
    
    // 当前正在处理的邮件消息 
    private EmailMessage currentMessage; 

    private String currentEmailFileName; 

    public ExchangeMailReceiver(ExchangeMailReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo; 
    }
    
    public ExchangeMailReceiver(){}
    

    /** 
     * 收邮件 
     */ 
    public List<AcceptMail> receiveAllMail(List<EmailMessage>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) throws Exception{ 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        try{
            // 获取邮件 
            acceptMailList=this.getAllMail(list,total,dicDataDao,ossUtil,path); 
        }catch(Exception e ){
            e.printStackTrace();
        }        
        return acceptMailList;
    } 
    

    /** 
     * 获取messages中的当天邮件 
     * @throws Exception 
     */ 
    private List<AcceptMail>getAllMail(List<EmailMessage>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) throws Exception {
        int errorCounter = 0; //邮件下载出错计数器 
        int successCounter = 0; //邮件下载成功计数器 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        dicdataDao=dicDataDao;
        oSSUtil=ossUtil;      
        for(EmailMessage ms:list){
            this.currentMessage=ms; 
            //目前只解析数据字典中招聘网站字典项配置的招聘网站
            String dataValue=this.getFrom();//获取发件人地址 
            if(StringUtils.isNotEmpty(dataValue)){
                int count=dicdataDao.queryByDataValue(dataValue);
                if(count==0){
                   continue; 
                } 
            }else{
                continue; 
            }           
            if("service@mail7.lietou-edm.com".equals(dataValue)){
                String subject=this.getSubject();
                if(!(subject.indexOf("【")!=-1 && subject.indexOf("】")!=-1)){
                    continue;
                }
            }
            AcceptMail acceptMail = new AcceptMail();
            try {
                System.out.println("-----开始获取邮件-----");               
                this.showMailBasicInfo(acceptMail);
                getMail(acceptMail,path); // 获取当前message
                System.out.println("------成功获取邮件-----");
                successCounter++;
                acceptMail.setIsReadSuccess(1);                
            }catch (Exception e) {
                e.printStackTrace();
                errorCounter++;
                logger.error("下载邮件出错",e.getMessage());
                acceptMail.setIsReadSuccess(0);
            }
            acceptMailList.add(acceptMail);            
        }
        System.out.println("------------------"); 
        System.out.println("成功下载了" + successCounter + "封邮件"); 
        System.out.println("失败下载了" + errorCounter + "封邮件"); 
        System.out.println("------------------");
        return acceptMailList;
       
    }

    /** 
     * 显示邮件的基本信息 
     */ 
    private void showMailBasicInfo(AcceptMail acceptMail) throws Exception{ 
         showMailBasicInfo(this.currentMessage,acceptMail); 
    }
    
    private void showMailBasicInfo(EmailMessage message,AcceptMail acceptMail) throws Exception{
        acceptMail.setMessageId(this.getMessageId());
        acceptMail.setSubject(this.getSubject());
        // 获取招聘网站
        List<DicData> list = dicdataDao.selectByDicCode("resume_source");
        String messageSource = "";
        for (DicData dd : list) {
            if (this.getFrom().contains(dd.getDataValue())) {
                messageSource = dd.getName();
            }
        }
        acceptMail.setMessageSource(messageSource);
        acceptMail.setSendTime(TenpayUtil.getTimestamp2(this.getSentDate()));
        acceptMail.setIsRead(1);
        boolean flag=this.isContainAttach();
        if(flag){
            acceptMail.setIsContainAttach(1); 
        }else{
            acceptMail.setIsContainAttach(0);  
        }
    } 

 
    /** 
     * 获得发件人的地址和姓名 
     * @throws Exception 
     */ 
    private String getFrom() throws Exception { 
        return getFrom(this.currentMessage); 
    } 

    private String getFrom(EmailMessage mimeMessage) throws Exception { 
        String messageId=mimeMessage.getInternetMessageId();
        String sendName=mimeMessage.getSender().getName();
        String from ="";
        if(messageId.contains("quickmail.51job.com")||sendName.contains("前程无忧")){
            from = "resume@quickmail.51job.com"; 
        }else if(messageId.contains("mail.zhaopinmail.com")||sendName.contains("智联")){
            from="service@zhaopinmail.com";
        }else if(sendName.contains("新安人才网")){
            from="resume@mail2.goodjobs.cn";
        }else if(sendName.contains("猎聘网")){
            from="service@mail7.lietou-edm.com";
        }      
        return from; 
    }

    
    /** 
     * 获得邮件主题 
     */ 
    private String getSubject() throws Exception { 
        return getSubject(this.currentMessage); 
    } 

    
    @SuppressWarnings("finally")
    private String getSubject(EmailMessage mimeMessage) throws Exception { 
        String subject = "";
        try {
            // 将邮件主题解码
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if (subject == null) {
                subject = "";
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("获得邮件主题失败", e.getMessage());
        }finally{
            return subject; 
        }
    } 

    /** 
     * 获得邮件发送日期 
     */ 
    private String getSentDate() throws Exception { 
        return getSentDate(this.currentMessage); 
    } 

    private String getSentDate(EmailMessage mimeMessage) throws Exception { 
    	Date receivedDate = mimeMessage.getDateTimeSent();  
        if (receivedDate == null) { 
        	return "";  
        }          
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receivedDate);  
    } 
    
    /** 
     * 获得此邮件的Message-ID 
     */ 
    private String getMessageId() throws Exception { 
        return getMessageId(this.currentMessage); 
    } 

    private String getMessageId(EmailMessage mimeMessage) throws Exception { 
        String internetMessageId="";
        try {
            internetMessageId= mimeMessage.getInternetMessageId();
        }catch (Exception e) {           
            e.printStackTrace();
        } 
        return internetMessageId;
    } 

    /** 
     * 判断此邮件是否包含附件 
     */ 
    private boolean isContainAttach() throws Exception { 
        return isContainAttach(this.currentMessage); 
    } 
    
    private boolean isContainAttach(EmailMessage mimeMessage) throws Exception { 
        boolean attachflag = false; 
        if(mimeMessage.getHasAttachments()){
            attachflag = true; 
        }
        return attachflag; 
    } 

       
    /** 
     * 获得当前邮件内容，附件
     */ 
    private void getMail(AcceptMail acceptMail,String path) throws Exception { 
        try { 
        	//保存邮件源文件
            this.saveMessageAsFile(this.currentMessage,acceptMail,path);
            //解析邮件
            this.parseMessage(this.currentMessage,acceptMail, path);
          }catch (Exception e) { 
            e.printStackTrace(); 
            throw new Exception("解析邮件错误!"); 
         } 
     } 
    
    /** 
     * 保存邮件源文件 
     */ 
    private void saveMessageAsFile(EmailMessage message,AcceptMail acceptMail,String path) {
        File file=new File("");
        try { 
            long oriFileName=System.currentTimeMillis();
            String fileNameWidthExtension =path+ oriFileName + this.receiverInfo.getEmailFileSuffix();  
            //读取邮件消息的内容
            message.load();
            String result=createLocalEmail(message); 
            if(StringUtils.isNotEmpty(result)){
                // 读取邮件消息流中的数据 
                StringReader in = new StringReader(result); 
                file=saveFile(fileNameWidthExtension, in);
                OSSClient ossClient = oSSUtil.getClient();           
                String newFileName = this.receiverInfo.getEmailDir()+ oriFileName + this.receiverInfo.getEmailFileSuffix(); 
                this.currentEmailFileName = newFileName; 
                System.out.println("邮件原件的存储路径: " + newFileName);
                bucketName=oSSUtil.getBucketName();
                Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,file);
                boolean uploadflag = (boolean)uploadResult.get("flag");
                if(uploadflag){
                    String savePath = (String)uploadResult.get("objectkey");
                    acceptMail.setOriginalMail(savePath);
                }
            }           
           file.delete();
        }catch (Exception e) {
          logger.info("保存邮件源文件 出错",e.getMessage());
          e.printStackTrace();
          file.delete();
        }
    }  
    
    
    @SuppressWarnings({ "static-access" })
    private String createLocalEmail(EmailMessage emailmessage){       
        String result="";
        try{
            Session session =null;
            // 创建MimeMessage实例对象  
            MimeMessage message = new MimeMessage(session);
            // 设置邮件主题  
            message.setSubject(emailmessage.getSubject(), "utf-8");  
            // 设置发送人  
            String from=getFrom(emailmessage);
            message.setFrom(new InternetAddress(from));  
            // 设置发送时间  
            message.setSentDate(emailmessage.getDateTimeSent());  
            // 设置收件人  
            String toUser="hfhr@xdf.cn";
            InternetAddress[] internetAddressTo = new InternetAddress().parse(toUser);        
            message.setRecipients(RecipientType.TO, internetAddressTo);  
            // 设置html内容为邮件正文，指定MIME类型为text/html类型，并指定字符编码为utf-8  
            String body=emailmessage.getBody().toString();
            message.setContent(body,"text/html;charset=utf-8");
            // 保存并生成最终的邮件内容  
            message.saveChanges();   
            // 将邮件消息的内容写入ByteArrayOutputStream流中 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            message.writeTo(baos); 
            result=baos.toString();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /** 
     * 保存文件内容 
     * @param fileName    文件名 
     * @param input        输入流 
     * @throws IOException 
     */ 
    private  File saveFile(String fileName, Reader input) throws IOException { 
        File file = new File(fileName);
        // 从输入流中读取数据，写入文件输出流 
        BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8")); 
        BufferedReader bis = new BufferedReader(input); 
        int aByte; 
        while ((aByte = bis.read()) != -1) { 
            bos.write(aByte); 
        } 
        // 关闭流 
        bos.flush(); 
        bos.close(); 
        bis.close();
        return file;
    } 
    
    
    /* 
     * 解析邮件 
     */ 
    private void parseMessage(EmailMessage message,AcceptMail acceptMail,String path) throws Exception{ 
        try{
            message.load();
            MessageBody messageBody=message.getBody();
            String type=messageBody.getBodyType().toString();
            System.out.println("邮件内容类型 :" + type);
            if("HTML".equals(type)){
                saveAttachment(messageBody.toString(),acceptMail,path);   
            }else if("Text".equals(type)){
                
            }
            //保存邮件附件
            if(message.getHasAttachments()){
                // 处理附件
                List<Attachment> attachs = message.getAttachments().getItems();
                String name = "";
                String savePath="";
                for (Attachment attach : attachs){
                    if (attach instanceof FileAttachment) {
                        // 接收邮件到临时目录
                        name=attach.getName();
                        System.out.println("附件名称 :" +name);
                        File file = new File(path,name);
                        ((FileAttachment)attach).load(file.getPath());
                        OSSClient ossClient = oSSUtil.getClient();           
                        String newFileName = this.receiverInfo.getAttachmentDir()+name ; 
                        System.out.println("邮件附件的存储路径: " + newFileName);
                        Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,file);
                        boolean uploadflag = (boolean)uploadResult.get("flag");
                        if(uploadflag){
                            savePath += (String)uploadResult.get("objectkey")+";";
                            file.delete();
                        }                                          
                    }
                } 
                if(savePath!=""){
                    savePath=savePath.substring(0,savePath.lastIndexOf(";"));
                    acceptMail.setAttachment(savePath); 
                }     
            }
        }catch(Exception e){
            e.printStackTrace();
        }                     
    } 
    
            
    /**
     * 读取内容为文本格式的邮件
     * @param content
     * @param acceptMail
     * @param path
     * @throws IOException
     */
    public void saveAttachment(String content,AcceptMail acceptMail,String path) throws IOException{
        long time=System.currentTimeMillis();
        String fileNameWidthExtension =path+time+".html";
        File file=new File(fileNameWidthExtension);
        FileWriter fw=new FileWriter(file);
        fw.write(content);
        fw.close();
        OSSClient ossClient = oSSUtil.getClient();
        String newFileName= this.receiverInfo.getHtmlEmailDir()+time+".html";
        acceptMail.setIsPhoto(0);    
        System.out.println("邮件转换格式的存储路径: " + newFileName);
        Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,file);
        boolean uploadflag = (boolean)uploadResult.get("flag");
        if(uploadflag){
            String mailHtmlPath = (String)uploadResult.get("objectkey");
            acceptMail.setMailHtml(mailHtmlPath);                                              
        }
        file.delete();         
   }

    
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
    @SuppressWarnings("unused")
    private File saveFile(InputStream is,String fileName)throws FileNotFoundException, IOException{
        File file=new File(fileName);        
        BufferedInputStream bis = new BufferedInputStream(is);  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));  
        int len = -1;  
        while ((len = bis.read()) != -1) {  
            bos.write(len);     
        }  
        bos.flush(); 
        bos.close();  
        bis.close();       
        return file;
        
    } 
    
    /**
     * 获取附件名称
     * @param part
     * @return
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unused")
    private String getFileName(String fileName) throws Exception{ 
        fileName = MimeUtility.decodeText(fileName); 
        String name = fileName; 
        if (fileName != null) { 
            int index = fileName.lastIndexOf("/"); 
            if (index != -1) { 
                name = fileName.substring(index + 1); 
            } 
        } 
        return name; 
    }    

}
