package com.ls.spt.question.service;

import org.springframework.stereotype.Service;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.question.entity.LstQuestion;

@Service
public interface QuestionService {
    
    PageView query(PageView pageView, LstQuestion question);
    
    int insertSimpleQuestion(LstQuestion question);
    
    int insertMultiQuestion(LstQuestion question);
    
    int editMultiQuestion(LstQuestion question);
    
    int delQuestionByCode(String code);
    
    LstQuestion getSimpleQuestion(String code);
    
    LstQuestion getMultiQuestion(String code);
    
    int updateByPrimaryKeySelective(LstQuestion question);

    int updateByPrimaryKey(LstQuestion question);
    
}
