package cn.xdf.selfStudyRoom.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.xdf.selfStudyRoom.domain.dao.RoleDao;
import cn.xdf.selfStudyRoom.domain.entity.ResRole;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.domain.entity.Role;
import cn.xdf.selfStudyRoom.service.RoleService;
import cn.xdf.selfStudyRoom.utils.CommonUtil;
import cn.xdf.selfStudyRoom.utils.PageView;


@Transactional(rollbackFor=Exception.class)
@Service
public class RoleServceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	
	@SuppressWarnings("rawtypes")
	@Override
	public PageView query(PageView pageView, Role role) {
		try{
			PageHelper.startPage(pageView.getPage(), pageView.getLimit());
			List<Role> list = roleDao.query(role);
			pageView.setData(list);
			pageView.setCount(((Page)list).getTotal());
			pageView.setCode("0");
		}catch(Exception e){
			e.printStackTrace();
			pageView.setCode("1");
		}
		pageView.setMsg("");
		return pageView;
	}
	
	 @Override
    public int updateStatus(String deleteIds, Integer status) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("ids", deleteIds.split(","));
        paraMap.put("status", status);
        paraMap.put("updateUser", CommonUtil.getMyUser().getUserId());
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
        role.setUpdateUser(CommonUtil.getMyUser().getUserId());
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
