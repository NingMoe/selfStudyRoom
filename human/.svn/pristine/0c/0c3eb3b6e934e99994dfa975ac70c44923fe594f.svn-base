package com.human.continuedClass.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.continuedClass.dao.ClassDetailsDao;
import com.human.continuedClass.dao.ClassInformationDao;
import com.human.continuedClass.dao.ClassMatchDao;
import com.human.continuedClass.dao.ClassStudentsDao;
import com.human.continuedClass.dao.CombinationClassDao;
import com.human.continuedClass.dao.ContinuedClassRuleDao;
import com.human.continuedClass.dao.RecommendClassDao;
import com.human.continuedClass.dao.RuleBackPhotoDao;
import com.human.continuedClass.dao.SchoolAreaMatchDao;
import com.human.continuedClass.dao.SendCardMailDao;
import com.human.continuedClass.entity.ClassInformation;
import com.human.continuedClass.entity.CombinationClass;
import com.human.continuedClass.entity.ContinuedClassRule;
import com.human.continuedClass.service.ContinuedClassRuleService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class ContinuedClassRuleServiceImpl implements ContinuedClassRuleService {
    
    @Resource
    private ContinuedClassRuleDao ccrDao;
    
    @Resource
    private ClassInformationDao  cDao;
    
    @Resource
    private ClassStudentsDao  cSDao;
    
    @Resource
    private  ClassMatchDao  cMDao;
    
    @Resource
    private ClassDetailsDao cDDao;
    
    @Resource
    private RecommendClassDao  rCDao;
    
    @Resource
    private SchoolAreaMatchDao saDao;
    
    @Resource
    private RuleBackPhotoDao rbpDao;
    
    @Resource
    private SendCardMailDao scmDao;
    
    @Resource
    private CombinationClassDao ccDao;
    
    @Override
    public PageView query(PageView pageView, ContinuedClassRule ccr) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", ccr);
        List<ContinuedClassRule> list = ccrDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(ContinuedClassRule ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ccr.setCreateUser(Common.getMyUser().getUsername());
            ccr.setCreateTime(new Date());
            ccrDao.insertSelective(ccr); 
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
    public ContinuedClassRule selectByPrimaryKey(Long id) {
        
        return ccrDao.selectByPrimaryKey(id);
    }

    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(ContinuedClassRule ccr) {       
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            ccr.setUpdateUser(Common.getMyUser().getUsername());
            ccr.setUpdateTime(new Date());
            ccrDao.updateByPrimaryKeySelective(ccr); 
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
            ccrDao.deleteByIds(paraMap);
            //删除所有规则关联的表数据
            for(String ruleId:ids){
               //删除临近校区配置
                saDao.deleteAll(Long.valueOf(ruleId));
               //删除原班数据
                ClassInformation cf=new ClassInformation();
                cf.setRuleId(Long.valueOf(ruleId));
                cf.setType(1);
                cDao.deleteAll(cf);
              //删除续班数据
                cf.setType(2);
                cDao.deleteAll(cf);
               //删除学员数据
                cSDao.deleteByRuleId(Long.valueOf(ruleId));
               //删除学员-续班班级数据
                cMDao.deleteByRuleId(Long.valueOf(ruleId));
                //删除学员-推荐班级数据
                rCDao.deleteByRuleId(Long.valueOf(ruleId));
                //删除背景图
                rbpDao.deleteByRuleId(Long.valueOf(ruleId));
                //删除邮箱
                scmDao.deleteByRuleId(Long.valueOf(ruleId));
                //删除套餐
                CombinationClass cc=new CombinationClass();
                cc.setRuleId(Long.valueOf(ruleId));
                ccDao.deleteAll(cc);                                
            }            
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
