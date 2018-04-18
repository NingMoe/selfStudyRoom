package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.SystemCompanyOrder;

@Mapper
public interface SystemCompanyOrderDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(SystemCompanyOrder record);

    SystemCompanyOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemCompanyOrder record);

}