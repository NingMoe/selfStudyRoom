package com.ls.spt.manager.service;


import java.util.List;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.manager.entity.Resources;
import com.ls.spt.manager.entity.Role;

public interface RoleService {

	PageView query(PageView pageView, Role role);
	
	int updateStatus(String deleteIds, Integer status);

    List<Role> queryRole(Role role);
    
    void add(Role role);
    
    void modify(Role role);
	
    List<Resources> getRoleRes(Role role);
    
    void saveRoleRes(Long roleId, List<String> list);
    

}
