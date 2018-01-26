package com.ls.spt.question.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.question.entity.LstQuestionType;

@Repository
public interface LstQuestionTypeDao {
    
    List<LstQuestionType> query(Map<Object, Object> map);
    List<LstQuestionType> getAll();
    
    int deleteByPrimaryKey(Integer id);

    int insert(LstQuestionType record);

    int insertSelective(LstQuestionType record);

    LstQuestionType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstQuestionType record);

    int updateByPrimaryKey(LstQuestionType record);
}