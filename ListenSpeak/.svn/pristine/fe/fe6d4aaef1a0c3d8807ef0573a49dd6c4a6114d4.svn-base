package com.ls.spt.studenttest.dao;

import org.springframework.stereotype.Repository;

import com.ls.spt.studentpc.student.entity.KsQuestion;
import com.ls.spt.studenttest.entity.LstTestStudentAnswer;

@Repository(value="lstTestStudentAnswerDao2")
public interface LstTestStudentAnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstTestStudentAnswer record);
    
    int updateByKsQuestion(KsQuestion question);

    int insertSelective(LstTestStudentAnswer record);

    LstTestStudentAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstTestStudentAnswer record);

    int updateByPrimaryKey(LstTestStudentAnswer record);
}