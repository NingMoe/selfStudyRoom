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

import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.ielts.service.IeltsTeacherTktService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teachertkt")
public class IeltsTeacherTktController {
    
    @Resource
    private IeltsTeacherTktService ieltsTeacherTktService;
    
    
    /**
     * 教师tkt详情页面
     * @return
     */
    @RequestMapping(value="/teachertktview")
    public ModelAndView teachertktview(){
        return new ModelAndView("/ielts/teachertkt/tktinfo");
    }    
    
    /**
     * 分页获取教师tkt详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherTkt ieltsTeacherTkt){
        return ieltsTeacherTktService.query(pageView, ieltsTeacherTkt);
    }
    
    /**
     * 更新教师tkt详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteachertkt")
    public Map<String, Object> updateteachertkt(IeltsTeacherTkt ieltsTeacherTkt){
        return ieltsTeacherTktService.updateteachertkt(ieltsTeacherTkt);
    }
    
    /**
     * 批量删除教师tkt详情
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteachertkt")
    public Map<String, Object> deletesteachertkt(String ids){
        return ieltsTeacherTktService.deletesteachertkt(ids);
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
