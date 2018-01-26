package com.human.resume.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.resume.entity.InterAppr;
import com.human.resume.service.InterviewApprovalService;
import com.human.utils.PageView;

@Controller
@RequestMapping("/recruit/InterAppr/")
public class InterviewApprovalController {
    
    @Resource
    private InterviewApprovalService approvalService;

    @RequestMapping("index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/recruitment/InterAppr/list");
        return mav;
    }
    
    @RequestMapping("endIndex")
    public ModelAndView endIndex(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/recruitment/InterAppr/endList");
        return mav;
    }
    
    /**
     * 分页查询待审批的人
     * @return
     */
    @RequestMapping(value="query")
    @ResponseBody
    public PageView query(PageView pageView,InterAppr ia){
        return  approvalService.queryToAppr(pageView, ia);
    }
    
    /**
     * 分页查询待审批的人
     * @return
     */
    @RequestMapping(value="queryEnd")
    @ResponseBody
    public PageView queryEnd(PageView pageView,InterAppr ia){
        return  approvalService.queryEndAppr(pageView, ia);
    }
}
