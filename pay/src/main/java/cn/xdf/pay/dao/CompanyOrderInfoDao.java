package cn.xdf.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import cn.xdf.pay.domain.CompanyOrderInfo;

@Mapper
public interface CompanyOrderInfoDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(CompanyOrderInfo record);

    CompanyOrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CompanyOrderInfo record);

    /**
     * 通过商户订单号查询订单
     * @param partnerTradeNo
     * @return
     */
    CompanyOrderInfo selectOrderInfoByOrderNo(String partnerTradeNo);
}