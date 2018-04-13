package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.OrderBill;

@Mapper
public interface OrderBillDao {
	
    int deleteByPrimaryKey(Long id);
 
    int insertSelective(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderBill record);

}