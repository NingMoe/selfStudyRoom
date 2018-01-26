package com.human.bpm.service;

import java.util.List;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.human.bpm.entity.ActCustomComment;
import com.human.bpm.entity.HrActNode;
import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.recruitment.entity.PositionHrUser;
import com.human.utils.PageView;

public interface HrWorkflowService {
    public HrResumeFlow startProcess(InterviewSchedule interviewSchedule);
    
    /**
     * 人力确认环节提交
     * @param flowCode
     */
    public void tjResumeFlow(String flowCode);
    
    /**
     * 会签提交
     * @param businessKey
     * @return
     * result  1为同意  0为不同意 2为退回
     */
    public void tjResumeMultiFlow(ActCustomComment comment);
    
    /**
     * 获取当前任务
     * @param businessKey
     * @return
     */
    public Task getCurrentTask(String businessKey);
    
    /**
     * 获取当前任务
     * @param businessKey
     * @return
     */
    public Task getCurrentTask(String businessKey,String assignee);
    
    /**
     * 根据业务编码获取当前审批人
     * @param businessKey
     * @return
     */
    public List<ActCustomComment> getAssignee(String businessKey);
    
    /**
     * 添加审批意见
     * @param comment
     * @return
     */
    public void addComment(ActCustomComment comment);
    
    /**
     * 获取人力确认环节分页
     * @param pageView
     * @param flow
     * @return
     */
    PageView getHrResumeRlqrPage(PageView pageView,HrResumeFlow flow);
    
    /**
     * 删除流程
     * processInstanceId:流程ID
     * delReason：删除原因
     */
    public void delProcess(String flowCode,String processInstanceId,String delReason);
    
    /**
     * 根据流程CODE获取流程定义ID
     */
    public ProcessInstance getProcessInstanceByFlowCode(String flowCode);
    
    /**
     * 根据流程编码以及节点ID获取评论
     */
    public List<ActCustomComment> getNodeComment(ActCustomComment comment);
    
    /**
     * 根据流程编码获取当前节点
     */
    HrActNode getCurrNode(String flowCode);
    
    /**
     * 获取职位所对应的人力确认人员
     * @param positionId
     * @return
     */
    List<PositionHrUser> getPositionHrUsersByPositionId(Integer positionId);
    
}
