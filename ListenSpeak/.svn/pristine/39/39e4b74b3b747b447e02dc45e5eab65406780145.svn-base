package com.ls.spt.manager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ls.spt.manager.entity.ResRole;
import com.ls.spt.manager.entity.Resources;
import com.ls.spt.manager.entity.Role;

@Repository
public interface RoleDao {
	

	/**
	 * 分页查询角色
	 * @param map
	 * @return
	 */
	List<Role> query(Map<Object, Object> map);
	
	/**
     * 根据主键更新角色状态_支持批量
     * @param roleIds
     */
    int updateStatusByIds(Map<String,Object> map);
    
    /**
     * 根据角色属性查询角色
     * @param role
     * @return
     */
    List<Role> queryRole(Role role);
    
    /**
     * 新增角色
     * @param role
     * @return
     */
    int insert(Role role);
    
    /**
     * 获取角色的权限
     * @param roleCode
     * @return
     */
    List<Resources> getRoleRes(Role role);
    
    /**
     * 删除角色权限
     * @param role
     * @return
     */
    int deleteRoleRes(ResRole resRole);
    
    
    /**
     * 保存角色权限信息
     * @param resRole
     */
    void saveRoleRes(ResRole resRole);
    
    
    /**
     * 更新角色信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

}