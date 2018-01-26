package com.ls.spt.lstBasePaper.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;

public interface LstPaperQuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstPaperQuestion record);

    int insertSelective(LstPaperQuestion record);

    LstPaperQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstPaperQuestion record);

    int updateByPrimaryKey(LstPaperQuestion record);

    List<LstPaperQuestion> query(Map<String, Object> map);

    int deleteByCode(LstPaperQuestion lpq);

    public LstPaperQuestion getPaperQuestionInfo(Map<String, Object> map);
    
    /**
     * 获取考试题目详情
     * @param mapparams
     * @return
     */
    public List<com.ls.spt.studenttest.entity.LstPaperQuestion> selectStudentQuestion(Map<String, Object> mapparams);
}