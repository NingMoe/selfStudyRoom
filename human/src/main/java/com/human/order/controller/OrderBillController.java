package com.human.order.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.order.service.OrderBillService;

@Controller
@RequestMapping("/finance/weixin")
public class OrderBillController {
	
	private final  Logger logger = LogManager.getLogger(OrderBillController.class);
	
	@Resource
	private OrderBillService orderBillService;
	
	@RequestMapping(value="queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request,HttpServletResponse response){
	    Map<String,Object> result = new HashMap<String,Object>();
	    orderBillService.downLoadWxOrderBill(request,response);
	    return result;
	     
	}
	
	/**
	 * 微信对账单首页
	 * @return
	 */
	@RequestMapping("index")
    public ModelAndView index() {	    
	    ModelAndView mav=new ModelAndView("/finance/weixin/list");	    
	    return mav;
	}
	
}