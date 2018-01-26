package com.ls.spt.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ls.spt.manager.dao.ResourceDao;
import com.ls.spt.manager.entity.Resources;
import com.ls.spt.manager.entity.TeacherUser;
import com.ls.spt.manager.service.TeacherUserService;



/**
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 * @author liuwei 
 * 2016.07-12
 * @Email:liuwei@xdf.cn
 * @version 1.0v
 */
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	@Resource
	private ResourceDao resourceDao ;
		
	@Resource
    private TeacherUserService teacherUserService;
	
	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		//取得用户的权限
	    TeacherUser tu=new TeacherUser();
        tu.setPhone(username);          
        tu.setStatus("1");
        // 验证用户账号与密码是否正确
        TeacherUser user= teacherUserService.queryUser(tu);
        if (user==null) {
            throw new UsernameNotFoundException(username+" not exist!");  
        }		  
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
        // 封装成spring security的user
        MyUser userdetail = new MyUser(user.getId(), user.getPassword(), user.getName(), user.getPhone(), grantedAuths, true, true, true, true);
        return userdetail;
	}

	
	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(TeacherUser user) {
		List<Resources> resources = resourceDao.getResourcesByUserName(String.valueOf(user.getPhone()));
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}
}