package com.human.bpm.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.MultiInstanceActivityBehavior;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.human.bpm.dao.BpmNodeConfigDao;
import com.human.bpm.dao.BpmTransitionConfigDao;
import com.human.bpm.entity.BpmAction;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.bpm.entity.BpmTransitionConfig;
import com.human.bpm.service.BpmConfigService;
import com.human.recruitment.dao.PositionProcessNodeConfigDao;
import com.human.recruitment.dao.PositionProcessUserDao;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessUser;

@Service
@Component("bpmConfig")
public class BpmConfigServiceImpl implements BpmConfigService{
    
    @Autowired
    RepositoryService repositoryService;
    
    @Autowired
    BpmNodeConfigDao nodeDao;
    
    @Autowired
    BpmTransitionConfigDao transitionDao;
    
    @Autowired
    private PositionProcessNodeConfigDao nodeConfigDao;
    
    @Autowired
    private PositionProcessUserDao processUserDao;
    
    @Override
    @Transactional
    public void doCustomConfig(String deployId) {
        // TODO Auto-generated method stub
        ProcessDefinition  processDef = repositoryService.createProcessDefinitionQuery().deploymentId(deployId).list().get(0);
        String processdefId = processDef.getId();
        BpmnModel model = repositoryService.getBpmnModel(processdefId); 
        List<BpmNodeConfig> nodes = new ArrayList<BpmNodeConfig>();
        List<BpmTransitionConfig> transitions = new ArrayList<BpmTransitionConfig>();
        if(model != null) {  
            Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();  
            for(FlowElement e : flowElements) {  
                if(e.getClass().toString().indexOf("UserTask")>0){
                    BpmNodeConfig node = new BpmNodeConfig();
                    node.setNodeId(e.getId());
                    node.setNodeName(e.getName());
                    node.setProcessdefId(processdefId);
                    node.setProcessName(processDef.getName());
                    nodes.add(node);
                }
            }  
        } 
        
        RepositoryServiceImpl repositoryServiceImpl = (org.activiti.engine.impl.RepositoryServiceImpl) repositoryService;
        
        ProcessDefinitionEntity pde = (ProcessDefinitionEntity) (repositoryServiceImpl.getDeployedProcessDefinition(processdefId));
        List<ActivityImpl> ais = pde.getActivities();
        
        for(ActivityImpl a:ais){
            if(a.getProperty("type").toString().equals("userTask")){
                String nodeType = "";
                String assigneeExpression = "";
                if(a.getProperty("multiInstance")!=null){
                    nodeType = BpmNodeConfig.NODETYPE_M;
                    MultiInstanceActivityBehavior m = (MultiInstanceActivityBehavior)a.getActivityBehavior();
                    assigneeExpression = m.getCollectionExpression().toString();
                }else{
                    TaskDefinition td =(TaskDefinition)a.getProperty("taskDefinition");
                    td.getCandidateGroupIdExpressions().toString();
               
                    if(td!=null && !td.getCandidateGroupIdExpressions().isEmpty()){
                        nodeType = BpmNodeConfig.NODETYPE_G;
                        String temp = td.getCandidateGroupIdExpressions().toString();
                        assigneeExpression = temp.substring(1,temp.length()-1);
                    }else{
                        nodeType = BpmNodeConfig.NODETYPE_P;
                        if(td.getAssigneeExpression()!=null){
                            assigneeExpression = td.getAssigneeExpression().toString();
                        }
                    }
                }
                for(BpmNodeConfig node : nodes){
                    if(node.getNodeId().equals(a.getId())){
                        if(StringUtils.isEmpty(node.getAssigneeExp())&&StringUtils.isNoneEmpty(assigneeExpression)){
                            if(nodeType.equals(BpmNodeConfig.NODETYPE_G)){
                                node.setAssigneeExp(assigneeExpression.substring(assigneeExpression.indexOf("}")+1,assigneeExpression.length()-1));
                            }else{
                                node.setAssigneeExp(assigneeExpression.substring(2, assigneeExpression.length()-1));
                            }
                            node.setNodeType(nodeType);
                        }
                        
                    }
                }
            }
            
            if(a.getProperty("type").toString().equals("exclusiveGateway")){
                String sourceNode = a.getIncomingTransitions().get(0).getSource().getId();
                List<PvmTransition> pts = a.getOutgoingTransitions();
                for(PvmTransition p:pts){
                    BpmTransitionConfig transition = new BpmTransitionConfig();
                    transition.setTransitionId(p.getId());
                    if(a.getProperty("name")!=null){
                        transition.setTransitionName(p.getProperty("name").toString());
                    }
                    transition.setProcessdefId(processdefId);
                    transition.setProcessName(processDef.getName());
                    transition.setSourceNode(sourceNode);
                    transition.setTargetNode(p.getDestination().getId());
                    if(p.getProperty("conditionText")!=null){
                        transition.setConditionText(p.getProperty("conditionText").toString());
                    }
                    transitions.add(transition);
                }
            }
            
           
        }
        for(BpmTransitionConfig ts :transitions){
            transitionDao.insert(ts);
        }
        for(BpmNodeConfig no:nodes){
            nodeDao.insertSelective(no);
        }
    }
    
    @Override
    public List<BpmNodeConfig> getAllConfig(String processDef) {
        return nodeDao.getAllConfig(processDef);
    }
    
    @Override
    public BpmTransitionConfig selectByPrimaryCondition(BpmTransitionConfig config) {
        // TODO Auto-generated method stub
        return transitionDao.selectByPrimaryCondition(config);
    }
    
    @Override
    public BpmTransitionConfig selectByCondition(BpmTransitionConfig config) {
        // TODO Auto-generated method stub
        List<BpmTransitionConfig> configs =  transitionDao.selectByCondition(config);
        if(configs!=null && configs.size()>0){
            return configs.get(0);
        }
        return null;
    }
    
    @Override
    public List<BpmTransitionConfig> selectListByCondition(BpmTransitionConfig config) {
        return transitionDao.selectByCondition(config);
    }
    
    @Override
    public List<BpmTransitionConfig> getListByCondition(BpmTransitionConfig config, String assignee) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public PositionProcessNodeConfig getSignConfigByTaskAndFlowCode(String flowCode, String taskKey) {
        // TODO Auto-generated method stub
        PositionProcessNodeConfig nodeConfig = new PositionProcessNodeConfig();
        nodeConfig.setFlowCode(flowCode);
        nodeConfig.setNodeId(taskKey);
        return nodeConfigDao.getPassNumByTaskAndFlowCode(nodeConfig);
    }
    
    @Override
    public List<PositionProcessUser> getNextAssignee(BpmAction bpmAction) {
        // TODO Auto-generated method stub
        return processUserDao.getProcessUserByBpmAction(bpmAction);
    }
}
