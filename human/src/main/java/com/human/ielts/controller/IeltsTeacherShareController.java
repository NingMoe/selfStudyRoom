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

import com.human.ielts.entity.IeltsTeacherShare;
import com.human.ielts.service.IeltsTeacherShareService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teachershare")
public class IeltsTeacherShareController {

    @Resource
    private IeltsTeacherShareService ieltsTeacherShareService;
    
    /**
     * 教师考勤详情页面
     * @return
     */
    @RequestMapping(value="/teachershareview")
    public ModelAndView teachershareview(){
        return new ModelAndView("/ielts/teachershare/shareinfo");
    }    
    
    /**
     * 分页获取教学分享
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherShare ieltsTeacherShare){
        return ieltsTeacherShareService.query(pageView, ieltsTeacherShare);
    }
    
    /**
     * 更新教学分享
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteachershare")
    public Map<String, Object> updateteachershare(IeltsTeacherShare ieltsTeacherShare){
        return ieltsTeacherShareService.updateteachershare(ieltsTeacherShare);
    }
    
    /**
     * 批量删除教学分享
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteachershare")
    public Map<String, Object> deletesteachershare(String ids){
        return ieltsTeacherShareService.deletesteachershare(ids);
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
