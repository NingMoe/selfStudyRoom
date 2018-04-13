package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.OrderRefundInfo;

@Mapper
public interface OrderRefundInfoDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(OrderRefundInfo record);

    OrderRefundInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderRefundInfo record);

}