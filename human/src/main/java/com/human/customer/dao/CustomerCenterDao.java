package com.human.customer.dao;

import java.util.List;
import java.util.Map;

import com.human.customer.entity.CenterMenuDept;
import com.human.customer.entity.CustomerCenterMenu;

public interface CustomerCenterDao {

    List<CustomerCenterMenu> query(Map<String, Object> map);

    int add(CustomerCenterMenu cm);

    void updateState(CustomerCenterMenu cm);

    CustomerCenterMenu selectById(Long id);

    int editMenu(CustomerCenterMenu cm);

    List<CenterMenuDept> getDeptsByMenuId(Long menuId);

    void delDeptByMenuId(Long menuId);

    void insertMenuDept(List<CenterMenuDept> depts);

    List<CustomerCenterMenu> getAllMenus();

}
