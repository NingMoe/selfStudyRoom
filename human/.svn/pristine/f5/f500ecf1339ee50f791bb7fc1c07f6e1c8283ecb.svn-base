package com.human.recruitment.service;


import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.recruitment.entity.InsideRecommendManagerEntity;
import com.human.utils.PageView;

public interface InsideRecommendManagerService {
    
    /**
     * 分页查询
     */
    PageView query(PageView pageView,InsideRecommendManagerEntity  irm);
    
    /**
     * 导出本页
     */
    Map<String, Object> exportThisPage(PageView pageView,InsideRecommendManagerEntity  irm,HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出选择的
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出全部
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
}
