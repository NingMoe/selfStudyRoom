package com.ls.spt.lstBasePaper.service;

import java.util.List;
import java.util.Map;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstBasePaper.entity.LstQuestion;

public interface LstQuestionService {

    LstQuestion getLstQuestion(String id,int page);

    String getAreaName(Integer city);

    int getTotalNum(String id);

    LstQuestion getQuestionType(String id, int page);

    LstQuestion getQuestion(String id, String code);
    
    List<LstQuestion> getAllQuestion(String id, String code);

    int insertToQuestion(String paperId, String questionId, String xh);

    Map<String, Object> getPaperQuestionList(String paperId, String questionId, String xh);

    PageView query(LstQuestion lst, PageView pageView);

    Map<String, Object>  selectPrimaryBykey(String code);

    List<Map<String, Object>> getQuestionByCode(String code);

    int selectQuestionCount(int questionId, Integer valueOf);

    List<LstQuestion> getQuestionInfoByCode(LstQuestion lst);

    List<String> getCodes(LstQuestion lst);

    List<LstQuestion> getSenceQuestion(LstQuestion lst);

    String selectQuestionTypeName(LstQuestion lst);

    int insert(LstQuestion lst);

    List<LstQuestion> selectInfoByCode(LstQuestion lst);

    List<Integer> getMaxXh(int paperId);

    List<Map<String,Object>> used(String code);


}
