package com.human.manager.service;

import java.util.List;

import com.human.manager.entity.Resources;


public interface ResourceService {
    
    List<Resources> findAll();
    
    List<Resources> selectByKey(Resources resource);
    
    void add(Resources resources);
    
    Resources getById(Long id);
    
    void modify(Resources resource);
    
    void delete(Resources resource);
    
	/*
	
	List<ManagerResource> getResourcesByUserName(String username);

	PageView query(PageView pageView, ManagerResource resources);


	*/

}
