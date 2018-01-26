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

import com.human.ielts.entity.IeltsEnrollInfo;
import com.human.ielts.entity.IeltsStudentInfo;
import com.human.ielts.service.IeltsStudentBookService;
import com.human.ielts.service.IeltsStudentInfoService;
import com.human.ielts.service.IeltsClassTypeService;
import com.human.ielts.service.IeltsEnrollinfoService;
import com.human.utils.PageView;

@Controller
@RequestMapping(value="/ielts/teacherstudent")
public class IeltsTeacherStudentController {
    
    @Resource
    private IeltsStudentInfoService ieltsStudentInfoService;
    
    @Resource
    private IeltsClassTypeService ieltsclassTypeService;
    
    @Resource
    private IeltsStudentBookService ieltsStudentBookService;
    
    @Resource
    private IeltsEnrollinfoService ieltsEnrollinfoService;
    
    /**
     * 学生信息页面
     * @return
     */
    @RequestMapping(value="/studentview")
    public ModelAndView studentview(){
        return new ModelAndView("/ielts/teacherstudent/student");
    }
    
    /**
     * 学生分数详细页面
     * @return
     */
    @RequestMapping(value="/enrollinfoview")
    public ModelAndView enrollinfoview(){
        return new ModelAndView("/ielts/teacherstudent/enrollinfo");
    }

    /**
     * 获取教师学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView getstudentinfo(PageView pageView,IeltsStudentInfo ieltsStudentInfo){
        return ieltsStudentInfoService.getteacherstudentinfo(pageView,ieltsStudentInfo);
    }
    
    /**
     * 查询学员基础信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectstudentinfo")
    public Map<String, Object> selectstudentinfo(IeltsStudentInfo ieltsStudentInfo){
        return ieltsStudentInfoService.selectstudentinfo(ieltsStudentInfo);
    }
    
    /**
     * 分页获取学生分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getenrollinfo")
    public PageView getenrollinfo(PageView pageView,IeltsEnrollInfo ieltsEnrollInfo){
        return ieltsEnrollinfoService.getenrollinfo(pageView, ieltsEnrollInfo);
    }
    
    /**
     * 查询学员分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectenrollinfo")
    public Map<String, Object> selectenrollinfo(IeltsEnrollInfo ieltsEnrollInfo){
        return ieltsEnrollinfoService.selectenrollinfo(ieltsEnrollInfo);
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
