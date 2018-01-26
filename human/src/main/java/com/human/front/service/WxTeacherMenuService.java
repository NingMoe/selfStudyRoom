package com.human.front.service;

import java.util.List;

import com.human.front.entity.MenuDept;
import com.human.front.entity.MenuUser;
import com.human.front.entity.WxTeacherMenu;
import com.human.front.entity.WxTeacherModule;
import com.human.utils.PageView;

public interface WxTeacherMenuService {
    /**
     * 分页查询续班规则
     */
    PageView query(PageView pageView,WxTeacherMenu menu);
    
    List<WxTeacherMenu> getAllMenus(String email);
    
    Integer addMenu(WxTeacherMenu menu);
    
    Integer editMenu(WxTeacherMenu menu);
    
    WxTeacherMenu QueryById(Integer id);
    
    void addMenuDepts(List<MenuDept> menuDepts);
    
    void addMenuUser(MenuUser mu);
    
    Integer getMenuUserCnt(MenuUser mu);
    
    Integer delMenuUser(MenuUser mu);
    
    List<MenuDept> getDeptsByMenuId(Integer menuId);
    
    List<MenuUser> getUsersByMenuId(Integer menuId);
    
    public List<WxTeacherModule> getAllModules();
}
