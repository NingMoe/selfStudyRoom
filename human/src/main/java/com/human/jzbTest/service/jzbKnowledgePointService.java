package com.human.jzbTest.service;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.jzbKnowledgePoint;
import com.human.utils.PageView;

public interface jzbKnowledgePointService {

    PageView query(PageView pageView,jzbKnowledgePoint jzb);

    int delete(int id);

    int insert(jzbKnowledgePoint jzb);

    jzbKnowledgePoint selectByPrimaryKey(int id);

    int update(jzbKnowledgePoint jzb);

    Map<String, Object> dele(String deleteIds);
    
    List<jzbKnowledgePoint> selectKnowledge(int quesId);
    
    List<jzbKnowledgePoint> queryByCondition(jzbKnowledgePoint point);

    List<jzbKnowledgePoint> selectCount(String questionId);

    List<Map<String, Object>> queryByKnow(int knowledgeId,String questionId,String month,String mainConfigId);
    List<Map<String, Object>> queryByKnowledge(int knowledgeId,String questionId);

    Integer selectMaxConfigNum(int knowledgeId);

    List<jzbKnowledgePoint> getKnowledgeNameAndTitleNum(JzbQuestion jq);

    List<JzbQuestion> getQuestions(JzbQuestion jq);

    List<JzbQuestion> getQuestionByCode(JzbQuestion jq);

    List<JzbQuestionAnswer> getQuesAnswerList(int id);

    int getTitleNum(JzbQuestion jq);

    List<JzbQuestion> getQuestionsForPhone(JzbQuestion jq);

    String getIds(String getqCode);

}
