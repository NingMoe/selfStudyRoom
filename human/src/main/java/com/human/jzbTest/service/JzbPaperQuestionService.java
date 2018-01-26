package com.human.jzbTest.service;

import java.util.List;
import com.human.jzbTest.entity.JzbPaperErrorDto;
import com.human.jzbTest.entity.JzbPaperQuestion;

public interface JzbPaperQuestionService {
    /*
     * 通过试卷Id查询错题
     */
    List<JzbPaperErrorDto> selectByPaperId(Integer paperId);
    
    int updateByPrimaryKeySelective(JzbPaperQuestion jpq);
    
    int setStuAnswer(JzbPaperQuestion jpq);
    
    Integer selectPassNum(Integer paperId);
}
