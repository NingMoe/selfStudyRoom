package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;

import com.human.teacherservice.entity.LibBookType;

public interface LibBookTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LibBookType record);

    int insertSelective(LibBookType record);

    LibBookType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LibBookType record);

    int updateByPrimaryKey(LibBookType record);

    /**
     * 获取学校所有书籍分类
     * @param school_id
     * @return
     */
    public List<LibBookType> queryByTypeName(String school_id);

    /**
     * 分页获取分类
     * @param map
     * @return
     */
    public List<LibBookType> query(Map<Object, Object> map);

    /**
     * 通过id获取分类下所有书籍信息
     * @param id
     * @return
     */
    public LibBookType selectBookById(Map<String, Object> map);
}