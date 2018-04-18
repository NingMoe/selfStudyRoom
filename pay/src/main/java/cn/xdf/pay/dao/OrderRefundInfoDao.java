package cn.xdf.pay.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.OrderRefundInfo;

@Mapper
public interface OrderRefundInfoDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderRefundInfo record);

    OrderRefundInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefundInfo record);
    
    /**
     * 通过订单号查询退款单
     * @param orderNo
     * @return
     */
    List<OrderRefundInfo> selectByOrderNo(String orderNo);
    
    /**
     * 查询某订单号对应的退款总金额
     * @param orderNo
     * @return
     */
    BigDecimal selectTotalRefundByOrderNo(String orderNo);
    
    /**
     * 查询系统所有处于退款中的退款单
     * @return
     */
    List<OrderRefundInfo> selectAll();
    
    /**
     * 通过退款单号查询退款单
     * @param orderRefundNo
     * @return
     */
    OrderRefundInfo selectByOrderRefundNo(String orderRefundNo);

}