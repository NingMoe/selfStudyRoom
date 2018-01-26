package com.human.recruitment.service;

import java.util.List;

import com.human.recruitment.entity.HrInterviewRecord;
import com.human.recruitment.entity.HrPositiveRecord;
import com.human.utils.PageView;

public interface HrPositiveRecordService {
    public void initPositiveRecord();
    
    HrPositiveRecord selectByPrimaryKey(Integer id);
    
    PageView getPositiveRecordPage(PageView pageView,HrPositiveRecord positiveRecord);
    
    void updateRecordList(List<HrPositiveRecord> records);
    
    HrInterviewRecord getInterviewRecord(Integer positiveId);
    
    void updateInterviewRecord(HrInterviewRecord record);
}
