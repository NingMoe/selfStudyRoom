package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.basic.dao.RecruitMailDao;
import com.human.basic.entity.RecruitMail;
import com.human.basic.service.RecruitMailService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;
import com.human.utils.mailUtils.ExchangeMailUtil;
import com.human.utils.mailUtils.MailUtil;

@Service
public class RecruitMailServiceImpl implements RecruitMailService{
     
    @Resource
    private RecruitMailDao recruitMailDao;
    

    @Override
    public PageView query(PageView pageView, RecruitMail recruitMail) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", recruitMail);
        List<RecruitMail> list = recruitMailDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public int insertSelective(RecruitMail recruitMail) {
        
        return recruitMailDao.insertSelective(recruitMail);
    }


    @Override
    public RecruitMail selectByPrimaryKey(Long id) {
        return recruitMailDao.selectByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKeySelective(RecruitMail record) {
        return recruitMailDao.updateByPrimaryKeySelective(record);
    }


    @Override
    public Map<String, Object> updateStatus(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] userIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", userIds);
            paraMap.put("updateUser", Common.getAuthenticatedUsername());
            paraMap.put("updateTime", TimeUtil.getCurrentTimestamp());
            recruitMailDao.updateByIds(paraMap);
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }


    @Override
    public Map<String, Object> checkRecruitMail(Long id, int type) {
        Map<String,Object> map=new HashMap<String,Object>();
        RecruitMail recruitMail= recruitMailDao.selectByPrimaryKey(id);
        String pop3MailServerHost=recruitMail.getPop3MailServerHost();
        String exchangeMailServerHost=recruitMail.getExchangeMailServerHost();
        String sendMailServerHost=recruitMail.getSendMailServerHost();
        String userName=recruitMail.getMailUser();
        String password=recruitMail.getMailPassword();
        String domain=recruitMail.getDomain();
        if(type==1){//pop3协议
            map=MailUtil.checkRecruitMail(pop3MailServerHost, userName, password);
            if((boolean) map.get("flag")){
                recruitMail.setPopIsValid(0);
            }else{
                recruitMail.setPopIsValid(1);
            }            
        }else if(type==2){//Exchange协议
            map=ExchangeMailUtil.checkRecruitMail(exchangeMailServerHost, userName, password,domain);
            if((boolean) map.get("flag")){
                recruitMail.setExchangeIsValid(0);
            }else{
                recruitMail.setExchangeIsValid(1);
            }           
        }else if(type==3){//检测发件服务器连接
            map=ExchangeMailUtil.checkRecruitMail(sendMailServerHost, userName, password,domain);
            if((boolean) map.get("flag")){
                recruitMail.setSendIsValid(0);
            }else{
                recruitMail.setSendIsValid(1);
            }           
        }
        recruitMailDao.updateByPrimaryKeySelective(recruitMail);
        return map;
    }


    @Override
    public List<RecruitMail> queryAllMail() {
        return recruitMailDao.queryAllMail();
    }

}
