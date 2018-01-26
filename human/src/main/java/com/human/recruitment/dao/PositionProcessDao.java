package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.PositionProcess;
import com.human.recruitment.entity.PositionProcessExtend;

@Repository
public interface PositionProcessDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PositionProcess record);

    PositionProcess selectByPrimaryKey(Integer id);
    
    PositionProcess getPositionProcessByPrimaryCondition(PositionProcess record);

    int updateByPrimaryKeySelective(PositionProcess record);
    
    List<PositionProcess> getListByPositionId(Integer positionId);
    
    PositionProcessExtend getPositionProcessExtend(Integer positionId);
    
    int disablePositionProcess(PositionProcess record);
    /*
     * 通过部门获取流程职位
     */
    List<PositionProcess> getListByDeptId(String deptId);
    

}