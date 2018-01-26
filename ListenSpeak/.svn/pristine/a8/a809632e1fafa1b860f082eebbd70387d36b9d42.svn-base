package com.ls.spt.zuoye.dao;

import java.util.List;
import java.util.Map;
import com.ls.spt.zuoye.entity.LstZuoyeQuestion;

public interface LstZuoyeQuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstZuoyeQuestion record);

    int insertSelective(LstZuoyeQuestion record);

    LstZuoyeQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstZuoyeQuestion record);

    int updateByPrimaryKey(LstZuoyeQuestion record);
    
    public List<LstZuoyeQuestion> selectStudentQuestion(Map<String, Object> map);
}