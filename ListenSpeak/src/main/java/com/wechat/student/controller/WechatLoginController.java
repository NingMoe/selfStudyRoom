package com.wechat.student.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wechat.student.service.WechatLoginService;

@Controller
@RequestMapping(value="/wechat/binding/student")
public class WechatLoginController {
    
    @Resource
    private WechatLoginService wechatLoginService;
    
    /**
     * 微信端学生绑定页
     * @return
     */
    @RequestMapping(value="/bindingview")
    public ModelAndView bindingview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/wechat/student/binding");
    }
    
    /**
     * 微信跳转页
     * @return
     */
    @RequestMapping(value="/wxview")
    public ModelAndView wxview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(wechatLoginService.getwxview(request, response));
    }
    
    /**
     * 微信接收页
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(wechatLoginService.getview(request, response));
    }
    
    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/isRegister", method = RequestMethod.POST)
    public Map<String, Object> isRegister(String phone){
        return wechatLoginService.isRegister(phone);
    }
    
    /**
     * 获取登录短信验证码
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getLoginShortMsg", method = RequestMethod.POST)
    public Map<String, Object> getLoginShortMsg(String phone){
        return wechatLoginService.getLoginShortMsg(phone);
    }
    
    /**
     * 绑定验证
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/studentLogin", method = RequestMethod.POST)
    public Map<String, Object> studentLogin(String phone, String short_msg, String password, HttpServletRequest request){
        return wechatLoginService.studentLogin(phone, short_msg, password, request);
    }
    
    /**
     * 完善姓名
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addName")
    public Map<String, Object> addName(String name, String sex, HttpServletRequest request){
        return wechatLoginService.addName(name, sex, request);
    }

}
