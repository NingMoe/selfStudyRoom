package com.human.manager.service;

import java.util.List;
import java.util.Map;

import com.human.manager.entity.UserRole;
import com.human.manager.entity.Users;
import com.human.utils.PageView;

public interface UserService {
	
    List<Users> queryUser(Users user);
    
	PageView query(PageView pageView, Users managerUser);
	
	/**
	 * 根据用户Id更新用户状态
	 * @param deleteIds
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(String deleteIds, Integer status);

	
	void add(Users managerUser);

	/**
	 * 更新帐号信息
	 * @param user
	 */
	void editLogInUserById(Users user);
	
	   /**
     * 更新用户资料信息
     * @param user
     */
	void updateMkUser(Users user);

    void saveUserRole(UserRole userRole,String roleIds);

    /**
     * 查询用户具有的角色
     * @param userId
     * @return
     */
    List<UserRole> getUserRole(Long userId);
    
    /**
     * 查询用户具有的角色
     * @param userId
     * @return
     */
    List<UserRole> getUserRole(String email);
    
    /**
     * 获取当天转正员工
     */
    List<Users> getNowPositiveUsers();

}
