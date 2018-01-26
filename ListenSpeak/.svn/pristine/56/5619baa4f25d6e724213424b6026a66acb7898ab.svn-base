package com.ls.spt.StudentClass.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.StudentClass.entity.StudentClass;
import com.ls.spt.StudentClass.service.StudentClassService;
import com.ls.spt.basic.entity.PageView;

@Controller
@RequestMapping(value="/studentclass/")
public class StudentClassController {
    
    private final  Logger logger = LogManager.getLogger(StudentClassController.class);
    
    @Resource
    StudentClassService studentclassService;
    
    @RequestMapping("toStudentInfo")
    @ResponseBody
    public ModelAndView studentInfo(StudentClass sc,String invitationCode){
        logger.info("------------跳转班级学生列表------------");
        ModelAndView mav=new ModelAndView("/studentclass/list");
        mav.addObject("sc", sc);
        mav.addObject("invitationCode", invitationCode);
        return mav;
    }
    
    @RequestMapping("query")
    @ResponseBody
    public PageView query(PageView pageView,StudentClass sc){
        return studentclassService.query(pageView,sc);
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> delete(String ids){
        logger.info("-------------------删除学员数据开始------------------");
        Map<String, Object> map =new HashMap<>();
        StudentClass sc =new StudentClass();
        sc.setStatus("3");
        sc.setId( Integer.parseInt(ids));
        try {
            studentclassService.update(sc);
            map.put("flag",true);
            map.put("message","删除数据成功");
        }
        catch (Exception e) {
            map.put("flag",false);
            map.put("message","系统异常，删除数据失败");
            e.printStackTrace();
            e.getMessage();
        }
        return map;
    }
    @RequestMapping("toVerify")
    @ResponseBody
    public ModelAndView toVerify(StudentClass sc){
        ModelAndView mav=new ModelAndView("/studentclass/verify");
        mav.addObject("sc", sc);
        return mav;
    }
    @RequestMapping("verify")
    @ResponseBody
    public Map<String, Object> verify(StudentClass sc){
        logger.info("-----------------审核开始--------");
        Map<String, Object> map=new HashMap<>();
        try {
            studentclassService.update(sc);
            map.put("flag", true);
            map.put("message","审核成功" );
        }
        catch (Exception e) {
           e.printStackTrace();
           e.getMessage();
           map.put("message", "审核失败");
        }
        return map;
    }
    
}
