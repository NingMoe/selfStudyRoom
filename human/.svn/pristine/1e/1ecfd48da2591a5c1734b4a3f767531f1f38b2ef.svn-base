package com.human.binding.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.binding.entity.WechatTeacherBinding;

/**
 * 微信绑定通用接口
 * @author xdfzx
 *
 */
public interface WechatBindingService {

    /**
     * 获取微信跳转页面链接
     * @param request
     * @param response
     * @return
     */
    public String wxview(HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据接收微信参数决定跳转页面
     * @param request
     * @param response
     * @return
     */
    public String view(HttpServletRequest request, HttpServletResponse response);

    /**
     * 通过账号密码绑定
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> insertbindinginfo(HttpServletRequest request, HttpServletResponse response);

    /**
     * 解绑操作
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> deletebindinginfo(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 通过openId
     */
    public WechatTeacherBinding selectUserByOpenid(String openid);

}
