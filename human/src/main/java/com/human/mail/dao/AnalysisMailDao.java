package com.human.mail.dao;

import org.springframework.stereotype.Repository;

import com.human.mail.entity.AnalysisMail;
@Repository
public interface AnalysisMailDao {
    int deleteByPrimaryKey(Long id);

    int insert(AnalysisMail record);

    int insertSelective(AnalysisMail record);

    AnalysisMail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AnalysisMail record);

    int updateByPrimaryKey(AnalysisMail record);
    
    AnalysisMail selectByAcceptMailId(Long acceptMailId);
}