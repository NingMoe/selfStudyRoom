package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.xdf.selfStudyRoom.domain.entity.Resources;

@Mapper
public interface ResourcesDao {
	/**
     * 获取用户具有的权限
     * @param username
     * @return
     */
	@Select("SELECT r.* FROM resources r "
			+ " INNER JOIN resources_role rr ON rr.resc_id=r.id "
			+ " INNER JOIN user_role ur ON ur.role_id=rr.role_id "
			+ " INNER JOIN user u ON u.id=ur.user_id AND u.status=1 "
			+ " WHERE r.status =0 AND u.login_name=#{username}")
	List<Resources> getResourcesByLoginName(String username);
	
	/**
	 * 查询所有菜单
	 * @return
	 */
	@Select("SELECT r.id,r.name,r.parentId,r.resKey,r.type,"
			+ " r.resUrl,r.resc_desc,r.createTime,r.createUserId,"
			+ " r.update_time,r.update_user,r.sort,r.icon,r1.name parentName "
			+ " FROM resources r "
			+ " LEFT JOIN resources r1 ON r1.id=r.parentId "
			+ " where r.status =0 ORDER BY sort,id")
	List<Resources> findAll();
}
