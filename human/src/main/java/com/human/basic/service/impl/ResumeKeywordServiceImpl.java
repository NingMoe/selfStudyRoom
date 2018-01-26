package com.human.basic.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.basic.dao.ResumeKeywordDao;
import com.human.basic.entity.ResumeKeyword;
import com.human.basic.service.ResumeKeywordService;
import com.human.utils.Common;
import com.human.utils.PageView;
@Service
public class ResumeKeywordServiceImpl implements ResumeKeywordService {
    
    @Resource
    private  ResumeKeywordDao  rmDao;

    @Override
    public PageView query(PageView pageView, ResumeKeyword rm) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", rm);
        List<ResumeKeyword> list = rmDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public Map<String, Object> add(ResumeKeyword rm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rm.setCreateUser(Common.getMyUser().getUsername());
            rm.setCreateTime(new Date());
            rmDao.insertSelective(rm); 
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
    public ResumeKeyword selectByPrimaryKey(Long id) {
        return rmDao.selectByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> edit(ResumeKeyword rm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            rm.setUpdateUser(Common.getMyUser().getUsername());
            rm.setUpdateTime(new Date());
            rmDao.updateByPrimaryKeySelective(rm);
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
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            rmDao.deleteByIds(paraMap);
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
}
