package com.human.resume.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.human.resume.entity.ResumeEducationHistory;


public interface ResumeEducationHistoryService {
    
    List<ResumeEducationHistory> selectByResumeId(Long resumeId);
    
    Map<String,Object> insertEdu(HttpServletRequest request, ResumeEducationHistory reh);
    
    ResumeEducationHistory selectByPrimaryKey(Long id);
    
    Map<String, Object> editEdu(ResumeEducationHistory reh);
    
    Map<String, Object>deleteEdu(Long id);
    
    Map<String, Object>insertFastEdu(ResumeEducationHistory reh);
        
    int insertSelective(ResumeEducationHistory record);
}
