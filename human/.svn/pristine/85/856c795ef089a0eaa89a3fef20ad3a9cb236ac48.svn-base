package com.human.continuation.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.continuation.entity.TeacherContinuationClass;
import com.human.continuation.service.TeacherContinuationUserService;

@Controller
@RequestMapping(value="/wechat/binding/coutinuationuser")
public class TeacherContinuationUserController {

    @Resource
    private TeacherContinuationUserService teacherContinuationUserService;
    
    /**
     * 续班名单页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/main");
    }
    
    /**
     * 确认添加页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/listview")
    public ModelAndView listview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/list");
    }
    
    /**
     * 已配置名单页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/resultview")
    public ModelAndView resultview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/continuation/result");
        mav.addAllObjects(teacherContinuationUserService.getTeacherStudentClasses(request, response));
        return mav;
    }
    
    /**
     * 未配置名单页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/notconfiguredview")
    public ModelAndView notconfiguredview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/continuation/notconfigured");
        mav.addAllObjects(teacherContinuationUserService.getTeacherStudentClasses(request, response));
        return mav;
    }
    
    /**
     * 新增人班关系页
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addclasspeopleview")
    public ModelAndView addclasspeopleview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/continuation/classespeopleaddbyphone");
        return mav;
    }
    
    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addclasspeople")
    public Map<String, Object> addclasspeople(HttpServletRequest request, TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationUserService.addclasspeople(request, teacherContinuationClass);
    }
    
    /**
     * 获取教师代课班级信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getteacherclasses")
    public Map<String, Object> getTeacherClasses(HttpServletRequest request, HttpServletResponse response){
        return teacherContinuationUserService.getTeacherClasses(request, response);
    }
    
    /**
     * 获取教师要续班级
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getteacherstudentclasses")
    public Map<String, Object> getTeacherStudentClasses(HttpServletRequest request, HttpServletResponse response){
        return teacherContinuationUserService.getTeacherStudentClasses(request, response);
    }
    
    
    /**
     * 获取教师可续班级
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getTeacherNotBeginClasses")
    public Map<String, Object> getTeacherNotBeginClasses(HttpServletRequest request, HttpServletResponse response){
        return teacherContinuationUserService.getTeacherNotBeginClasses(request, response);
    }
    
    /**
     * 添加人班关系
     * @param classes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addstduentclasses")
    public Map<String, Object> addStduentClasses(HttpServletRequest request, HttpServletResponse response, String student_code, String student_name, String studentclasses){
        return teacherContinuationUserService.addStduentClasses(request, response, student_code, student_name, studentclasses);
    }
    
    /**
     * 添加班班关系
     * @param classes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addclassesclasses")
    public Map<String, Object> addClassesClasses(HttpServletRequest request, HttpServletResponse response, String oldclasses, String newclasses){
        return teacherContinuationUserService.addClassesClasses(request, response, oldclasses, newclasses);
    }
    
    /**
     * 删除人班关系
     * @param classes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/removestduentclasses")
    public Map<String, Object> removestduentclasses(HttpServletRequest request, HttpServletResponse response, String student_code, String studentclasses){
        return teacherContinuationUserService.removestduentclasses(request, response, student_code, studentclasses);
    }
    
    /**
     * 通过班号获取班级信息
     * @param classes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getclassinfobyclasscode")
    public Map<String, Object> getClassinfoByClasscode(String class_code){
        return teacherContinuationUserService.getClassinfoByClasscode(class_code);
    }
    
    /**
     * 通过班号获取学员以及所报班级
     * @param classes
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getstudentinfobyclasscode")
    public Map<String, Object> getStudentinfoByClasscode(HttpServletRequest request,String class_code, String student_name){
        return teacherContinuationUserService.getStudentinfoByClasscode(request, class_code, student_name);
    }
}
