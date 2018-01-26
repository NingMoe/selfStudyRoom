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

import com.human.ielts.entity.IeltsTeacherComplaint;
import com.human.ielts.service.IeltsTeacherComplaintService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teachercomplaint")
public class IeltsTeacherComplaintController {

    @Resource
    private IeltsTeacherComplaintService ieltsTeacherComplaintService;
    
    /**
     * 教师教学投诉页面
     * @return
     */
    @RequestMapping(value="/teachercomplaintview")
    public ModelAndView teachercomplaintview(){
        return new ModelAndView("/ielts/teachercomplaint/complaintinfo");
    }    
    
    /**
     * 分页获取教学投诉
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherComplaint ieltsTeacherComplaint){
        return ieltsTeacherComplaintService.query(pageView, ieltsTeacherComplaint);
    }
    
    /**
     * 更新教学投诉
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteachercomplaint")
    public Map<String, Object> updateteachercomplaint(IeltsTeacherComplaint ieltsTeacherComplaint){
        return ieltsTeacherComplaintService.updateteachercomplaint(ieltsTeacherComplaint);
    }
    
    /**
     * 批量删除教学投诉
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteachercomplaint")
    public Map<String, Object> deletesteachercomplaint(String ids){
        return ieltsTeacherComplaintService.deletesteachercomplaint(ids);
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
