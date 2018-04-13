package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.OrderInfo;

@Mapper
public interface OrderInfoDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);
    
    /**
     * 通过订单号查询订单
     * @param orderNo
     * @return
     */
    OrderInfo selectOrderByOrderNo(String orderNo);

}