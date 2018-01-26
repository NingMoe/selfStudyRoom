package com.ls.spt.lstBasePaper.dao;

import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;

import com.ls.spt.lstBasePaper.entity.LstQuestion;

public interface LstQuesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstQuestion record);

    int insertSelective(LstQuestion record);

    LstQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstQuestion record);

    int updateByPrimaryKey(LstQuestion record);

    LstQuestion getLstQuestion(Map<String, Object> map);

    String getAreaName(Integer province);

    int getTotalNum(String id);

    LstQuestion getQuestionType(Map<String, Object> map);

    LstQuestion getQuestion(Map<String, Object> map);
    
    List<LstQuestion> getAllQuestion(Map<String, Object> map);

//    List<LstQuestion> queryQuesByMonAndDiff(LstQuestion lst);

    int insertToQuestion(Map<String, Object> map);
//
    Map<String, Object> getPaperQuestionList(Map<String, Object> map);
//
//    List<LstQuestion> getCodeByDiffAndGrade(LstQuestion lst);
//
//    List<LstQuestion> getSceneQuestion(LstQuestion lst);
//
//    int getCodeNum(LstQuestion lst);
//
//    List<LstQuestion> getCommentCodeByDiffAndGrade(LstQuestion lst);

    List<LstQuestion> query(Map<Object, Object> map);

    Map<String, Object> selectByKey(String code);

    List<Map<String, Object>> getQuestionByCode(String code);

    int selectQuestionCount(Map<String, Object> map);

    List<LstQuestion> getQuestionInfoByCode(LstQuestion lst);

    List<String> getCodes(LstQuestion lst);

    List<LstQuestion> getSenceQuestion(LstQuestion lst);

    String selectQuestionTypeName(LstQuestion lst);

    int insertToPaperQuestion(LstQuestion lst);

    List<LstQuestion> selectInfoByCode(LstQuestion lst);

    List<Integer> getMaxXh(int paperId);

    List<Map<String,Object>> used(Map<String,Object> map);

}