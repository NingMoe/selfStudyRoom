package cn.xdf.pay.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.xdf.pay.domain.CallSystem;
import cn.xdf.pay.domain.PayMerchant;

@Mapper
public interface PayMerchantDao {
	
    int deleteByPrimaryKey(Long id);

    int insertSelective(PayMerchant record);

    PayMerchant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PayMerchant record);
    
    /**
     * 查询系统所有支付商户
     * @return
     */
    List<PayMerchant> selectAllPayMerchant();
    
    /**
     * 查询调用系统配置的微信支付商户
     * @param callSystem
     * @return
     */
    PayMerchant selectByCallSystem(CallSystem callSystem);
    
    /**
     * 通过订单号查询微信支付商户
     * @param orderNo
     * @return
     */
    PayMerchant selectByOrderNo(String orderNo);
    
    /**
     * 通过企业付款商户订单号查询微信支付商户
     * @param partnerTradeNo
     * @return
     */
    PayMerchant selectByPartnerTradeNo(String partnerTradeNo);

}