package com.human.mail.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
/**
 * 邮箱中邮件POJO
 * @author liuwei
 *
 */
public class AcceptMail implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String messageId;

    private String messageSource;

    private Timestamp sendTime;
    
    /**
     * 用于查询的投递时间
     * @return
     */
    private String deliveryDateStart;
    
    private String deliveryDateEnd;

    private String messageAccept;

    private Integer isRead;

    private Integer isContainAttach;

    private String subject;

    private String originalMail;

    private String attachment;

    private String mailHtml;

    private Integer isReadSuccess;

    private Integer isAnalysis;

    private Integer isAnalysisSuccess;
    
    private Integer isPhoto;
    
    /*
     * 机构名称Id
     */
    private String hrCompanyId;
    
    
    private String path;
    
    private String hrCompanyName;
    
    private Date statTime;

    private Date endTime;

    private Integer count;

    private Date failTime;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId == null ? null : messageId.trim();
    }

    public String getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(String messageSource) {
        this.messageSource = messageSource == null ? null : messageSource.trim();
    }


    public String getMessageAccept() {
        return messageAccept;
    }

    public void setMessageAccept(String messageAccept) {
        this.messageAccept = messageAccept == null ? null : messageAccept.trim();
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getOriginalMail() {
        return originalMail;
    }

    public void setOriginalMail(String originalMail) {
        this.originalMail = originalMail == null ? null : originalMail.trim();
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment == null ? null : attachment.trim();
    }

    public String getMailHtml() {
        return mailHtml;
    }

    public void setMailHtml(String mailHtml) {
        this.mailHtml = mailHtml == null ? null : mailHtml.trim();
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getIsContainAttach() {
        return isContainAttach;
    }

    public void setIsContainAttach(Integer isContainAttach) {
        this.isContainAttach = isContainAttach;
    }

    public Integer getIsReadSuccess() {
        return isReadSuccess;
    }

    public void setIsReadSuccess(Integer isReadSuccess) {
        this.isReadSuccess = isReadSuccess;
    }

    public Integer getIsAnalysis() {
        return isAnalysis;
    }

    public void setIsAnalysis(Integer isAnalysis) {
        this.isAnalysis = isAnalysis;
    }

    public Integer getIsAnalysisSuccess() {
        return isAnalysisSuccess;
    }

    public void setIsAnalysisSuccess(Integer isAnalysisSuccess) {
        this.isAnalysisSuccess = isAnalysisSuccess;
    }

    public Integer getIsPhoto() {
        return isPhoto;
    }

    public void setIsPhoto(Integer isPhoto) {
        this.isPhoto = isPhoto;
    }

    public String getHrCompanyId() {
        return hrCompanyId;
    }

    public void setHrCompanyId(String hrCompanyId) {
        this.hrCompanyId = hrCompanyId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHrCompanyName() {
        return hrCompanyName;
    }

    public void setHrCompanyName(String hrCompanyName) {
        this.hrCompanyName = hrCompanyName;
    }

    public Date getStatTime() {
        return statTime;
    }

    public void setStatTime(Date statTime) {
        this.statTime = statTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getFailTime() {
        return failTime;
    }

    public void setFailTime(Date failTime) {
        this.failTime = failTime;
    }

    public String getDeliveryDateStart() {
        return deliveryDateStart;
    }

    public void setDeliveryDateStart(String deliveryDateStart) {
        this.deliveryDateStart = deliveryDateStart;
    }

    public String getDeliveryDateEnd() {
        return deliveryDateEnd;
    }

    public void setDeliveryDateEnd(String deliveryDateEnd) {
        this.deliveryDateEnd = deliveryDateEnd;
    }  

    
    
    
}