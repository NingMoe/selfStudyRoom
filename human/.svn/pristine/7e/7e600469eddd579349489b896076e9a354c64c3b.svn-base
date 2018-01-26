package com.human.resume.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.resume.entity.ResumeIntention;

public interface ResumeIntentionService {
    /*
     * 通过简历ID查询求职意向
     */
    ResumeIntention selectByResumeId(Long resumeId);
    
    /**
     * 更新求职意向
     * @param request
     * @param ri
     * @return
     */
    Map<String,Object> updateInterntion(HttpServletRequest request, ResumeIntention ri);
}
