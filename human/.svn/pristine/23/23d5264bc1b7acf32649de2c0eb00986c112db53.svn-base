package com.human.ielts.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.ielts.entity.IeltsTeacherAttendance;
import com.human.ielts.service.IeltsTeacherAttendanceService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teacherattendance")
public class IeltsTeacherAttendanceController {
    
    @Resource
    private IeltsTeacherAttendanceService ieltsTeacherAttendanceService;
    
    /**
     * 教师考勤详情页面
     * @return
     */
    @RequestMapping(value="/teacherattendanceview")
    public ModelAndView teacherattendanceview(){
        return new ModelAndView("/ielts/teacherattendance/attendanceinfo");
    }    
    
    /**
     * 分页获取教师考勤详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherAttendance ieltsTeacherAttendance){
        return ieltsTeacherAttendanceService.query(pageView, ieltsTeacherAttendance);
    }
    
    /**
     * 更新教师考勤详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteacherattendance")
    public Map<String, Object> updateteacherattendance(IeltsTeacherAttendance ieltsTeacherAttendance){
        return ieltsTeacherAttendanceService.updateteacherattendance(ieltsTeacherAttendance);
    }
    
    /**
     * 批量删除教师考勤详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteacherattendance")
    public Map<String, Object> deletesteacherattendance(String ids){
        return ieltsTeacherAttendanceService.deletesteacherattendance(ids);
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
