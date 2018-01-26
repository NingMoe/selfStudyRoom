package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeProjectExperience;
@Repository
public interface ResumeProjectExperienceDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeProjectExperience record);

    int insertSelective(ResumeProjectExperience record);

    ResumeProjectExperience selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeProjectExperience record);

    int updateByPrimaryKey(ResumeProjectExperience record);
    
    List<ResumeProjectExperience> selectByResumeId(Long resumeId);

    int deleteByResumeId(Long resumeId);
}