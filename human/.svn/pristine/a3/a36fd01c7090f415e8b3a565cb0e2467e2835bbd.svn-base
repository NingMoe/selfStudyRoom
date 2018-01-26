package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.jzbKnowledgePoint;

public interface jzbKnowledgePointDao {
    int deleteByPrimaryKey(Integer id);

    int insert(jzbKnowledgePoint record);

    int insertSelective(jzbKnowledgePoint record);

    jzbKnowledgePoint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(jzbKnowledgePoint record);

    int updateByPrimaryKey(jzbKnowledgePoint record);

    List<jzbKnowledgePoint> query(Map<String, Object> map);

    void deleteByIds(Map<String, Object> paraMap);

    List<jzbKnowledgePoint> selectKnowledge(int quesId);
    
    List<jzbKnowledgePoint> queryByCondition(jzbKnowledgePoint point);

    List<jzbKnowledgePoint> searchZsd( Map<String ,Object> map);

    List<jzbKnowledgePoint> selectCount(Map<String ,Object> map);

    List<Map<String, Object>> queryByKnow(Map<String, Object> map);

    Integer selectMaxConfigNum(Map<String, Object> map);

    java.util.List<Map<String, Object>> queryByKnowledge(Map<String, Object> map);

    List<jzbKnowledgePoint> getKnowledgeNameAndTitleNum(JzbQuestion jq);

    List<JzbQuestion> getQuestions(JzbQuestion jq);

    List<JzbQuestion> getQuestionByCode(JzbQuestion jq);

    int getTitleNum(JzbQuestion jq);

    List<JzbQuestion> getQuestionsForPhone(JzbQuestion jq);

    String getIds(String qCode);
}