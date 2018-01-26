package com.human.resume.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.human.resume.entity.ResumeWorkHistory;


public interface ResumeWorkHistoryService {
    
    List<ResumeWorkHistory> selectByResumeId(Long resumeId);
    
    Map<String,Object> insertWorkHistory(HttpServletRequest request, ResumeWorkHistory rwh);
    
    ResumeWorkHistory selectByPrimaryKey(Long id);
    
    Map<String, Object> editWorkHistory(ResumeWorkHistory rwh);
    
    Map<String, Object>deleteWorkHistory(Long id);
}
