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

import com.human.ielts.entity.IeltsTeacherCertificate;
import com.human.ielts.entity.IeltsTeacherInfo;
import com.human.ielts.entity.IeltsTeacherSource;
import com.human.ielts.entity.IeltsTeacherTkt;
import com.human.ielts.service.IeltsTeacherCertificateService;
import com.human.ielts.service.IeltsTeacherInfoService;
import com.human.ielts.service.IeltsTeacherSourceService;
import com.human.ielts.service.IeltsTeacherTktService;

@Controller
@RequestMapping(value="/ielts/managerteacherinfo")
public class IeltsManagerteacherinfoController {
    
    @Resource
    private IeltsTeacherInfoService ieltsTeacherInfoService;
    
    @Resource
    private IeltsTeacherSourceService ieltsTeacherSourceService;
    
    @Resource
    private IeltsTeacherTktService ieltsTeacherTktService;
    
    @Resource
    private IeltsTeacherCertificateService ieltsTeacherCertificateService;
    
    /**
     * 教师学情统计页
     * @return
     */
    @RequestMapping(value="/teacherinfoview")
    public ModelAndView classview(){
        return new ModelAndView("/ielts/manager/teacherinfo");
    }
    
    /**
     * 查询雅思高分教师人数占比
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectieltsteachersource")
    public Map<String, Object> selectieltsteacher(IeltsTeacherSource ieltsTeacherSource){
        return ieltsTeacherSourceService.selectieltsteacher(ieltsTeacherSource);
    }
    
    /**
     * 查询TKT高分教师人数占比
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selecttktteacher")
    public Map<String, Object> selecttktteacher(IeltsTeacherTkt ieltsTeacherTkt){
        return ieltsTeacherTktService.selecttktteacher(ieltsTeacherTkt);
    }
    
    /**
     * 查询Celtaz证书人数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectceltazteachercount")
    public Map<String, Object> selectceltazteachercount(IeltsTeacherCertificate ieltsTeacherCertificate){
        return ieltsTeacherCertificateService.selectceltazteachercount(ieltsTeacherCertificate);
    }
    
    /**
     * 查询教师资格证人数
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectcertificatecount")
    public Map<String, Object> selectcertificatecount(IeltsTeacherCertificate ieltsTeacherCertificate){
        return ieltsTeacherCertificateService.selectcertificatecount(ieltsTeacherCertificate);
    }
    
    /**
     * 查询教师功底积分
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectallteacherintegral")
    public Map<String, Object> selectallteacherintegral(IeltsTeacherInfo ieltsTeacherInfo){
        return ieltsTeacherInfoService.selectallteacherintegral(ieltsTeacherInfo);
    }
    
    /**
     * 查询教师教研积分
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectallteacherintegralresearch")
    public Map<String, Object> selectallteacherintegralresearch(IeltsTeacherInfo ieltsTeacherInfo){
        return ieltsTeacherInfoService.selectallteacherintegralresearch(ieltsTeacherInfo);
    }
    
    /**
     * 查询教师教学成果积分
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectallteacherintegralteaching")
    public Map<String, Object> selectallteacherintegralteaching(IeltsTeacherInfo ieltsTeacherInfo){
        return ieltsTeacherInfoService.selectallteacherintegralteaching(ieltsTeacherInfo);
    }
    
    /**
     * 查询教师教学服务积分
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectallteacherintegralservice")
    public Map<String, Object> selectallteacherintegralservice(IeltsTeacherInfo ieltsTeacherInfo){
        return ieltsTeacherInfoService.selectallteacherintegralservice(ieltsTeacherInfo);
    }
    
    /**
     * 查询教师总积分
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/selectallteacherintegraltotal")
    public Map<String, Object> selectallteacherintegraltotal(IeltsTeacherInfo ieltsTeacherInfo){
        return ieltsTeacherInfoService.selectallteacherintegraltotal(ieltsTeacherInfo);
    }
    
    /**
     * 上传excel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upteacherintegral")
    public Map<String, Object> upteacherintegral(HttpServletRequest request){
        return ieltsTeacherInfoService.upteacherintegral(request);
    }
    
    /**
     * 上传往期数据excel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upteacherintegraldate")
    public Map<String, Object> upteacherintegraldate(HttpServletRequest request){
        return ieltsTeacherInfoService.upteacherintegraldate(request);
    }
    
    /**
     * 上传赛课excel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upteachermatchclass")
    public Map<String, Object> upteachermatchclass(HttpServletRequest request){
        return ieltsTeacherInfoService.upteachermatchclass(request);
    }
    
    /**
     * 上传往期赛课excel
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/upteachermatchclassdate")
    public Map<String, Object> upteachermatchclassdate(HttpServletRequest request){
        return ieltsTeacherInfoService.upteachermatchclassdate(request);
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
