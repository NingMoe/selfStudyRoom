package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.SystemOrder;

@Mapper
public interface SystemOrderDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(SystemOrder record);

    SystemOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemOrder record);
}