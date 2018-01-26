package com.human.jzbTest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.jzbTest.entity.JzbDept;
import com.human.jzbTest.entity.JzbDeptManager;

@Repository
public interface JzbDeptDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JzbDept record);

    int insertSelective(JzbDept record);

    JzbDept selectByPrimaryKey(Integer id);
    
    JzbDept getDeptById(Integer id);

    int updateByPrimaryKeySelective(JzbDept record);

    int updateByPrimaryKey(JzbDept record);
    
    List<JzbDept> getDeptsByCompany(String company);
    
    List<JzbDept> getAllDeptsByCompany(String company);
    
    List<JzbDeptManager> getDeptManagers(Integer deptId);
    
    List<JzbDeptManager> getDeptByCondition(JzbDeptManager manager);
    
    int addDeptManager(JzbDeptManager manager);
    
    int delDeptManager(JzbDeptManager manager);
    
    JzbDept getManageDeptByEmail(String email);
}