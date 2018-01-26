package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.resume.entity.ResumeAttachment;
@Repository
public interface ResumeAttachmentDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeAttachment record);

    int insertSelective(ResumeAttachment record);

    ResumeAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeAttachment record);

    int updateByPrimaryKey(ResumeAttachment record);

    List<ResumeAttachment> queryRa(ResumeAttachment ra);
}