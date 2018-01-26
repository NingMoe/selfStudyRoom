package com.human.resume.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.bpm.entity.ActCustomPhoto;
import com.human.resume.entity.ResumePhoto;
@Repository
public interface ResumePhotoDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumePhoto record);

    int insertSelective(ResumePhoto record);

    ResumePhoto selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumePhoto record);

    int updateByPrimaryKey(ResumePhoto record);
    
    List<ResumePhoto> selectByResumeId(Long resumeId);
    
    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int updateById(Long id);
    
    /**
     * 查询快速投递设置的图片简历
     * @param resumeId
     * @return
     */
    List<ResumePhoto> selectFastRp(Long resumeId);

    /**
     * 插入反馈评论图片
     * @param acp
     * @return
     */
    int insertActPhoto(ActCustomPhoto acp);

    int delActPhoto(Long id);
}