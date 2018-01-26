package com.human.activity.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.human.activity.entity.OrderRefundInfo;

public interface OrderRefundInfoService {
    
    /**
     * 更新退款单退款进度
     * @param request
     * @param response
     */
    void checkValidOrderRefundInfo(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 更新退款单退款进度
     * @param orderRefundInfo
     * @param request
     * @param response
     */
    void updateRefundDetail(OrderRefundInfo orderRefundInfo,HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 通过商户订单号查询退款单
     * @param orderNo
     * @return
     */
    OrderRefundInfo selectByOrderNo(String orderNo);
}
