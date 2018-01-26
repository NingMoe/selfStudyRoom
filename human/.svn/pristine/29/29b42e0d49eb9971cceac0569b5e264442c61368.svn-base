package com.human.resume.service;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.bpm.entity.ActCustomPhoto;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.ResumeHrRemark;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeBase;
import com.human.utils.PageView;

public interface ResumeService {

    /**
     * 分页查询简历
     */
    PageView query(PageView pageView,ResumeBase resumeBase);
    
    ResumeBase selectByPrimaryKey(Long id);
    
    /**
     * 查询简历的详情
     * @param id
     * @return
     */
    ResumeBase queryResumeDetail(Long id);
    
    int updateByPrimaryKeySelective(ResumeBase record);
    
    /**
     * 通过简历ID查找简历当前流程对象
     * @param resumeId
     * @return
     */
    HrResumeFlow selectHrFlowByResumeId(Integer resumeId);
    
    /**
     * 通过求职者ID去查询最新的简历
     * @param resumeSeekerId
     * @return
     */
    ResumeBase selectByResumeSeekerId(Long resumeSeekerId);
    
    /**
     * 通过求职者ID去查询我的简历
     * @param resumeSeekerId
     * @return
     */
    ResumeBase selectOriginalResumeByRsId(Long resumeSeekerId);
    
    /**
     * 复制一份最新的简历为我的简历
     * @param resumeSeekerId
     */
    void copyResume(Long resumeSeekerId);
    
    /**
     * 更新前台我的简历中的内部推荐
     * @param request
     * @param rb
     * @return
     */
    Map<String, Object>updateInsideRecommend(HttpServletRequest request, ResumeBase rb);
    
        
    /**
     * 前台快速投递结束方法
     * @param resumeId
     * @return
     */
    Map<String, Object>finishDelivery(String jstr ,String resumePhotoPathName,HttpServletRequest request);
    
    /**
     * 前台简历投递结束方法
     * @param resumeSeekerId
     * @param positionId
     * @return
     */
    Map<String, Object>finishDelivery(String jstr,HttpServletRequest request);
    
    /**
     * 新增简历
     */
    void addResume(ResumeSeeker rs);
    
    /**
     * 添加沟通记录
     */
    void addHrContent(ResumeHrRemark rhr);
    
    /**
     * 获取简历的沟通记录
     * param:简历ID
     */
    List<ResumeHrRemark> getCommentByResumeId(Integer resumeId);
    
    /**
     * 简历设置延迟面试
     * @param ids
     * @return
     */
    Map<String, Object> delayInterview(String ids);
    
    /**
     * 简历设置恢复面试
     * @param ids
     * @return
     */
    Map<String, Object> recoverInterview(String ids);

    /**
     * 查询面试反馈时的照片
     * @param acp
     * @return
     */
    List<ActCustomPhoto> selectActCustomPhoto(ActCustomPhoto acp);
}
