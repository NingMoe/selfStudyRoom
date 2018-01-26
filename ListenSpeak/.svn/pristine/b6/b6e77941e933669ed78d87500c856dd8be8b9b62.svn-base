package com.ls.spt.studentpc.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ls.spt.student.entity.StudentUser;
import com.ls.spt.student.service.StudentUserService;
import com.ls.spt.studentpc.student.service.RegisterService;
import com.ls.spt.utils.Common;
import com.ls.spt.utils.StudentPcConstants;
import com.ls.spt.utils.wxutils.WxUtil;

/**
 * 学生PC登录相关
 * @author xdfhf
 *
 */
@Controller
@RequestMapping(value="/studentpc/studentregister")
public class RegisterController {
    
    @Resource
    private RegisterService registerService;
    
    @Resource
    private StudentUserService studentUserService;
    
    
    @Value("${wechat.appId}") 
    private String openAppId;
    
    @Value("${wechat.appSecret}") 
    private String openAppSecret;
    
    /**
     * 进入学生二维码扫码登录页面
     */
    @RequestMapping(value="/qrcode")
    public ModelAndView qrcode(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("/studentpc/qrcode");
        mav.addObject("openAppId", openAppId);
        return mav;
    }
    
    /**
     * 用户登录
     * @param webUser 用户个人信息
     * @param httpSession 登录后用户信息存入session
     * @return 返回验证结果
     */
    @RequestMapping(value="userLogin")
    public ModelAndView userLogin(String code,String state,HttpServletRequest request,HttpSession httpSession){
        ModelAndView mav=new ModelAndView();
        if(null==code||code.trim().length()==0){
            mav.setViewName("/error/denied");
            mav.addObject("msg", "授权信息获取失败，请使用微信打开链接!");
            return mav;
        }
        System.out.println("code======================="+code);
        String unionid=null;
        System.out.println("state====="+state);
        if(Common.isMobileRequest(request)){
            System.out.println("===========手机登陆===============");
        }else{
            System.out.println("===========电脑登陆===============");
            Map<String,String> map=WxUtil.getAccessToken(code, openAppId, openAppSecret);
            System.out.println("===========电脑登陆获取unionid结果===============\n"+map.toString());
            unionid=map.get("unionid");
        }
        System.out.println("unionid======================="+unionid);
        if(null==unionid||unionid.trim().length()==0){
            mav.setViewName("/error/denied");
            mav.addObject("msg", "授权失败,无法获取unionid，请重试!");
            return mav;
        }
        httpSession.setAttribute(StudentPcConstants.STUDENT_UNIONID, unionid);
        if(state.indexOf(",")!=-1){
            state=state.substring(0,state.indexOf(","));
        }
        StudentUser studentUser = studentUserService.queryUserByUnionId(unionid);
        if(studentUser!=null){
            httpSession.setAttribute(StudentPcConstants.STUDENT_USER, studentUser);
            return new ModelAndView("redirect:/studentpc/studentinfo/view.html");
        }else{
            return new ModelAndView("redirect:/studentpc/studentregister/registerview.html");
        }
    }

    /**
     * 注册页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/registerview")
    public ModelAndView registerview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/studentpc/register");
    }
    
    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/isRegister", method = RequestMethod.POST)
    public Map<String, Object> isRegister(HttpServletRequest request, String phone, String short_msg){
        return registerService.isRegister(request, phone, short_msg);
    }
    
    /**
     * 获取登录短信验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getLoginShortMsg", method = RequestMethod.POST)
    public Map<String, Object> getLoginShortMsg(String phone){
        return registerService.getLoginShortMsg(phone);
    }
    
    /**
     * 注册
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/studentLogin", method = RequestMethod.POST)
    public Map<String, Object> studentLogin(String phone, String password, String short_msg, String name, String sex, HttpServletRequest request){
        return registerService.studentLogin(phone, password, short_msg, name, sex, request);
    }
}
