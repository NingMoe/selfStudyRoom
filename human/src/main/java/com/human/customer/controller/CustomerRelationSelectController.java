package com.human.customer.controller;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.human.customer.entity.CustomerSelect;
import com.human.customer.service.CustomerSelectService;



@Controller
@RequestMapping("/customer/select/")
public class CustomerRelationSelectController {
	
	private final  Logger logger = LogManager.getLogger(CustomerRelationSelectController.class);
	
	@Resource
	private CustomerSelectService selectService;
	
	/**
     * 进入资源管理页面
     * @return
     */
    @RequestMapping(value="index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("/customer/mailFox/relation_select");
        return mav;
    }
    
    /**
     * 查询
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public List<CustomerSelect> query(){
        return   selectService.findAll();
    }
    
    
    /**
     * 保存编辑后结果
     * 
     * @param model
     * @param role
     * @return
     */
    @RequestMapping(value = "update")
    @ResponseBody
    public Map<String, Object> update(String nodes) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CustomerSelect> selectList=JSONArray.parseArray(nodes, CustomerSelect.class);
            selectService.update(selectList);
            map.put("flag", true);
            map.put("msg", "保存成功!");
        }
        catch (Exception e) {
            logger.error("update error !", e);
            map.put("flag", false);
            map.put("msg", "保存失败，请稍后重试!");
        }
        return map;
    }
    
    @RequestMapping(value = "querySelect")
    @ResponseBody
    public Map<String, Object> querySelect(CustomerSelect cs) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<CustomerSelect> selectList=selectService.queryByParam(cs);
            map.put("flag", true);
            map.put("msg", "获取成功");
            map.put("selectList", selectList);
        }
        catch (Exception e) {
            logger.error("update error !", e);
            map.put("flag", false);
            map.put("msg", "获取失败");
            map.put("selectList", "");
        }
        return map;
    }
}
