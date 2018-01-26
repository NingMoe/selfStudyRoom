package com.human.examineelist.dao;

import java.util.Map;

import com.human.examineelist.entity.ClassComments;

public interface ClassCommentsDao {
//    int deleteByPrimaryKey(Long id);

    int insert(ClassComments record);

    int insertSelective(ClassComments record);

    ClassComments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClassComments record);

    int updateByPrimaryKey(ClassComments record);

    ClassComments queryforcode(String code);
    Map<String, Object> queryforcode2(String code);
}