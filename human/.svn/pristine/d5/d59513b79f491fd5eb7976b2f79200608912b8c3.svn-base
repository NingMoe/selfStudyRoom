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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import com.aliyun.oss.OSSClient;
import com.human.basic.dao.DicDataDao;
import com.human.basic.entity.DicData;
import com.human.mail.entity.AcceptMail;
import com.human.order.utils.TenpayUtil;
import com.human.utils.OSSUtil;

/** 
* 邮件接收器，目前支持pop3协议。 
* 能够接收文本、HTML和带有附件的邮件 
*/
@Component
public class MailReceiver{
    
    private final  Logger logger = LogManager.getLogger(MailReceiver.class);
                  
    private static DicDataDao dicdataDao;
    
    private static OSSUtil oSSUtil;
       
    private static String bucketName;
                          
	// 收邮件的参数配置 
    private MailReceiverInfo receiverInfo; 
    // 与邮件服务器连接后得到的邮箱 
    private Store store;
    // 收件箱 
    private Folder folder; 
    // 收件箱中的邮件消息 
    private Message[] messages; 
    // 当前正在处理的邮件消息 
    private Message currentMessage; 

    private String currentEmailFileName; 

    public MailReceiver(MailReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo; 
    }
    
    public MailReceiver(){
        
    }

    /** 
     * 收邮件 
     */ 
    public List<AcceptMail> receiveAllMail(List<String>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) throws Exception{ 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        if (this.receiverInfo == null){ 
            throw new Exception("必须提供接收邮件的参数！"); 
        } 
        // 连接到服务器 
        if ((boolean)(this.connectToServer().get("flag"))){ 
            // 打开收件箱 
            if(this.openInBoxFolder()){
                // 获取所有邮件 
                acceptMailList=this.getAllMail(list,total,dicDataDao,ossUtil,path); 
                this.closeConnection(); 
            }else { 
                throw new Exception("打开收件箱失败！"); 
            } 
        }else { 
            throw new Exception("连接邮件服务器失败！"); 
        }
        return acceptMailList;
    } 
    
    /** 
     * 登陆邮件服务器 
     */ 
    public  Map<String,Object> connectToServer() {
        Map<String,Object> map=new HashMap<String,Object>();
        // 判断是否需要身份认证 
        MyAuthenticator authenticator = null; 
        if (this.receiverInfo.isValidate()) { 
            // 如果需要身份认证，则创建一个密码验证器 
            authenticator = new MyAuthenticator(this.receiverInfo.getUserName(), this.receiverInfo.getPassword()); 
        } 
        //创建session 
        Session session = Session.getInstance(this.receiverInfo.getProperties(), authenticator); 
        //创建store,建立连接 
        try { 
            this.store = session.getStore(this.receiverInfo.getProtocal()); 
        } catch (NoSuchProviderException e) {
            logger.error(e.getMessage());
            map.put("flag", false);
            map.put("message", "邮箱不支持pop3协议!");
            return map; 
        } 
        try { 
            this.store.connect(this.receiverInfo.getMailServerHost(),this.receiverInfo.getUserName(),this.receiverInfo.getPassword()); 
        } catch (MessagingException e) {
            logger.error(e.getMessage());
            map.put("flag", false);
            map.put("message", "连接服务器失败！请检查pop3服务器地址、用户名或密码是否正确!");
            return map; 
        } 
        map.put("flag", true);
        map.put("message", "测试连接服务器成功！");
        return map; 
    } 
    /** 
     * 打开收件箱 
     */ 
    private boolean openInBoxFolder() { 
        try { 
            this.folder = store.getFolder("INBOX"); 
            // 读 写
            folder.open(Folder.READ_WRITE);//Folder.READ_WRITE
            return true; 
        } catch (MessagingException e) {
            logger.error(e.getMessage());
            System.err.println("打开收件箱失败！"); 
        } 
        return false; 
    }
        
    /** 
     * 断开与邮件服务器的连接 
     */ 
    private boolean closeConnection() { 
        try { 
            if (this.folder.isOpen()) { 
                this.folder.close(true); 
            } 
            this.store.close(); 
            System.out.println("成功关闭与邮件服务器的连接！"); 
            return true; 
        } catch (Exception e) {
            logger.error(e.getMessage());
            System.out.println("关闭和邮件服务器之间连接时出错！"); 
        } 
        return false; 
    } 

