package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import com.human.jzbTest.dao.JzbDeptDao;
import com.human.jzbTest.dao.JzbGradeDao;
import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.service.JzbGradeService;
import com.human.manager.dao.UserDeptDao;
import com.human.utils.Common;
import com.human.utils.PageView;
import com.human.utils.TimeUtil;


@Service
public class JzbGradeServiceImpl implements JzbGradeService {
                  
    @Resource
    private UserDeptDao userDeptDao;
    
    @Resource
    private JzbGradeDao  jGradeDao;
    
    @Resource
    private JzbDeptDao  jzbDeptdao;

    @Override
    public PageView query(PageView pageView, JzbGrade cf) {
        String email=Common.getMyUser().getEmailAddr();
        JzbDept jzbDept = jzbDeptdao.getManageDeptByEmail(email);
        List<Integer>daptIds=new ArrayList<Integer>();
        if(jzbDept!=null){
            daptIds.add(jzbDept.getId());
        }  
        Map<Object, Object> map = new HashMap<Object, Object>();        
        map.put("paging", pageView);
        map.put("t", cf);
        if(CollectionUtils.isNotEmpty(daptIds)){
            map.put("s", daptIds);
        }
        List<JzbGrade> list = jGradeDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(JzbGrade cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            jGradeDao.insertSelective(cf); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该部门下已经存在该年级，请输入其它年级名称!");
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
    public JzbGrade selectById(long id) {
        return jGradeDao.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(JzbGrade cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(Common.getMyUser().getUsername());
            cf.setUpdateTime(new Date());
            jGradeDao.updateByPrimaryKeySelective(cf); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "该部门下已经存在该年级，请输入其它年级名称!");
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
            jGradeDao.deleteByIds(paraMap);
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
    public List<JzbGrade> selectByDeptId(Long deptId) {
        return jGradeDao.selectByDeptId(deptId);
    }
    
    @Override
    public List<JzbGrade> selectByCompanyId(String companyId) {
        return jGradeDao.selectByCompanyId(companyId);
    }
    
    @Override
    public List<JzbGrade> selectKyByCompanyId(String companyId) {
        String month = TimeUtil.getCurrentMonth();
        if("10".equals(month)){
            month = "A";
        }
        if("11".equals(month)){
            month = "B";
        }
        if("12".equals(month)){
            month = "C";
        }
        return jGradeDao.selectValidGrade(companyId, month);
    }

}
