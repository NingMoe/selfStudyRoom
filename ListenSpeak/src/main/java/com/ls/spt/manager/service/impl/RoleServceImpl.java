package com.ls.spt.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ls.spt.basic.entity.PageView;
import com.ls.spt.manager.dao.RoleDao;
import com.ls.spt.manager.entity.ResRole;
import com.ls.spt.manager.entity.Resources;
import com.ls.spt.manager.entity.Role;
import com.ls.spt.manager.service.RoleService;
import com.ls.spt.utils.Common;

@Transactional
@Service
public class RoleServceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	@Override
	public PageView query(PageView pageView, Role role) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", role);
		List<Role> list = roleDao.query(map);
		pageView.setData(list);
		return pageView;
	}
	
	 @Override
    public int updateStatus(String deleteIds, Integer status) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("ids", deleteIds.split(","));
        paraMap.put("status", status);
        paraMap.put("updateUser", Common.getMyUser().getUserid());
        return roleDao.updateStatusByIds(paraMap);
    }

    @Override
    public List<Role> queryRole(Role role) {
        return roleDao.queryRole(role);
    }

    @Override
    public void add(Role role) {
        roleDao.insert(role);
    }
    
    @Override
    public void modify(Role role) {
        role.setUpdateUser(Common.getMyUser().getUserid());
        roleDao.updateByPrimaryKey(role);
    }

    @Override
    public List<Resources> getRoleRes(Role role) {
        return roleDao.getRoleRes(role);
    }
    
    @Override 
    public void saveRoleRes(Long roleId, List<String> list) {
        try {
            ResRole roleRes=new ResRole();
            roleRes.setRoleId(roleId);
            roleDao.deleteRoleRes(roleRes);
            for (String resourceId : list) {
                ResRole resRole = new ResRole();
                resRole.setRoleId(roleId);
                resRole.setResId(resourceId);
                roleDao.saveRoleRes(resRole);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
