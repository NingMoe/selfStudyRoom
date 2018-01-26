package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.basic.dao.XdfClassInfoDao;
import com.human.basic.entity.XdfClassInfo;
import com.human.jzbTest.dao.JzbGradeSubjectClassDao;
import com.human.jzbTest.dao.JzbGradeSubjectDao;
import com.human.jzbTest.entity.JzbGradeSubjectClass;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.service.JzbGradeSubjectClassService;
import com.human.manager.dao.UserDeptDao;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class JzbGradeSubjectClassServiceImpl implements JzbGradeSubjectClassService{
    
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private JzbGradeSubjectDao  jGradeSubjectDao;
    
    @Resource
    private JzbGradeSubjectClassDao  jGradeSubjectClassDao;
    
    @Autowired
    private XdfClassInfoDao xdfClassInfoDao;
    

    @Override
    public PageView query(PageView pageView, JzbGradeSubjectClass cf) {
        Map<Object, Object> map = new HashMap<Object, Object>(); 
        cf.setCompanyId(Common.getMyUser().getCompanyId());
        map.put("paging", pageView);
        map.put("t", cf);
        List<JzbGradeSubjectClass> list = jGradeSubjectClassDao.query(map);
        for(JzbGradeSubjectClass c:list){
            String containClassName = c.getContainClassName();
            System.out.println("containClassName:"+containClassName);
            c.setContainClassNameList(getListFromString(containClassName));
            String noContainClassName = c.getNoContainClassName();
            System.out.println("noContainClassName:"+noContainClassName);
            c.setNoContainClassNameList(getListFromString(noContainClassName));
            String containClassNumber = c.getContainClassNumber();
            System.out.println("containClassNumber:"+containClassNumber);
            c.setContainClassNumberList(getListFromString(containClassNumber));
            String noContainClassNumber = c.getNoContainClassNumber();
            System.out.println("noContainClassNumber:"+noContainClassNumber);
            c.setNoContainClassNumberList(getListFromString(noContainClassNumber));
            List<XdfClassInfo> classList = xdfClassInfoDao.queryClassByRule(c);
            if(classList!=null && classList.size()>0){
                c.setClassNum(classList.size());
                c.setClassList(classList);
            }else{
                c.setClassNum(0);
            }
        }
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(JzbGradeSubjectClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            jGradeSubjectClassDao.insertSelective(cf);            
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
    @Transactional(rollbackFor=Exception.class)
    public JzbGradeSubjectClass selectById(long id) {        
        return jGradeSubjectClassDao.selectByPrimaryKey(id);
    }
    
    @Override
    public Map<String, Object> edit(JzbGradeSubjectClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(Common.getMyUser().getUsername());
            cf.setUpdateTime(new Date());
            jGradeSubjectClassDao.updateByPrimaryKeySelective(cf); 
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
            jGradeSubjectClassDao.deleteByIds(paraMap);
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
    public Map<String, Object> deleteAll(Long gradeSubjectId) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            jGradeSubjectClassDao.deleteAll(gradeSubjectId);
            map.put("flag", true);
            map.put("message", "全部删除成功");
        }catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "全部删除失败");
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return map;
    }

    @Override
    public List<JzbGradeSubjectClass> selectClassRuleByParams(JzbGradeSubjectDto record) {        
        return jGradeSubjectClassDao.selectClassRuleByParams(record);
    }
    
    private List<String> getListFromString(String s){
        if(StringUtils.isEmpty(s)){
            return null;
        }
        List<String> result = new ArrayList<String>();
        if(s.indexOf(",")>0||s.indexOf("，")>0){
            String tmp = s.replaceAll("，", ",");
            String[] tmpArr = tmp.split(",");
            for(String tmpStr:tmpArr){
                if(StringUtils.isNotEmpty(tmpStr)){
                    result.add(tmpStr);
                }
            }
        }else{
            result.add(s);
        }
        return result;
    }
}
