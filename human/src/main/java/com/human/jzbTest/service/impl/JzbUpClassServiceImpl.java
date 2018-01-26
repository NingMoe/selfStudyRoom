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
import com.human.jzbTest.dao.JzbUpClassDao;
import com.human.jzbTest.entity.JzbGradeSubjectDto;
import com.human.jzbTest.entity.JzbUpClass;
import com.human.jzbTest.service.JzbUpClassService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Service
public class JzbUpClassServiceImpl implements JzbUpClassService {
    
    @Resource
    private JzbUpClassDao  jzbUpClassDao;
    

    @Override
    public PageView query(PageView pageView, JzbUpClass cf) {
        Map<Object, Object> map = new HashMap<Object, Object>(); 
        map.put("paging", pageView);
        map.put("t", cf);
        List<JzbUpClass> list = jzbUpClassDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(JzbUpClass cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            jzbUpClassDao.insertSelective(cf);            
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该科目下已经存在相同的升班期规则,请选择其它规则!");
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
    public JzbUpClass selectById(long id) {        
        return jzbUpClassDao.selectByPrimaryKey(id);
    }



    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> delete(String deleteIds) {
        Map<String,Object> map=new HashMap<String,Object>();
        try{
            String[] ids = deleteIds.split(",");
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("ids", ids);
            jzbUpClassDao.deleteByIds(paraMap);
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
    public List<JzbUpClass> selectUpClassRuleByParams(JzbGradeSubjectDto record) {       
        return jzbUpClassDao.selectUpClassRuleByParams(record);
    }

}
