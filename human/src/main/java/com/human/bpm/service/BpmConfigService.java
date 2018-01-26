package com.human.bpm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.human.bpm.entity.BpmAction;
import com.human.bpm.entity.BpmNodeConfig;
import com.human.bpm.entity.BpmTransitionConfig;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessUser;

@Service
public interface BpmConfigService {
    /**
     * 做自定义流程配置
     * 节点配置及流转路径配置
     * @param deployId
     */
    void doCustomConfig(String deployId);
    
    /**
     * 根据流程定义ID获取节点配置
     * @param processDef
     * @return
     */
    public List<BpmNodeConfig> getAllConfig(String processDef);
    
    /**
     * 获取流转路径
     * @param config
     * @return
     */
    public BpmTransitionConfig selectByPrimaryCondition(BpmTransitionConfig config);
    
    /**
     * 根据条件获取流转路径
     * @param config
     * @return
     */
    public BpmTransitionConfig selectByCondition(BpmTransitionConfig config);
    
    /**
     * 根据条件获取流转路径列表
     * @param config
     * @return
     */
    public List<BpmTransitionConfig> selectListByCondition(BpmTransitionConfig config);
    
    /**
     * 根据条件获取流转路径列表(过滤权限)
     * @param config assignee
     * @return
     */
    public List<BpmTransitionConfig> getListByCondition(BpmTransitionConfig config,String assignee);
    
    /**
     * 获取节点配置
     * @param flowCode
     * @param taskKey
     * @return
     */
    public PositionProcessNodeConfig getSignConfigByTaskAndFlowCode(String flowCode,String taskKey);
    
    /**
     * 根据当前节点 以及所做的操作  获取下一节点审批人
     */
    public List<PositionProcessUser> getNextAssignee(BpmAction bpmAction);
}
