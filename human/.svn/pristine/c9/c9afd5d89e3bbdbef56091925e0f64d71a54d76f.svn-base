package com.human.activity.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.activity.entity.ActivityInfo;
import com.human.utils.PageView;

public interface ActivityInfoService {
    /**
     * 分页查询
     * @param pageView
     * @param info
     * @return
     */
    PageView queryActivityByPage(PageView pageView,ActivityInfo info);
    
    /**
     * 新增活动
     * @param jsonStrings
     * @param request
     * @return
     */
    Map<String, Object> addActivity(String jsonStrings,HttpServletRequest request);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ActivityInfo selectByPrimaryKey(Long id);
    /**
     * 编辑活动
     * @param jsonStrings
     * @param request
     * @return
     */
    Map<String, Object> editActivity(String jsonStrings,HttpServletRequest request);
    
    /**
     * 导出活动列表
     * @param ids
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response);
    /**
     * 导出活动支付明细
     * @param ids
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportSelectPayInfo(Long activityId, HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 活动封账
     * @param deleteIds
     * @return
     */
    Map<String, Object> closeAccount(String deleteIds);
}
