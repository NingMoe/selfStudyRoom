package com.human.activity.controller;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.human.activity.entity.FrontOrderInfoParams;
import com.human.activity.service.BuyerInfoService;
import com.human.activity.service.OrderInfoService;
import com.human.activity.service.OrderRefundInfoService;




/**
 * 订单管理控制层
 * @author liuwei63
 *
 */
@Controller
@RequestMapping(value="/activity/orderInfo/")
public class OrderInfoController {
    
    private final Logger logger = LogManager.getLogger(OrderInfoController.class);
    
    @Resource
    private OrderInfoService orderInfoService;
    
    @Resource
    private OrderRefundInfoService orderRefundInfoService;
        
    @Resource
    private BuyerInfoService buyerInfoService;
    
    
    /**
     * 生成业务支付订单之前的校验(公众号支付)
     * @param fops
     * @param request
     * @return
     */
    @RequestMapping(value="checkOrderInfo")
    @ResponseBody
    public Map<String, Object> checkOrderInfo(FrontOrderInfoParams fops,HttpServletRequest request ){ 
        logger.info("生成业务支付订单之前的校验----");
        return orderInfoService.checkOrderInfo(fops, request);   
    }
    
    
    /**
     * H5支付保存订单并下单
     * @param fops
     * @param request
     * @return
     */
    @RequestMapping(value="payOrderInfoByH5")
    @ResponseBody
    public Map<String, Object> payOrderInfoByH5(FrontOrderInfoParams fops,HttpServletRequest request,HttpServletResponse response){ 
        logger.info("H5支付保存订单并下单----");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map =orderInfoService.payOrderInfoByH5(fops, request,response); 
        }catch(Exception e){
            e.printStackTrace();
            map.put("flag", false);
            map.put("message", "H5支付保存订单并下单失败!");
        }
        return map ;
    }
   
    /**
     * 生成业务支付订单
     * @param activityId
     * @param testSiteCode
     * @param httpSession
     * @param flag 1:PC端请求 ，2：移动端请求
     * @return
     */ 
    @RequestMapping(value="insertOrderInfo")
    public ModelAndView insertOrderInfo(FrontOrderInfoParams fops,HttpServletRequest request){   
        logger.info("生成业务支付订单----");
        return orderInfoService.insertOrderInfo(fops, request);   
    }
            
    /**
     * 获取H5支付参数
     * @param request
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getWxPayParamById",method=RequestMethod.POST)
    public Map<String, Object> getWxPayParamById(HttpServletRequest request,HttpServletResponse response,long orderInfoId){
        logger.info("获取H5支付参数----");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=orderInfoService.getWxPayParamById(request, response, orderInfoId);                   
        }catch(Exception e){
            e.printStackTrace();
            logger.error("获取H5支付参数失败:",e.getMessage());            
        }
        return map;     
    }

    
    /**
     * 支付成功更新订单信息及买家信息
     * @param request
     * @param 
     * @return
     * @throws Exception 
     */
    @ResponseBody
    @RequestMapping(value="queryOrderInfo",method=RequestMethod.POST)
    public synchronized Map<String, Object> queryOrderInfoById(HttpServletRequest request,HttpServletResponse response,long orderInfoId) throws Exception{
        logger.info("支付成功更新订单信息及买家信息----");
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            map=orderInfoService.queryOrderInfoById(request, response, orderInfoId); 
        }catch(Exception e){
            e.printStackTrace();
            logger.error("支付成功更新订单信息及买家信息失败:",e.getMessage());   
        }
        return map; 
    }
    
        
    /**
     * 支付失败调用微信关闭订单接口关闭原订单避免重复支付
     * @param request
     * @param orderInfoId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="closeOrderInfo",method=RequestMethod.POST)
    public Map<String, Object> closeOrderInfoById(HttpServletRequest request,HttpServletResponse response,long orderInfoId)throws Exception{
        logger.info("支付失败调用微信关闭订单接口关闭订单----");
        Map<String, Object> map = new HashMap<String, Object>();        
        try {
            map=orderInfoService.closeOrderInfoById(request, response, orderInfoId);                    
        }catch(Exception e) {
            e.printStackTrace();
            logger.error("支付失败调用微信关闭订单接口关闭订单失败:",e.getMessage());               
        }
        return map;     
    }
    
    
}
