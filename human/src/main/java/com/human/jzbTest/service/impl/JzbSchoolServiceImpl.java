package com.human.jzbTest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.human.jzbTest.dao.JzbSchoolDao;
import com.human.jzbTest.entity.JzbSchool;
import com.human.jzbTest.service.JzbSchoolService;
import com.human.manager.dao.UserDeptDao;
import com.human.manager.entity.HrCompany;
import com.human.utils.Common;
import com.human.utils.PageView;


@Service
public class JzbSchoolServiceImpl implements JzbSchoolService {
       
    @Resource
    private JzbSchoolDao  jzbDao;
            
    @Resource
    private UserDeptDao userDeptDao;

    
    @Override
    public PageView query(PageView pageView, JzbSchool cf) {
        List<HrCompany> listCompany=userDeptDao.getUserCompany(Common.getMyUser().getUserid());
        List<String>hrCompanyIds=new ArrayList<String>();
        if(CollectionUtils.isNotEmpty(listCompany)){
            for(HrCompany hc:listCompany){
                hrCompanyIds.add(hc.getCompanyId());
            }
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", cf);
        if(CollectionUtils.isNotEmpty(hrCompanyIds)){
            map.put("s", hrCompanyIds);
        }
        List<JzbSchool> list = jzbDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> add(JzbSchool cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            List<HrCompany> listCompany=userDeptDao.getUserCompany(Common.getMyUser().getUserid());
            if(CollectionUtils.isNotEmpty(listCompany)){
                cf.setSchoolCompanyId(listCompany.get(0).getCompanyId());
            }
            cf.setCreateUser(Common.getMyUser().getUsername());
            cf.setCreateTime(new Date());
            jzbDao.insertSelective(cf); 
            map.put("flag", true);
            map.put("message", "添加成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "已经存在该公立学校，请输入其它公立学校名称!");
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
    public JzbSchool selectById(long id) {
        return jzbDao.selectByPrimaryKey(id);
    }
    
    @Override
    public List<JzbSchool> selectByAreaAndGrade(Integer areaId, Integer gradeId) {
        // TODO Auto-generated method stub
        return jzbDao.selectByAreaAndGrade(areaId, gradeId);
    }
    
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> edit(JzbSchool cf) {
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            cf.setUpdateUser(Common.getMyUser().getUsername());
            cf.setUpdateTime(new Date());
            jzbDao.updateByPrimaryKeySelective(cf); 
            map.put("flag", true);
            map.put("message", "编辑成功!");
        }catch(DuplicateKeyException e){
            map.put("flag", false);
            map.put("message", "已经存在该公立学校，请输入其它公立学校名称!");
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
            jzbDao.deleteByIds(paraMap);
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
    public void downLoadJzbSchoolExcel(HttpServletRequest request, HttpServletResponse response) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Map<String, Object> uploadLoadJzbSchoolExcel(MultipartFile multipartFile) {
        // TODO Auto-generated method stub
        return null;
    }


}
