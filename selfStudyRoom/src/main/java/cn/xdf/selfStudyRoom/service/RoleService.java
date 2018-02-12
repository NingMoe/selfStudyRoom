package cn.xdf.selfStudyRoom.service;


import java.util.List;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.domain.entity.Role;
import cn.xdf.selfStudyRoom.utils.PageView;


public interface RoleService {

	PageView query(PageView pageView, Role role);
	
	int updateStatus(String deleteIds, Integer status);

    List<Role> queryRole(Role role);
    
    void add(Role role);
    
    void modify(Role role);
	
    List<Resources> getRoleRes(Role role);
    
    void saveRoleRes(Long roleId, List<String> list);
    

}
