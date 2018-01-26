package com.human.teacherservice.service;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.teacherservice.entity.LibBookBuy;
import com.human.utils.PageView;

public interface LibBookBuyService {
    /**
     * 新增我的反馈
     * @param request
     * @param info
     * @return
     */
    Map<String, Object> saveMyFeedBack(HttpServletRequest request,LibBookBuy info);
    
    /**
     * 分页查询反馈记录
     * @param pageView
     * @param libBook
     * @return
     */
    PageView query(PageView pageView, LibBookBuy info);
    
    /**
     * 导出全部反馈记录
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> exportAll(HttpServletRequest request, HttpServletResponse response);
}
