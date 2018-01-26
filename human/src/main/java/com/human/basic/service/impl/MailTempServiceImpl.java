package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.basic.dao.MailTempDao;
import com.human.basic.entity.MailMessage;
import com.human.basic.entity.MailParam;
import com.human.basic.entity.MailTem;
import com.human.basic.service.MailTempService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class MailTempServiceImpl implements MailTempService {

    private final Logger logger = LogManager.getLogger(MailTempServiceImpl.class);
            
    @Resource
    private  MailTempDao  mailTempDao;
       
    
    @Override
    public PageView queryTem(PageView pageView, MailTem mt,String deptIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(null!=deptIds&&deptIds.length()>0){
            mt.setDeptIds(deptIds.split(","));
        }
        map.put("paging", pageView);
        map.put("t", mt);
        List<MailTem> list = mailTempDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<MailTem> getTemLit(MailTem mt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("t", mt);
        return mailTempDao.query(map);
    }

    @Override
    public List<MailParam> queryParam(MailParam mp) {
        return mailTempDao.queryParam(mp);
    }

    @Override
    public int add(MailTem mt) {
        return mailTempDao.add(mt);
    }

    @Override
    public MailTem queryById(Long id) {
        return mailTempDao.queryById(id);
    }

    @Override
    public int edit(MailTem mt) {
        return mailTempDao.edit(mt);
    }

    @Override
    @Transactional
    public Map<String,Object> delTemp(String deleteIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] userIds = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", userIds);
            paraMap.put("state", 1);
            paraMap.put("updateUser",Common.getAuthenticatedUsername());
            mailTempDao.delTemp(paraMap);
            map.put("flag", true);
            map.put("message", "操作成功");
        }
        catch (Exception e) {
            map.put("flag", false);
            map.put("message", "操作失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }
    
    @Override
    public Integer sendMessage(MailMessage mm) {
        
        return 1;
    }
    
    @Override
    @Async
    public void sendMailMessage(List<MailMessage> mms) {
        if (mms == null || mms.size() == 0) {
            logger.info("待发送邮件为空，取消发送");
            return;
        } 
        for (MailMessage mm : mms) {
             sendMessage(mm);
        }
    }

}
