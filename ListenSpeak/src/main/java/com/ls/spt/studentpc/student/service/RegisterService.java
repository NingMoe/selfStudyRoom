package com.ls.spt.studentpc.student.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {

    /**
     * 判断手机号是否注册过
     * @param phone
     * @return
     */
    public Map<String, Object> isRegister(HttpServletRequest request,String phone, String short_msg);

    /**
     * 获取登录短信验证码
     * @param phone
     * @return
     */
    public Map<String, Object> getLoginShortMsg(String phone);

    /**
     * 绑定验证
     * @param phone
     * @param short_msg
     * @param password
     * @param request
     * @return
     */
    public Map<String, Object> studentLogin(String phone, String name, String sex, String short_msg, String password, HttpServletRequest request);

}
