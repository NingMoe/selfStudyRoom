package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.bpm.entity.BpmAction;
import com.human.recruitment.entity.PositionProcessUser;

@Repository
public interface PositionProcessUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionProcessUser record);

    PositionProcessUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionProcessUser record);

    int insertProcessUsers(List<PositionProcessUser> users);
    
    List<PositionProcessUser> getProcessUserByCondition(PositionProcessUser user);
    
    List<PositionProcessUser> getProcessUserByBpmAction(BpmAction bpmAction);
    
}