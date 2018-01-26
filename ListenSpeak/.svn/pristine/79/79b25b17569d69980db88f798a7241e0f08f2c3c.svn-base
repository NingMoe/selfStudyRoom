package com.wechat.student.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WechatLoginService {
    

    /**
     * 微信跳转
     * @param request
     * @param response
     * @return
     */
    public String getwxview(HttpServletRequest request, HttpServletResponse response);

    /**
     * 跳转接收
     * @param request
     * @param response
     * @return
     */
    public String getview(HttpServletRequest request, HttpServletResponse response);

    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    public Map<String, Object> isRegister(String phone);

    /**
     * 获取登录短信验证码
     * @param phone
     * @return
     */
    public Map<String, Object> getLoginShortMsg(String phone);

    /**
     * 绑定验证
     * @param phone
     * @param msg
     * @param unionid
     * @return
     */
    public Map<String, Object> studentLogin(String phone, String short_msg, String password, HttpServletRequest request);

    /**
     * 完善姓名
     * @param name
     * @param request
     * @return
     */
    public Map<String, Object> addName(String name, String sex, HttpServletRequest request);
}
