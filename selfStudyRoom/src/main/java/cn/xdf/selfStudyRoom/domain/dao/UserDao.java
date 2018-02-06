package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;

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
import cn.xdf.selfStudyRoom.domain.sqlBulider.UserSqlBuilder;

@Mapper
public interface UserDao {
	
	@Delete("DELETE FROM user WHERE id =#{id}")
    int deleteByPrimaryKey(Long id);
	
	@InsertProvider(type = UserSqlBuilder.class, method = "insertSelective")
	@SelectKey(keyProperty="id", before=false, resultType=Long.class, statement = {"select LAST_INSERT_ID()"})
    int insertSelective(final User user);

	@Select("SELECT * FROM user WHERE id = #{id}")
    User selectByPrimaryKey(Long id);

	@UpdateProvider(type=UserSqlBuilder.class,method="updateByPrimaryKeySelective") 
    int updateByPrimaryKeySelective(final User user);
	
	@Results({
	  @Result(property = "id", column = "id"),
	  @Result(property = "name", column = "name"),
	  @Result(property = "loginName", column = "login_name"),
	  @Result(property = "password", column = "password"),
	  @Result(property = "phone", column = "phone")
    })
	@SelectProvider(type=UserSqlBuilder.class, method="query")
	List<User> query(final User user);


	@SelectProvider(type=UserSqlBuilder.class, method="findByLoginName")
	User findByLoginName(final User user);
}