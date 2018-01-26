package com.human.resume.dao;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeTrainHistory;
@Repository
public interface ResumeTrainHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeTrainHistory record);

    int insertSelective(ResumeTrainHistory record);

    ResumeTrainHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeTrainHistory record);

    int updateByPrimaryKey(ResumeTrainHistory record);

    int deleteByResumeId(Long resumeId);
}