package com.ls.spt.manager.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ls.spt.manager.entity.MessageRecord;

@Repository
public interface MessageRecordDao {

    int insertSelective(MessageRecord record);

    MessageRecord selectByPrimaryKey(Long id);

    MessageRecord getMsg(@Param(value="phone")String phone, @Param(value="time")String time);
}