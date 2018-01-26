package com.human.recruitment.dao;

import java.util.List;
import java.util.Map;

import com.human.recruitment.entity.HrResumeEntryhandler;

public interface HrResumeEntryhandlerDao {

    int initResumeEntry(String flowCode);
    
    int updateByPrimaryKeySelective(HrResumeEntryhandler record);
    
    List<HrResumeEntryhandler> selectEntryPage(Map<String,Object> map );
    
    HrResumeEntryhandler selectByPrimaryKey(Integer id);
    
    List<HrResumeEntryhandler> selectByIdCard(String idCard);
    
    int updateStatusToPisitive(List<String> idCards);

}