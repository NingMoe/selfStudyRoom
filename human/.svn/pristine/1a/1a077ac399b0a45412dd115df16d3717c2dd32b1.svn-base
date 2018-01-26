package com.human.ielts.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
@RequestMapping(value="/ielts/student")
public class IeltsStudentController {
    
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
        return new ModelAndView("/ielts/student/student");
    }
    
    /**
     * 学生信息新增页面
     * @return
     */
    @RequestMapping(value="/addstudentinfoview")
    public ModelAndView addstudentinfoview(){
        ModelAndView mav = new ModelAndView("/ielts/student/studentinfoadd");
        mav.addObject("classtype",ieltsclassTypeService.getclasstype());
        mav.addObject("book",ieltsStudentBookService.getbook());
        return mav;
    }
    
    /**
     * 学生信息上传页面
     * @return
     */
    @RequestMapping(value="/upstudentinfoview")
    public ModelAndView upstudentinfoview(){
        return new ModelAndView("/ielts/student/studentinfoup");
    }
    
    /**
     * 学生信息修改页面
     * @return
     */
    @RequestMapping(value="/studeninfochangetview")
    public ModelAndView studeninfochangetview(){
        ModelAndView mav = new ModelAndView("/ielts/student/studentinfochange");
        mav.addObject("classtype",ieltsclassTypeService.getclasstype());
        mav.addObject("book",ieltsStudentBookService.getbook());
        return mav;
    }
    
    /**
     * 学生分数详细页面
     * @return
     */
    @RequestMapping(value="/enrollinfoview")
    public ModelAndView enrollinfoview(){
        return new ModelAndView("/ielts/student/enrollinfo");
    }
    
    /**
     * 学生分数新增页面
     * @return
     */
    @RequestMapping(value="/addenrollinfoview")
    public ModelAndView addenrollinfoview(){
        return new ModelAndView("/ielts/student/enrollinfoadd");
    }
    
    /**
     * 学生分数修改页面
     * @return
     */
    @RequestMapping(value="/changeenrollinfoview")
    public ModelAndView changeenrollinfoview(){
        return new ModelAndView("/ielts/student/enrollinfochange");
    }
    
    /**
     * 获取学生信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/query")
    public PageView getstudentinfo(PageView pageView,IeltsStudentInfo ieltsStudentInfo){
        return ieltsStudentInfoService.getstudentinfo(pageView,ieltsStudentInfo);
    }
    
    /**
     * 新增学员基础信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertstudentinfo")
    public Map<String, Object> insertstudentinfo(IeltsStudentInfo ieltsStudentInfo, String classids, String bookids, String teachermails){
        return ieltsStudentInfoService.insertstudentinfo(ieltsStudentInfo, classids, bookids, teachermails);
    }
    
    /**
     * 修改学员基础信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updatestudentinfo")
    public Map<String, Object> updatestudentinfo(IeltsStudentInfo ieltsStudentInfo, String classids, String bookids, String teachermails){
        return ieltsStudentInfoService.updatestudentinfo(ieltsStudentInfo, classids, bookids, teachermails);
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
     * 初始化班级类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getclasstype")
    public Map<String, Object> getclasstype(){
        return ieltsclassTypeService.getclasstype();
    }
    
    /**
     * 初始化书籍
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getbook")
    public Map<String, Object> getbook(){
        return ieltsStudentBookService.getbook();
    }
    
    /**
     * 批量删除学生信息和分数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletestudentinfo")
    public Map<String, Object> deletestudentinfo(String ids){
        return ieltsStudentInfoService.deletestudentinfo(ids);
    }
    
    /**
     * 批量上传学生信息和分数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upstudentinfo")
    public Map<String, Object> upstudentinfo(HttpServletRequest request){
        return ieltsStudentInfoService.upstudentinfo(request);
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
     * 新增学员分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertenrollinfo")
    public Map<String, Object> insertenrollinfo(IeltsEnrollInfo ieltsEnrollInfo){
        return ieltsEnrollinfoService.insertenrollinfo(ieltsEnrollInfo);
    }
    
    /**
     * 删除学员分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deleteenrollinfo")
    public Map<String, Object> deleteenrollinfo(IeltsEnrollInfo ieltsEnrollInfo){
        return ieltsEnrollinfoService.deleteenrollinfo(ieltsEnrollInfo);
    }
    
    /**
     * 批量删除学员分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletesenrollinfo")
    public Map<String, Object> deletesenrollinfo(String ids){
        return ieltsEnrollinfoService.deletesenrollinfo(ids);
    }
    
    /**
     * 修改学员分数信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateenrollinfo")
    public Map<String, Object> updateenrollinfo(IeltsEnrollInfo ieltsEnrollInfo){
        return ieltsEnrollinfoService.updateenrollinfo(ieltsEnrollInfo);
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
