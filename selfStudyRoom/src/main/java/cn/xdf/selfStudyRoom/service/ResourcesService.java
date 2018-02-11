package cn.xdf.selfStudyRoom.service;

import java.util.List;
import cn.xdf.selfStudyRoom.domain.entity.MenuFirstList;
import cn.xdf.selfStudyRoom.domain.entity.Resources;

public interface ResourcesService {
	/**
     * 获取用户具有的权限
     * @param loginName
     * @return
     */
	List<Resources> getResourcesByLoginName(String loginName);
	/**
	 * 获取用户的一级菜单
	 * @param username
	 * @return
	 */
	List<MenuFirstList> selectMenuList(String username);
	
	/**
	 * 查询所有菜单树
	 * @return
	 */
	List<Resources> findAll();
	
	/**
	 * 根据条件获取资源信息
	 * @param resource
	 * @return
	 */
	List<Resources> selectByKey(Resources resource);
	
	/**
	 * 增加资源
	 * @param resources
	 */
    void add(Resources resources);
    
    /**
     * 根据Id查询
     * @param id
     * @return
     */
    Resources getById(Long id);
    
    /**
     * 修改资源
     * @param resource
     */
    void modify(Resources resource);
    
    /**
     * 删除资源
     * @param resource
     */
    void delete(Resources resource);
}
