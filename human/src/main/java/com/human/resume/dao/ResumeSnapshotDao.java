package com.human.resume.dao;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeSnapshot;
@Repository
public interface ResumeSnapshotDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeSnapshot record);

    int insertSelective(ResumeSnapshot record);

    ResumeSnapshot selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeSnapshot record);

    int updateByPrimaryKey(ResumeSnapshot record);
}