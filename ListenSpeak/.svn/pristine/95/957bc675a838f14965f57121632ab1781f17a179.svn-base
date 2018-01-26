package com.ls.spt.student.dao;

import java.util.List;
import java.util.Map;

import com.ls.spt.manager.entity.UserRole;
import com.ls.spt.student.entity.StudentUser;

public interface StudentUserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(StudentUser record);

    int insertSelective(StudentUser record);

    StudentUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StudentUser record);

    int updateByPrimaryKey(StudentUser record);
    
    /**
     * 根据条件查找教师用户
     * @param username
     * @return
     */
    StudentUser queryUser(StudentUser tu);
    
    
    /**
     * 分页查询教师用户
     * @param map
     * @return
     */
    List<StudentUser> query(Map<Object, Object> map);
    
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

    /**
     * 通过unionid获取用户
     * @param unionid
     * @return
     */
    public StudentUser selectByUnionid(String unionid);
    
    /**
     * 通过手机号获取学生信息
     * @param map
     * @return
     */
    public StudentUser selectByPhone(Map<String, Object> map);
}