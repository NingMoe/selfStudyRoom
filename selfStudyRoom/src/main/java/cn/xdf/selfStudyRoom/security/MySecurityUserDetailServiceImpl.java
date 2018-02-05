package cn.xdf.selfStudyRoom.security;

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
import org.springframework.stereotype.Component;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.domain.entity.User;
import cn.xdf.selfStudyRoom.service.ResourcesService;
import cn.xdf.selfStudyRoom.service.UserService;



@Component
public class MySecurityUserDetailServiceImpl implements UserDetailsService {

	@Resource
	private UserService userService;
	
	@Resource
	private ResourcesService resourceService;
	
	/**
	 * 登录验证
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User loginUser=new User();
		loginUser.setLoginName(userName);
		loginUser.setStatus("1");
		User user=userService.findByLoginName(loginUser);
		if(user==null){
			throw new UsernameNotFoundException("UserName " + userName + " not found"); 
		}
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
		// 封装成spring security的user
		SecurityUser userdetail = new SecurityUser(user.getId(), user.getPassword(), user.getName(),user.getLoginName(), grantedAuths, true,true, true,true);
        return userdetail;
	}
	
	/**
	 * 取得用户的权限
	 * @param user
	 * @return
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(User user) {
		List<Resources> resources = resourceService.getResourcesByLoginName(user.getLoginName());
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}

}
