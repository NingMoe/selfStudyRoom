package cn.xdf.selfStudyRoom.service;

import cn.xdf.selfStudyRoom.domain.entity.User;
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
}
