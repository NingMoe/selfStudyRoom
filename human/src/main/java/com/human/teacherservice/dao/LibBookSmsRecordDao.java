package com.human.teacherservice.dao;

import org.springframework.stereotype.Repository;
import com.human.teacherservice.entity.LibBookSmsRecord;

@Repository
public interface LibBookSmsRecordDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(LibBookSmsRecord record);

    LibBookSmsRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(LibBookSmsRecord record);
    
    LibBookSmsRecord selectByBorrowId(Integer borrowId);

}