package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.domain.entity.UserRole;
import cn.xdf.selfStudyRoom.domain.sqlBulider.UserSqlBuilder;

@Mapper
public interface UserDao {
	
	@Delete("DELETE FROM user WHERE id =#{id}")
    int deleteByPrimaryKey(Long id);
	
	@InsertProvider(type = UserSqlBuilder.class, method = "insertSelective")
	@SelectKey(keyProperty="id", before=false, resultType=Long.class, statement = {"select LAST_INSERT_ID()"})
    int insertSelective(final User user);

	@Select("SELECT id, name, login_name AS loginName, phone, sex FROM user WHERE id = #{id}")
    User selectByPrimaryKey(Long id);

	@UpdateProvider(type=UserSqlBuilder.class,method="updateByPrimaryKeySelective") 
    int updateByPrimaryKeySelective(final User user);
	
	@Results({
	  @Result(property = "id", column = "id",id = true),
	  @Result(property = "name", column = "name"),
	  @Result(property = "loginName", column = "login_name"),
	  @Result(property = "password", column = "password"),
	  @Result(property = "phone", column = "phone")
    })
	@SelectProvider(type=UserSqlBuilder.class, method="query")
	List<User> query(final User user);


	@SelectProvider(type=UserSqlBuilder.class, method="findByLoginName")
	User findByLoginName(final User user);
	
	/**
	 * 分页查询
	 * @param user
	 * @return
	 */
	List<User> queryPageUser(User user);
	
	
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