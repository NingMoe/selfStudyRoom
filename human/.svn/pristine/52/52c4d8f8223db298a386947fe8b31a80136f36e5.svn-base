package com.human.jzbTest.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.human.jzbTest.entity.JzbGrade;
import com.human.jzbTest.entity.JzbPaperQuestion;
import com.human.jzbTest.entity.JzbPaperQuestionDto;

@Repository
public interface JzbPaperQuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(JzbPaperQuestion record);

    JzbPaperQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbPaperQuestion record);

    /*
     * 通过试卷Id查询错题
     */
    List<JzbPaperQuestionDto> selectByPaperId(Integer paperId);
    
    int insertPaperQuestions(List<JzbPaperQuestion> questions);
    
    List<Integer> getPageQuestionIds(JzbPaperQuestion pq);
    
    int setStuAnswer(JzbPaperQuestion pq);
    
    int setTkStuAnswer(JzbPaperQuestion pq);
    
    int setAnswerId(Integer paperId);
    
    Integer selectPassNum(Integer paperId);
    
    List<JzbGrade> getValidKsGrade(String companyId);
}