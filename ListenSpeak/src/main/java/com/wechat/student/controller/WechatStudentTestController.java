package com.wechat.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.student.service.WechatStudentTestService;

@Controller
@RequestMapping(value="/wechat/binding/studentTest")
public class WechatStudentTestController {
    
    @Resource
    private WechatStudentTestService wechatStudentTestService; 
    
    /**
     * 获取学生所有考试
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentAllTest")
    public Map<String, Object> getStudentAllTest(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentTestService.getStudentAllTest(request, response);
    }
    
    /**
     * 获取学生已完成考试
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentEndTest")
    public Map<String, Object> getStudentEndTest(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentTestService.getStudentEndTest(request, response);
    }
    
    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentNotEndTest")
    public Map<String, Object> getStudentNotEndTest(HttpServletRequest request, HttpServletResponse response){
        return wechatStudentTestService.getStudentNotEndTest(request, response);
    }

}
