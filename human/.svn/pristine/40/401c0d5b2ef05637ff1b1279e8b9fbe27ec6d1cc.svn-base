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

import com.human.ielts.entity.IeltsTeacherOperate;
import com.human.ielts.service.IeltsTeacherOperateService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teacheroperate")
public class IeltsTeacherOperateController {

    @Resource
    private IeltsTeacherOperateService ieltsTeacherOperateService;
    
    /**
     * 教师考勤详情页面
     * @return
     */
    @RequestMapping(value="/teacheroperateview")
    public ModelAndView teacheroperateview(){
        return new ModelAndView("/ielts/teacheroperate/operateinfo");
    }    
    
    /**
     * 分页获取教学运营支持
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView query(PageView pageView, IeltsTeacherOperate ieltsTeacherOperate){
        return ieltsTeacherOperateService.query(pageView, ieltsTeacherOperate);
    }
    
    /**
     * 更新教学运营支持
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateteacheroperate")
    public Map<String, Object> updateteacheroperate(IeltsTeacherOperate ieltsTeacherOperate){
        return ieltsTeacherOperateService.updateteacheroperate(ieltsTeacherOperate);
    }
    
    /**
     * 批量删除教学运营支持
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesteacheroperate")
    public Map<String, Object> deletesteacheroperate(String ids){
        return ieltsTeacherOperateService.deletesteacheroperate(ids);
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
