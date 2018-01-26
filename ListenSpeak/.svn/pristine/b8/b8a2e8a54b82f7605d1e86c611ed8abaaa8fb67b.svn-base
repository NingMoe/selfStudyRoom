package com.ls.spt.lstClassTest.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstClassTest.entity.LstClassTest;

public interface LstClassTestDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstClassTest record);

    int insertSelective(LstClassTest record);

    LstClassTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstClassTest record);

    int updateByPrimaryKey(LstClassTest record);

    List<Map<String, Object>> query(Map<String, Object> map);

    int bathDelete(Map<String, Object> deleteIds);

    int insertToTestCl(Map<String, Object> map);

    List<Map<String, Object>> getTestInfo(Integer id);

    int deleteToTestCl(LstClassTest lct);
}