package com.human.basic.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.basic.entity.ClassStudentInfo;
import com.human.basic.entity.ClassBizBatch;

@Repository
public interface XdfClassOrderInfoDao {
    int insert(ClassBizBatch record);

    ClassBizBatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClassBizBatch record);
    
    int insertOrderItems(List<ClassStudentInfo> orderBatchItemDtos);
    
}