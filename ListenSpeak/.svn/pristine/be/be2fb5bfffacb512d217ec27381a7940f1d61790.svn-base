package com.ls.spt.lstBasePaper.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.lstBasePaper.entity.LstBasePaper;
import com.ls.spt.lstBasePaper.entity.LstPaperQuestion;


public interface LstBasePaperDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstBasePaper record);

    int insertSelective(LstBasePaper record);

    LstBasePaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstBasePaper record);

    int updateByPrimaryKey(LstBasePaper record);

    List<LstBasePaper> query(Map<Object, Object> map);

    int selectUseCount(int id);
    
    int selectNumCount(int id);

    int deleteByIds(Map<String, Object> paraMap);

    String getAreaName(Integer province);

    List<Map<String, Object>> getQuestionType();

    int getSpeakNum(Map<Object, Object> map);

    int insertQuestionType(Map<String, Object> map);

    List<Map<String, Object>> getTypeAndNum(Integer id);

    int getUseNum(LstBasePaper l);

    int getTotalNum(LstBasePaper l);

    int updateById(LstBasePaper lst);

    List<Map<String, Object>> getQuestionTypeNum(LstBasePaper lst);

    int updateQuestionType(Map<String, Object> map);

    int updateXh(Map<String, Object> map);

    List<LstBasePaper> queryForTestCl(Map<Object, Object> map);
    
    /**
     * 获取考试题目详情
     * @param mapparams
     * @return
     */
    public List<LstPaperQuestion> selectStudentQuestion(Map<String, Object> mapparams);

    int getquestionNum(Integer paperId);
}