package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbPaperMonthConfig;
import com.human.jzbTest.entity.JzbPaperMonthLevel;

public interface JzbPaperMonthConfigService {
    
    JzbPaperMonthConfig selectByPrimaryKey(Integer id);

    boolean insert(JzbPaperMonthConfig jzbpapermonthconfig,boolean flag);

    List<JzbPaperMonthConfig> selectByMainId(String id);

    JzbPaperMonthConfig selectByMonthAndId(String month, int mainConfigId);

    int delete(JzbPaperMonthConfig jzbpapermonthconfig);
    
    JzbPaperMonthConfig getPaperMonthConfig(JzbPaperMonthConfig jpmcf);

    Map<String, Object> selectExamName(JzbPaperMonthConfig jzbpapermonthconfig);
    
    Map<String, Object> checkMonthConfigStatus(Integer mainConfigId,Integer monthConfigId);

    int updateStatus(int monthConfigId);

    int insertMonthLevel(JzbPaperMonthLevel le);

    List<JzbPaperMonthLevel> getMonthLevel( int month);
    
    List<JzbPaperMonthLevel> getMonthUseLevel( int month);

    int deleteMonthLevel(Integer id);

    boolean updateByprimary(JzbPaperMonthConfig jzbpapermonthconfig, boolean flag);

    int deleteMonthLevelByMonthId(JzbPaperMonthConfig jzbpapermonthconfig);
    

}
