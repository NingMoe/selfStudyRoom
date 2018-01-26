package com.human.security;

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

import com.human.manager.dao.ResourceDao;
import com.human.manager.entity.Resources;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;


/**
 * User userdetail该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 * 
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 * @author xiazhenyou 
 * 2016.07-12
 * @Email:xiazhenyou@xdf.cn
 * @version 1.0v
 */
public class MyUserDetailServiceImpl implements UserDetailsService {
	
	@Resource
	private ResourceDao resourceDao ;
	
	@Resource
	private UserService userService;
	// 登录验证
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.err.println("-----------MyUserDetailServiceImpl loadUserByUsername ----------- ");
		
		//取得用户的权限
	    Users u=new Users();
	    u.setLoginName(username);
	    u.setStatus(0);
		List<Users> users =userService.queryUser(u);
		if  (users.size()==0)  
            throw new UsernameNotFoundException(username+" not exist!");  
		Users us=users.get(0);
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(us);
		// 封装成spring security的user
        MyUser userdetail = new MyUser(us.getId(), us.getUserPassword(), us.getName(),us.getEmpId(),us.getLoginName(), us.getEmpSource(), us.getBusPhone(), us.getComName(),us.getCompanyId(), us.getCompanyName(),
                us.getDeptId(), us.getDeptName(), us.getSynTime(), us.getEmailAddr(), us.getEmpPhone(), us.getEthic(), us.getGraduageSch(), us.getHighestEduc(), us.getHrStatus(), us.getJobCode(),
                us.getMajor(), us.getNationalId(), us.getNationnlDesc(), us.getSex(), us.getJobZhiji(), us.getJoinDate(), us.getBirthDate(), us.getCreateUser(), us.getCreateName(), grantedAuths, true,
                true, true, true // 用户的权限
        );
        return userdetail;
    }

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(Users user) {
		List<Resources> resources = resourceDao.getResourcesByUserName(String.valueOf(user.getLoginName()));
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			// TODO:ZZQ 用户可以访问的资源名称（或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			// 关联代码：applicationContext-security.xml
			// 关联代码：com.huaxin.security.MySecurityMetadataSource#loadResourceDefine
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}
}