package com.human.continuation.dao;

import java.util.List;
import java.util.Map;

import com.human.continuation.entity.TeacherContinuationConfig;

public interface TeacherContinuationConfigDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TeacherContinuationConfig record);

    int insertSelective(TeacherContinuationConfig record);

    TeacherContinuationConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherContinuationConfig record);

    int updateByPrimaryKey(TeacherContinuationConfig record);

    /**
     * 分页查询配置信息
     * @param map
     * @return
     */
    public List<TeacherContinuationConfig> query(Map<String, Object> map);

    /**
     * 获取升班期配置
     * @param mappara
     * @return
     */
    public List<TeacherContinuationConfig> getWindowConfig(Map<String, Object> mappara);
}