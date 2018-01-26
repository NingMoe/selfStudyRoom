package com.ls.spt.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.entity.UserRole;

@Repository
public interface TeacherUserDao {
    
    int deleteByPrimaryKey(Long id);
    
    int insertSelective(TeacherUser record);

    TeacherUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TeacherUser record);
    
    /**
     * 根据条件查找教师用户
     * @param username
     * @return
     */
    TeacherUser queryUser(TeacherUser tu);
    
    
    /**
     * 分页查询教师用户
     * @param map
     * @return
     */
    List<TeacherUser> query(Map<Object, Object> map);
    
    /**
     * 根据用户ID更新用户状态
     * @param paraMap
     * @return
     */
    int updateByIds(Map<String, Object> paraMap);
    
    
    /**
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    List<UserRole> getUserRole(UserRole ur);
    
    
    /**
     * 删除用户角色
     * @param userId
     * @return
     */
    int delUserRole(UserRole userRole);

    /**
     * 保存用户角色对应关系
     * @param ur
     * @return
     */
    int saveUserRole(UserRole ur);

}