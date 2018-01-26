package com.human.utils.mailUtils;


import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.human.basic.entity.AttachMent;
import com.human.basic.entity.MailMessage;
import com.human.order.utils.TenpayUtil;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.search.LogicalOperator;
import microsoft.exchange.webservices.data.core.enumeration.search.SortDirection;
import microsoft.exchange.webservices.data.core.enumeration.service.ConflictResolutionMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.core.service.schema.AppointmentSchema;
import microsoft.exchange.webservices.data.core.service.schema.EmailMessageSchema;
import microsoft.exchange.webservices.data.core.service.schema.ItemSchema;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.Attachment;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.FileAttachment;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;
import microsoft.exchange.webservices.data.search.filter.SearchFilter;

@Component
public class ExchangeMailUtil {
		
    /**
      * 检查邮箱Exchange协议能否正常连接
      * @param mailServerHost
      * @param userName
      * @param password
      * @return
      */
     @SuppressWarnings("unused")
    public  static Map<String, Object> checkRecruitMail(String mailServerHost,String userName,String password,String domain){
         Map<String,Object> map=new HashMap<String,Object>();
         mailServerHost="https://"+mailServerHost+"/EWS/exchange.asmx";
         ExchangeMailReceiverInfo receiverInfo = new ExchangeMailReceiverInfo(mailServerHost,userName,password,domain);
         ExchangeService service = getExchangeService(receiverInfo);
         // 绑定收件箱      
         try {
            Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
            map.put("flag", true);
            map.put("message", "测试连接服务器成功!");
         }catch (Exception e) {             
            map.put("flag", false);
            map.put("message", "测试连接服务器失败!请检查Exchange服务器地址、用户名或密码、域名是否正确!"); 
        }
         return map;
     }
	

