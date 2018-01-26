package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;

import com.human.teacherservice.entity.LibBookError;

public interface LibBookErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LibBookError record);

    int insertSelective(LibBookError record);

    LibBookError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LibBookError record);

    int updateByPrimaryKey(LibBookError record);

    /**
     * 分页获取异常书籍
     * @param map
     * @return
     */
    public List<LibBookError> query(Map<Object, Object> map);
}