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

import com.human.ielts.entity.IeltsTeacherArticle;
import com.human.ielts.service.IeltsTeacherArticleService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teacherarticle")
public class IeltsTeacherArticleController {

    @Resource
    private IeltsTeacherArticleService ieltsTeacherArticleService;
    
    /**
     * 教师考勤详情页面
     * @return
     */
    @RequestMapping(value="/teacherarticleview")
    public ModelAndView teacherarticleview(){
        return new ModelAndView("/ielts/teacherarticle/articleinfo");
    }    
    
    /**
     * 分页获取教师教研文章
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherArticle ieltsTeacherArticle){
        return ieltsTeacherArticleService.query(pageView, ieltsTeacherArticle);
    }
    
    /**
     * 更新教师教研文章
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteacherarticle")
    public Map<String, Object> updateteacherarticle(IeltsTeacherArticle ieltsTeacherArticle){
        return ieltsTeacherArticleService.updateteacherarticle(ieltsTeacherArticle);
    }
    
    /**
     * 批量删除教师教研文章
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteacherarticle")
    public Map<String, Object> deletesteacherarticle(String ids){
        return ieltsTeacherArticleService.deletesteacherarticle(ids);
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
