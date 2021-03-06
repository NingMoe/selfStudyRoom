package cn.xdf.pay.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cn.xdf.pay.annotation.WriteDataSource;
import cn.xdf.pay.dao.SystemOrderDao;
import cn.xdf.pay.domain.SystemOrder;

@Service
public class SystemOrderService {
	
	private static Logger logger = LoggerFactory.getLogger(SystemOrderService.class);
	
	@Autowired
	private SystemOrderDao systemOrderDao;
	
	@WriteDataSource
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,readOnly=false,rollbackFor=Exception.class)
	public int saveSystemOrder(SystemOrder systemOrder){
		logger.info("------保存调用系统支付订单------");
		return this.systemOrderDao.insertSelective(systemOrder);		
	}
	
}
