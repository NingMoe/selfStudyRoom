package com.human.manager.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.human.manager.entity.HrCompany;
import com.human.manager.entity.HrOrganization;
import com.human.manager.entity.UserDept;

@Repository
public interface UserDeptDao {

    /**
     * 根据用户ID获取有权限的机构
     * @param userId
     * @return
     */
    List<HrCompany> getUserCompany(Long userId);

    /**
     * 根据用户ID和机构获取用户有权限的部门
     * @param up
     * @return
     */
    List<HrOrganization> getUserOrg(UserDept up);

    /**
     * 根据用户ID和机构获取用户权限部门树
     * @param up
     * @return
     */
    List<HrOrganization> getUserDeptTree(long userId);

    /**
     * 删除用户的管理部门
     * @param userId
     * @return
     */
    int delDeptByUserId(Long userId);

    /**
     * 保存用户的管理部门
     * @param ud
     * @return
     */
    int saveUserDept(UserDept ud);

    /**
     * 获取用户所有具有的权限
     * @param userId
     * @return
     */
    List<UserDept> getUserAllOrg(long userId);

}
