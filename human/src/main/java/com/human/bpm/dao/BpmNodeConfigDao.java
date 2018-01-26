package com.human.bpm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.human.bpm.entity.BpmNodeConfig;

@Repository
public interface BpmNodeConfigDao {
    BpmNodeConfig selectByNodeId(String nodeId);
    
    int insert(BpmNodeConfig record);

    int insertSelective(BpmNodeConfig record);
    
    List<BpmNodeConfig> getAllConfig(String processDef);
    
    List<BpmNodeConfig> getMyAllNode(Long userId);
    
    
    BpmNodeConfig selectByNodeIdAndFlowCode(@Param("flowCode")String flowCode,@Param("nodeId")String nodeId);
    
}