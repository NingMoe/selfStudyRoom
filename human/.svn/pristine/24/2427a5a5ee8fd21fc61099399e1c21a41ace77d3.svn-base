package com.human.resume.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.human.resume.entity.ResumeProjectExperience;


public interface ResumeProjectExperienceService {
    
    List<ResumeProjectExperience> selectByResumeId(Long resumeId);
    
    Map<String,Object> insertProjectExperience(HttpServletRequest request, ResumeProjectExperience rpe);
    
    ResumeProjectExperience selectByPrimaryKey(Long id);
    
    Map<String, Object> editProjectExperience(ResumeProjectExperience rpe);
    
    Map<String, Object> deleteProjectExperience(Long id);
}
