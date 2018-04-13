package cn.xdf.pay.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.xdf.pay.annotation.ReadDataSource;
import cn.xdf.pay.dao.PayMerchantDao;
import cn.xdf.pay.domain.CallSystem;
import cn.xdf.pay.domain.PayMerchant;

@Service
public class PayMerchantService {
	
	private static Logger logger = LoggerFactory.getLogger(PayMerchantService.class);
	
	@Autowired
	private PayMerchantDao payMerchantDao;
	
	@ReadDataSource
	public List<PayMerchant> selectAllPayMerchant(){
		logger.info("------查询全部微信支付商户------");
		return  this.payMerchantDao.selectAllPayMerchant();				
	}
	
	@ReadDataSource
	public PayMerchant selectByCallSystem(CallSystem callSystem){
		logger.info("------查询调用系统配置的微信支付商户------");
		return  this.payMerchantDao.selectByCallSystem(callSystem);	
	}
	
	@ReadDataSource
	public PayMerchant selectByOrderNo(String orderNo){
		logger.info("------查询订单的微信支付商户------");
		return  this.payMerchantDao.selectByOrderNo(orderNo);	
	}
}
