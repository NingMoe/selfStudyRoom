package com.human.front.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.human.order.entity.AccessTokenRequestHandler;
import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.RecruitAcceptanceService;



/**
 * 微信服务端
 * @author 刘威
 * @dateTime 2016-10-09
 */
@Controller
@RequestMapping("/s/weixin/")
public class WeiXinController{
	
	private final Logger logger = LogManager.getLogger(WeiXinController.class);
	
    @Resource
	private RecruitAcceptanceService recruitAcceptanceService;
	
	
	
	@Transactional
    @RequestMapping(value="oauth")
    public ModelAndView oauth(HttpServletRequest request,HttpServletResponse response){
	    ModelAndView mav = new ModelAndView("");
	    try{
	        String code = request.getParameter("code");
	        System.out.println("code ==="+code );
	        // 获取微信用户openid
	        String openId = AccessTokenRequestHandler.getOpenId(code);
	        HttpSession session = request.getSession(true);
	        session.setAttribute("openId", openId);
	        //通过微信openId去查询是否已经绑定求职者	
	        ResumeSeeker rs=recruitAcceptanceService.selectByOpenId(openId);
	        if(rs==null){//未绑定
	            mav = new ModelAndView("/front/resume/authorization");   	            
	        }else{//已绑定
	            mav = new ModelAndView("/front/resume/index");   
	        }
	        mav.addObject("openId", openId);
	    }catch(Exception e){
	        e.printStackTrace();
	        logger.error("获取微信用户openId失败!", e.getMessage());	        
	    }	    
	    return mav;
	}

	
	
	
	
	
}