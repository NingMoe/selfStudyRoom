package cn.xdf.selfStudyRoom.service;


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
}
