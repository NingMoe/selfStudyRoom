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

import com.human.ielts.entity.IeltsTeacherMatchclass;
import com.human.ielts.service.IeltsTeacherMatchclassService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teachermatchclass")
public class IeltsTeacherMatchclassController {

    @Resource
    private IeltsTeacherMatchclassService ieltsTeacherMatchclassService;
    
    /**
     * 教师赛课信息页面
     * @return
     */
    @RequestMapping(value="/teachermatchclassview")
    public ModelAndView teachermatchclassview(){
        return new ModelAndView("/ielts/teachermatchclass/matchclassinfo");
    }    
    
    /**
     * 分页获取赛课信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherMatchclass ieltsTeacherMatchclass){
        return ieltsTeacherMatchclassService.query(pageView, ieltsTeacherMatchclass);
    }
    
    /**
     * 更新赛课信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteachermatchclass")
    public Map<String, Object> updateteachermatchclass(IeltsTeacherMatchclass ieltsTeacherMatchclass){
        return ieltsTeacherMatchclassService.updateteachermatchclass(ieltsTeacherMatchclass);
    }
    
    /**
     * 批量删除赛课信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteachermatchclass")
    public Map<String, Object> deletesteachermatchclass(String ids){
        return ieltsTeacherMatchclassService.deletesteachermatchclass(ids);
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
