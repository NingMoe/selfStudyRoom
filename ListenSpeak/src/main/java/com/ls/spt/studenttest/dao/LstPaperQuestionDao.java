package com.ls.spt.studenttest.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.studenttest.entity.LstPaperQuestion;

@Repository(value="studentselfpaperquestion")
public interface LstPaperQuestionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LstPaperQuestion record);

    int insertSelective(LstPaperQuestion record);

    LstPaperQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LstPaperQuestion record);

    int updateByPrimaryKey(LstPaperQuestion record);

    /**
     * 获取考试题目详情
     * @param mapparams
     * @return
     */
    public List<LstPaperQuestion> selectStudentQuestion(Map<String, Object> mapparams);
}