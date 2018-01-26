package com.human.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.customer.entity.CenterModel;
import com.human.customer.service.CustomerCenterConfigModelService;
import com.human.utils.PageView;

@Controller
@RequestMapping("/customer/centerModel/")
public class CustomerCenterConfigModelController {

    private final  Logger logger = LogManager.getLogger(CustomerCenterConfigModelController.class);
    
    @Resource
    private CustomerCenterConfigModelService  centerModelService;
    
    @RequestMapping("index")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/customer/centerModel/index");
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,CenterModel cd) {
        return  centerModelService.query(pageView,cd);
    }
    
    @RequestMapping("addView")
    public ModelAndView addView() {
        ModelAndView mav=new ModelAndView("/customer/centerModel/add");
        return mav;
    }
    
    /**
     * 新增部门
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String,Object> add(CenterModel cd,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map=  centerModelService.add(cd,request);
        }catch(Exception e){
            logger.error(e);
            map.put("flag", false);
            map.put("msg", "新增失败");
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav=new ModelAndView("/customer/centerModel/edit");
        CenterModel model= centerModelService.queryById(id);
        mav.addObject("model",model);
        return mav;
    }
    
    
    /**
     * 编辑部门
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String,Object> edit(CenterModel cd,HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            map=  centerModelService.edit(cd,request);
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
            centerModelService.delByIds(deleteIds);
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
