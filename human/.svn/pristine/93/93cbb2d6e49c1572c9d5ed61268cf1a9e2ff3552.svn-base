package com.human.continuedClass.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.continuedClass.dao.SendCardMailDao;
import com.human.continuedClass.entity.SendCardMail;
import com.human.continuedClass.service.SendCardMailService;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.mailUtils.ExchangeMailUtil;

@Service
public class SendCardMailServiceImpl implements SendCardMailService{

    
    @Resource
    private SendCardMailDao scmDao;
       
    
    @Override
    public PageView query(PageView pageView, SendCardMail scm) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", scm);
        List<SendCardMail> list = scmDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(SendCardMail scm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            scm.setCreateUser(Common.getMyUser().getUsername());
            scm.setCreateTime(new Date());
            SendCardMail r=scmDao.selectByRuleId(scm.getRuleId());
            if(r!=null){//如果有设置为无效
               r.setStatus(true); 
               scmDao.updateByPrimaryKeySelective(r);
            }            
            scmDao.insertSelective(scm); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public SendCardMail selectByPrimaryKey(Long id) {
        return scmDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(SendCardMail scm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            scm.setUpdateUser(Common.getMyUser().getUsername());
            scm.setUpdateTime(new Date());
            scmDao.updateByPrimaryKeySelective(scm); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            scmDao.deleteByIds(paraMap);           
            map.put("flag", true);
            map.put("message", "删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> checkMail(Long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        SendCardMail scm = selectByPrimaryKey(id);
        String sendServerHost = scm.getSendServerHost();
        String userName = scm.getMailUser();
        String password = scm.getMailPassword();
        String domain=scm.getMailDomain();
        map = ExchangeMailUtil.checkRecruitMail(sendServerHost, userName, password, domain);
        if ((boolean) map.get("flag")) {
            scm.setSendIsValid(false);
        }else {
            scm.setSendIsValid(true);
        }
        scmDao.updateByPrimaryKeySelective(scm);
        return map;
    }

}
