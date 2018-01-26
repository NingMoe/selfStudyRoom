package com.ls.spt.studentpc.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.question.entity.QuestionVoiceParam;
import com.ls.spt.studentpc.student.service.StudentZuoyeService;


@Controller
@RequestMapping(value="/studentpc/studentzuoye")
public class StudentZuoyeController {

    @Resource
    private StudentZuoyeService studentZuoyeService;
    
    /**
     * 作业页面
     * @return
     */
    @RequestMapping(value="/zuoyeview")
    public ModelAndView zuoyeview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/zuoye");
        return mav;
    }
    
    /**
     * 作业详情页面
     * @return
     */
    @RequestMapping(value="/studentzuoyeview")
    public ModelAndView studenttestview(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/studentzuoye");
        mav.addObject("not_end_zuoye", studentZuoyeService.getStudentNotEndZuoye(request, response));//未完成作业
        mav.addObject("end_zuoye", studentZuoyeService.getStudentEndZuoye(request, response, 1, 10));//已完成作业
        mav.addObject("student_growth",studentZuoyeService.selectGrowthTrajectory(request, response));
        return mav;
    }
    
    /**
     * 作业报告页面
     * @return
     */
    @RequestMapping(value="/studentzuoyebaogaoview")
    public ModelAndView studentzuoyebaogaoview(HttpServletRequest request, HttpServletResponse response, Integer zuoye_id, String class_code){
        ModelAndView mav = new ModelAndView("/studentpc/studentzuoyebaogao");
        mav.addObject("zuoye_info", studentZuoyeService.getZuoyeInfo(request, response, zuoye_id, class_code));//作业基础信息
        return mav;
    }
    
    /**
     * 获取作业试题
     * @param request
     * @param response
     * @param class_code
     * @param zuoye_id
     * @param th
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getZuoyeQuestion")
    public Map<String, Object> getZuoyeQuestion(HttpServletRequest request, HttpServletResponse response, String class_code, Integer zuoye_id, Integer th){
        return  studentZuoyeService.getZuoyeQuestion(request, response, class_code, zuoye_id, th);
    }
    
    /**
     * 获取学生作业成长轨迹
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentGrowthTrajectory")
    public Map<String, Object> getStudentAllZuoye(HttpServletRequest request, HttpServletResponse response){
        return studentZuoyeService.selectGrowthTrajectory(request, response);
    }
    
    /**
     * 获取学生已完成作业
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentEndZuoye")
    public Map<String, Object> getStudentEndZuoye(HttpServletRequest request, HttpServletResponse response, Integer pageNow, Integer pageSize){
        return studentZuoyeService.getStudentEndZuoye(request, response, pageNow, pageSize);
    }
    
    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getStudentNotEndZuoye")
    public Map<String, Object> getStudentNotEndZuoye(HttpServletRequest request, HttpServletResponse response){
        return studentZuoyeService.getStudentNotEndZuoye(request, response);
    }
    
    /**
     * 作业上传语音
     * @param param
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sogouParseZuoye")
    public Map<String, Object> sogouParseZuoye(QuestionVoiceParam param,HttpServletRequest request, HttpServletResponse response){
        return studentZuoyeService.sogouParseZuoye(param, request, response);
    }
}
