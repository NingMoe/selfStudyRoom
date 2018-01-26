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
import com.human.continuation.service.TeacherContinuationClassService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/continuationclass")
public class TeacherContinuationClassController {
    
    @Resource
    private TeacherContinuationClassService teacherContinuationClassService;
    
    /**
     * 获取班级页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/classes");
    }
    
    /**
     * 新增人班关系页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/addpeopleclassesview")
    public ModelAndView addpeopleclassesview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/classespeopleadd");
    }
    
    /**
     * 新增班班关系页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/addclassesclassesview")
    public ModelAndView addclassesclassesview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/continuation/classesclassesadd");
    }

    /**
     * 分页查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView page, TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.query(page, teacherContinuationClass);
    }

    /**
     * 新增人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert")
    public Map<String, Object> insert(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.insert(teacherContinuationClass);
    }
    
    /**
     * 新增班级中所有学员的人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertclass")
    public Map<String, Object> insertclass(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.insertclass(teacherContinuationClass);
    }
    
    /**
     * 修改人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update")
    public Map<String, Object> update(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.update(teacherContinuationClass);
    }
    
    /**
     * 查询人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.select(teacherContinuationClass);
    }
    
    /**
     * 删除人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete")
    public Map<String, Object> delete(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.delete(teacherContinuationClass);
    }
    
    /**
     * 往接口中插入人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateisadd")
    public Map<String, Object> updateIsadd(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.updateIsadd(teacherContinuationClass);
    }
    
    /**
     * 删除接口中人班关系
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateisnotadd")
    public Map<String, Object> updateIsnotadd(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.updateIsnotadd(teacherContinuationClass);
    }
    
    /**
     * 通过姓名手机号获取所有学员号
     * @param teacherContinuationClass
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getstudentcodes")
    public Map<String, Object> getStduentCodes(TeacherContinuationClass teacherContinuationClass){
        return teacherContinuationClassService.getStduentCodes(teacherContinuationClass);
    }
}
