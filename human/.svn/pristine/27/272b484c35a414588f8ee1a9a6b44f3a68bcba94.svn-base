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

import com.human.ielts.entity.IeltsTeacherSource;
import com.human.ielts.service.IeltsTeacherSourceService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teachersource")
public class IeltsTeacherSourceController {

    @Resource
    private IeltsTeacherSourceService ieltsTeacherSourceService;
    
    /**
     * 教师雅思成绩详情页面
     * @return
     */
    @RequestMapping(value="/teacherieltssourceview")
    public ModelAndView teacherieltssourceview(){
        return new ModelAndView("/ielts/teacherieltssource/ieltssourceinfo");
    }    
    
    /**
     * 分页获取教师雅思成绩
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherSource ieltsTeacherSource){
        return ieltsTeacherSourceService.query(pageView, ieltsTeacherSource);
    }
    
    /**
     * 更新教师雅思成绩
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteachersource")
    public Map<String, Object> updateteachersource(IeltsTeacherSource ieltsTeacherSource){
        return ieltsTeacherSourceService.updateteachersource(ieltsTeacherSource);
    }
    
    /**
     * 批量删除教师雅思成绩
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteachersource")
    public Map<String, Object> deletesteachersource(String ids){
        return ieltsTeacherSourceService.deletesteachersource(ids);
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
