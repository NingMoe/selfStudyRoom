package com.human.customer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.customer.entity.CenterDept;
import com.human.customer.entity.CenterMenuDept;
import com.human.customer.entity.CenterModel;
import com.human.customer.entity.CustomerCenterMenu;
import com.human.customer.service.CustomerCenterConfigDeptService;
import com.human.customer.service.CustomerCenterManagerService;
import com.human.utils.Common;
import com.human.utils.OSSUtil;
import com.human.utils.PageView;

@Controller
@RequestMapping("/customer/centerManager/")
public class CustomerCenterManagerController {
    
    private final  Logger logger = LogManager.getLogger(CustomerCenterManagerController.class);
    
    @Value("${oss.bucket}")
    private  String bucketName;
    
    @Value("${oss.wxCustomerPath}")
    private  String wxCustomerPath;
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Resource
    private OSSUtil ossUtil;
    
    @Resource
    private CustomerCenterManagerService managerService;
    
    @Resource
    private CustomerCenterConfigDeptService centerDeptService;
    
    /**
     * 进入列表页面
     * @return
     */
    @RequestMapping(value="toList")
    public ModelAndView toList(){
        ModelAndView mav=new ModelAndView("/customer/centerManager/list");
        List<CenterModel> models = managerService.getModels();
        mav.addObject("models", models);
        return mav;
    }
    
    /**
     * 分页查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,CustomerCenterMenu menu){
        return  managerService.query(pageView, menu);
    }
    
    /**
     * 进入新增页面
     * @return
     */
    @RequestMapping(value="toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/customer/centerManager/add");
        List<CenterModel> models = managerService.getModels();
        mav.addObject("models", models);
        return mav;
    }
    
    
    /**
     * 新增菜单
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(CustomerCenterMenu cm,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map=  managerService.addMenu(cm,request);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "新增失败");
        }
        return map;
    }
    
    
    /**
     * 更新菜单
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public Map<String, Object> del(CustomerCenterMenu cm) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            managerService.delMenu(cm);
            map.put("flag", true);
            map.put("msg", "操作成功");
        }
        catch (Exception e) {
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    /*
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value="toEdit")
    public ModelAndView toEdit(Long id){
        ModelAndView mav=new ModelAndView("/customer/centerManager/edit");
        List<CenterModel> models = managerService.getModels();
        mav.addObject("models", models);
        CustomerCenterMenu menu = managerService.QueryById(id);
        mav.addObject("menu",menu);
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    
    
    /**
     * 更新菜单
     * @return
     */
    @RequestMapping(value = "edit")
    @ResponseBody
    public Map<String, Object> edit(CustomerCenterMenu cm, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map = managerService.editMenu(cm, request);
        }
        catch (Exception e) {
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "更新失败");
        }
        return map;
    }

    /**
     * 进入部门分配页面
     * 
     * @return
     */
    @RequestMapping(value = "toGrantDept")
    public ModelAndView toGrantDept(Long menuId) {
        ModelAndView mav = new ModelAndView("/customer/centerManager/grant_dept");
        mav.addObject("menuId", menuId);
        return mav;
    }
    
    
    @RequestMapping(value = "showTree")
    @ResponseBody
    public List<CenterDept> showTree(Long menuId) {
        List<CenterDept> deptList =centerDeptService.getDepts();
        List<CenterMenuDept> menuDeptList = managerService.getDeptsByMenuId(menuId);
        for (CenterDept mDept : deptList) {
            for (CenterMenuDept me : menuDeptList) {
                if (mDept.getId()==me.getDeptId()) {
                    mDept.setIsChecked(true);
                    break;
                }
            }
        }
        return deptList;
    }
    
    /**
     * 保存菜单部门
     * @return
     */
    @ResponseBody
    @RequestMapping("saveMenuDept")
    public Map<String,Object> saveMenuDept(Long menuId,String deptids){
        Map<String,Object> map = new HashMap<String,Object>();
        List<String> list = new ArrayList<String>();
        if(deptids.length()>0){
             list = Common.removeSameItem(Arrays.asList(deptids.split(",")));
        }
        try {
            List<CenterMenuDept> depts = new ArrayList<CenterMenuDept>();
            for(String s:list){
                if(StringUtils.isNotEmpty(s)){
                    CenterMenuDept d = new CenterMenuDept();
                    d.setMenuId(menuId);
                    d.setDeptId(Long.valueOf(s));
                    depts.add(d);
                }
            }
            managerService.addMenuDepts(depts);
            map.put("flag", true);
            map.put("msg", "保存成功");
        } catch (Exception e) {
            logger.error("保存失败",e);
            map.put("flag", false);
            map.put("msg", "保存失败");
        }
        return map;
    }
    
    
    
}
