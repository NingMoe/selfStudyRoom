package com.ls.spt.studentpc.student.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StudentInfoService {

    /**
     * 修改密码
     * @param old_password
     * @param new_password
     * @return
     */
    public Map<String, Object> changePassword(String old_password, String new_password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 退出
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> loginout(HttpServletRequest request, HttpServletResponse response);

    /**
     * 解绑
     * @param request
     * @param response
     * @return
     */
    public Map<String, Object> bindingout(HttpServletRequest request, HttpServletResponse response);

}
