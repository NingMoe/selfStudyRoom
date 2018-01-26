package com.human.recruitment.service;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.resume.entity.ResumeBase;
import com.human.utils.PageView;

public interface InterviewObserverService {
    
    /**
     * 分页查询
     */
    PageView query(PageView pageView,ResumeBase resumeBase);
    
    /**
     * 导出本页
     */
    Map<String, Object> exportThisPage(PageView pageView,ResumeBase resumeBase,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出选择的
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出全部
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
}
