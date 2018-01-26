package com.human.binding.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.binding.service.WechatBindingService;

/**
 * 微信统一绑定
 * @author xdf-zx
 *
 */
@Controller
@RequestMapping(value="/wechat/binding")
public class WechatBindingController {
    
    @Resource
    private WechatBindingService wechatBindingService;
    
    /**
     * 功能测试页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/testloginview")
    public ModelAndView testloginview(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.setAttribute("binding_openid", "aaa");
        session.setAttribute("re_url", "asd");
        session.setAttribute("binding_email_addr", "zhouxin15");
        return new ModelAndView("/binding/testloginview");
    }
    
    /**
     * 功能测试页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/testview")
    public ModelAndView testview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("/binding/testview");
    }
    
    /**
     * 微信跳转页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/wxview")
    public ModelAndView wxview(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(wechatBindingService.wxview(request,response));
    }
    
    /**
     * 微信接收页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/view")
    public ModelAndView view(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView(wechatBindingService.view(request,response));
    }
    
    /**
     * 通过账号密码绑定
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertbindinginfo")
    public Map<String, Object> insertbindinginfo(HttpServletRequest request, HttpServletResponse response){
        return wechatBindingService.insertbindinginfo(request,response);
    }
    
    /**
     * 解绑操作
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/deletebindinginfo")
    public Map<String, Object> deletebindinginfo(HttpServletRequest request, HttpServletResponse response){
        return wechatBindingService.deletebindinginfo(request,response);
    }
}
