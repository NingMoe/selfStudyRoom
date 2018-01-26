package com.human.jzbTest.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.jzbTest.dao.JzbGradeSubjectDao;
import com.human.jzbTest.entity.JzbGradeSubject;
import com.human.jzbTest.service.JzbGradeSubjectService;
import com.human.manager.dao.UserDeptDao;
import com.human.utils.Common;
import com.human.utils.PageView;


@Service
public class JzbGradeSubjectServiceImpl implements JzbGradeSubjectService {
                  
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private JzbGradeSubjectDao  jGradeSubjectDao;

    @Override
    public PageView query(PageView pageView, JzbGradeSubject cf) {
        Map<Object, Object> map = new HashMap<Object, Object>();        
        map.put("paging", pageView);
        map.put("t", cf);
        List<JzbGradeSubject> list = jGradeSubjectDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(JzbGradeSubject cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            jGradeSubjectDao.insertSelective(cf); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该年级下已经存在相同班级类型的相同该科目，请选择其它科目!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "添加失败，请稍后重试!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public JzbGradeSubject selectById(long id) {        
        return  jGradeSubjectDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(JzbGradeSubject cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(Common.getMyUser().getUsername());
            cf.setUpdateTime(new Date());
            jGradeSubjectDao.updateByPrimaryKeySelective(cf); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该年级下已经存在相同班级类型的相同该科目，请选择其它科目!");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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
            jGradeSubjectDao.deleteByIds(paraMap);
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
    public List<JzbGradeSubject> selectByGradeId(Long gradeId) {       
        return jGradeSubjectDao.selectByGradeId(gradeId);
    }
    

    



}
