package com.human.teacherservice.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.teacherservice.entity.TeacherActManager;
import com.human.utils.PageView;

public interface TeacherActManagerService {
    
    /**
     * 按条件获取活动
     * @param managerUser
     * @return
     */
    public List<TeacherActManager> query(TeacherActManager teacherActManager);
    
    /**
     * 分页获取活动申请列表
     * @param pageView
     * @param managerUser
     * @return
     */
    public PageView query(PageView pageView, TeacherActManager teacherActManager);
    
    /**
     * 删除申请活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> deleteTeacherAct(TeacherActManager teacherActManager);
    
    /**
     * 发布新的活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> insertTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request);
    
    /**
     * 更新发布的活动
     * @param managerUser
     * @return
     */
    public Map<String, Object> updateTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request);

    
    /**
     * 活动同意
     * @param teacherActManager
     * @return
     */
    public Map<String, Object> isagreeTeacherAct(TeacherActManager teacherActManager);

    
    /**
     * 通过id获取单个活动信息
     * @param teacherActManager
     * @return
     */
    public Map<String, Object> queryTeacherActById(TeacherActManager teacherActManager);

}
