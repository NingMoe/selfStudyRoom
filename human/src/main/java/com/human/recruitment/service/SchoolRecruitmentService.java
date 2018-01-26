package com.human.recruitment.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.recruitment.entity.SchoolRecruitment;
import com.human.utils.PageView;

public interface SchoolRecruitmentService {

    /**
     * 分页查询
     */
    PageView query(PageView pageView,SchoolRecruitment sr);
    
    /**
     * 新增校园招聘活动
     * @param sr
     * @return
     */
    Map<String, Object> add(SchoolRecruitment sr);
    
    /**
     * 根据主键查询校园招聘活动
     * @param sr
     * @return
     */
    SchoolRecruitment selectByPrimaryKey(long id);
    
    /**
     * 编辑校园招聘活动
     * @param sr
     * @return
     */
    Map<String, Object> edit(SchoolRecruitment sr);
    
    /**
     * 删除校园招聘活动
     * @param deleteIds
     * @return
     */
    Map<String, Object> delete(String deleteIds);
    
    /**
     * 导出选择校园招聘活动
     * @param ids
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 导出本页校园招聘活动
     */
    Map<String, Object> exportThisPage(PageView pageView,SchoolRecruitment sr,HttpServletRequest request, HttpServletResponse response);
    
    
    /**
     * 导出全部校园招聘活动
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
}
