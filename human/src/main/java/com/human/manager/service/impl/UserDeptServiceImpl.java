package com.human.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.human.manager.dao.UserDeptDao;
import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;
import com.human.manager.service.UserDeptService;

@Service
public class UserDeptServiceImpl implements UserDeptService {

    @Resource
    private UserDeptDao userDeptDao;
    
    @Override
    public List<HrCompany> getUserCompany(Long userId) {
        return userDeptDao.getUserCompany(userId);
    }

    @Override
    public List<HrOrganization> getUserOrg(UserDept ud) {
        return userDeptDao.getUserOrg(ud);
    }

    @Override
    public List<HrOrganization> getUserDeptTree(long userId) {
        return userDeptDao.getUserDeptTree(userId);
    }

    @Override
    @Transactional
    public void saveUserDept(Long userId, List<String> list) {
        try {
            userDeptDao.delDeptByUserId(userId);
            for (String deptId : list) {
                UserDept ud = new UserDept();
                ud.setUserId(userId);;
                ud.setDeptId(deptId);;
                userDeptDao.saveUserDept(ud);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public List<UserDept> getUserAllOrg(long userId) {
        return userDeptDao.getUserAllOrg(userId);
    }

}
