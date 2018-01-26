package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbPaperMonthLevel;

public interface JzbPaperMonthLevelDao {
    int insert(JzbPaperMonthLevel record);

    int insertSelective(JzbPaperMonthLevel record);

    List<JzbPaperMonthLevel> getMonthLevel(int monthId);

    int deleteMonthLevel(Integer id);

    int deleteMonthLevelByMainId(Map<String, Object> paraMap);

    List<JzbPaperMonthLevel> getMonthUseLevel(int monthId);

    int deleteMonthLevelByMonthId(Integer monthId);
}