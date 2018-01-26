package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.ResumeHrRemark;

@Repository
public interface ResumeHrRemarkDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ResumeHrRemark record);

    ResumeHrRemark selectByPrimaryKey(Integer id);
    
    List<ResumeHrRemark> selectByResumeId(Integer resumeId);
}