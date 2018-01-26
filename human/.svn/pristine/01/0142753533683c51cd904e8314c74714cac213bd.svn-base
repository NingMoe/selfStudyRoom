package com.human.activity.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.BuyerInfoDto;
import com.human.activity.entity.FrontOrderInfoParams;
import com.human.utils.PageView;

public interface BuyerInfoService {
    /**
     * 分页查询
     * @param pageView
     * @param info
     * @return
     */
    PageView queryPayDetailsByPage(PageView pageView,BuyerInfoDto info);
    
    /**
     * 通过订单号查询
     * @param orderNo
     * @return
     */
    BuyerInfo selectByOrderNo(String orderNo);
    
    /**
     * 查看我的卡卷登录校验
     * @param info
     * @param request
     * @return
     */
    Map<String, Object> checkLoginInfo(FrontOrderInfoParams fops,HttpServletRequest request);
    
    /**
     * 查询我的卡卷
     * @param record
     * @return
     */
    List<BuyerInfoDto> selectMyCard(BuyerInfo record);
    
    /**
     * 查询学员号
     * @param studentName
     * @param mobile
     * @return
     */
    String getStudentCodeByPhone(String studentName, String mobile);
}


