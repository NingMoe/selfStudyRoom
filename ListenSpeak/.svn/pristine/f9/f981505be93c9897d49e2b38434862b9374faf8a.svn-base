package com.ls.spt.zuoye.service;

import java.util.List;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.studentzuoye.entity.LstStudentZuoye;
import com.ls.spt.zuoye.entity.LstClassZuoyeDto;
import com.ls.spt.zuoye.entity.LstZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeStudentAnswer;

public interface LstZuoyeService {
    
    PageView query(PageView pageView, LstZuoye zuoye);
    
    LstZuoye selectZuoyeById(Integer id);
    
    void insertLstZuoye(LstZuoye zuoye);
    
    void deleteZuoye(Integer id);
    
    void editLstZuoye(LstZuoye zuoye);
    
    int updateByPrimaryKeySelective(LstZuoye zuoye);

    int updateByPrimaryKey(LstZuoye zuoye);
    
    List<LstClass> getClassList(Integer teacher);
    
    List<LstQuestion> getZuoyeQuestionList(Integer zuoyeId);
    
    List<LstQuestion> getZuoyeQuestion(Integer zuoyeId);
    
    void delQuestion(LstQuestion question);
    
    int updateQuestionXh(LstQuestion question);
    
    void addZuoyeQuestion(LstQuestion question);
    
    void releaseZuoye(Integer id);
    
    /**
     * 查找老师未批改的作业
     */
    List<LstClassZuoyeDto> getNoCompleteZuoye();
    
    /**
     * 查找老师已批改的作业
     */
    PageView getCompleteZuoyePage(PageView pageView);
    
    /**
     * 根据班级号及作业获取学生列表
     */
    PageView getStudentZuoyePage(PageView pageView,LstStudentZuoye studentZuoye);
    
    /**
     * 根据
     * @param code
     * @return
     */
    LstQuestion getMultiStudentQuestion(LstZuoyeStudentAnswer answer,String code);

    LstQuestion getSimpleStudentQuestion(LstZuoyeStudentAnswer answer,String code);
    
    void pgSimpleZuoye(LstZuoyeStudentAnswer answer);
    
    void pgMultiZuoye(List<LstZuoyeStudentAnswer> answers,String isEnd);

    int getTjNum(LstStudentZuoye lz,String zs);
}
