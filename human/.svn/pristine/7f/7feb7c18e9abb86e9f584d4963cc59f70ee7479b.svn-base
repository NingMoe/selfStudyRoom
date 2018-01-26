package com.human.resume.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.bpm.entity.ActCustomPhoto;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.resume.entity.ResumeBase;
@Repository
public interface ResumeBaseDao {
    int deleteByPrimaryKey(Long id);

    int insert(ResumeBase record);

    int insertSelective(ResumeBase record);

    ResumeBase selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResumeBase record);
   

    int editByPrimaryKey(ResumeBase record);
    
    /**
     * 查询应聘者简历投递记录
     * @param rb
     * @return
     */
    List<ResumeBase> jlQuery(ResumeSeeker rs);
    
    /*
     * 分页查询简历
     * @param map
     * @return
     */
    List<ResumeBase>query(Map<String,Object> map);

    /**
     * 查询简历详情
     * @param id
     * @return
     */
    ResumeBase queryResumeDetail(Long id);
    
    /**
     * 根据流程编码更新简历状态
     * @param flowCode
     * @return
     */
    int updateResumeStatus(@Param("flowCode")String flowCode,@Param("status")String status);
    
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
     * 通过求职者ID及职位ID去查询投递简历
     * @param record
     * @return
     */
    ResumeBase selectDeliveryResume(ResumeBase record);
    
    
    /**
     * 根据求职者OPENID 获取投递记录数
     */
    Integer getFrontResumeCntByOpenId(String openId);
    
    /**
     * 根据求职者OPENID 获取投递记录数
     */
    List<ResumeBase> getFrontResumeByOpenId(String openId);
    
    Integer isPositionHasTd(@Param("openId")String openId,@Param("positionId")Integer positionId);
    
    /**
     * 查询求职者是否有正在走的流程简历
     * @param record
     * @return
     */
    ResumeBase selectRbByReSeekId(ResumeBase record);
    
    /**
     * 锁定简历（一个求职者只能有一份简历在走）
     */
    int lockResumeBaseToFlow(Integer resumeId);
    
    /**
     * 解锁简历（流程结束）
     */
    int unlockResumeBaseToFlow(String flowCode);
    
    /**
     * 简历设置延迟面试
     * @param map
     * @return
     */
    int delayInterviewByIds(Map<String, Object> map);
    
    /**
     * 简历设置恢复面试
     * @param map
     * @return
     */
    int recoverInterviewByIds(Map<String, Object> map);

    List<ActCustomPhoto> selectActCustomPhoto(ActCustomPhoto acp);
}