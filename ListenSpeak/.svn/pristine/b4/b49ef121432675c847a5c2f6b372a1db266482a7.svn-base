package com.ls.spt.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ls.spt.manager.entity.MenuFirstList;
import com.ls.spt.manager.entity.Resources;

@Repository
public interface ResourceDao {
    
    /**
     * 获取左侧菜单
     * @param username
     * @return
     */
    List<MenuFirstList> selectMenuList(String username);
	
	/**
	 * 获取所有权限
	 * @return
	 */
	List<Resources> findAll();
	
	/**
     * 获取用户具有的权限
     * @param username
     * @return
     */
	List<Resources> getResourcesByUserName(String username);
	
	
	/**
     * 根据条件获取资源信息
     * @param resourceKey
     * @return
     */
	List<Resources> selectByKey(Resources resource);

	/**
     * 新增资源
     * @param resources
     * @return
     */
    int insert(Resources resources);

    /**
     * 根据ID查询权限信息
     * @param id
     * @return
     */
    Resources getById(Long id);

    /**
     * 编辑资源
     * @param resources
     * @return
     */
    int updateByPrimaryKey(Resources resource);
    
    /**
     * 删除资源
     * @param resourceIds
     * @return
     */
    int updateToDelete(Resources resource);
    

	
}