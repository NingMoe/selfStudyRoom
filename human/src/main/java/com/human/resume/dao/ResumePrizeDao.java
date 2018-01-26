package com.human.resume.dao;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumePrize;
@Repository
public interface ResumePrizeDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumePrize record);

    int insertSelective(ResumePrize record);

    ResumePrize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumePrize record);

    int updateByPrimaryKey(ResumePrize record);
}