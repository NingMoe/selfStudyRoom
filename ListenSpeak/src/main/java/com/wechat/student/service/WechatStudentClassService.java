package com.wechat.student.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WechatStudentClassService {
    
    /**
     * 获取学生班级数量
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getCount(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生所有班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllClass(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生待加入班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotInClass(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生已经加入的所有班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllInClass(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生已经加入还未结课的班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndInClass(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生已经加入已经结课的班级
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndInClass(HttpServletRequest request, HttpServletResponse response);
}
