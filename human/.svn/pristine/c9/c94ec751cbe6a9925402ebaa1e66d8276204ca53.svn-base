package com.human.front.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.human.front.dao.WxTeacherMenuDao;
import com.human.front.entity.MenuDept;
import com.human.front.entity.MenuUser;
import com.human.front.entity.WxTeacherMenu;
import com.human.front.entity.WxTeacherModule;
import com.human.front.service.WxTeacherMenuService;
import com.human.utils.Common;
import com.human.utils.PageView;
@Service
public class WxTeacherMenuServiceImpl implements WxTeacherMenuService{
    
    @Resource
    private WxTeacherMenuDao wxTeacherMenuDao;
    
    @Override
    public PageView query(PageView pageView, WxTeacherMenu menu) {
        String company = Common.getMyUser().getCompanyId();
        menu.setCompany(company);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("paging", pageView);
        map.put("t", menu);
        List<WxTeacherMenu> list = wxTeacherMenuDao.query(map);
        pageView.setRecords(list);
        return pageView;
    }
    
    @Override
    public List<WxTeacherMenu> getAllMenus(String email) {
        return wxTeacherMenuDao.getAllMenus(email);
    }
    
    @Override
    public Integer addMenu(WxTeacherMenu menu) {
        menu.setStatus("1");
        menu.setCompany(Common.getMyUser().getCompanyId());
        return wxTeacherMenuDao.insert(menu);
    }
    
    @Override
    public Integer editMenu(WxTeacherMenu menu) {
        return wxTeacherMenuDao.updateByPrimaryKeySelective(menu);
    }
    
    @Override
    public WxTeacherMenu QueryById(Integer id) {
        return wxTeacherMenuDao.selectById(id);
    }
    
    @Override
    public void addMenuDepts(List<MenuDept> menuDepts) {
        Integer menuId = menuDepts.get(0).getMenuId();
        wxTeacherMenuDao.delDeptByMenuId(menuId);
        wxTeacherMenuDao.insertMenuDept(menuDepts);
    }
    
   @Override
    public void addMenuUser(MenuUser mu) {
        wxTeacherMenuDao.insertMenuUser(mu);
    }
   
   @Override
    public Integer getMenuUserCnt(MenuUser mu) {
        return wxTeacherMenuDao.getMenuUserCnt(mu);
    }
   
   @Override
    public Integer delMenuUser(MenuUser mu) {
        return wxTeacherMenuDao.delMenuUser(mu);
    }
   
   @Override
    public List<MenuDept> getDeptsByMenuId(Integer menuId) {
        return wxTeacherMenuDao.getDeptsByMenuId(menuId);
    }
   
   @Override
    public List<MenuUser> getUsersByMenuId(Integer menuId) {
       return wxTeacherMenuDao.getUsersByMenuId(menuId);
    }
   
   @Override
    public List<WxTeacherModule> getAllModules() {
        return wxTeacherMenuDao.getAllModules();
    }
}
