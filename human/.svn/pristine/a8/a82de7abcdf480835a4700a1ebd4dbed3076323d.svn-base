package com.human.manager.service;

import java.util.List;

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;

/**
 * 用户管理部门的接口
 * @author HF-121093-01
 *
 */
public interface UserDeptService {
    
    /**
     * 获取用户管理的机构
     * @param userId
     * @return
     */
    List<HrCompany> getUserCompany(Long userId);

    /**
     * 获取用户管理的部门
     * @param userId
     * @return
     */
    List<HrOrganization> getUserOrg(UserDept ud);

    /**
     * 获取用户具有权限的组织架构
     * @param userId
     * @return
     */
    List<HrOrganization> getUserDeptTree(long userId);

    /**
     * 保存用户的部门权限
     * @param userId
     * @param list
     */
    void saveUserDept(Long userId, List<String> list);

    /**
     * 获取用户具有所有部门ID
     * @param userId
     * @return
     */
    List<UserDept> getUserAllOrg(long userId);
}
