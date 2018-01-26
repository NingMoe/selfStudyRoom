package com.human.utils.mailUtils;

import java.util.Properties;

/** 
* 发邮件的基本信息 
*/ 
public class MailSendInfo {
	// 发件邮件服务器的IP、端口和协议 
    private String mailServerHost; 
    private final static String mailServerPort = "25"; 
    private final static String protocal = "smtp";
    //是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）  
    private final static String IS_ENABLED_DEBUG_MOD = "false";  
    // 发件邮件服务器的用户名和密码 
    private String userName; 
    private String password; 
    // 邮件附件路径 
    private String attachmentDir;
    // 是否需要身份验证 
    private final static boolean validate = true; 

    /** 
     * 获得邮件会话属性 
     */ 
    public Properties getProperties(){ 
        Properties p = new Properties();         
        p.setProperty("mail.transport.protocol", protocal);  
        p.setProperty("mail.smtp.host", this.mailServerHost);  
        p.setProperty("mail.smtp.port", mailServerPort);  
        p.setProperty("mail.smtp.auth", validate ? "true" : "false");  
        p.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD);
        return p; 
    }
    
    
    
    public MailSendInfo(String mailServerHost, String userName, String password, String attachmentDir) {
        this.mailServerHost = mailServerHost;
        this.userName = userName;
        this.password = password;
        this.attachmentDir = attachmentDir;
    }

    
    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAttachmentDir() {
        return attachmentDir;
    }

    public void setAttachmentDir(String attachmentDir) {
        this.attachmentDir = attachmentDir;
    }

    public static String getMailserverport() {
        return mailServerPort;
    }

    public static String getProtocal() {
        return protocal;
    }

    public static boolean isValidate() {
        return validate;
    }

    
}
