package com.ls.spt.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.basic.entity.BaseDictionary;

@Repository
public interface BaseDictionaryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDictionary record);

    BaseDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDictionary record);

    int updateByPrimaryKey(BaseDictionary record);
    
    List<BaseDictionary> selectByCondition(Map<Object,Object> map);
}