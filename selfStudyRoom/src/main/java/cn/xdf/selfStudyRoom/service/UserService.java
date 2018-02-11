package cn.xdf.selfStudyRoom.service;


import java.util.Map;

import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.utils.PageView;
/**
 * 用户服务层
 * @author liuwei63
 */
public interface UserService {
	/**
     * 根据用户的账号查找用户
     * @param user
     * @return
     */
	User findByLoginName(User user);
	
	/**
	 * 分页查询
	 * @param user
	 * @param pageView
	 * @return
	 */
	PageView queryUser(User user,PageView pageView);
	
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	Map<String, Object> add(User user);
	
	/**
	 * 根据主键查找
	 * @param id
	 * @return
	 */
	User selectByPrimaryKey(Long id);
	
	/**
	 * 编辑用户
	 * @param user
	 * @return
	 */
	Map<String,Object> update(User user);
	
	/**
	 * 更新用户状态
	 * @param deleteIds
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(String deleteIds,Integer status);
}
