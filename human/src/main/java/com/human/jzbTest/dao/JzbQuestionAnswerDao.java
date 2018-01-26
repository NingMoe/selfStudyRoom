package com.human.jzbTest.dao;

import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbQuestionAnswer;

@Repository
public interface JzbQuestionAnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(JzbQuestionAnswer record);

    JzbQuestionAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbQuestionAnswer record);
    
    int delImg(Integer id);
}