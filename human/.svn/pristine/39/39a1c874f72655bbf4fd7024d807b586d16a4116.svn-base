package com.human.stuadmin.dao;

import java.util.List;
import java.util.Map;

import com.human.stuadmin.entity.StuAdmin;

public interface StuAdminDao {
    int deleteByPrimaryKey(String code);

    int insert(StuAdmin record);

    int insertSelective(StuAdmin record);

    StuAdmin selectByPrimaryKey(String code);

    int updateByPrimaryKeySelective(StuAdmin record);

    int updateByPrimaryKey(StuAdmin record);
    
    List<StuAdmin> query(Map<Object, Object> map);

    List<StuAdmin> queryClassCode(String sClassCode);

    int updateAcce(StuAdmin sa);

    int updatePhone(Map<String, Object> map);

    int updateStatusBySclassCode(Map<String, Object> map);

    List<StuAdmin> selectByCodeAndSclassCode(Map<String, Object> map);
}