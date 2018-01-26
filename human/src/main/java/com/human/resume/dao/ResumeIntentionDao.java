package com.human.resume.dao;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeIntention;
@Repository
public interface ResumeIntentionDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeIntention record);

    int insertSelective(ResumeIntention record);

    ResumeIntention selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeIntention record);

    int updateByPrimaryKey(ResumeIntention record);
    
    /*
     * 通过简历ID查询求职意向
     */
    ResumeIntention selectByResumeId(Long resumeId);
}