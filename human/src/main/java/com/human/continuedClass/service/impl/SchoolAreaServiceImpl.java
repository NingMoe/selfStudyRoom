package com.human.continuedClass.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.continuedClass.dao.SchoolAreaDao;
import com.human.continuedClass.entity.SchoolArea;
import com.human.continuedClass.service.SchoolAreaService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class SchoolAreaServiceImpl implements SchoolAreaService {
    
    @Resource
    private SchoolAreaDao saDao;

    @Override
    public PageView query(PageView pageView, SchoolArea ccr) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ccr);
        List<SchoolArea> list = saDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(SchoolArea ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ccr.setCreateUser(Common.getMyUser().getUsername());
            ccr.setCreateTime(new Date());
            saDao.insertSelective(ccr); 
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
    public SchoolArea selectByPrimaryKey(Long id) {
        
        return saDao.selectByPrimaryKey(id);
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(SchoolArea ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ccr.setUpdateUser(Common.getMyUser().getUsername());
            ccr.setUpdateTime(new Date());
            saDao.updateByPrimaryKeySelective(ccr); 
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
    public List<SchoolArea> getSchoolArea(SchoolArea sa){ 
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("t", sa);
        List<SchoolArea> list = saDao.getSchoolArea(map);
        return list;
    }

    @Override
    public SchoolArea selectByName(String name) {        
        return saDao.selectByName(name);
    }
}
