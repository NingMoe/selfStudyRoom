package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.human.jzbTest.entity.JzbPaperMonthConfig;

public interface JzbPaperMonthConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JzbPaperMonthConfig record);

    int insertSelective(JzbPaperMonthConfig record);

    JzbPaperMonthConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbPaperMonthConfig record);

    int updateByPrimaryKey(JzbPaperMonthConfig record);

    List<JzbPaperMonthConfig> selectByMainId(String id);

    JzbPaperMonthConfig selectByMonthAndId(Map<String, Object> map);

    int delete(JzbPaperMonthConfig jzbpapermonthconfig);
    
    JzbPaperMonthConfig getPaperMonthConfig(JzbPaperMonthConfig jpmcf);

    Map<String, Object> selectExamName(JzbPaperMonthConfig jzbpapermonthconfig);
    
    List<JzbPaperMonthConfig> selectByPreCodeAndTkMonth(@Param("preCode")String preCode,@Param("knowledge")String knowledge);

    int updateStatus(Map<String, Object> map);
    
    int updateByPrimaryKeyS(JzbPaperMonthConfig record);
}