package com.human.jzbTest.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.jzbTest.dao.JzbDeptDao;
import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbDeptManager;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.service.JzbDeptService;
import com.human.utils.PageView;

@Service
public class JzbDeptServiceImpl implements JzbDeptService{
    
    @Autowired
    private JzbDeptDao dao;
    
    @Override
    public List<JzbDept> getDeptsByCompany(String company) {
        return dao.getDeptsByCompany(company);
    }
    
    @Override
    public PageView getDeptPage(PageView pageView, JzbDept dept) {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", dept);
        List<JzbDept> list = dao.getAllDeptsByCompany(dept.getCompany());
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public int addDept(JzbDept dept) {
        return dao.insert(dept);
    }
    
    
    @Override
    public int editDept(JzbDept dept) {
        return dao.updateByPrimaryKeySelective(dept);
    }
    
    @Override
    public JzbDept getDeptById(Integer id) {
        return dao.getDeptById(id);
    }
    
    @Override
    public List<JzbDeptManager> getDeptManagers(Integer deptId) {
        return dao.getDeptManagers(deptId);
    }
    
    @Override
    public boolean isManagerExist(JzbDeptManager manager) {
        List<JzbDeptManager> managers = dao.getDeptByCondition(manager);
        if(managers!=null && managers.size()>0){
            return true;
        }
        return false;
    }
    
    @Override
    public int addDeptManager(JzbDeptManager manager) {
        return dao.addDeptManager(manager);
    }
    
    @Override
    public int delDeptManager(JzbDeptManager manager) {
        return dao.delDeptManager(manager);
    }
    
    @Override
    public JzbDept getManageDeptByEmail(String email) {
        return dao.getManageDeptByEmail(email);
    }
}
