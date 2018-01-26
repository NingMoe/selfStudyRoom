package com.human.jzbTest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.basic.entity.AreaInfo;
import com.human.basic.entity.DicData;
import com.human.jzbTest.entity.CacheQuestion;
import com.human.jzbTest.entity.JzbCacheAnswer;
import com.human.jzbTest.entity.JzbQuestion;
import com.human.jzbTest.entity.JzbQuestionAnswer;
import com.human.jzbTest.entity.JzbQuestionKnowledge;

@Repository
public interface JzbQuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(JzbQuestion record);

    JzbQuestion selectByPrimaryKey(Integer id);
    
    JzbQuestion selectQuestionById(String id);
    
    JzbQuestion selectQuestionByCode(String qCode);
    
    JzbQuestion selectMultiQuestionById(String id);
    
    int updateByPrimaryKeySelective(JzbQuestion record);
    
    int updateStatus(JzbQuestion record);

    int updateByPrimaryKey(JzbQuestion record);
     
    List<JzbQuestion> selectQuestionPage(Map<Object,Object> map);

    List<Integer> selectForQuestion(Map<String, Object> map);
    
    int insertQuestionAnswers(List<JzbQuestionAnswer> answers);
    
    int insertQuestionKnowledges(List<JzbQuestionKnowledge> knowledges);
    
    void deleteQuestionKnowledges(Integer questionId);
    
    void deleteQuestionAnswers(Integer questionId);
    
    String getMaxCodeByCodePre(String codePre);
    
    List<JzbQuestionAnswer> getQuesAnswerList(Integer questionId);
     
    void updateAnswerByQuestionIdAndXh(JzbQuestionAnswer answer);
    
    List<AreaInfo> getAreaByGrade(Integer gradeId);
    
    String getKsTitleByMonthConfig(Integer monthConfigId);
    
    List<CacheQuestion> selectSimpleQuestionsByPrecode(String preCode);
    
    List<CacheQuestion> selectMultiQuestionsByPrecode(String preCode);
    
    List<JzbCacheAnswer> selectCacheAnswerByPrecode(String preCode);
    
    List<DicData> getQuestionClassType();
    
    List<DicData> getQuestionSubjectByGrade(String grade);

    List<JzbQuestion> getquestionArr(Map<String, Object> map);

    JzbQuestion selectPrimaryKey(Integer valueOf);
    
}