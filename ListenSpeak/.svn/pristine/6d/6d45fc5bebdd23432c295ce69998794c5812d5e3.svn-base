package com.ls.spt.student.service;

import java.util.List;
import java.util.Map;

import com.ls.spt.manager.entity.UserRole;
import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.utils.PageView;

public interface StudentUserService {
    
    /**
     * 通过条件查询学生用户
     * @param tu
     * @return
     */
    StudentUser queryUser(StudentUser tu);
    
    /**
     * 通过unionId查询学生用户
     * @param tu
     * @return
     */
    StudentUser queryUserByUnionId(String unionId);
    
    /**
     * 更新学生用户信息
     * @param user
     */
    int updateByPrimaryKeySelective(StudentUser record);
    
    /**
     * 分页查询学生用户
     * @param tu
     * @param pageView
     * @return
     */
    PageView queryUser(StudentUser tu,PageView pageView);
    
    /**
     * 新增学生用户
     * @param tu
     * @return
     */
    Map<String, Object> add(StudentUser tu);
    
    /**
     * 根据学生用户Id更新状态
     * @param deleteIds
     * @param status
     * @return
     */
    Map<String, Object> updateStatus(String deleteIds,Integer status);
    
    /**
     * 根据主键查询学生用户
     * @param id
     * @return
     */
    StudentUser selectByPrimaryKey(Integer id);
    
    /**
     * 更新学生用户信息
     * @param tu
     * @return
     */
    Map<String,Object> update(StudentUser tu);
    
    
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
