package com.human.recruitment.service;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.recruitment.entity.HrResumeFlow;
import com.human.recruitment.entity.InterviewEntity;
import com.human.recruitment.entity.InterviewSchedule;
import com.human.utils.PageView;

public interface InterviewService {
    
    /**
     * 分页查询面试安排
     */
    PageView query(PageView pageView,InterviewEntity interviewEntity);
    
    /**
     * 安排面试导出本页
     */
    Map<String, Object> exportThisPage(PageView pageView,InterviewEntity interviewEntity,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出选择的安排面试
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出全部安排面试
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
    
    HrResumeFlow selectByPrimaryKey(Integer id);
    
    Map<String,Object>edit(InterviewSchedule interviewSchedule);
}
