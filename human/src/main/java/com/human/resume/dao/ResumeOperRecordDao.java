package com.human.resume.dao;

import com.human.resume.entity.ResumeOperRecord;

public interface ResumeOperRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ResumeOperRecord record);

    ResumeOperRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResumeOperRecord record);

}