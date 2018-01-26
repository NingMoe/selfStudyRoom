package com.human.manager.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.UserRole;
import com.human.manager.entity.Users;
import com.human.security.MyUser;

@Repository
public interface UserDao  {

	/**
	 * 根据条件查找用户
	 * @param username
	 * @return
	 */
	List<Users> queryUser(Users user);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	List<Users> query(Map<Object, Object> map);
	
	/**
	 * 根据用户ID更新用户状态
	 * @param paraMap
	 * @return
	 */
	int updateByIds(Map<String, Object> paraMap);
	/**
     * 插入用户
     * @param record
     * @return
     */
    int insertUser(Users record);
	
	/**
	 * 插入用户扩展信息
	 */
    int inserMkUser(Users user);
    
    /**
     * 根据ID更新用户登陆信息
     * @param record
     * @return
     */
    int editLogInUserById(Users record);
	
    /**
     * 根据ID更新用户扩展表信息
     * @param record
     * @return
     */
    int editUserExt(Users record);
    
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
     * 根据用户ID获取角色列表
     * @param userId
     * @return
     */
    List<UserRole> getUserRole(UserRole ur);

    /**
     * 更新用户资料
     * @param user
     * @return
     */
    int updateMkUser(Users user);

    /**
     * 获取当天转正员工
     */
    List<Users> getNowPositiveUsers();
    
	
}
