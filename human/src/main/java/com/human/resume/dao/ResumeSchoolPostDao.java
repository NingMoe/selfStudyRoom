package com.human.resume.dao;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeSchoolPost;
@Repository
public interface ResumeSchoolPostDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeSchoolPost record);

    int insertSelective(ResumeSchoolPost record);

    ResumeSchoolPost selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeSchoolPost record);

    int updateByPrimaryKey(ResumeSchoolPost record);

    int deleteByResumeId(Long resumeId);
}