package com.human.utils.mailUtils;

import java.util.Properties;

/** 
 * pop3收邮件的基本信息 
 */ 
public class MailReceiverInfo {
	// 邮件服务器的IP、端口和协议 
    private String mailServerHost; 
    private String mailServerPort = "110"; 
    private String protocal = "pop3";//pop3
    // 登陆邮件服务器的用户名和密码 
    private String userName; 
    private String password; 
    // 保存邮件的路径 
    private String attachmentDir = "";//附件路径
    private String emailDir = "";//原始邮件路径
    private String htmlEmailDir = "";//保存HTML格式邮件路径
    private String emailFileSuffix = ".eml";   
    // 是否需要身份验证 
    private boolean validate = true; 

    /** 
     * 获得邮件会话属性 
     */ 
    public Properties getProperties(){ 
        Properties p = new Properties(); 
        p.put("mail.store.protocol", this.protocal);
        p.put("mail.pop3.host", this.mailServerHost); 
        p.put("mail.pop3.port", this.mailServerPort); 
        p.put("mail.pop3.auth", validate ? "true" : "false"); 
        return p; 
    } 
    
    public String getProtocal() { 
        return protocal; 
    } 

    public void setProtocal(String protocal) { 
        this.protocal = protocal; 
    } 

    public String getAttachmentDir() { 
        return attachmentDir; 
    } 

    public void setAttachmentDir(String attachmentDir) { 
        this.attachmentDir = attachmentDir; 
    } 

    public String getEmailDir() { 
        return emailDir; 
    } 

    public void setEmailDir(String emailDir) { 
        this.emailDir = emailDir; 
    } 

    public String getHtmlEmailDir() {
        return htmlEmailDir;
    }

    public void setHtmlEmailDir(String htmlEmailDir) {
        this.htmlEmailDir = htmlEmailDir; 
    }

    public String getEmailFileSuffix() { 
        return emailFileSuffix; 
    } 

    public void setEmailFileSuffix(String emailFileSuffix) { 
        if (!emailFileSuffix.startsWith(".")){ 
            emailFileSuffix = "." + emailFileSuffix; 
        } 
        this.emailFileSuffix = emailFileSuffix; 
    } 

    public String getMailServerHost() { 
        return mailServerHost; 
    } 

    public void setMailServerHost(String mailServerHost) { 
        this.mailServerHost = mailServerHost; 
    } 

    public String getMailServerPort() { 
        return mailServerPort; 
    } 

    public void setMailServerPort(String mailServerPort) { 
        this.mailServerPort = mailServerPort; 
    } 

    public String getPassword() { 
        return password; 
    } 

    public void setPassword(String password) { 
        this.password = password; 
    } 

    public String getUserName() { 
        return userName; 
    } 

    public void setUserName(String userName) { 
        this.userName = userName; 
    } 

    public boolean isValidate() { 
        return validate; 
    } 

    public void setValidate(boolean validate) { 
        this.validate = validate; 
    } 
    
}
