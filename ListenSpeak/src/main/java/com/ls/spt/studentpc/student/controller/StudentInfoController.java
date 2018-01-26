package com.ls.spt.studentpc.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.studentpc.student.service.StudentInfoService;
import com.ls.spt.utils.StudentPcConstants;


/**
 * 学生个人信息相关
 * @author xdfhf
 *
 */
@Controller
@RequestMapping(value="/studentpc/studentinfo")
public class StudentInfoController {
    
    @Resource
    private StudentInfoService studentInfoService;
    
    /**
     * 学生PC导航页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/index");
        HttpSession httpSession = request.getSession();
        StudentUser studentUser = (StudentUser) httpSession.getAttribute(StudentPcConstants.STUDENT_USER);
        mav.addObject("studentUser", studentUser);
        return mav;
    }
    
    
    /**
     * 学生登录后首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/firstview")
    public ModelAndView firstview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/studentpc/first");
    }
    
    
    /**
     * 修改密码页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/changepasswordview")
    public ModelAndView changePasswordView(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/studentpc/changePassword");
    }
    
    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/changePassword", method=RequestMethod.POST)
    public Map<String, Object> changePassword(String old_password, String new_password, HttpServletRequest request, HttpServletResponse response){
        return studentInfoService.changePassword(old_password, new_password,request, response);
    }
    
    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/loginout")
    public Map<String, Object> loginout(HttpServletRequest request, HttpServletResponse response){
        return studentInfoService.loginout(request, response);
    }
    
    /**
     * 解绑
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/bindingout")
    public Map<String, Object> bindingout(HttpServletRequest request, HttpServletResponse response){
        return studentInfoService.bindingout(request, response);
    }
    
}
