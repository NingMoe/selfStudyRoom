package com.ls.spt.StudentClass.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ls.spt.StudentClass.dao.StudentClassDao;
import com.ls.spt.StudentClass.entity.StudentClass;
import com.ls.spt.StudentClass.service.StudentClassService;
import com.ls.spt.basic.entity.PageView;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    
    private final  Logger logger = LogManager.getLogger(StudentClassServiceImpl.class);
    @Resource
    StudentClassDao scDao;
    @Override
    public PageView query(PageView pageView, StudentClass sc) {
        logger.info("---------班级管理分页查询开始-------");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("paging", pageView);
        map.put("t", sc);
        List<Map<String, Object>> list = scDao.query(map);
        pageView.setData(list);
        return pageView;
    }
    @Override
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            scDao.deleteByIds(paraMap);
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
    public int update(StudentClass sc) {
        // TODO Auto-generated method stub
        return scDao.updateByPrimaryKeySelective(sc);
    }
    
    

}
