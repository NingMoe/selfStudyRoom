package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.ContinuedClassRule;

@Repository
public interface ContinuedClassRuleDao {
    int deleteByPrimaryKey(Long id);
    
    int insertSelective(ContinuedClassRule record);

    ContinuedClassRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ContinuedClassRule record);

    List<ContinuedClassRule> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);

}