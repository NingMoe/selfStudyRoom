package com.human.recruitment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.recruitment.entity.PositionProcessScoreItem;

@Repository
public interface PositionProcessScoreItemDao {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByProcessId(Integer processId);

    int insert(PositionProcessScoreItem record);

    PositionProcessScoreItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PositionProcessScoreItem record);

    int insertScoreItems(List<PositionProcessScoreItem> scoreItemList);
    
    List<PositionProcessScoreItem> getScoreItemsByProcessId(Integer positionProcessId);
}