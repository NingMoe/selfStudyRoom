package com.ls.spt.lstClass.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.basic.entity.DicData;
import com.ls.spt.basic.entity.PageView;
import com.ls.spt.basic.service.DictionaryService;
import com.ls.spt.lstClass.entity.LstClass;
import com.ls.spt.lstClass.service.LstClassService;
import com.ls.spt.utils.Common;
//import com.ls.spt.utils.PageView;


@Controller
@RequestMapping(value="/lstclass/")
public class LstClassController {
    
    private final  Logger logger = LogManager.getLogger(LstClassController.class);
    
    @Autowired 
    LstClassService lstclassService;
    
    @Resource
    private DictionaryService dictionaryService;
    
    /**
     * 数据管理首页
     */
    @RequestMapping("list")
    public ModelAndView index() {
        ModelAndView mav=new ModelAndView("/lstclass/list");
        List<DicData> subject = dictionaryService.getDataByKey("subject");
        List<DicData> grade = dictionaryService.getDataByKey("grade");
        mav.addObject("subject", subject);
        mav.addObject("grade", grade);
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,LstClass lc) {
        return  lstclassService.query(pageView,lc);
    }
    
    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mav=new ModelAndView("/lstclass/add");
        List<DicData> subject = dictionaryService.getDataByKey("subject");
        List<DicData> grade = dictionaryService.getDataByKey("grade");
        mav.addObject("subject", subject);
        mav.addObject("grade", grade);
        return mav;
    }
    
    @RequestMapping("add")
    @ResponseBody
    public Map<String,Object> add(LstClass lc){
        Map<String,Object> map =new HashMap<String, Object>();
        Integer userId=Common.getMyUser().getUserid();
        lc.setCreateUser(userId);
        lc.setTeacher(userId);
        lc.setStatus("1");
        lc.setCreateTime(new Date());
        logger.info("--------新增数据开始------------------");
        try {
           lstclassService.insert(lc); 
           map.put("flag", true);
           map.put("message", "新增数据成功");
        }
        catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "新增数据异常");
        }
        return map;
    }
    
    @RequestMapping("toEdit")
    public ModelAndView toEdit(String  id){
        ModelAndView mav=new ModelAndView("/lstclass/edit");
        Map<String, Object> list=lstclassService.selectPrimaryKey(Integer.valueOf(id));
        List<DicData> subject = dictionaryService.getDataByKey("subject");
        List<DicData> grade = dictionaryService.getDataByKey("grade");
        mav.addObject("subject", subject);
        mav.addObject("grade", grade);
        mav.addObject("lst", list);
        return mav;
    }
    
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(LstClass lc){
        Map<String,Object> map =new HashMap<String, Object>();
        Integer userId=Common.getMyUser().getUserid();
        lc.setUpdateUser(userId);
        lc.setUpdateTime(new Date());
        logger.info("--------新增数据开始------------------");
        try {
           lstclassService.update(lc); 
           map.put("flag", true);
           map.put("message", "编辑数据成功");
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "编辑数据异常");
        }
        return map;
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        logger.info("禁用数据开始");
        Map<String, Object> map =new HashMap<>();
        try {
            map=lstclassService.delete(ids);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return map;
    }
    @RequestMapping("goUse")
    @ResponseBody
    public Map<String, Object> goUse(Integer id){
        logger.info("启用用数据开始");
        Map<String, Object> map =new HashMap<>();
        try {
            map=lstclassService.goUse(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return map;
    }
}
