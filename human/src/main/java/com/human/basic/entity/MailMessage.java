package com.human.basic.entity;

import java.util.List;

public class MailMessage {
   
    private String subject;//邮件主题
    
    private String from;//发件人
    
    private String[] to;//收件人
    
    private String[] cc ;//抄送人
    
    private String[] bcc ;//密送人
    
    private String type;//邮件类型  1：纯文本邮件  2：不带附件邮件  3：带附件混合邮件
    
    private String message;//邮件内容
    
    private String isContainAttach;//是否包含附件  0否 1是
    
    private String isContainPhoto;//是否包含内嵌图片  0否 1是
    
    private List<AttachMent>attachments ;//邮件附件
    
    private String[] photo;//邮件内嵌图片
    
    
    public MailMessage() {
        
    }
    
    public MailMessage(String subject, String from, String[] to, String type, String message) {
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.type = type;
        this.message = message;
    }
    
    public MailMessage(MailMessage message){
        this.subject = message.subject;
        this.from = message.from;
        this.to = message.to;
        this.cc = message.cc;
        this.bcc = message.bcc;
        this.type = message.type;
        this.message = message.message;
        this.isContainAttach = message.isContainAttach;
        this.isContainPhoto = message.isContainAttach;
        this.attachments = message.attachments;
        this.photo = message.photo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsContainAttach() {
        return isContainAttach;
    }

    public void setIsContainAttach(String isContainAttach) {
        this.isContainAttach = isContainAttach;
    }

    
    public String getIsContainPhoto() {
        return isContainPhoto;
    }

    public void setIsContainPhoto(String isContainPhoto) {
        this.isContainPhoto = isContainPhoto;
    }

    
    
    public List<AttachMent> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachMent> attachments) {
        this.attachments = attachments;
    }

    public String[] getPhoto() {
        return photo;
    }

    public void setPhoto(String[] photo) {
        this.photo = photo;
    }
 
    
    
}
