package com.human.manager.service;


import java.util.List;

import com.human.manager.entity.Resources;
import com.human.manager.entity.Role;
import com.human.utils.PageView;

public interface RoleService {

	PageView query(PageView pageView, Role role);
	
	int updateStatus(String deleteIds, Integer status);

    List<Role> queryRole(Role role);
    
    void add(Role role);
    
    void modify(Role role);
	
    List<Resources> getRoleRes(Role role);
    
    void saveRoleRes(Long roleId, List<String> list);
    

}
