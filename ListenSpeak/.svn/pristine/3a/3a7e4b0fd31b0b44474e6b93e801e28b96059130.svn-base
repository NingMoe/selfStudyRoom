package com.wechat.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/wechat/binding/studentinfo")
public class WechatStudentinfoController {
    
    /**
     * 首页
     * @return
     */
    @RequestMapping(value="/studentindex")
    public ModelAndView studentindex(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/wechat/student/studentindex");
    }
}
