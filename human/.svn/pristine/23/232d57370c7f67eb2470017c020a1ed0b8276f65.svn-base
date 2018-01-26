package com.human.recruitment.service;

import com.human.recruitment.entity.HrEntryBase;
import com.human.recruitment.entity.HrResumeEntryhandler;
import com.human.security.MyUser;
import com.human.utils.PageView;

public interface SeekerEntryService {
    
    PageView getPositionPage(PageView pageView,HrResumeEntryhandler entryhandler,MyUser myUser);
    
    HrResumeEntryhandler selectByPrimaryKey(Integer id);
    
    HrResumeEntryhandler selectByIdCard(String idCard);
    
    int updateByPrimaryKeySelective(HrResumeEntryhandler entryhandler);
    
    HrEntryBase getBaseSeeker(Integer entryHandlerId);
    
    HrEntryBase getComplexSeeker(Integer entryHandlerId);
    
    void addSeekerDetail(HrEntryBase entryBase);
    
    void editSeekerDetail(HrEntryBase entryBase);
    
    void sendOffer(HrResumeEntryhandler entryhandler);
}
