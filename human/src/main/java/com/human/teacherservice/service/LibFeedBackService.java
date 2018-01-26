package com.human.teacherservice.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.teacherservice.entity.LibFeedBack;
import com.human.utils.PageView;

public interface LibFeedBackService {
    /**
     * 新增我的意见反馈
     * @param request
     * @param info
     * @return
     */
    Map<String, Object> saveMyFeedBack2(HttpServletRequest request,LibFeedBack info);
    
    /**
     * 分页查询意见反馈记录
     * @param pageView
     * @param libBook
     * @return
     */
    PageView query(PageView pageView, LibFeedBack info);
    
    /**
     * 导出全部意见反馈记录
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
}
