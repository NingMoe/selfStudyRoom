package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.SystemPay;

@Mapper
public interface SystemPayDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(SystemPay record);

    SystemPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemPay record);

}