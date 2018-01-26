package com.human.utils.mailUtils;



/** 
 * Exchange协议收邮件的基本信息 
*/ 
public class ExchangeMailReceiverInfo {
    
    private String mailServer;
    
    private String user;
    
    private String password;
    
    private String domain;
    //附件路径
    private String attachmentDir;
    //原始邮件路径
    private String emailDir;
    //保存HTML格式邮件路径
    private String htmlEmailDir;
    
    private String emailFileSuffix = ".eml"; 
    
    public ExchangeMailReceiverInfo(String mailServer, String user, String password, String domain) {
        this.mailServer = mailServer;
        this.user = user;
        this.password = password;
        this.domain = domain;
    }
    
    public ExchangeMailReceiverInfo(String mailServer, String user, String password, String domain,String attachmentDir,String emailDir,String htmlEmailDir) {
        this.mailServer = mailServer;
        this.user = user;
        this.password = password;
        this.domain = domain;
        this.attachmentDir = attachmentDir;
        this.emailDir = emailDir;
        this.htmlEmailDir = htmlEmailDir;
   }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
    
    
}
