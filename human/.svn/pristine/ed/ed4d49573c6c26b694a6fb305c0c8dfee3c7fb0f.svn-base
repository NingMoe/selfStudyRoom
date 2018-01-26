package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.PositionProcessNodeConfig;

@Repository
public interface PositionProcessNodeConfigDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProcessId(Integer processId);
    
    int insert(PositionProcessNodeConfig record);
    
    int insertNodeConfigs(List<PositionProcessNodeConfig> nodeConfigs);

    PositionProcessNodeConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionProcessNodeConfig record);
    
    int updateByPrimaryKey(PositionProcessNodeConfig record);
    
    PositionProcessNodeConfig selectByProcessIdAndNodeId(PositionProcessNodeConfig nodeConfig);
    
    PositionProcessNodeConfig getPassNumByTaskAndFlowCode(PositionProcessNodeConfig nodeConfig);
}