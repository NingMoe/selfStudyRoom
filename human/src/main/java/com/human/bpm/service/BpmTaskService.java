package com.human.bpm.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.human.bpm.entity.BpmTaskActivity;


public interface BpmTaskService {
    /**
     * 启动流程，并且完成第一个任务
     * @param processDefinitionKey
     * @param businessKey
     * @param variables
     * @return
     */
    ProcessInstance start(String processDefinitionKey, String businessKey, Map<String, Object> variables);
    
    /**
     * 仅启动流程，不做其他处理
     * @param processDefinitionKey
     * @param businessKey
     * @param variables
     * @return
     */
    ProcessInstance startOnly(String processDefinitionKey, String businessKey, Map<String, Object> variables);
    
    /**
     * 提交
     * @param processInstanceId
     * @param taskId
     * @param comment
     * @param variables
     */
    void complete(String processInstanceId, String taskId, String comment, Map<String, Object> variables);
    
    /**
     * 根据流程实例ID获取流程实例
     * @param procInsId
     * @return
     */
    public ProcessInstance getProcIns(String procInsId);
    
    /**
     * 获取任务
     * @param taskId 任务ID
     */
    public Task getTask(String taskId);
    
    /**
     * 删除任务
     * @param taskId 任务ID
     * @param deleteReason 删除原因
     */
    public void deleteTask(String taskId, String deleteReason);
    
    /**
     * 签收任务
     * @param taskId 任务ID
     * @param userId 签收用户ID（用户登录名）
     */
    public void claim(String taskId, String userId);
    
    /**
     * 流程跟踪
     * @param processInstanceId
     * @return
     */
    public List<BpmTaskActivity> getTaskActivities(String processInstanceId);
    
}   
