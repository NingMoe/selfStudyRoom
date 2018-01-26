package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeLanguage;
@Repository
public interface ResumeLanguageDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeLanguage record);

    int insertSelective(ResumeLanguage record);

    ResumeLanguage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeLanguage record);

    int updateByPrimaryKey(ResumeLanguage record);
    
    List<ResumeLanguage> selectByResumeId(Long resumeId);

    int deleteByResumeId(Long resumeId);
}