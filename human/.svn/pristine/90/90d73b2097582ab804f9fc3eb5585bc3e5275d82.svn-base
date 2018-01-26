package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeEducationHistory;
@Repository
public interface ResumeEducationHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeEducationHistory record);

    int insertSelective(ResumeEducationHistory record);

    ResumeEducationHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeEducationHistory record);

    int updateByPrimaryKey(ResumeEducationHistory record);
    
    List<ResumeEducationHistory> selectByResumeId(Long resumeId);

    int deleteByResumeId(Long resumeId);
}