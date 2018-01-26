package com.human.resume.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.human.resume.entity.ResumeLanguage;


public interface ResumeLanguageService {
    
    List<ResumeLanguage> selectByResumeId(Long resumeId);
    
    Map<String,Object> insertLanguage(HttpServletRequest request, ResumeLanguage rl);
    
    ResumeLanguage selectByPrimaryKey(Long id);
    
    Map<String, Object> editLanguage(HttpServletRequest request,ResumeLanguage rl);
    
    Map<String, Object>deleteLanguage(Long id);
}
