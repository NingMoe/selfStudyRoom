package cn.xdf.selfStudyRoom.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import cn.xdf.selfStudyRoom.domain.dao.ResourcesDao;
import cn.xdf.selfStudyRoom.domain.entity.Resources;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * @author liuwei63
 *
 */
@Service
public class MySecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Resource
	private ResourcesDao resourcesDao;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	/**
	 * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行
	 * 加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> resources = resourcesDao.findAll();
			for (Resources resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				//通过资源名称来表示具体的权限 注意：必须"ROLE_"开头				
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getResUrl(), configAttributes);
			}
		}
	}
	
	/**
	 * 根据URL，找到相关的权限配置
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object  object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if (resourceMap == null) {
			loadResourceDefine();
		}
		if (requestUrl.indexOf("?") > -1) {
			requestUrl = requestUrl.substring(0, requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		return configAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {		
		return true;
	}

}
