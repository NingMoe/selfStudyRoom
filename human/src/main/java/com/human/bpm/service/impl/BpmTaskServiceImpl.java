package com.human.bpm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.RuntimeServiceImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.bpm.entity.BpmTaskActivity;
import com.human.bpm.entity.BpmTaskInfo;
import com.human.bpm.service.BpmTaskService;

@Service
public class BpmTaskServiceImpl implements BpmTaskService{
    
    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected HistoryService historyService;
    
    @Autowired
    protected TaskService taskService;
    
    @Autowired
    protected IdentityService identityService;
    
    @Override
    public ProcessInstance start(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        ProcessInstance processInstance = startOnly(processDefinitionKey, businessKey, variables);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        taskService.complete(task.getId());
        return processInstance;
    }
    
    
    
    @Override
    public ProcessInstance startOnly(String processDefinitionKey, String businessKey, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
    }
    
    @Override
    @Transactional
    public void complete(String processInstanceId, String taskId, String comment, Map<String, Object> variables) {
        taskService.complete(taskId, variables);
        if(StringUtils.isNotEmpty(comment)){
            taskService.addComment(taskId, processInstanceId, comment);
        }
    }
    
    @Override
    public void deleteTask(String taskId, String deleteReason) {
        taskService.deleteTask(taskId, deleteReason);
    }
    
    @Override
    public void claim(String taskId, String userId) {
        taskService.claim(taskId, userId);
        //identityService.createGroupQuery().groupId(paramString)
    }
    
    @Override
    public Task getTask(String taskId) {
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }
    
    @Override
    public ProcessInstance getProcIns(String procInsId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(procInsId).singleResult();
    }
    
    @Override
    public List<BpmTaskActivity> getTaskActivities(String processInstanceId) {
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String cuAid = taskService.createNativeTaskQuery()
        .sql("select * from act_ru_task where PROC_INST_ID_ = '"+processInstanceId+"' ").list().get(0).getTaskDefinitionKey();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        Map<String,BpmTaskActivity> actMap = new HashMap<String, BpmTaskActivity>();
        List<HistoricActivityInstance> actlist = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().desc().list();        
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).list();
        Map<String,HistoricTaskInstance> taskMap = new HashMap<String, HistoricTaskInstance>();
        for (HistoricTaskInstance ht : taskList) {
            taskMap.put(ht.getId(), ht);
        }
        
        for (HistoricActivityInstance hai : actlist) {
            String activityId = hai.getActivityId();
            ActivityImpl activityImpl = (ActivityImpl)getActivity(processDefinitionId,activityId);
            BpmTaskActivity ta = actMap.get(activityId);
            if(ta == null){
                ta = new BpmTaskActivity();
                actMap.put(activityId, ta);
                
                ta.setActivityId(activityImpl.getId());
                ta.setX(activityImpl.getX());
                ta.setY(activityImpl.getY());
                ta.setWidth(activityImpl.getWidth());
                ta.setHeight(activityImpl.getHeight());
                
                String type = (String)activityImpl.getProperty("type");
                ta.setType(type);
                
                ta.setTasks(new ArrayList<BpmTaskInfo>());
            }
            String assignee = hai.getAssignee();
            Date startTime = hai.getStartTime();
            Date endTime = hai.getEndTime();
            
            BpmTaskInfo ti = new BpmTaskInfo();
            ta.getTasks().add(ti);
            if(hai.getActivityId().equals(cuAid)){
                ta.setCurTask(true);
            }
            ti.setStartTime(DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm:ss"));
            
            if(endTime!=null){
                ti.setEndTime(DateFormatUtils.format(endTime, "yyyy-MM-dd HH:mm:ss"));
            }
            if(StringUtils.equals("userTask", ta.getType())){//如果是用户任务则查询人员
                String taskId = hai.getTaskId();
                HistoricTaskInstance ht = taskMap.get(taskId);
                ti.setDeleteReason(ht.getDeleteReason());
                
                if(ti.getEndTime()==null && ht.getEndTime()!=null){
                    ti.setEndTime(DateFormatUtils.format(ht.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                }
                
                if(ta.isCurTask()&&StringUtils.isBlank(assignee)){
                    List<HistoricIdentityLink> identityLinks = historyService.getHistoricIdentityLinksForTask(taskId);
                    
                    List<String> assigneeList = new ArrayList<String>();
                    List<String> assigneeNameList = new ArrayList<String>();
                    
                    for (HistoricIdentityLink identityLink : identityLinks) {
                        
                        if(StringUtils.equalsIgnoreCase(identityLink.getType(), IdentityLinkType.CANDIDATE)){
                            
                            String groupId = identityLink.getGroupId();
                            List<org.activiti.engine.identity.User> userList = identityService.createUserQuery().memberOfGroup(groupId).list();                             
                            for (org.activiti.engine.identity.User user: userList) {
                                String userId = user.getId();
                                assigneeList.add(userId);
                                assigneeNameList.add(user.getLastName());
                            }
                        }
                    }
                    ti.setAssignee(StringUtils.join(assigneeList,","));
                    ti.setAssigneeName(StringUtils.join(assigneeNameList,","));
                
                }else{
                    ti.setAssignee(assignee);
                    ti.setAssigneeName(assignee);
                }
            }
        }
        return new ArrayList<BpmTaskActivity>(actMap.values());
    }
    
    private PvmActivity getActivity(String processDefinitionId,String activityId){
        RepositoryServiceImpl repositoryServiceImpl = (RepositoryServiceImpl)repositoryService;
        ReadOnlyProcessDefinition deployedProcessDefinition = repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
        return deployedProcessDefinition.findActivity(activityId);
    }
    
    /** 
     *  
     * @param currentTaskEntity 
     *            当前任务节点 
     * @param targetTaskDefinitionKey 
     *            目标任务节点（在模型定义里面的节点名称） 
     * @throws Exception 
     */  
    private void jump(final TaskEntity currentTaskEntity, String targetTaskDefinitionKey){
        String processDefId = currentTaskEntity.getProcessDefinitionId();
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(processDefId);  
        String s = currentTaskEntity.getEventName();
        
        final ActivityImpl activity =  pde.findActivity(targetTaskDefinitionKey);  
  
        final ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery()  
                .executionId(currentTaskEntity.getExecutionId()).singleResult();  
  
        //包装一个Command对象  
        ((RuntimeServiceImpl) runtimeService).getCommandExecutor().execute(  
            new Command<java.lang.Void>()  
            {  
                @Override  
                public Void execute(CommandContext commandContext)  
                {  
                    //创建新任务  
                    execution.setActivity(activity);  
                    execution.executeActivity(activity);  
  
                    //删除当前的任务  
                    //不能删除当前正在执行的任务，所以要先清除掉关联  
                    currentTaskEntity.setExecutionId(null);  
                    taskService.saveTask(currentTaskEntity);  
                    taskService.deleteTask(currentTaskEntity.getId(), true);  
  
                    return null;  
                }  
            });  
    }  
}
