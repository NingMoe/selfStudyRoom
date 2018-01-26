package com.human.continuedClass.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.human.continuedClass.entity.SchoolAreaMatch;

@Repository
public interface SchoolAreaMatchDao {
    int deleteByPrimaryKey(Long id);
    
    int insertSelective(SchoolAreaMatch record);

    SchoolAreaMatch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SchoolAreaMatch record);

    List<SchoolAreaMatch> query(Map<Object, Object> map);
    
    int deleteByIds(Map<String, Object> map);

    List<SchoolAreaMatch> selectBySmId(SchoolAreaMatch ccr);
    
    int deleteBySmId(SchoolAreaMatch ccr);
    
    int deleteAll(Long ruleId);
}