	/**
	 * 创建邮件服务
	 * @return 邮件服务
	 */
	public  static  ExchangeService getExchangeService(ExchangeMailReceiverInfo receiverInfo) {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
		// 用户认证信息
		ExchangeCredentials credentials;
		String user=receiverInfo.getUser();
        String password=receiverInfo.getPassword();
		String domain=receiverInfo.getDomain();
		String mailServer=receiverInfo.getMailServer();
		if (domain == null) {
			credentials = new WebCredentials(user, password);
		} else {
			credentials = new WebCredentials(user, password, domain);
		}
		service.setCredentials(credentials);
		try {
			service.setUrl(new URI(mailServer));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return service;
	}

	/**
	 * 收取邮件
	 * 
	 * @param max
	 *            最大收取邮件数
	 * @param searchFilter
	 *            收取邮件过滤规则
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<EmailMessage> receive(ExchangeMailReceiverInfo receiverInfo,int max, SearchFilter searchFilter) throws Exception {
		ExchangeService service = getExchangeService(receiverInfo);
		// 绑定收件箱,同样可以绑定发件箱
		Folder inbox = Folder.bind(service, WellKnownFolderName.Inbox);
		// 获取文件总数量
		int count = inbox.getTotalCount();
		if (max > 0) {
			count = count > max ? max : count;
		}
		// 循环获取邮箱邮件
		ItemView view = new ItemView(count);
		// 按照时间顺序收取
		view.getOrderBy().add(ItemSchema.DateTimeReceived, SortDirection.Descending);
		FindItemsResults<Item> findResults;
		if (searchFilter == null) {
			findResults = service.findItems(inbox.getId(), view);
		} else {
			findResults = service.findItems(inbox.getId(), searchFilter, view);
		}
		ArrayList<EmailMessage> result = new ArrayList<>();
		if (findResults.getTotalCount() != 0) {
		    PropertySet detailedPropertySet = new PropertySet(BasePropertySet.FirstClassProperties, AppointmentSchema.Recurrence);
			service.loadPropertiesForItems(findResults, detailedPropertySet);
			for (Item item : findResults.getItems()) {			    			                    
				EmailMessage message = (EmailMessage) item;
				result.add(message);
			}
		}
		return result;
	}


	/**
	 * 发送邮件mail
	 * @param mailMessage
	 * @return
	 * @throws Exception
	 */
	public static Boolean send(ExchangeMailReceiverInfo receiverInfo,MailMessage mailMessage) throws Exception {
	    Boolean result = false;
        try{
    		ExchangeService service = getExchangeService(receiverInfo);
    		EmailMessage msg = new EmailMessage(service);		
    		msg.setSubject(mailMessage.getSubject());
    		MessageBody body = MessageBody.getMessageBodyFromText(mailMessage.getMessage());
    		body.setBodyType(BodyType.HTML);
    		msg.setBody(body);
    		//设置收件人 
            String[] to=mailMessage.getTo();
            if(to!=null && to.length>0){
                for (String toPerson : to) {
                    msg.getToRecipients().add(toPerson);
                }
            }
            //设置抄送人
            String[] cc=mailMessage.getCc();        
    		if (cc != null && cc.length>0) {
    			for (String ccPerson : cc) {
    				msg.getCcRecipients().add(ccPerson);
    			}
    		}
    		//设置密送人 (不会在邮件收件人名单中显示出来)
    		String[] bcc=mailMessage.getBcc();
    		if (bcc != null && bcc.length>0) {
                for (String bccPerson : bcc) {
                    msg.getBccRecipients().add(bccPerson);
                }
            }
    		// 附件  
            List<AttachMent>attachments=mailMessage.getAttachments();
    		if (attachments != null && attachments.size()>0) {
    			for (AttachMent attachment:attachments) {
    			    if("2".equals(attachment.getAttachmentPathType())){
    			        msg.getAttachments().addFileAttachment(attachment.getName(),attachment.getAttachmentPath());
    			    }else{
    			        msg.getAttachments().addFileAttachment(attachment.getName(),attachment.getContentStream());
    			    }				
    			}
    		}
    		// 设置发送时间  
    		msg.setReminderDueBy(new Date());
    		msg.send();
    		result = true; 
        }catch(Exception e ){
            e.printStackTrace();
        }
        return result;
	}


	/**
	 * 读取邮件的基本信息
	 * @param mail
	 * @throws Exception
	 */
    private static void showMailBasicInfo(EmailMessage mail) throws Exception {
		System.out.println("-------- 邮件ID：" + mail.getInternetMessageId() + " ---------");
		System.out.println("主题: " + mail.getSubject());
		System.out.println("发送人: " + mail.getFrom().getName());
		System.out.println("发送人: " + mail.getSender().getName());
		System.out.println("收件人:" + mail.getReceivedBy().getName());
		List<EmailAddress> list = mail.getCcRecipients().getItems();
		for (EmailAddress ea : list) {
			System.out.println("抄送人:" + ea.getName() + ", 地址:" + ea.getAddress());
		}
		list = mail.getToRecipients().getItems();
		for (EmailAddress ea : list) {
			System.out.println("收件人:" + ea.getName() + ", 地址:" + ea.getAddress());
		}
		System.out.println("发送时间:" + dateChangeToString(mail.getDateTimeSent()));
		System.out.println("接收时间: " + dateChangeToString(mail.getDateTimeReceived()));
		System.out.println("是否已读:" + mail.getIsRead());
		System.out.println("是新邮件:" + mail.getIsNew());
		System.out.println("包含附件:" + mail.getHasAttachments());
		mail.load();
		MessageBody messageBody=mail.getBody();
		System.out.println("邮件内容类型 :" + messageBody.getBodyType().toString());
		System.out.println("邮件内容 :" + messageBody.toString());
	}

	
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 * @throws Exception
	 */
	private static String dateChangeToString(Date date) throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
    /**
     * 获取接收邮件箱中当天的邮件
     * @param mailServerHost
     * @param userName
     * @param password
     * @param domain
     * @return
     */
    public static List<EmailMessage> getEmailByParams(String mailServerHost,String userName,String password,String domain){        
        // Outlook Web Access路径通常为/EWS/exchange.asmx
        mailServerHost="https://"+mailServerHost+"/EWS/exchange.asmx";
        ExchangeMailReceiverInfo receiverInfo = new  ExchangeMailReceiverInfo(mailServerHost,userName,password,domain);
        // 创建过滤器,收取今天的邮件
        String bill_date = TenpayUtil.formatDate();
        String timeStrat = bill_date + " 00:00:00";
        String timeEnd = bill_date + " 23:59:59";
        Date dateStart = TenpayUtil.getDate(timeStrat);
        Date dateEnd = TenpayUtil.getDate(timeEnd);
        SearchFilter sf1 = new SearchFilter.IsGreaterThanOrEqualTo(EmailMessageSchema.DateTimeSent, dateStart);
        SearchFilter sf2 = new SearchFilter.IsLessThanOrEqualTo(EmailMessageSchema.DateTimeSent, dateEnd);
        SearchFilter sf = new SearchFilter.SearchFilterCollection(LogicalOperator.And, sf1, sf2);
        ArrayList<EmailMessage> mails=new ArrayList<EmailMessage>(100);
        try {
            mails =receive(receiverInfo,200, sf);
        }catch (Exception e) {
           e.printStackTrace();
        }
        return mails;       
    }

    

    public static void main(String[] args) throws Exception {
          // Outlook Web Access路径通常为/EWS/exchange.asmx
          ExchangeMailReceiverInfo receiverInfo = new  ExchangeMailReceiverInfo("https://mailbj.xdf.cn/EWS/exchange.asmx", "hfhr","NewOrientalHR6810000", "staff");
          // 创建过滤器, 条件为邮件未读
          SearchFilter sf1 = new SearchFilter.IsEqualTo(EmailMessageSchema.IsRead, true);
          String bill_date=TenpayUtil.formatDate();
          String timeStrat=bill_date+" 00:00:00";
          String timeEnd=bill_date+" 23:59:59";
          Date dateStart=TenpayUtil.getDate(timeStrat);
          Date dateEnd=TenpayUtil.getDate(timeEnd);
          SearchFilter sf2 = new SearchFilter.IsGreaterThanOrEqualTo(EmailMessageSchema.DateTimeSent,dateStart);
          SearchFilter sf3 = new SearchFilter.IsLessThanOrEqualTo(EmailMessageSchema.DateTimeSent,dateEnd);
          SearchFilter sf = new SearchFilter.SearchFilterCollection(LogicalOperator.And,sf1,sf2,sf3);
          ArrayList<EmailMessage> mails = receive(receiverInfo,200, sf);
          for (EmailMessage mail : mails) {
                showMailBasicInfo(mail);
                // 更新已读
                if (!mail.getIsRead()) {
                    mail.setIsRead(true);
                    mail.update(ConflictResolutionMode.AlwaysOverwrite);
                }
                // 处理附件
                List<Attachment> attachs = mail.getAttachments().getItems();
                try {
                    if (mail.getHasAttachments()) {
                        for (Attachment attach : attachs) {
                            if (attach instanceof FileAttachment) {
                                // 接收邮件到临时目录
                                System.out.println(attach.getName());
                                File tempZip = new File("E:/temp",attach.getName());
                                ((FileAttachment)attach).load(tempZip.getPath());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}
