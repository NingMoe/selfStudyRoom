package com.human.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.human.manager.dao.ResourceDao;
import com.human.manager.entity.Resources;
import com.human.manager.service.ResourceService;
import com.human.utils.PageView;

@Service
public class ResourceServiceImpl implements ResourceService {
    
    @Resource
    private ResourceDao resourceDao;
    
    @Override
    public List<Resources> findAll() {
        return resourceDao.findAll();
    }
    
    @Override
    public List<Resources> selectByKey(Resources resource) {
        return resourceDao.selectByKey(resource);
    }
    
    @Override
    public void add(Resources resources) {
        resourceDao.insert(resources);
    }
    
    @Override
    public Resources getById(Long id) {
        return resourceDao.getById(id);
    }
    
    @Override
    public void modify(Resources resources) {
        resourceDao.updateByPrimaryKey(resources);
    }
    
    @Override
    public void delete(Resources resources) {
        resourceDao.updateToDelete(resources);
    }

    
/*
	
	
	@Resource
	private RoleResourceDao roleResourceDao;
	
	



	@Override
	public PageView query(PageView pageView, ManagerResource resources) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", resources);
		List<ManagerResource> list = managerResourceDao.query(map);
		pageView.setRecords(list);
		return pageView;
	}

    @Override
    public List<ManagerResource> getResourcesByUserName(String username) {
        return managerResourceDao.getResourcesByUserName(username);
    }
*/
}
