package com.ls.spt.studentpc.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.studentpc.student.service.StudentClassService;


/**
 * 学生班级
 * @author xdfhf
 *
 */
@Controller(value="pcStudentController")
@RequestMapping(value="/studentpc/studentclass")
public class StudentClassController {
    
    @Resource
    private StudentClassService studentClassService;
    
    /**
     * 学生班级页面
     * @return
     */
    @RequestMapping(value="/studentclassview")
    public ModelAndView studentclassview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/studentclass");
        mav.addObject("studentClass", studentClassService.getStudentAllInClass(request, response));
        return mav;
    }
    
    /**
     * 申请入班页面
     * @return
     */
    @RequestMapping(value="/addClassView")
    public ModelAndView addClassView(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/studentclassadd");
        return mav;
    }
    
    /**
     * 申请入班
     * @param invitation_code
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/applyForJoinClass")
    public Map<String, Object> applyForJoinClass(String invitation_code, String phone, HttpServletRequest request, HttpServletResponse response){
        return studentClassService.applyForJoinClass(invitation_code, phone, request, response);
    }
    
    /**
     * 获取学生班级数量
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getCount")
    public Map<String, Object> getCount(HttpServletRequest request, HttpServletResponse response){
        return studentClassService.getCount(request, response);
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
        return studentClassService.getStudentAllClass(request, response);
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
        return studentClassService.getStudentNotInClass(request, response);
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
        return studentClassService.getStudentAllInClass(request, response);
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
        return studentClassService.getStudentNotEndInClass(request, response);
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
        return studentClassService.getStudentEndInClass(request, response);
    }
}
