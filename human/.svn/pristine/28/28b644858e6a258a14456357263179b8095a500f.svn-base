package com.human.utils.mailUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.human.basic.dao.DicDataDao;
import com.human.mail.entity.AcceptMail;
import com.human.utils.OSSUtil;

import microsoft.exchange.webservices.data.core.service.item.EmailMessage;

public class MailUtil {
    
	/**
	 * pop3协议收邮件的实体类
	 * @param mailServerHost
	 * @param userName
	 * @param password
	 * @param attachmentDir
	 * @param emailDir
	 * @param htmlEmailDir
	 * @return
	 */
	public static MailReceiverInfo getMailReceiverInfo(String mailServerHost,String userName,String password,String attachmentDir,String emailDir,String htmlEmailDir){
	    MailReceiverInfo receiverInfo = new MailReceiverInfo();
        receiverInfo.setMailServerHost(mailServerHost);
        receiverInfo.setMailServerPort("110");//
        receiverInfo.setUserName(userName);
        receiverInfo.setPassword(password);
        receiverInfo.setAttachmentDir(attachmentDir);
        receiverInfo.setEmailDir(emailDir);
        receiverInfo.setHtmlEmailDir(htmlEmailDir);
        receiverInfo.setValidate(true);
        return receiverInfo;
	}

	/**
	 * 检查邮箱POP3协议能否正常连接
	 * @param mailServerHost
	 * @param userName
	 * @param password
	 * @return
	 */
	public static Map<String, Object> checkRecruitMail(String mailServerHost,String userName,String password){
	    Map<String,Object> map=new HashMap<String,Object>();
	    MailReceiverInfo receiverInfo=getMailReceiverInfo(mailServerHost,userName, password,"","","");
        MailReceiver receiver = new MailReceiver(receiverInfo);
        map=receiver.connectToServer();
	    return map;
	}
	
	
	/**
     * Exchange协议收邮件的实体类
     * @param mailServerHost
     * @param userName
     * @param password
     * @param attachmentDir
     * @param emailDir
     * @param htmlEmailDir
     * @return
     */
    public static ExchangeMailReceiverInfo getExchangeMailReceiverInfo(String mailServerHost,String userName,String password,String domain ,String attachmentDir,String emailDir,String htmlEmailDir){
        ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,userName,password,domain,attachmentDir,emailDir,htmlEmailDir);
        return receiverInfo;
    }
	

    /**
     * 读取pop3协议邮件
     * @param receiverInfo
     * @param list
     */
	public static List<AcceptMail> readMailByParams(MailReceiverInfo receiverInfo,List<String>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) {    
        MailReceiver receiver = new MailReceiver(receiverInfo); 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        try {
            acceptMailList=receiver.receiveAllMail(list,total,dicDataDao,ossUtil,path);
        } catch (Exception e){
            e.printStackTrace();
        }
        return acceptMailList;
    }
	
	/**
     * 读取Exchange协议邮件
     * @param receiverInfo
     * @param list
     */
    public static List<AcceptMail> readExchangeMailByParams(ExchangeMailReceiverInfo receiverInfo,List<EmailMessage>list,int total,DicDataDao dicDataDao,OSSUtil ossUtil,String path) {    
        ExchangeMailReceiver receiver = new ExchangeMailReceiver(receiverInfo); 
        List<AcceptMail>acceptMailList=new ArrayList<AcceptMail>();
        try {
            acceptMailList=receiver.receiveAllMail(list,total,dicDataDao,ossUtil,path);
        } catch (Exception e){
            e.printStackTrace();
        }
        return acceptMailList;
    }
}
