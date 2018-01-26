package com.human.order.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.human.order.entity.OrderBill;

public interface OrderBillService {
	
	int insert(OrderBill record);
	
	void downLoadWxOrderBill(HttpServletRequest request,HttpServletResponse response);
	
}
