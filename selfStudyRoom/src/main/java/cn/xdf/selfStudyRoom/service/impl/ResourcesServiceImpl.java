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

}
