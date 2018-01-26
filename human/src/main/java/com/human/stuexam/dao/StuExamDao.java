package com.human.stuexam.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.human.stuexam.entity.StuExam;

@Repository
public interface StuExamDao {
    List<StuExam> query(Map<Object, Object> map);

    int deleteByIds(Map<String, Object> paraMap);

    int insert(StuExam se);

    int updateByPrimaryKeySelective(StuExam se);

    StuExam selectByPrimaryKey(long id);

    List<StuExam> queryInfoByCode(String code);

    List<StuExam> lookforTearcher(StuExam se);

    Map<String, Object> getBeginAndEndTime(String classCode);



}