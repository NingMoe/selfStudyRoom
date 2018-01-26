package com.human.front.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.human.recruitment.entity.ResumeSeeker;
import com.human.recruitment.service.HrPositionService;
import com.human.teacherservice.service.impl.LibraryWxServiceImpl;
import com.human.utils.Constants;
import com.human.utils.JsonUtil;

@Controller
@RequestMapping(value = "/free/")
public class WxController {
    
    private final  Logger logger = LogManager.getLogger(WxController.class);
    
    @Autowired
    private HrPositionService positionService;
    
    /**
     * 统一处理微信过来的链接
     * @return
     * @throws IOException 
     */
    @RequestMapping(value="wx")
    public ModelAndView wx(HttpServletRequest request,HttpServletResponse response) throws IOException{
        logger.info("进入微信回调页面");
        ModelAndView mav = new ModelAndView("/front/home/wx");
        String openId = getOpenId(request);
        String callBack = request.getParameter("state");
        HttpSession session = request.getSession();
        String bindStatus = "";
        ResumeSeeker seeker = positionService.getResumeSeekerByOpenId(openId);
        if(seeker==null){
            bindStatus = Constants.NOBIND;
        }else{
            bindStatus = Constants.HASBIND;
        }
        session.setAttribute(Constants.OPENID, openId);
        session.setAttribute(Constants.BINDSTATUS, bindStatus);
        mav.addObject("callBack", callBack);
        logger.info("进入原始页面");
        return mav;
    }
    
    private String getOpenId(HttpServletRequest request) {
        logger.info("获取微信OPENID---START");
        String code =  request.getParameter("code");
        String getTeacherUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?";// 获取班级信息接口地址
        JsonUtil jsonUtil = new JsonUtil();
        List<NameValuePair> namelist = new ArrayList<NameValuePair>();
        namelist.add(new BasicNameValuePair("appid", "wx762fd407faa3be61"));
        namelist.add(new BasicNameValuePair("secret", "34ea4a0fa63044e49d973058c9a5e694"));
        namelist.add(new BasicNameValuePair("code", code));
        namelist.add(new BasicNameValuePair("grant_type", "authorization_code"));
        JSONObject jsonObject = jsonUtil.getJson(getTeacherUrl, namelist);
        String openid = LibraryWxServiceImpl.getJSONObjectString(jsonObject, "openid");
        logger.info("获取微信OPENID---OVER");
        return openid;
    }
}
