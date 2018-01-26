package com.human.mail.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.human.basic.dao.RecruitMailDao;
import com.human.basic.entity.RecruitMail;
import com.human.jobs.service.JobsService;
import com.human.mail.dao.AcceptMailDao;
import com.human.mail.entity.AcceptMail;
import com.human.mail.service.AcceptMailService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.mailUtils.ExchangeMailUtil;
import com.human.utils.mailUtils.MailUtil;
import com.sun.star.uno.RuntimeException;


@Service
public class AcceptMailServiceImpl implements AcceptMailService {
    
    private final Logger logger = LogManager.getLogger(AcceptMailServiceImpl.class);
    
    @Resource
    private AcceptMailDao acceptMailDao;
    
    @Resource
    private JobsService jobsService;
    
    @Resource
    private RecruitMailDao recruitMailDao;
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.attachmentPath}")
    private  String attachmentPath;
    
    @Value("${oss.emailPath}")
    private  String emailPath;
    
    @Value("${oss.htmlEmailPath}")
    private  String htmlEmailPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void downLoadAcceptMail(String path){
        /*
         * 第一步：对各个学校的接收邮箱进行连接检查并对异常邮箱作标记并保存
         */  
       List<RecruitMail> list= recruitMailDao.queryAllMail();
       List<RecruitMail> normalList=new ArrayList<RecruitMail>();//标记正常邮箱
       List<RecruitMail> exceptionList=new ArrayList<RecruitMail>();//标记异常邮箱
       if(list!=null && list.size()>0){
           for(RecruitMail recruitMail:list){
               //检查Exchange协议连接是否通顺
               Map<String, Object>checkExchangeMap=checkRecruitMail(recruitMail,2);
               if((boolean)checkExchangeMap.get("flag")){
                   normalList.add(recruitMail);
               }else{
                   exceptionList.add(recruitMail);
               }              
           }  
       }
       /*
        * 第二步：对各个学校的连接正常的招聘接收邮箱进行分组
        */      
       Map<String,List<RecruitMail>>map=new HashMap<String,List<RecruitMail>>();//用来存放不同学校接收邮箱的MAP  
       if(normalList!=null && normalList.size()>0){
           //对各个招聘接收邮箱按学校分组（因为一个学校可能配置多个招聘接收邮箱）                 
           for(RecruitMail recruitMail:normalList){
                String hrCompanyId = recruitMail.getHrCompanyId();
                if (map.containsKey(hrCompanyId)) {
                    List<RecruitMail> lists = (List<RecruitMail>)map.get(hrCompanyId);
                    lists.add(recruitMail);
                 }else {
                    List<RecruitMail> lists =new ArrayList<RecruitMail>();
                    lists.add(recruitMail);
                    map.put(hrCompanyId, lists);
                 }        
            }
       }
       /*
        * 第三步：读取各个学校的招聘接收邮箱信息
        */ 
       for(Map.Entry entry : map.entrySet()){
           List<RecruitMail> list2=(List<RecruitMail>)entry.getValue();
           jobsService.readMailBySchoolId(list2,path);
       }              
   }
        
    /**
     * 检查邮箱通信协议正常与否
     * @param id
     * @param type
     * @return
     */
    @SuppressWarnings("finally")
    @Async
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> checkRecruitMail(RecruitMail recruitMail,int type) {
        Map<String,Object> map=new HashMap<String,Object>();
        String pop3MailServerHost=recruitMail.getPop3MailServerHost();
        String exchangeMailServerHost=recruitMail.getExchangeMailServerHost();
        String userName=recruitMail.getMailUser();
        String password=recruitMail.getMailPassword();
        String domain=recruitMail.getDomain();
        Integer popIsValid=recruitMail.getPopIsValid();
        Integer exchangeIsValid=recruitMail.getExchangeIsValid();
        if(type==1){//pop3协议
            map=MailUtil.checkRecruitMail(pop3MailServerHost, userName, password);
            if((boolean) map.get("flag")){
                recruitMail.setPopIsValid(0);
            }else{
                recruitMail.setPopIsValid(1);
            }            
        }else{//Exchange协议
            map=ExchangeMailUtil.checkRecruitMail(exchangeMailServerHost, userName, password,domain);
            if((boolean) map.get("flag")){
                recruitMail.setExchangeIsValid(0);
            }else{
                recruitMail.setExchangeIsValid(1);
            }           
        }
        //判断是否发生变化，如果发生变化更新邮箱协议连接状态
        try{
            if(popIsValid!=recruitMail.getPopIsValid()||exchangeIsValid!=recruitMail.getExchangeIsValid()){
                recruitMailDao.updateByPrimaryKeySelective(recruitMail);
            }  
        }catch(Exception e){
            logger.error("更新邮箱协议连接状态发生错误", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }finally{
            return map;
        }        
    }
    

    @Override
    public int queryByMessageId(String messageId) {       
        return acceptMailDao.queryByMessageId(messageId);
    }

    @Override
    public List<AcceptMail> queryFailAnalysis() {
        return acceptMailDao.queryFailAnalysis();
    }

    
    @Override
    public PageView query(PageView pageView, AcceptMail acceptMail) {       
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", acceptMail);
        map.put("userId",Common.getMyUser().getUserid());
        List<AcceptMail> list = acceptMailDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
