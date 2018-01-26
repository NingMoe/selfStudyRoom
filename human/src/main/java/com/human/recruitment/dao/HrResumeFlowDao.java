package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.human.bpm.entity.ActCustomScorce;
import com.human.bpm.entity.ActEmailNotify;
import com.human.bpm.entity.ActNode;
import com.human.bpm.entity.HrActNode;
import com.human.recruitment.entity.HrResumeFlow;

public interface HrResumeFlowDao {
    
    List<HrResumeFlow> selectResumeFlowPage(Map<String,Object> map);
    
    int deleteByPrimaryKey(Integer id);

    int insert(HrResumeFlow record);

    HrResumeFlow selectByPrimaryKey(Integer id);
    
    HrResumeFlow selectByFlowCode(String flowCode);

    int updateByPrimaryKeySelective(HrResumeFlow record);
    
    int updateByFlowCodeSelective(HrResumeFlow record);
    
    int updateByFlowCode(HrResumeFlow record);
    
    int updateByProcessInstanceIdSelective(HrResumeFlow record);
    
    public String getMaxCodeByPrefix(String prefix);
    /**
     * 通过简历ID查找简历当前流程状态
     * @param resumeId
     * @return
     */
    HrResumeFlow selectHrfByResumeId(Integer resumeId);
    
    
    HrResumeFlow selectNewFlowResumeId(Integer resumeId);
    /***
     * 面试反馈评分
     * @param acs
     * @return
     */
    int insertScore(ActCustomScorce acs);
    
    /**
     * @param resumeId
     * @return
     */
    List<ActNode> getNodesByResumeId(Integer resumeId);
    
    /**
     * 根据简历ID以及节点ID获取当前节点
     * @param resumeId
     * @param nodeId
     * @return
     */
    ActNode getFirstNodeByResumeNode(@Param("resumeId")Integer resumeId,@Param("nodeId")String nodeId);
    
    HrActNode getCurrNode(String flowCode);
    
    List<ActEmailNotify> getFlowDb();
    
    List<ActEmailNotify> getWatcherFlowDb();
    
    List<ActEmailNotify> getCsMsgDb();
    
    List<ActEmailNotify> getCsHrDb();
    
    List<ActEmailNotify> getCsWatcherDb();

}