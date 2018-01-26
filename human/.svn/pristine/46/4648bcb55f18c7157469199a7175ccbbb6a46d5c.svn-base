package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.human.basic.entity.DicData;
import com.human.jzbTest.entity.jzbPaperMainConfig;

public interface jzbPaperMainConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(jzbPaperMainConfig record);

    int insertSelective(jzbPaperMainConfig record);

    jzbPaperMainConfig selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(jzbPaperMainConfig record);

    int updateByPrimaryKey(jzbPaperMainConfig record);

    List<jzbPaperMainConfig> query(Map<String, Object> map);

    void deleteByMap(Map<String, Object> paraMap);

    Map<String, Object> save(String str);
    
    jzbPaperMainConfig getPaperMainConfig(jzbPaperMainConfig jzb);

    Map<String, Object> getDicData(Map<String, Object> map);

    jzbPaperMainConfig selectPaperInfo(jzbPaperMainConfig jzb);

    int deleteMonth( Map<String, Object> map);

    List<Map<String, Object>> getList(Map<String, Object> map);
    
    List<DicData> selectValidClassType(@Param("gradeIds")String gradeIds,@Param("month")String month);
    
    List<DicData> selectValidSubject(@Param("gradeIds")String gradeIds,@Param("classType")String classType,@Param("month")String month);
    
}