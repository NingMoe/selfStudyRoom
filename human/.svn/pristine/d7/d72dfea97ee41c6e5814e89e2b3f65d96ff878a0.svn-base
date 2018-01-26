package com.human.jzbTest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.human.jzbTest.entity.JzbQuestionPaper;

@Repository
public interface JzbQuestionPaperDao {
    
    int deleteByPrimaryKey(Integer id);
   
    int insertSelective(JzbQuestionPaper record);

    JzbQuestionPaper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JzbQuestionPaper record);
    
    List<JzbQuestionPaper> selectByOpenId(String openId);
    
    JzbQuestionPaper selectLastPaperByOpenId(String openId);
    
    Integer getExistTotalTimesByOpenId(JzbQuestionPaper paper);
    
    Integer getExistMonthTotalTimesByOpenId(JzbQuestionPaper paper);
    
    Integer getExistDayTimesByOpenId(JzbQuestionPaper paper);
    
    List<JzbQuestionPaper> getNoJiaoyanPaper();
    
}