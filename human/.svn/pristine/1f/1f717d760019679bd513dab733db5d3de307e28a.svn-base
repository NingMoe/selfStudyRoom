package com.human.basic.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.basic.dao.MailNoticeTypeDao;
import com.human.basic.dao.MailTempDao;
import com.human.basic.entity.MailNoticeType;
import com.human.basic.entity.MailTem;
import com.human.basic.service.MailNoticeTypeService;
import com.human.utils.Common;
import com.human.utils.PageView;
@Service
public class MailNoticeTypeServiceImpl implements MailNoticeTypeService {
    
    private final Logger logger = LogManager.getLogger( MailNoticeTypeServiceImpl.class);
    
    @Resource
    private  MailNoticeTypeDao  mailNoticeTypeDao;
    
    
    @Override
    public PageView queryTem(PageView pageView, MailNoticeType mnt, String deptIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(null!=deptIds&&deptIds.length()>0){
            mnt.setDeptIds(deptIds.split(","));
        }
        map.put("paging", pageView);
        map.put("t", mnt);
        List<MailNoticeType> list = mailNoticeTypeDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }


    @Override
    public int add(MailNoticeType mnt) {        
        return mailNoticeTypeDao.insertSelective(mnt);
    }


    @Override
    public MailNoticeType queryById(Long id) {
        return mailNoticeTypeDao.selectByPrimaryKey(id);
    }


    @Override
    public int edit(MailNoticeType mnt) {
        return mailNoticeTypeDao.updateByPrimaryKeySelective(mnt);
    }


    @Override
    public Map<String, Object> delTemp(String deleteIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            paraMap.put("updateUser",Common.getAuthenticatedUsername());
            mailNoticeTypeDao.delTemp(paraMap);
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
    public List<MailNoticeType> queryByParams(MailNoticeType mnt) {
     
        return mailNoticeTypeDao.queryByParams(mnt);
        
    }

}
