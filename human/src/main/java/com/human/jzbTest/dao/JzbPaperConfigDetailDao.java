package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbPaperConfigDetail;

public interface JzbPaperConfigDetailDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JzbPaperConfigDetail record);

    int insertSelective(JzbPaperConfigDetail record);

    JzbPaperConfigDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbPaperConfigDetail record);

    int updateByPrimaryKey(JzbPaperConfigDetail record);

    List<JzbPaperConfigDetail> selectByMonth(Integer id);

    List<Map<String, Object>> searchZsdDetail(Map<String, Object> map);

    List<Map<String, Object>> selectKnowledge(Map<String, Object> map);

    List<Map<String, Object>> queryByCondition(Map<String, Object> map);

    void delete(Map<String, Object> map);

    List<Map<String, Object>> selectDictKnowledge(Map<String, Object> map);

    List<Map<String, Object>> searchZsd(Map<String, Object> map);

    JzbPaperConfigDetail selectforkey(Map<String, Object> map);

    Map<String, Object> getMonthId(Map<String, Object> map);

    int deleteByMonthId(Map<String, Object> map);

    Map<String, Object> queryForTkMonth(Map<String, Object> map);

}