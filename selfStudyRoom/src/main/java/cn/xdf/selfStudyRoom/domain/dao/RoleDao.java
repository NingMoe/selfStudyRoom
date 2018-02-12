package cn.xdf.selfStudyRoom.domain.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import cn.xdf.selfStudyRoom.domain.entity.ResRole;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.domain.entity.Role;


@Mapper
public interface RoleDao {
	
	/**
	 * 分页查询角色
	 * @param map
	 * @return
	 */
	List<Role> query(Role role);
	
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