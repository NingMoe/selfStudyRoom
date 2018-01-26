package com.wechat.student.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WechatStudentTestService {

    /**
     * 获取学生所有考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentAllTest(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生已完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentEndTest(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取学生未完成考试
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> getStudentNotEndTest(HttpServletRequest request, HttpServletResponse response);

}
