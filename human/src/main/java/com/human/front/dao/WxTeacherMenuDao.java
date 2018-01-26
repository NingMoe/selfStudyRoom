package com.human.front.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.front.entity.MenuDept;
import com.human.front.entity.MenuUser;
import com.human.front.entity.WxTeacherMenu;
import com.human.front.entity.WxTeacherModule;


@Repository
public interface WxTeacherMenuDao {
    List<WxTeacherMenu> query(Map<Object,Object> map);
    
    List<WxTeacherMenu> getAllMenus(String email);
    
    WxTeacherMenu selectById(Integer id);
    
    Integer insert(WxTeacherMenu menu);
    
    Integer updateByPrimaryKeySelective(WxTeacherMenu menu);
    
    Integer insertMenuDept(List<MenuDept> depts);
    
    Integer insertMenuUser(MenuUser mu);
    
    Integer delDeptByMenuId(Integer menuId);
    
    Integer delMenuUser(MenuUser mu);
    
    Integer getMenuUserCnt(MenuUser mu);
    
    List<MenuDept> getDeptsByMenuId(Integer menuId);
    
    List<MenuUser> getUsersByMenuId(Integer menuId);
    
    List<WxTeacherModule> getAllModules();
}
