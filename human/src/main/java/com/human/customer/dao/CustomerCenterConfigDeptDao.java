package com.human.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.customer.entity.CenterDept;

@Repository
public interface CustomerCenterConfigDeptDao {

    int add(CenterDept cmc);

    int delByIds(String[] ids);
    
    int edit(CenterDept cmc);

    List<CenterDept> queryDeptByName(String deptName);

    List<CenterDept> queryDept(Map<String, Object> map);

    CenterDept queryById(Long id);
    
    List<CenterDept> getDepts();
    
    List<CenterDept> getAllDeptMenu();

}
