package com.wechat.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.student.service.WechatStudentZuoyeService;

@Controller
@RequestMapping(value="/wechat/binding/studentZuoye")
public class WechatStudentZuoyeController {
    
    @Resource
    private WechatStudentZuoyeService wechatStudentZuoyeService;
    
    /**
     * 获取学生所有作业
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentAllZuoye")
    public Map<String, Object> getStudentAllZuoye(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentZuoyeService.getStudentAllZuoye(request, response);
    }
    
    /**
     * 获取学生已完成作业
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentEndZuoye")
    public Map<String, Object> getStudentEndZuoye(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentZuoyeService.getStudentEndZuoye(request, response);
    }
    
    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentNotEndZuoye")
    public Map<String, Object> getStudentNotEndZuoye(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentZuoyeService.getStudentNotEndZuoye(request, response);
    }

}
