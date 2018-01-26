package com.human.continuation.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.continuation.entity.TeacherContinuationConfig;
import com.human.continuation.service.TeacherContinuationConfigService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/teacher/continuationconfig")
public class TeacherContinuationConfigController {
    
    @Resource
    private TeacherContinuationConfigService teacherContinuationConfigService;
    
    /**
     * 获取配置页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/continuation/config");
        mav.addAllObjects(teacherContinuationConfigService.selectManagerDept());
        return mav;
    }
    
    /**
     * 新增配置页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/addview")
    public ModelAndView addview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/continuation/configadd");
        mav.addAllObjects(teacherContinuationConfigService.selectManagerDept());
        mav.addAllObjects(teacherContinuationConfigService.selectFiscalYear());
        mav.addObject("sbq",teacherContinuationConfigService.getSbqList());
        return mav;
    }
    
    /**
     * 修改配置页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/changeview")
    public ModelAndView changeview(HttpServletRequest request, HttpServletResponse response, TeacherContinuationConfig teacherContinuationConfig){
        ModelAndView mav = new ModelAndView("/continuation/configchange");
        mav.addAllObjects(teacherContinuationConfigService.selectManagerDept());
        mav.addAllObjects(teacherContinuationConfigService.selectFiscalYear());
        mav.addObject("sbq",teacherContinuationConfigService.getSbqList());
        mav.addAllObjects(teacherContinuationConfigService.select(teacherContinuationConfig));
        return mav;
    }
    
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, TeacherContinuationConfig teacherContinuationConfig){
        return teacherContinuationConfigService.query(pageView, teacherContinuationConfig);
    }
    
    /**
     * 新增关系配置
     * @param teacherContinuationConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insert")
    public Map<String, Object> insert(TeacherContinuationConfig teacherContinuationConfig){
        return teacherContinuationConfigService.insert(teacherContinuationConfig);
    }
    
    /**
     * 修改关系配置
     * @param teacherContinuationConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update")
    public Map<String, Object> update(TeacherContinuationConfig teacherContinuationConfig){
        return teacherContinuationConfigService.update(teacherContinuationConfig);
    }
    
    /**
     * 查询关系配置
     * @param teacherContinuationConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/select")
    public Map<String, Object> select(TeacherContinuationConfig teacherContinuationConfig){
        return teacherContinuationConfigService.select(teacherContinuationConfig);
    }
    
    /**
     * 删除关系配置
     * @param teacherContinuationConfig
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete")
    public Map<String, Object> delte(TeacherContinuationConfig teacherContinuationConfig){
        return teacherContinuationConfigService.delete(teacherContinuationConfig);
    }

}
