package com.ls.spt.manager.service;

import java.util.List;
import java.util.Map;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.entity.UserRole;

public interface TeacherUserService {
    
    /**
     * 通过条件查询教师用户
     * @param tu
     * @return
     */
    TeacherUser queryUser(TeacherUser tu);
    
    /**
     * 更新教师用户信息
     * @param user
     */
    int updateByPrimaryKeySelective(TeacherUser record);
    
    /**
     * 分页查询教师用户
     * @param tu
     * @param pageView
     * @return
     */
    PageView queryUser(TeacherUser tu,PageView pageView);
    
    /**
     * 新增教师用户
     * @param tu
     * @return
     */
    Map<String, Object> add(TeacherUser tu);
    
    /**
     * 根据教师用户Id更新状态
     * @param deleteIds
     * @param status
     * @return
     */
    Map<String, Object> updateStatus(String deleteIds,Integer status);
    
    /**
     * 根据主键查询教师用户
     * @param id
     * @return
     */
    TeacherUser selectByPrimaryKey(Long id);
    
    /**
     * 更新教师用户信息
     * @param tu
     * @return
     */
    Map<String,Object> update(TeacherUser tu);
    
    
    /**
     * 查询用户具有的角色
     * @param userId
     * @return
     */
    List<UserRole> getUserRole(Long userId);
    
    /**
     * 保存用户角色
     * @param userRole
     * @param roleIds
     * @return
     */
    Map<String,Object> saveUserRole(UserRole userRole,String roleIds);
}
