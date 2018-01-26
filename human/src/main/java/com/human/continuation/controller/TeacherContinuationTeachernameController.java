package com.human.continuation.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.continuation.entity.TeacherContinuationTeachername;
import com.human.continuation.service.TeacherContinuationTeachernameService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/continuationteachername")
public class TeacherContinuationTeachernameController {
    
    @Resource
    private TeacherContinuationTeachernameService teacherContinuationTeachernameService;
    
    /**
     * 班级教师名称管理页面
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/teachername");
    }
    
    
    /**
     * 新增班级教师名称页面
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addview")
    public ModelAndView insertview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/teachernameadd");
    }
    
    /**
     * 删除班级教师名称页面
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/changeview")
    public ModelAndView updateview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/teachernamechange");
    }
    
    /**
     * 分页获取管理的班级
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.query(pageView, teacherContinuationTeachername);
    }
    
    /**
     * 分页获取班级教师
     * @param pageView
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/classesquery")
    public PageView classesquery(PageView pageView, TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.classesquery(pageView, teacherContinuationTeachername);
    }
    
    /**
     * 新增班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert")
    public Map<String, Object> insert(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.insert(teacherContinuationTeachername);
    }
    
    /**
     * 修改班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update")
    public Map<String, Object> update(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.update(teacherContinuationTeachername);
    }
    
    
    /**
     * 查询班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.select(teacherContinuationTeachername);
    }
    
    
    /**
     * 删除班级教师
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete")
    public Map<String, Object> delete(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.delete(teacherContinuationTeachername);
    }
    
    /**
     * 获取班级名称
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getclassname")
    public Map<String, Object> getclassname(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.getclassname(teacherContinuationTeachername);
    }
    
    /**
     * 获取教师姓名
     * @param teacherContinuationTeachername
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getteachername")
    public Map<String, Object> getteachername(TeacherContinuationTeachername teacherContinuationTeachername){
        return teacherContinuationTeachernameService.getteachername(teacherContinuationTeachername);
    }

}
