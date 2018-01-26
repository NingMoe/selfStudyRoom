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

import com.human.ielts.entity.IeltsTeacherFeedback;
import com.human.ielts.service.IeltsTeacherFeedbackService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teacherfeedback")
public class IeltsTeacherFeedbackController {

    @Resource
    private IeltsTeacherFeedbackService ieltsTeacherFeedbackService;
    
    /**
     * 教师教学反馈页面
     * @return
     */
    @RequestMapping(value="/teacherfeedbackview")
    public ModelAndView teacherfeedbackview(){
        return new ModelAndView("/ielts/teacherfeedback/feedbackinfo");
    }    
    
    /**
     * 分页获取教学反馈
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherFeedback ieltsTeacherFeedback){
        return ieltsTeacherFeedbackService.query(pageView, ieltsTeacherFeedback);
    }
    
    /**
     * 更新教学反馈
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteacherfeedback")
    public Map<String, Object> updateteacherfeedback(IeltsTeacherFeedback ieltsTeacherFeedback){
        return ieltsTeacherFeedbackService.updateteacherfeedback(ieltsTeacherFeedback);
    }
    
    /**
     * 批量删除教学反馈
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteacherfeedback")
    public Map<String, Object> deletesteacherfeedback(String ids){
        return ieltsTeacherFeedbackService.deletesteacherfeedback(ids);
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
