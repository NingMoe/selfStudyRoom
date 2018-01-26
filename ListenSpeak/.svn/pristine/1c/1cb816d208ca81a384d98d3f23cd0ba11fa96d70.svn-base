package com.ls.spt.zuoye.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.question.entity.LstQuestion;
import com.ls.spt.zuoye.entity.LstClassZuoyeDto;
import com.ls.spt.zuoye.entity.LstZuoye;
import com.ls.spt.zuoye.entity.LstZuoyeClass;

@Repository
public interface LstZuoyeDao {
    
    List<LstZuoye> query(Map<Object, Object> map);
    
    int deleteByPrimaryKey(Integer id);

    int insert(LstZuoye record);

    int insertSelective(LstZuoye record);

    LstZuoye selectByPrimaryKey(Integer id);
    
    LstZuoye selectZuoyeById(Integer id);

    int updateByPrimaryKeySelective(LstZuoye record);

    int updateByPrimaryKey(LstZuoye record);
    
    int insertZuoyeClassList(List<LstZuoyeClass> list);
    
    int delZuoyeClassList(Integer zuoyeId);
    
    int delZuoyeQuestionList(Integer zuoyeId);
    
    int delZuoyeQuestion(LstQuestion question);
    
    int updateQuestionXh(LstQuestion question);
    
    int addZuoyeQuestion(LstQuestion question);
    
    Integer getZuoyeMaxXh(Integer zuoyeId);
    
    int initStudentZuoye(Integer zuoyeId);
    
    int initStudentZuoyeQuestion(Integer zuoyeId);
    
    List<LstClassZuoyeDto> getNoCompleteZuoye(Integer teacher);
    
    List<LstClassZuoyeDto> getCompleteZuoye(Map<Object,Object> map);
    
}