package com.human.front.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.human.basic.entity.SmsRecord;
import com.human.bpm.entity.ActNode;
import com.human.recruitment.service.HrPositionService;
import com.human.resume.entity.ResumeBase;
import com.human.utils.Constants;

@Controller
@RequestMapping(value = "/front/interview/")
public class FrontInterviewController {
    
    private final  Logger logger = LogManager.getLogger(FrontInterviewController.class);

    @Autowired
    private HrPositionService positionService;
    
    /**
     * 进入面试主页
     * @return
     */
    @RequestMapping(value="toInterview")
    public ModelAndView toInterview(HttpServletRequest request){
        logger.info("进入面试主页");
        ModelAndView mav = new ModelAndView("/front/interview/main");
       /* String openId = (String) request.getSession().getAttribute(Constants.OPENID);
        *//**
         * 根据OPENID获取投递记录数以及消息记录数
         *//*
        Integer tdCnt = positionService.getFrontResumeCntByOpenId(openId);
        *//**
         * 根据OPENID获取电话  进行查询未读短信记录数
         *//*   
        Integer smsCnt = positionService.getFrontResumeCntByOpenId(openId);*/
        /*mav.addObject("tdCnt", tdCnt);
        mav.addObject("smsCnt", smsCnt);*/
        return mav;
    }
    
    /**
     * 进入简历投递记录页面
     * @return
     */
    @RequestMapping(value="toTdjl")
    public ModelAndView toTdjl(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/front/interview/tdjl");
        String openId = (String) request.getSession().getAttribute(Constants.OPENID);
        /**
         * 根据OPENID获取投递记录
         */
        List<ResumeBase> list = positionService.getFrontResumeByOpenId(openId);
        mav.addObject("tdjl", list);
        return mav;
    }
    
    /**
     * 进入简历投递记录页面
     * @return
     */
    @RequestMapping(value="toTddetail")
    public ModelAndView toTddetail(ResumeBase resumeBase){
        ModelAndView mav = new ModelAndView("/front/interview/tddetail");
        //根据简历ID获取流转记录及状态
        List<ActNode> nodes = positionService.getNodesByResumeId(resumeBase.getId().intValue());
        mav.addObject("nodes", nodes);
        mav.addObject("resumeBase", resumeBase);
        return mav;
    }
    
    
    /**
     * 进入消息记录页面
     * @return
     */
    @RequestMapping(value="toMessjl")
    public ModelAndView toMessjl(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/front/interview/messjl");
        String openId = (String) request.getSession().getAttribute(Constants.OPENID);
        /**
         * 根据OPENID获取投递记录
         */
        List<ResumeBase> list = positionService.getFrontResumeByOpenId(openId);
        mav.addObject("tdjl", list);
        return mav;
    }
    
    /**
     * 进入消息记录页面
     * @return
     */
    @RequestMapping(value="toMxdetail")
    public ModelAndView toMxdetail(ResumeBase resumeBase){
        ModelAndView mav = new ModelAndView("/front/interview/mxdetail");
        List<SmsRecord> records = positionService.getSmsByResumeId(resumeBase.getId().intValue());
        SmsRecord currRecord = null;
        if(records!=null && records.size()>0){
            currRecord = records.get(0);
            records.remove(0);
        }
        mav.addObject("records", records);
        mav.addObject("currRecord", currRecord);
        mav.addObject("resumeBase", resumeBase);
        return mav;
    }

}