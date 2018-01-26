package com.wechat.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.student.service.WechatStudentClassService;

@Controller
@RequestMapping(value="/wechat/binding/studentClass")
public class WechatStudentClassController {
    
    @Resource
    private WechatStudentClassService wechatStudentClassService;
    
    /**
     * 获取学生班级数量
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCount")
    public Map<String, Object> getCount(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getCount(request, response);
    }
    
    /**
     * 获取学生所有班级
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentAllClass")
    public Map<String, Object> getStudentAllClass(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getStudentAllClass(request, response);
    }
    
    /**
     * 获取学生待加入班级
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentNotInClass")
    public Map<String, Object> getStudentNotInClass(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getStudentNotInClass(request, response);
    }
    
    /**
     * 获取学生已经加入所有的班级
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentAllInClass")
    public Map<String, Object> getStudentAllInClass(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getStudentAllInClass(request, response);
    }
    
    /**
     * 获取学生已经加入还未结课的班级
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentNotEndInClass")
    public Map<String, Object> getStudentNotEndInClass(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getStudentNotEndInClass(request, response);
    }
    
    /**
     * 获取学生已经加入已经结课的班级
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentEndInClass")
    public Map<String, Object> getStudentEndInClass(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentClassService.getStudentEndInClass(request, response);
    }
}
