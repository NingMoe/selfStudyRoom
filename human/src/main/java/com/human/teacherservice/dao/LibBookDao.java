package com.human.teacherservice.dao;

import java.util.List;
import java.util.Map;

import com.human.teacherservice.entity.LibBook;

public interface LibBookDao {
    int deleteByPrimaryKey(Integer book_id);

    int insert(LibBook record);

    int insertSelective(LibBook record);

    LibBook selectByPrimaryKey(Integer book_id);

    int updateByPrimaryKeySelective(LibBook record);

    int updateByPrimaryKey(LibBook record);

    /**
     * 分页获取图书信息
     * @param map
     * @return
     */
    public List<LibBook> query(Map<Object, Object> map);

    /**
     * 通过id获取集合map
     * @param list
     * @return
     */
    List<Map<String, Object>> selectByIds(List<Integer> list);

    /**
     * 批量禁用
     * @param list
     * @return
     */
    int updateFalseByIds(List<Integer> list);
    
    
    /**
     * 批量启用
     * @param list
     * @return
     */
    int updateTrueByIds(List<Integer> list);

    /**
     * 前端分页获取图书信息
     * @param mapparam
     * @return
     */
    public List<LibBook> selectBookInfo(Map<String, Object> mapparam);
}