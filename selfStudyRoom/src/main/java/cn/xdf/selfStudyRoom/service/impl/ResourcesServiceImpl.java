package cn.xdf.selfStudyRoom.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.xdf.selfStudyRoom.domain.dao.ResourcesDao;
import cn.xdf.selfStudyRoom.domain.entity.MenuFirstList;
import cn.xdf.selfStudyRoom.domain.entity.Resources;
import cn.xdf.selfStudyRoom.service.ResourcesService;

@Transactional
@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Resource
	private ResourcesDao resourcesDao;
	
	@Override
	public List<Resources> getResourcesByLoginName(String username) {		
		return resourcesDao.getResourcesByLoginName(username);
	}

	@Override
	public List<MenuFirstList> selectMenuList(String username) {
		return resourcesDao.selectMenuList(username);
	}

	@Override
	public List<Resources> findAll() {		
		return resourcesDao.findAll();
	}

	@Override
	public List<Resources> selectByKey(Resources resource) {		
		 return resourcesDao.selectByKey(resource);
	}

	@Override
	public void add(Resources resources) {
		resourcesDao.insert(resources);		
	}

	@Override
	public Resources getById(Long id) {
		return resourcesDao.getById(id);
	}

	@Override
	public void modify(Resources resource) {
		resourcesDao.updateByPrimaryKey(resource);
		
	}

	@Override
	public void delete(Resources resource) {
		resourcesDao.updateToDelete(resource);		
	}

}
