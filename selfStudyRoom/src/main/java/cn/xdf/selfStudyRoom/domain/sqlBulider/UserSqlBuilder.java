package cn.xdf.selfStudyRoom.domain.sqlBulider;

import org.apache.ibatis.jdbc.SQL;
import cn.xdf.selfStudyRoom.domain.entity.User;

public class UserSqlBuilder {
	
	public String insertSelective(final User user){
	   SQL sql= new SQL(){{
		   INSERT_INTO("user");
		    if(user.getId()!=null){
				VALUES("id","#{id,jdbcType=BIGINT}");
			}
			if(user.getName()!=null){
				VALUES("name", "#{name,jdbcType=VARCHAR}");
			}
			if(user.getLoginName()!=null){
				VALUES("login_name", "#{loginName,jdbcType=VARCHAR}");
			}
			if(user.getPassword()!=null){
				VALUES("password", "#{password,jdbcType=VARCHAR}");
			}
			if(user.getPhone()!=null){
				VALUES("phone", "#{phone,jdbcType=VARCHAR}");
			}
			if(user.getCreateTime()!=null){
				VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
			}
			if(user.getCreateUser()!=null){
				VALUES("create_user", "#{createUser,jdbcType=VARCHAR}");
			}
			if(user.getUpdateTime()!=null){
				VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
			}
			if(user.getUpdateUser()!=null){
				VALUES("update_user", "#{updateUser,jdbcType=VARCHAR}");
			}
			if(user.getStatus()!=null){
				VALUES("status", "#{status,jdbcType=VARCHAR}");			
			}
		   
	   }};
	   System.out.println("sql=="+sql.toString());
	   return sql.toString();
	
	}
	
	public String updateByPrimaryKeySelective(final User user){	
	    return new SQL(){{
	    	UPDATE("user");
	    	if(user.getName()!=null){
				SET("name=#{name,jdbcType=VARCHAR}");
			}
	    	if(user.getLoginName()!=null){
	    		SET("login_name=#{loginName,jdbcType=VARCHAR}");
			}
			if(user.getPassword()!=null){
				SET("password=#{password,jdbcType=VARCHAR}");
			}
			if(user.getPhone()!=null){
				SET("phone=#{phone,jdbcType=VARCHAR}");
			}
			if(user.getCreateTime()!=null){
				SET("create_time=#{createTime,jdbcType=TIMESTAMP}");
			}
			if(user.getCreateUser()!=null){
				SET("create_user=#{createUser,jdbcType=VARCHAR}");
			}
			if(user.getUpdateTime()!=null){
				SET("update_time=#{updateTime,jdbcType=TIMESTAMP}");
			}
			if(user.getUpdateUser()!=null){
				SET("update_user=#{updateUser,jdbcType=VARCHAR}");
			}
			if(user.getStatus()!=null){	
				SET("status=#{status,jdbcType=VARCHAR}");
			}
			WHERE("id=#{id,jdbcType=BIGINT}");
	    }}.toString();
    }
	
	public String query(final User user){
		return new SQL(){{
			SELECT("id, name, login_name, password, phone");  
	           FROM("user");  
	           if(user.getName() != null){  
	              WHERE("name=#{name,jdbcType=VARCHAR}");  
	           }  
	           if(user.getPhone() != null){  
	        	  WHERE("phone like CONCAT('%',#{phone,jdbcType=VARCHAR},'%')");  
	           }  
			
		}}.toString();
	}
	
	public String findByLoginName(final User user){
		return new SQL(){{
			SELECT("id, name,login_name,password,phone"); 
			FROM("user"); 
			if(user.getLoginName()!=null){
				WHERE("login_name=#{loginName,jdbcType=VARCHAR}"); 
			}
			if(user.getPassword()!=null){
				WHERE("password=#{password,jdbcType=VARCHAR}");
			}
			if(user.getStatus()!=null){
				WHERE("status=#{status,jdbcType=VARCHAR}");
			}			
		}}.toString();
	}
	
}