    /** 
     * 获取messages中的当天邮件 
     * @throws Exception 
     */ 
    private List<AcceptMail>getAllMail(List<String>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) throws Exception {
        int end=this.folder.getMessageCount();
        int start=end-total+1;
        int i=0;
        int messagesCount=list.size();
        Message[]messageAll=this.folder.getMessages(start,end);
        List<Message>messages=new ArrayList<Message>();
        long timeStart=System.currentTimeMillis();
        for(Message message:messageAll){
            if(list.contains(((MimeMessage)message).getMessageID())){
                messages.add(message); 
                i++;
                if(i==messagesCount){
                   break; 
                }
            }
        }
        long timeEnd=System.currentTimeMillis();
        System.out.println("匹配邮件耗时："+(timeEnd- timeStart)/1000+"秒");
        int errorCounter = 0; //邮件下载出错计数器 
        int successCounter = 0; //邮件下载成功计数器 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        dicdataDao=dicDataDao;
        oSSUtil=ossUtil;      
        for(Message ms:messages){
            this.currentMessage=ms; 
            //目前只解析数据字典中招聘网站字典项配置的招聘网站
            String dataValue=this.getFrom();//获取发件人地址             
            int count=dicdataDao.queryByDataValue(dataValue);
            if(count==0){
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
                System.out.println("正在获取第"+ms.getMessageNumber() +"封邮件");               
                this.showMailBasicInfo(acceptMail);
                getMail(acceptMail,path); // 获取当前message
                System.out.println("成功获取第"+ms.getMessageNumber()+"封邮件");
                successCounter++;
                acceptMail.setIsReadSuccess(1);                
            }catch (Exception e) {
                e.printStackTrace();
                errorCounter++;
                logger.error("下载第" + this.currentMessage.getMessageNumber() + "封邮件时出错",e.getMessage());
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
    private void showMailBasicInfo(Message message,AcceptMail acceptMail) throws Exception{
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
     * 判断邮件是否已读 
     * @throws Exception 
     */ 
    private boolean isSeen() throws MessagingException { 
        return isSeen(this.currentMessage); 
    } 
    
    
    /**  
     * 判断邮件是否已读  
     * @param msg 邮件内容  
     * @return 如果邮件已读返回true,否则返回false  
     * @throws MessagingException   
     */  
    private  boolean isSeen(Message mimeMessage) throws MessagingException {  
        return mimeMessage.getFlags().contains(Flags.Flag.SEEN);  
    } 
    
    /** 
     * 获得邮件的优先级 
     * @param msg 邮件内容 
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low) 
     * @throws MessagingException  
     */
    private String getPriority() throws MessagingException {
    	return getPriority(this.currentMessage);
    }
    
  
    private String getPriority(Message msg) throws MessagingException {  
        String priority = "普通";  
        String[] headers = msg.getHeader("X-Priority");  
        if (headers != null) {  
            String headerPriority = headers[0];  
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)  
                priority = "紧急";  
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)  
                priority = "低";  
            else  
                priority = "普通";  
        }  
        return priority;  
    }   
    
    
    
    
    /** 
     * 获得邮件的收件人，抄送，和密送的地址和姓名，根据所传递的参数的不同 
     * "to"----收件人 "cc"---抄送人地址 "bcc"---密送人地址 
     */ 
    private String getTOAddress() throws Exception { 
        return getMailAddress("TO", this.currentMessage); 
    } 

    private String getCCAddress() throws Exception { 
        return getMailAddress("CC", this.currentMessage); 
    } 

    private String getBCCAddress() throws Exception { 
        return getMailAddress("BCC", this.currentMessage); 
    } 

    
    /** 
     * 获得邮件地址 
     * @param type        类型，如收件人、抄送人、密送人 
     * @param mimeMessage    邮件消息 
     * @return 
     * @throws Exception 
     */ 
    private String getMailAddress(String type, Message mimeMessage) 
            throws Exception { 
        String mailaddr = ""; 
        String addtype = type.toUpperCase(); 
        InternetAddress[] address = null; 
        if (addtype.equals("TO") || addtype.equals("CC") 
                || addtype.equals("BCC")) { 
            if (addtype.equals("TO")) { 
                address = (InternetAddress[]) mimeMessage 
                        .getRecipients(Message.RecipientType.TO); 
            } else if (addtype.equals("CC")) { 
                address = (InternetAddress[]) mimeMessage 
                        .getRecipients(Message.RecipientType.CC); 
            } else { 
                address = (InternetAddress[]) mimeMessage 
                        .getRecipients(Message.RecipientType.BCC); 
            } 
            if (address != null) { 
                for (int i = 0; i < address.length; i++) { 
                    // 先获取邮件地址 
                    String email = address[i].getAddress(); 
                    if (email == null){ 
                        email = ""; 
                    }else { 
                        email = MimeUtility.decodeText(email); 
                    } 
                    // 再取得个人描述信息 
                    String personal = address[i].getPersonal(); 
                    if (personal == null){ 
                        personal = ""; 
                    } else { 
                        personal = MimeUtility.decodeText(personal); 
                    } 
                    // 将个人描述信息与邮件地址连起来 
                    String compositeto = personal + "<" + email + ">"; 
                    // 多个地址时，用逗号分开 
                    mailaddr += "," + compositeto; 
                } 
                mailaddr = mailaddr.substring(1); 
            } 
        } else { 
            throw new Exception("错误的地址类型！!"); 
        } 
        return mailaddr; 
    } 

    /** 
     * 获得发件人的地址和姓名 
     * @throws Exception 
     */ 
    private String getFrom() throws Exception { 
        return getFrom(this.currentMessage); 
    } 

    private String getFrom(Message mimeMessage) throws Exception { 
        InternetAddress[] address = (InternetAddress[]) mimeMessage.getFrom(); 
        // 获得发件人的邮箱 
        String from = address[0].getAddress(); 
        if (from == null){ 
            from = ""; 
        }
        //智联招聘因为有多个发件箱，故作特殊处理
        if(from!="" && from.indexOf("service@zhaopinmail.com")!=-1 ){
            from="service@zhaopinmail.com";
        }
//        // 获得发件人的描述信息 
//        String personal = address[0].getPersonal(); 
//        if (personal == null){ 
//            personal = ""; 
//        } 
//        // 拼成发件人完整信息 
//        String fromaddr = personal + "<" + from + ">";         
        return from; 
    } 

    /** 
     * 获取messages中message的数量 
     * @return 
     */ 
    private int getMessageCount() { 
        return this.messages.length; 
    } 

    /** 
     * 获得收件箱中新邮件的数量 
     * @return 
     * @throws MessagingException 
     */ 
    private int getNewMessageCount() throws MessagingException { 
        return this.folder.getNewMessageCount(); 
    } 

    /** 
     * 获得收件箱中未读邮件的数量 
     * @return 
     * @throws MessagingException 
     */ 
    private int getUnreadMessageCount() throws MessagingException { 
        return this.folder.getUnreadMessageCount(); 
    } 

    /** 
     * 获得邮件主题 
     */ 
    private String getSubject() throws MessagingException { 
        return getSubject(this.currentMessage); 
    } 

    
    @SuppressWarnings("finally")
    private String getSubject(Message mimeMessage) throws MessagingException { 
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

    private String getSentDate(Message mimeMessage) throws Exception { 
    	Date receivedDate = mimeMessage.getSentDate();  
        if (receivedDate == null) { 
        	return "";  
        }          
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receivedDate);  
    } 

    /** 
     * 判断此邮件是否需要回执，如果需要回执返回"true",否则返回"false" 
     */ 
    private boolean getReplySign() throws MessagingException { 
        return getReplySign(this.currentMessage); 
    } 

    private boolean getReplySign(Message mimeMessage) throws MessagingException { 
        boolean replysign = false; 
        String needreply[] = mimeMessage 
                .getHeader("Disposition-Notification-To"); 
        if (needreply != null) { 
            replysign = true; 
        } 
        return replysign; 
    } 

    /** 
     * 获得此邮件的Message-ID 
     */ 
    private String getMessageId() throws MessagingException { 
        return getMessageId(this.currentMessage); 
    } 

    private String getMessageId(Message mimeMessage) throws MessagingException { 
        return ((MimeMessage) mimeMessage).getMessageID(); 
    } 

    /** 
     * 判断此邮件是否是新邮件，如果不是返回返回false,反之返回true 
     */ 
    private boolean isNew() throws MessagingException { 
        return isNew(this.currentMessage); 
    }
       
    private boolean isNew(Message mimeMessage) throws MessagingException { 
        boolean isnew = false; 
        Flags flags = mimeMessage.getFlags(); 
        Flags.Flag[] flag = flags.getSystemFlags(); 
        for (int i = 0; i < flag.length; i++) { 
            if (flag[i] == Flags.Flag.SEEN) { 
                isnew = true; 
                break; 
            } 
        } 
        return isnew; 
    } 

    /** 
     * 判断此邮件是否包含附件 
     */ 
    private boolean isContainAttach() throws Exception { 
        return isContainAttach(this.currentMessage); 
    } 
    private boolean isContainAttach(Part part) throws Exception { 
        boolean attachflag = false; 
        if (part.isMimeType("multipart/*")){ 
            // 如果邮件体包含多部分 
            Multipart mp = (Multipart) part.getContent(); 
            int partCount = mp.getCount();  
            // 遍历每部分 
            for (int i = 0; i<partCount; i++){ 
                // 获得每部分的主体 
                BodyPart bodyPart = mp.getBodyPart(i); 
                String disposition = bodyPart.getDisposition(); 
                if ((disposition != null) 
                        && ((disposition.equals(Part.ATTACHMENT))||(disposition.equals(Part.INLINE)))){ 
                    attachflag = true; 
                } else if (bodyPart.isMimeType("multipart/*")) { 
                    attachflag = isContainAttach((Part) bodyPart); 
                } else { 
                    String contype = bodyPart.getContentType(); 
                    if (contype.toLowerCase().indexOf("application") != -1){ 
                        attachflag = true; 
                    } 
                    if (contype.toLowerCase().indexOf("name") != -1){ 
                        attachflag = true; 
                    } 
                } 
                if (attachflag) break;   
            } 
        } else if (part.isMimeType("message/rfc822")) { 
            attachflag = isContainAttach((Part) part.getContent()); 
        } 
        return attachflag; 
    } 

    /** 
     * 获得邮件文本内容 
     * @param part 邮件体 
     * @param content 存储邮件文本内容的字符串 
     * @throws MessagingException 
     * @throws IOException 
     */  
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {  
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断  
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;   
        if (part.isMimeType("text/*") && !isContainTextAttach) {  
            content.append(part.getContent().toString());  
        } else if (part.isMimeType("message/rfc822")) {   
            getMailTextContent((Part)part.getContent(),content);  
        } else if (part.isMimeType("multipart/*")) {  
            Multipart multipart = (Multipart) part.getContent();  
            int partCount = multipart.getCount();  
            for (int i = 0; i < partCount; i++) {  
                BodyPart bodyPart = multipart.getBodyPart(i);  
                getMailTextContent(bodyPart,content);  
            }  
        }  
    } 
       
    /** 
     * 获得当前邮件内容，附件
     */ 
    private void getMail(AcceptMail acceptMail,String path) throws Exception { 
        try { 
        	//保存邮件源文件
            this.saveMessageAsFile(currentMessage,acceptMail,path);
            this.parseMessage(currentMessage,acceptMail, path);
        } catch (IOException e) { 
            throw new IOException("保存邮件出错，检查保存路径!"); 
        } catch (MessagingException e) { 
            throw new MessagingException("邮件转换出错!"); 
        } catch (Exception e) { 
            e.printStackTrace(); 
            throw new Exception("未知错误!"); 
        } 
    } 
    
    /** 
     * 保存邮件源文件 
     */ 
    private void saveMessageAsFile(Message message,AcceptMail acceptMail,String path) {
        File file=new File("");
        try { 
            long oriFileName=System.currentTimeMillis();
            String fileNameWidthExtension =path+ oriFileName + this.receiverInfo.getEmailFileSuffix();  
            // 将邮件消息的内容写入ByteArrayOutputStream流中 
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            message.writeTo(baos); 
            // 读取邮件消息流中的数据 
            StringReader in = new StringReader(baos.toString()); 
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
           file.delete();
        }catch (Exception e) {
          logger.info("保存邮件源文件 出错",e.getMessage());
          e.printStackTrace();
          file.delete();
        }
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
    private void parseMessage(Message message,AcceptMail acceptMail,String path) throws IOException,MessagingException { 
        Object content = message.getContent(); 
        // 处理多部分邮件 
        if (content instanceof Multipart){
        	saveAttachment(message,acceptMail,path);
        }else{            
        	saveAttachment(content.toString(),acceptMail,path);            
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
     * 保存附件  
     * @param part 邮件中多个组合体中的其中一个组合体  
     * @param destDir  附件保存目录  
     * @throws UnsupportedEncodingException  
     * @throws MessagingException  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
	public void saveAttachment(Part part,AcceptMail acceptMail,String path)
			throws UnsupportedEncodingException, MessagingException, FileNotFoundException, IOException {
		String name = "";
		String fileNameWidthExtension = "";
		String savePath="";
		if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent(); // 复杂体邮件
			// 复杂体邮件包含多个邮件体
			int partCount = multipart.getCount();
			for (int i = 0; i < partCount; i++) {
				// 获得复杂体邮件中其中一个邮件体
				BodyPart bodyPart = multipart.getBodyPart(i);
				// 某一个邮件体也有可能是由多个邮件体组成的复杂体
				String disp = bodyPart.getDisposition();
				if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
					InputStream is = bodyPart.getInputStream();
					name = getFileName(bodyPart);
					fileNameWidthExtension = path+name;
					File file=saveFile(is, fileNameWidthExtension);
					OSSClient ossClient = oSSUtil.getClient();           
		            String newFileName = this.receiverInfo.getAttachmentDir()+name ; 
		            System.out.println("邮件附件的存储路径: " + newFileName);
		            Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,file);
		            boolean uploadflag = (boolean)uploadResult.get("flag");
		            if(uploadflag){
		                savePath += (String)uploadResult.get("objectkey")+";";
		                file.delete();
		            }		            
				} else if (bodyPart.isMimeType("multipart/*")){
					saveAttachment(bodyPart,acceptMail,path);
				} else {
					String contentType = bodyPart.getContentType();
			        if ((contentType.length() >= 9)&& (contentType.toLowerCase().substring(0, 4).equals("text"))) {
						fileNameWidthExtension =path+System.currentTimeMillis()+ ".html";						
					}else if ((contentType.length() >= 9)&& (contentType.toLowerCase().substring(0, 9).equals("image/gif"))) {
						fileNameWidthExtension = path+System.currentTimeMillis()+ ".gif";
					}else if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
					    //不是文本格式也不是纯图片格式
						name = getFileName(bodyPart);
						System.out.println("Attachment: " + name);
						fileNameWidthExtension = path+name;
					}
			        File file=saveFile(bodyPart.getInputStream(),fileNameWidthExtension);
			        OSSClient ossClient = oSSUtil.getClient();
			        String newFileName="";
			        if(fileNameWidthExtension.endsWith("html")){
			            newFileName= this.receiverInfo.getHtmlEmailDir()+System.currentTimeMillis()+ ".html";
			            acceptMail.setIsPhoto(0);
			        }else if(fileNameWidthExtension.endsWith("gif")){
			            newFileName= this.receiverInfo.getHtmlEmailDir()+System.currentTimeMillis()+ ".gif";
			            acceptMail.setIsPhoto(1);
			        }else{
			            newFileName= this.receiverInfo.getHtmlEmailDir()+name;
			        }    
                    System.out.println("邮件转换格式的存储路径: " + newFileName);
                    Map<String,Object> uploadResult =oSSUtil.uploadFile(ossClient,bucketName,newFileName,file);
                    boolean uploadflag = (boolean)uploadResult.get("flag");
                    if(uploadflag){
                        String mailHtmlPath = (String)uploadResult.get("objectkey");
                        acceptMail.setMailHtml(mailHtmlPath);                                              
                    }
                    file.delete();
				}
			}
			if(savePath!=""){
			    savePath=savePath.substring(0,savePath.lastIndexOf(";"));
			    acceptMail.setAttachment(savePath); 
			}           
		} else if (part.isMimeType("message/rfc822")) {
			saveAttachment((Part) part.getContent(),acceptMail,path);
		}
	} 
    
    /**  
     * 读取输入流中的数据保存至指定目录  
     * @param is 输入流  
     * @param fileName 文件名  
     * @throws FileNotFoundException  
     * @throws IOException  
     */  
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
    private String getFileName(Part part) throws MessagingException, UnsupportedEncodingException { 
        String fileName = part.getFileName(); 
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
    
    /** 
     * 获得尖括号之间的字符 
     * @param str 
     * @return 
     * @throws Exception 
     */ 
    private String getInfoBetweenBrackets(String str) throws Exception { 
        int i, j; //用于标识字符串中的"<"和">"的位置 
        if (str == null) { 
            str = "error"; 
            return str; 
        } 
        i = str.lastIndexOf("<"); 
        j = str.lastIndexOf(">"); 
        if (i != -1 && j != -1){ 
            str = str.substring(i + 1, j); 
        } 
        return str; 
    }

}
