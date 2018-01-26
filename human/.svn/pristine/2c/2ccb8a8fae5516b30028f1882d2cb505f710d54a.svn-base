package com.human.activity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.human.activity.entity.OrderRefundInfo;

@Repository
public interface OrderRefundInfoDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderRefundInfo record);

    OrderRefundInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefundInfo record);
    
    /**
     * 通过退款单号查询退款单
     * @param orderRefundNo
     * @return
     */
    OrderRefundInfo selectByOrderRefundNo(String orderRefundNo);
    
    /**
     * 查询所有处于退款中的退款单
     * @return
     */
    List<OrderRefundInfo> getOrderRefundList();
    
    /**
     * 通过商户订单号查询退款单
     * @param orderNo
     * @return
     */
    OrderRefundInfo selectByOrderNo(String orderNo);
}