package com.human.sign.service;

import java.util.Map;
import com.human.sign.entity.SignActivity;
import com.human.utils.PageView;

public interface SignActivityService {
    /**
     * 分页查询
     * @param pageView
     * @param info
     * @return
     */
    PageView queryActivityByPage(PageView pageView,SignActivity info);
    
    /**
     * 新增签名活动
     * @param info
     * @return
     */
    Map<String, Object> addActivity(SignActivity info);
    
    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SignActivity selectByPrimaryKey(Long id);
    
    /**
     * 编辑签名活动
     * @param jsonStrings
     * @param request
     * @return
     */
    Map<String, Object> editActivity(SignActivity info);
    
    /**
     * 根据时间戳查询
     * @param activityTime
     * @return
     */
    SignActivity selectByActivityTime(Long activityTime);
    
    /**
     * 根据主键查询签到率明细
     * @param id
     * @return
     */
    Map<String, Object> selectById(Long id);
    
}
