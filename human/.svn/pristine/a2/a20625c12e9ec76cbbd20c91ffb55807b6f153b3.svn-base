package com.human.jzbTest.service;

import java.util.List;

import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbDeptManager;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.utils.PageView;

public interface JzbDeptService {
    List<JzbDept> getDeptsByCompany(String company);
    
    JzbDept getDeptById(Integer id);
    
    int addDept(JzbDept dept);
    
    int editDept(JzbDept dept);
    
    PageView getDeptPage(PageView pageView,JzbDept dept);
    
    List<JzbDeptManager> getDeptManagers(Integer deptId);
    
    boolean isManagerExist(JzbDeptManager manager);
    
    int addDeptManager(JzbDeptManager manager);
    
    int delDeptManager(JzbDeptManager manager);
    
    JzbDept getManageDeptByEmail(String email);
    
}
