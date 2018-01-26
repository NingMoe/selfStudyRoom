package com.human.activity.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.human.activity.entity.FrontOrderInfoParams;
import com.human.activity.entity.OrderInfo;

public interface OrderInfoService {
    /**
     * 生成支付订单之前的校验
     * @param fops
     * @param request
     * @return
     */
    Map<String, Object> checkOrderInfo(FrontOrderInfoParams fops,HttpServletRequest request );
    
    /**
     * 生成订单
     * @param fops
     * @param request
     * @return
     */
    ModelAndView insertOrderInfo (FrontOrderInfoParams fops,HttpServletRequest request);
    
    
    /**
     * 获取H5支付参数
     * @param request
     * @param response
     * @param orderInfoId
     * @return
     */
    Map<String, Object> getWxPayParamById(HttpServletRequest request,HttpServletResponse response,long orderInfoId);
    
    /**
     * 支付成功更新订单信息及买家信息
     * @param request
     * @param response
     * @param orderInfoId
     * @return
     */
    Map<String, Object> queryOrderInfoById(HttpServletRequest request,HttpServletResponse response,long orderInfoId);
    
    /**
     * 支付失败调用微信关闭订单接口关闭原订单避免重复支付
     * @param request
     * @param response
     * @param orderInfoId
     * @return
     */
    Map<String, Object> closeOrderInfoById(HttpServletRequest request,HttpServletResponse response,long orderInfoId);
            
    /**
     * 发起退款并处理相应逻辑
     * @param request
     * @param response
     * @return
     */
    Map<String, Object> refundOrderInfo(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 微信支付回调
     * @param request
     * @param response
     * @return
     */
    String weixinNotify(HttpServletRequest request,HttpServletResponse response);
    
    /**
     * 通过主键查询订单
     * @param id
     * @return
     */
    OrderInfo selectByPrimaryKey(Long id);
    
    /**
     * 更新订单
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderInfo record);
    
    /**
     * 通过商户订单号查询订单
     * @param orderNo
     * @return
     */
    OrderInfo getOrderInfoByOrderNo(String orderNo);
    
    /**
     * H5支付保存订单并下单
     * @param fops
     * @param request
     * @return
     */
    Map<String, Object> payOrderInfoByH5(FrontOrderInfoParams fops,HttpServletRequest request,HttpServletResponse response );
}
