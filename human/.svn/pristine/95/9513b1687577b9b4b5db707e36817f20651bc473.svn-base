package com.human.customer.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.human.customer.dao.CustomerMailFoxDao;
import com.human.customer.entity.CustomerMailFox;
import com.human.customer.entity.CustomerMailfoxDemand;
import com.human.customer.service.CustomerMailFoxService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.execl.StringUtil;

@Service
public class CustomerMailFoxServiceImpl implements CustomerMailFoxService {

    @Resource
    private CustomerMailFoxDao  mailFoxDao;
    
    @Override
    public PageView query(PageView pageView, CustomerMailFox mailFox) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", mailFox);
        List<CustomerMailFox> list = mailFoxDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void save(CustomerMailFox mailFox) {
        mailFoxDao.save(mailFox);
    }

    @Override
    public CustomerMailFox queryById(Integer id) {
        return  mailFoxDao.queryById(id);
    }

    @Override
    public  void updateReadType(Long id,Integer readType,Boolean isRead) {
        CustomerMailFox cmf=new CustomerMailFox();
        cmf.setReadType(readType);
        cmf.setId(id);
        cmf.setIsRead(isRead);
        mailFoxDao.updateReadType(cmf);
    }  
    
    @Override
    @Transactional(rollbackOn=Exception.class)
    public void customerUpdate(CustomerMailFox mailFox) {
        mailFoxDao.delDemand(mailFox.getId());
        String localUser = Common.getAuthenticatedUsername();
        Timestamp localTime = new Timestamp(new Date().getTime());
        Integer state = mailFox.getState();
        if (null!=state&&state >= 2) {
            mailFox.setSolUser(localUser);
            mailFox.setStartTime(localTime);
            if (state >= 3) {
                if (!mailFox.getIsTracet()) {
                    mailFox.setTracer(localUser);
                }
                mailFox.setTracerStartTime(localTime);
                if (state > 4) {
                    mailFox.setTracerEndTime(localTime);
                    mailFox.setSolTime(localTime);
                }
            }
        }
        if(null!=mailFox.getCmd()) {
            for(CustomerMailfoxDemand cmd:mailFox.getCmd()) {
                if(!StringUtil.isEmptyOrBlank(cmd.getDemandDesc())){
                    cmd.setCreateUser(Common.getAuthenticatedUsername());
                    cmd.setBaseId(mailFox.getId());
                     mailFoxDao.insertDemand(cmd);
                }
            }
        }
        mailFoxDao.update(mailFox);
        
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void managerUpdate(CustomerMailFox mailFox) {
        mailFoxDao.delDemand(mailFox.getId());
        String localUser = Common.getAuthenticatedUsername();
        Timestamp localTime = new Timestamp(new Date().getTime());
        mailFox.setCompleteUser(localUser);
        mailFox.setCompleteTime(localTime);
        Integer state = mailFox.getState();
        if (null!=state&&state >= 2) {
            mailFox.setSolUser(localUser);
            mailFox.setStartTime(localTime);
            if (state >= 3) {
                if (!mailFox.getIsTracet()) {
                    mailFox.setTracer(localUser);
                }
                mailFox.setTracerStartTime(localTime);
                if (state > 4) {
                    mailFox.setTracerEndTime(localTime);
                    mailFox.setSolTime(localTime);
                }
            }
        }
        if (null != mailFox.getCmd()) {
            for (CustomerMailfoxDemand cmd : mailFox.getCmd()) {
                if(!StringUtil.isEmptyOrBlank(cmd.getDemandDesc())){
                    cmd.setCreateUser(Common.getAuthenticatedUsername());
                    cmd.setBaseId(mailFox.getId());
                    mailFoxDao.insertDemand(cmd);
                }
            }
        }
        mailFoxDao.managerUpdate(mailFox);
    }

    @Override
    public void updateTracer(CustomerMailFox mailFox) {
        mailFox.setTracer(Common.getAuthenticatedUsername());
        mailFoxDao.updateTracer(mailFox);
        
    }


}
