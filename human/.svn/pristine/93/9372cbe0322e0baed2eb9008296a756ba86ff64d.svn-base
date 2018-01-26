package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;

import com.human.teacherservice.entity.TeacherActManager;

public interface TeacherActManagerDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TeacherActManager record);

    TeacherActManager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TeacherActManager record);
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    List<TeacherActManager> query(Map<Object, Object> map);

}