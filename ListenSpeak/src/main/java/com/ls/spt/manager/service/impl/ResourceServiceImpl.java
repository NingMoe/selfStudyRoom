package com.ls.spt.manager.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ls.spt.manager.dao.ResourceDao;
import com.ls.spt.manager.entity.Resources;
import com.ls.spt.manager.service.ResourceService;

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
}
