package com.human.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.customer.entity.CenterDept;
import com.human.customer.service.CustomerCenterConfigDeptService;
import com.human.utils.PageView;

@Controller
@RequestMapping("/customer/centerDept/")
public class CustomerCenterConfigDeptController {

    private final  Logger logger = LogManager.getLogger(CustomerCenterConfigDeptController.class);
    
    @Value("${oss.fileurl}")
    private  String fileurl;
    
    @Resource
    private CustomerCenterConfigDeptService  centerDeptService;
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/customer/centerDept/index");
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,CenterDept cd) {
        return  centerDeptService.query(pageView,cd);
    }
    
    @RequestMapping("addView")
    public ModelAndView addView() {
        ModelAndView mav=new ModelAndView("/customer/centerDept/add");
        return mav;
    }
    
    /**
     * 新增部门
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(CenterDept cd,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map=  centerDeptService.add(cd,request);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "新增失败");
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav=new ModelAndView("/customer/centerDept/edit");
        CenterDept dept= centerDeptService.queryById(id);
        mav.addObject("dept",dept);
        mav.addObject("fileurl",fileurl);
        return mav;
    }
    
    
    /**
     * 编辑部门
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> edit(CenterDept cd,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map=  centerDeptService.edit(cd,request);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    /**
     * 新增部门
     * @return
     */
    @RequestMapping(value="del")
    @ResponseBody
    public Map<String,Object> del(String deleteIds){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            centerDeptService.delByIds(deleteIds);
            map.put("flag", true);
            map.put("msg", "操作成功");
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "操作失败");
        }
        return map;
    }
    
    
    
    
}
