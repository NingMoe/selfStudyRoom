package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeWorkHistory;
@Repository
public interface ResumeWorkHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeWorkHistory record);

    int insertSelective(ResumeWorkHistory record);

    ResumeWorkHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeWorkHistory record);

    int updateByPrimaryKey(ResumeWorkHistory record);
    
    List<ResumeWorkHistory> selectByResumeId(Long resumeId);
    
    int deleteByResumeId(Long resumeId);
}