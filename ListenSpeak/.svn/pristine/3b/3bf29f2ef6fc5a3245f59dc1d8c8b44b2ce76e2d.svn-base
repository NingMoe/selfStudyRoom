package com.ls.spt.lstClassTest.service;

import java.util.List;
import java.util.Map;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClassTest.entity.LstTestStudentAnswer;
import com.ls.spt.question.entity.LstQuestion;

public interface LstTestStudentAnswerService {

    List<LstTestStudentAnswer> getStudentTestAnswerInfo(Integer id);

    int insert(LstTestStudentAnswer aList);

    PageView query(PageView pageView, LstTestStudentAnswer ltsa);

    LstTestStudentAnswer getGradeInfo(LstTestStudentAnswer lsta);

    int insertGrade(LstTestStudentAnswer answer);

    int updateStudentTestStatus(int studentId, int testId);

    List<LstTestStudentAnswer> getDescTopFive(LstTestStudentAnswer lsta);

    List<LstTestStudentAnswer> getDescBackwardFive(LstTestStudentAnswer lsta);

    Map<String, Object> getScoreList(LstTestStudentAnswer lsta);

    List<LstQuestion> getTestQuestion(int paperId);

    LstQuestion getSimpleTestQuestion(LstTestStudentAnswer ltsa, String code);

    LstQuestion getMultiStudentQuestion(LstTestStudentAnswer ltsa, String code);

    void pgMultiTest(List<LstTestStudentAnswer> list);

    void pgSimpleTest(LstTestStudentAnswer list);

    List<Map<String, Object>> getclassAvgScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> getTotalAvgScore(LstTestStudentAnswer lsta);

    LstTestStudentAnswer selectPrimarykey(LstTestStudentAnswer lsta);

    LstTestStudentAnswer getOverallScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> getStuAvgScore(LstTestStudentAnswer lsta);

    List<Map<String, Object>> selectAllQuesForStu(LstTestStudentAnswer lsta);


}
