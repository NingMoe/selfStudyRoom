package com.human.activity.dao;

import org.springframework.stereotype.Repository;
import com.human.activity.entity.OrderInfo;

@Repository
public interface OrderInfoDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    /**
     * 通过订单号查询支付订单
     */
    OrderInfo getOrderInfoByOrderNo(String orderNo); 
}