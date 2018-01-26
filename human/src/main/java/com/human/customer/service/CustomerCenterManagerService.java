package com.human.customer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.customer.entity.CenterMenuDept;
import com.human.customer.entity.CenterModel;
import com.human.customer.entity.CustomerCenterMenu;
import com.human.front.entity.WxTeacherMenu;
import com.human.utils.PageView;

public interface CustomerCenterManagerService {
    /**
     * 分页查询续班规则
     */
    PageView query(PageView pageView,CustomerCenterMenu menu);
    
    List<CenterModel> getModels();
    
    Map<String, Object> addMenu(CustomerCenterMenu cm, HttpServletRequest request);
    
    void delMenu(CustomerCenterMenu cm);
    
    CustomerCenterMenu QueryById(Long id);
    
    Map<String, Object> editMenu(CustomerCenterMenu cm, HttpServletRequest request);
    
    void addMenuDepts(List<CenterMenuDept> depts);
    
    List<CenterMenuDept> getDeptsByMenuId(Long menuId);
    
    
    
    List<WxTeacherMenu> getAllMenus(String email);

}
