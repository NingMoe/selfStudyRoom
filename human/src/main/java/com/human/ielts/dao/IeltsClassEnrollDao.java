package com.human.ielts.dao;

import java.util.List;
import java.util.Map;

import com.human.ielts.entity.IeltsClassEnroll;

public interface IeltsClassEnrollDao {
    int deleteByPrimaryKey(Integer id);

    int insert(IeltsClassEnroll record);

    int insertSelective(IeltsClassEnroll record);

    IeltsClassEnroll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IeltsClassEnroll record);

    int updateByPrimaryKey(IeltsClassEnroll record);

    /**
     * 删除id关联的班级
     * @param id
     */
    public int deleteByStudentId(Integer book_id);

    /**
     * 通过班级类型id删除
     * @param id
     * @return
     */
    public int deleteByClasstypeId(Integer class_type_id);

    /**
     * 获取各个班级学员分布
     * @param mapparam
     * @return
     */
    public List<IeltsClassEnroll> selectclasstypecount(Map<String, Object> mapparam);

    List<IeltsClassEnroll> selectTeacherclasstypecount(Map<String, Object> mapparam);
}