package com.human.continuedClass.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.continuedClass.dao.SchoolAreaMatchDao;
import com.human.continuedClass.entity.SchoolAreaMatch;
import com.human.continuedClass.service.SchoolAreaMatchService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class SchoolAreaMatchServiceImpl implements SchoolAreaMatchService {
    
    @Resource
    private SchoolAreaMatchDao saDao;

    @Override
    public PageView query(PageView pageView, SchoolAreaMatch ccr) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ccr);
        List<SchoolAreaMatch> list = saDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(SchoolAreaMatch ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            String createUser=Common.getMyUser().getUsername();
            Date  createTime=new Date();
            long schoolAreaId=ccr.getSchoolAreaId();
            long ruleId=ccr.getRuleId();
            for(long matchId:ccr.getMatchIds()){
                SchoolAreaMatch sam=new SchoolAreaMatch();
                sam.setCreateUser(createUser);
                sam.setCreateTime(createTime);
                sam.setSchoolAreaId(schoolAreaId);
                sam.setMatchId(matchId);
                sam.setRuleId(ruleId);
                saDao.insertSelective(sam); 
            }
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
    public SchoolAreaMatch selectByPrimaryKey(Long id) {
        
        return saDao.selectByPrimaryKey(id);
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(SchoolAreaMatch ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            //第一步：删除之前的临近校区
            long schoolAreaId=ccr.getSchoolAreaId();
            saDao.deleteBySmId(ccr);
            //第二步：保存临近校区
            String createUser=Common.getMyUser().getUsername();
            Date  createTime=new Date();
            long ruleId=ccr.getRuleId();
            for(long matchId:ccr.getMatchIds()){
                SchoolAreaMatch sam=new SchoolAreaMatch();
                sam.setCreateUser(createUser);
                sam.setCreateTime(createTime);
                sam.setSchoolAreaId(schoolAreaId);
                sam.setMatchId(matchId);
                sam.setRuleId(ruleId);
                saDao.insertSelective(sam); 
            } 
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
    public Map<String, Object> delete(String deleteIds,long ruleId) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            paraMap.put("ruleId", ruleId);
            saDao.deleteByIds(paraMap);
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
    public List<SchoolAreaMatch> selectBySmId(SchoolAreaMatch ccr) {
        return saDao.selectBySmId(ccr);
    }
}
