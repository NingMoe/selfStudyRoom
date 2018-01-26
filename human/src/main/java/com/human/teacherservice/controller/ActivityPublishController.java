package com.human.teacherservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.teacherservice.entity.TeacherActManager;
import com.human.teacherservice.service.TeacherActManagerService;
import com.human.utils.PageView;

/**
 * 
 * @author zx
 * 教师服务-活动发布
 */
@Controller
@RequestMapping(value="/teacher/activity/")
public class ActivityPublishController {
    
    @Resource
    private TeacherActManagerService teacherActManagerService;

    /**
     * 活动主页
     * @return
     */
    @RequestMapping(value="view")
    public ModelAndView view(){
        return new ModelAndView("/teacherservice/activitypublish/activitypublish");
    }
    
    /**
     * 活动新增页
     * @return
     */
    @RequestMapping(value="addview")
    public ModelAndView addview(){
        return new ModelAndView("/teacherservice/activitypublish/add");
    }
    
    /**
     * 活动修改页
     * @return
     */
    @RequestMapping(value="changeview")
    public ModelAndView changeview(){
        return new ModelAndView("/teacherservice/activitypublish/change");
    }
    
    /**
     * 分页获取活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="query")
    public PageView queryTeacherAct(TeacherActManager teacherActManager, PageView pageView){
        return teacherActManagerService.query(pageView, teacherActManager);
    }
    
    /**
     * 分页获取活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryById")
    public Map<String, Object> queryTeacherActById(TeacherActManager teacherActManager){
        return teacherActManagerService.queryTeacherActById( teacherActManager);
    }
    
    /**
     * 新发布活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="insert")
    public Map<String, Object> insertTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request){
        return teacherActManagerService.insertTeacherAct(teacherActManager, request);
    }
    
    /**
     * 修改活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="update")
    public Map<String, Object> updateTeacherAct(TeacherActManager teacherActManager, HttpServletRequest request){
        return teacherActManagerService.updateTeacherAct(teacherActManager,request);
    }
    
    /**
     * 分页获取活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="delete")
    public Map<String, Object> deleteTeacherAct(TeacherActManager teacherActManager){
        return teacherActManagerService.deleteTeacherAct(teacherActManager);
    }
    
    /**
     * 同意活动
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @ResponseBody
    @RequestMapping(value="isagree")
    public Map<String, Object> isagreeTeacherAct(TeacherActManager teacherActManager){
        return teacherActManagerService.isagreeTeacherAct(teacherActManager);
    }
    
    /**
     * spring接收date参数
     * @param teacherActManager
     * @param pageView
     * @return
     */
    @org.springframework.web.bind.annotation.InitBinder  
    public void InitBinder(WebDataBinder dataBinder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        dateFormat.setLenient(false);  
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }  
}
