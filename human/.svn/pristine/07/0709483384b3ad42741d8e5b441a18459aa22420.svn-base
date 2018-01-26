package com.human.recruitment.service;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.recruitment.entity.ResumeTalent;
import com.human.utils.PageView;

public interface TalentService {
    
    /**
     * 分页查询人才库
     */
    PageView query(PageView pageView,ResumeTalent  resumeTalent);
    
    /**
     * 人才库导出本页
     */
    Map<String, Object> exportThisPage(PageView pageView,ResumeTalent  resumeTalent,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出选择的人才库
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出全部人才库
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 移出人才库
     */
    Map<String, Object> updateStatus(String deleteIds);
    
    int  deleteByResumeId(Integer resumeId);
}
