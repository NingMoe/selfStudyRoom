package com.human.manager.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.human.manager.dao.UserDao;
import com.human.manager.entity.UserRole;
import com.human.manager.entity.Users;
import com.human.manager.service.UserService;
import com.human.utils.Common;
import com.human.utils.PageView;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;

	@Override
	public List<Users> queryUser(Users user) {
		List<Users> users = userDao.queryUser(user);
		return users;
	}

	@Override
	public PageView query(PageView pageView, Users user) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("paging", pageView);
		map.put("t", user);
		List<Users> list = userDao.query(map);
		pageView.setRecords(list);
		return pageView;
	}

	@Override
    public Map<String, Object> updateStatus(String deleteIds, Integer status) {
	    Map<String,Object> map=new HashMap<String,Object>();
	    try{
	    String[] userIds=deleteIds.split(",");
	    Map<String,Object> paraMap=new HashMap<String,Object>();
	    paraMap.put("ids", userIds);
	    paraMap.put("status", status);
	    paraMap.put("updateUser", Common.getMyUser().getUserid());
        userDao.updateByIds(paraMap);
        map.put("flag", true);
        map.put("message", "操作成功");
	    }catch(Exception e){
	        map.put("flag", false);
	        map.put("message", "操作失败");
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	    }
        return map;
    }
	
	@Override
	public void add(Users user) {
	    try{
	        //用户公共表插入
	        userDao.insertUser(user);
	        //插入扩展信息
	        userDao.inserMkUser(user);
	    }catch(Exception e){
	        throw new RuntimeException(e);
	    }
	}

	@Override
    public void editLogInUserById(Users user) {
        userDao.editLogInUserById(user);
    }

    @Override
    public void saveUserRole(UserRole userRole,String roleIds) {
        try{
            userDao.delUserRole(userRole);
            List<String> list = new ArrayList<String>();
            if(roleIds.length()>0){
                 list = Common.removeSameItem(Arrays.asList(roleIds.split(",")));
            }
            for (String roleId : list) {
                userRole.setRoleId(Long.valueOf(roleId));
                userDao.saveUserRole(userRole);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
           
    }

    @Override
    public List<UserRole> getUserRole(Long userId) {
        UserRole ur=new UserRole();
        ur.setUserId(userId);
        return userDao.getUserRole(ur);
    }

    @Override
    public void updateMkUser(Users user) {
        try{
        userDao.updateMkUser(user);
        userDao.editUserExt(user);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public List<UserRole> getUserRole(String email) {
        Users u = new Users();
        u.setLoginName(email);
        List<Users> users = queryUser(u);
        
        UserRole ur=new UserRole();
        ur.setUserId(users.get(0).getId());
        return userDao.getUserRole(ur);
    }
    
    @Override
    public List<Users> getNowPositiveUsers() {
        return userDao.getNowPositiveUsers();
    }
	
}
