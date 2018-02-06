package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import cn.xdf.selfStudyRoom.domain.entity.MenuFirstList;
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
	
	/**
	 * 查询登录用户菜单列表
	 * @param username
	 * @return
	 */
	@Select("SELECT rr1.id,rr1.name first_name,rr1.resUrl first_href,rr1.icon first_icon,rr2.id sec_id,"
			+"  rr2.name sec_name,rr2.resUrl sec_href,rr2.icon sec_icon "
			+"  FROM ("
			+"  SELECT DISTINCT r.id,r.name,r.resUrl,r.icon,r.sort "
			+"  FROM resources r" 
            +"  INNER JOIN resources_role rr "
            +"  ON r.id = rr.resc_id" 
        	+"  INNER JOIN user_role ur" 
        	+"  ON ur.role_id = rr.role_id"  
        	+"  INNER JOIN role r1 "
        	+"  ON r1.id = ur.role_id" 
        	+"  INNER JOIN user u" 
        	+"  ON u.id = ur.user_id"  
        	+"  WHERE u.login_name = #{userName,jdbcType=VARCHAR}"
			+"  AND u.status = 1 "
			+"  AND r.status = 0 "
		    +"  AND r1.status=0"
			+"  AND r.type=0  "     
			+"  ) rr1"
	        +"  JOIN ("
			+"  SELECT DISTINCT" 
			+"  r.id,r.parentId,r.name,r.icon,r.resUrl,r.sort"
			+"  FROM"
			+"  resources r " 
			+"  INNER JOIN resources_role rr "
			+"  ON r.id = rr.resc_id" 
			+"  INNER JOIN user_role ur "
			+"  ON ur.role_id = rr.role_id" 
			+"  INNER JOIN role r1 "
			+"  ON r1.id = ur.role_id " 
			+"  INNER JOIN user u "
			+"  ON u.id = ur.user_id" 
			+"  WHERE u.login_name = #{userName,jdbcType=VARCHAR}"
			+"  AND u.status = 1 " 
		    +"  AND r.status = 0 " 
			+"  AND r1.status=0"
			+"  AND r.type=1   "
			+"  ) rr2 ON rr1.id=rr2.parentId"
			+"  ORDER BY rr1.sort,rr1.id,rr2.sort,rr2.id")
	@ResultMap("cn.xdf.selfStudyRoom.domain.dao.ResourcesDao.menuResultMap")
	List<MenuFirstList> selectMenuList(String userName);
}
