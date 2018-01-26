package com.ls.spt.lstClassTest.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstClassTest.entity.LstStudentTest;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.question.entity.LstQuestion;

public interface LstTestStudentAnswerDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstTestStudentAnswer record);

    int insertSelective(LstTestStudentAnswer record);

    LstTestStudentAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstTestStudentAnswer record);

    int updateByPrimaryKey(LstTestStudentAnswer record);

    List<LstTestStudentAnswer> getStudentTestAnswerInfo(Integer id);

    List<LstStudentTest> query(Map<String, Object> map);

    LstTestStudentAnswer getGradeInfo(LstTestStudentAnswer lsta);

    int insertGradeSelective(LstTestStudentAnswer answer);

    int updateStudentTestStatus(Map<String, Object> map);

    Map<String, Object> getStatus(Map<String, Object> map);

    List<LstTestStudentAnswer> getDescTopFive(LstTestStudentAnswer lsta);

    List<LstTestStudentAnswer> getDescBackwardFive(LstTestStudentAnswer lsta);

    Map<String, Object> getScoreList(LstTestStudentAnswer lsta);

    List<LstQuestion> getTestQuestion(int paperId);

    LstTestStudentAnswer selectByCondition(LstTestStudentAnswer ltsa);

    void updateStudentTestTeacherScore(LstTestStudentAnswer list);

    List<Map<String, Object>> getclassAvgScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> getTotalAvgScore(LstTestStudentAnswer lsta);

    LstTestStudentAnswer getOverallScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> getStuAvgScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> selectAllQuesForStu(LstTestStudentAnswer lsta);

    LstTestStudentAnswer getScore(LstTestStudentAnswer list);
}