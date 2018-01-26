package com.human.recruitment.service;

import java.util.List;

import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionProcessNodeConfig;
import com.human.recruitment.entity.PositionProcessScoreItem;
import com.human.recruitment.entity.PositionProcessUser;

public interface PositionProcessService {
    
    public PositionProcess getPositionProcessById(Integer id);
    
    public PositionProcess getPositionProcessByPrimaryCondition(PositionProcess positionProcess);
    
    public void addPositionProcess(PositionProcess positionProcess);
    
    public void editPositionProcess(PositionProcess positionProcess);
    
    public void disablePositionProcess(PositionProcess positionProcess);
    
    public int addProcessUser(PositionProcessUser user);
    
    public int delProcessUser(Integer id);
    
    public List<PositionProcessUser> getProcessUserByCondition(PositionProcessUser processUser);
    
    /**
     * 更新流程节点配置配置
     * @param nodeConfig
     */
    public int updatePositionNodeConfig(PositionProcessNodeConfig nodeConfig);
    
    public PositionProcessNodeConfig selectNodeConfigByProcessIdAndNodeId(PositionProcessNodeConfig nodeConfig);
    
    public PositionProcessNodeConfig selectNodeConfigById(Integer configId);
    
    public List<PositionProcessScoreItem> getScoreItemsByProcessId(Integer positionProcessId);
    
    /*
     * 通过部门获取流程职位
     */
    List<PositionProcess> getListByDeptId(String deptId);
    
}
