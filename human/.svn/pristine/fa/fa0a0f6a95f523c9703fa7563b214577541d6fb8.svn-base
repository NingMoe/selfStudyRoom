package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.SchoolArea;

@Repository
public interface SchoolAreaDao {
    int deleteByPrimaryKey(Long id);
    
    int insertSelective(SchoolArea record);

    SchoolArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SchoolArea record);

    List<SchoolArea> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);
    
    List<SchoolArea> getSchoolArea(Map<Object,Object> map);
    
    SchoolArea selectByName(String name);

}