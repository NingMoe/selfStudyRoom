package com.human.jzbTest.service;

import java.util.List;

import com.human.jzbTest.entity.JzbStudentBmRecord;
import com.human.jzbTest.entity.JzbStudentDto;
import com.human.utils.PageView;

public interface JzbStudentService {
    
    PageView query(PageView pageView,JzbStudentDto jsd);
    
    List<JzbStudentBmRecord> getBmRecord(Integer paperId);
    
    String getXdfStudentCode(String studentName, String mobile, String schoolId);
    
}
