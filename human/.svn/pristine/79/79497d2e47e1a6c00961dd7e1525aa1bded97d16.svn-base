package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;

import com.human.recruitment.entity.HrInterviewRecord;
import com.human.recruitment.entity.HrPositiveRecord;

public interface HrPositiveRecordDao {
    int deleteByPrimaryKey(Integer id);

    int initPositiveRecord();

    HrPositiveRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrPositiveRecord record);
    
    int insertPositiveRecords();
    
    List<HrPositiveRecord> selectInitRecords();
    
    List<HrPositiveRecord> selectPositiveRecordPage(Map<Object,Object> map);
    
    HrInterviewRecord getInterviewRecord(Integer positiveId);
    
    int updateInterviewRecord(HrInterviewRecord record);

}