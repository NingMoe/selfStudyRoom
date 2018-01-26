package com.human.bpm.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;

import com.human.bpm.entity.BpmProcessDefinition;
import com.human.utils.PageView;

public interface BpmProcessDefinitionService {
    /**
     * 部署流程定义
     * @param name 流程定义的名称
     * @param inputStream 部署ZIP输入流
     */
    public String deployFromZip(String name,InputStream inputStream);
    
    
    /**
     * 根據流程定义ID查找流程定义
     * @param processDefinitionId
     * @return ProcessDefinition
     */
    public ProcessDefinition findProcessDefinition(String processDefinitionId);
    
    
    /**
     * 根據流程实例ID查找流程定义
     * @param processDefinitionId
     * @return ProcessDefinition
     */
    public ProcessDefinition findProcessDefinitionByPid(String processInstanceId);
    
    /**
     * 分页查找所有流程定义
     * @return List<ProcessDefinition>
     */
    public PageView findAllProcessDefinition(PageView pageView,BpmProcessDefinition bpmProcessDefinition);
    
    /**
     * 查找所有流程定义
     * @return List<ProcessDefinition>
     */
    public List<BpmProcessDefinition> findAllProcessDef();
    
    /**
     * 根据流程定义部署ID获取流程图
     */
    public BpmnModel getBpmnModelByPdid(String pdId);
    
    /**
     * 根据流程定义ID获取所有任务
     */
    public List<ActivityImpl> getActivities(String processDefinitionId);
}
