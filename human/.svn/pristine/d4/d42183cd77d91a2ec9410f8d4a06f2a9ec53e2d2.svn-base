package com.human.bpm.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.human.bpm.entity.BpmProcessDefinition;
import com.human.bpm.service.BpmProcessDefinitionService;
import com.human.utils.PageView;

@Service
public class BpmProcessDefinitionServiceImpl implements BpmProcessDefinitionService{
    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected HistoryService historyService;
    
    @Override
    public String deployFromZip(String name, InputStream inputStream) {
        ZipInputStream zis = new ZipInputStream(inputStream);
        Deployment deployment = repositoryService.createDeployment().name(name).addZipInputStream(zis).deploy();
        return deployment.getId();
    }
    
    @Override
    public PageView findAllProcessDefinition(PageView pageView,BpmProcessDefinition bpmProcessDefinition) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if(StringUtils.isNotEmpty(bpmProcessDefinition.getName())){
            processDefinitionQuery = processDefinitionQuery.processDefinitionNameLike("%"+bpmProcessDefinition.getName()+"%");
        }
        List<ProcessDefinition> list = processDefinitionQuery.listPage(pageView.getPageNow()-1, pageView.getPageSize());
        List<BpmProcessDefinition> records = new ArrayList<BpmProcessDefinition>();
        for(ProcessDefinition p:list){
            BpmProcessDefinition pm = new BpmProcessDefinition();
            if(p.isSuspended()){
                pm.setStatus("2");
            }else{
                pm.setStatus("1");
            }
            pm.setId(p.getId());
            pm.setKey(p.getKey());
            pm.setName(p.getName());
            pm.setVersion(p.getVersion());
            records.add(pm);
        }
        pageView.setRecords(records);
        return pageView;
    }
    
    @Override
    public List<BpmProcessDefinition> findAllProcessDef() {
        List<BpmProcessDefinition> records = new ArrayList<BpmProcessDefinition>();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> list = processDefinitionQuery.active().list();
        for(ProcessDefinition p:list){
            BpmProcessDefinition pm = new BpmProcessDefinition();
            if(p.isSuspended()){
                pm.setStatus("2");
            }else{
                pm.setStatus("1");
            }
            pm.setId(p.getId());
            pm.setKey(p.getKey());
            pm.setName(p.getName());
            pm.setVersion(p.getVersion());
            records.add(pm);
        }
        return records;
    }
    
    @Override
    public ProcessDefinition findProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        return processDefinition;
    }
    
    @Override
    public ProcessDefinition findProcessDefinitionByPid(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = findProcessDefinition(processDefinitionId);
        return processDefinition;
    }
    
    @Override
    public List<ActivityImpl> getActivities(String processDefinitionId) {
        RepositoryServiceImpl repositoryServiceImpl = (RepositoryServiceImpl)repositoryService;
        ProcessDefinitionEntity deployedProcessDefinition = (ProcessDefinitionEntity)repositoryServiceImpl.getDeployedProcessDefinition(processDefinitionId);
        return deployedProcessDefinition.getActivities();
    }
    
    @Override
    public BpmnModel getBpmnModelByPdid(String pdId) {
        return repositoryService.getBpmnModel(pdId);
    }
}
