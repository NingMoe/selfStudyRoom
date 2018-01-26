package com.ls.spt.zuoye.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;

public interface LstZuoyeReportDao {

    int getTjNum(Map<String, Object> map);

    List<Map<String, Object>> getZuoyeDfl(LstStudentZuoye lz);

    List<Map<String, Object>> getZuoyeSitu(LstStudentZuoye lz);

    List<Map<String, Object>> getQuestionCodes(LstStudentZuoye lz);

    List<Map<String, Object>> getQuestions(Map<String, Object> map);

    Map<String, Object> getScore(LstStudentZuoye lz);

    List<Map<String, Object>> getZuotiSitu(LstStudentZuoye lz);

    Map<String, Object> getzuoyeInfo(LstStudentZuoye lz);

    Map<String, Object> getStudentZuoyeByCode(LstStudentZuoye lz);

    List<Map<String, Object>> getStudentQuestion(LstStudentZuoye lz);

    Map<String, Object> totalScoreList(LstStudentZuoye lz);

    List<Map<String, Object>> getGrowthData(LstStudentZuoye lz);

    Map<String, Object> getScoreForStudent(LstStudentZuoye lz);

    int beat(LstStudentZuoye lz);

    int totalStudent(LstStudentZuoye lz);

    List<Map<String, Object>> pgNum(LstStudentZuoye lz);

    Map<String, Object> getavgScore(LstStudentZuoye lz);

    List<Map<String, Object>> getStudentLevel(LstStudentZuoye lz);

}