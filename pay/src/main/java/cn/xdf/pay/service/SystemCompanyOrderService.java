package cn.xdf.pay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.dao.SystemCompanyOrderDao;
import cn.xdf.pay.domain.SystemCompanyOrder;


@Service
public class SystemCompanyOrderService {
	
	private static Logger logger = LoggerFactory.getLogger(SystemCompanyOrderService.class);
	
	@Autowired
	private SystemCompanyOrderDao systemCompanyOrderDao;
	
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int saveSystemCompanyOrder(SystemCompanyOrder systemCompanyOrder){
		logger.info("------保存调用系统-企业付款零钱订单------");
		return this.systemCompanyOrderDao.insertSelective(systemCompanyOrder);		
	}
	
}
