package com.human.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.human.order.entity.OrderBill;
@Repository
public interface OrderBillDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    int insertSelective(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderBill record);

    int updateByPrimaryKey(OrderBill record);
    
}