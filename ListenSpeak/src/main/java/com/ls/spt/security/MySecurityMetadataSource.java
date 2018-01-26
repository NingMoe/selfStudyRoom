package com.ls.spt.security;

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

import com.ls.spt.manager.dao.ResourceDao;
import com.ls.spt.manager.entity.Resources;

/**
 * 加载资源与权限的对应关系
 * @author liuwei 
 * 2016.07-12
 * @Email:xiazhenyou@xdf.cn
 * @version 1.0v
 */
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Resource
	private ResourceDao resourcesDao;
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	/**
	 * 加载所有资源与权限的关系
	 * @PostConstruct是Java EE 5引入的注解，
	 * Spring允许开发者在受管Bean中使用它。当DI容器实例化当前受管Bean时，
	 * @PostConstruct注解的方法会被自动触发，从而完成一些初始化工作，
	 */
	@PostConstruct
	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> resources = resourcesDao.findAll();
			for (Resources resource : resources) {
				Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
				ConfigAttribute configAttribute = new SecurityConfig("ROLE_" + resource.getResKey());
				configAttributes.add(configAttribute);
				resourceMap.put(resource.getResUrl(), configAttributes);
			}
		}
	}
	
	//返回所请求资源所需要的权限
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
		return configAttributes;
	}


}