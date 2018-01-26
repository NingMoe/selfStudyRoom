package com.human.activity.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
import com.human.activity.entity.ActivityInfo;
import com.human.activity.entity.BuyerInfoDto;
import com.human.activity.entity.OrderInfo;
import com.human.activity.entity.OrderRefundInfo;
import com.human.activity.entity.Product;
import com.human.activity.service.ActivityInfoService;
import com.human.activity.service.BuyerInfoService;
import com.human.activity.service.OrderInfoService;
import com.human.activity.service.OrderRefundInfoService;
import com.human.activity.service.ProductService;
import com.human.utils.PageView;


/**
 * 
 * @author liuwei63
 *
 */
@Controller
@RequestMapping("/basic/activity/")
public class ActivityInfoController {
    
    private final Logger logger = LogManager.getLogger(ActivityInfoController.class);
    
    @Resource
    private ActivityInfoService  aIService;
    
    @Resource
    private ProductService  productService;
    
    @Resource
    private BuyerInfoService buyerInfoService;
    
    @Resource
    private OrderInfoService orderInfoService;
    
    @Resource
    private OrderRefundInfoService orderRefundInfoService;
    
    /**
     * 活动列表页
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("index")
    public ModelAndView query( ) {
        ModelAndView mav=new ModelAndView("/activity/list");
        return mav;
    }
    
    /**
     * 分页查询
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("query")
    @ResponseBody
    public PageView queryActivityByPage(PageView pageView,ActivityInfo info) {
        logger.info("分页查询---");
        return  aIService.queryActivityByPage(pageView,info);
    }
    
    /**
     * 跳转新增界面
     * @param model
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mav=new ModelAndView("/activity/add");       
        return mav;
    }
    
    /**
     * 保存新增活动
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="add")
    @ResponseBody
    public Map<String, Object> addActivity(String jsonStrings,HttpServletRequest request) {
        logger.info("保存新增活动");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>(16);
        try {            
            map=aIService.addActivity(jsonStrings,request);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("新增活动失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "新增活动失败！");
        }
        return map;
    }
    
    /**
     * 进入编辑界面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mav = new ModelAndView("/activity/edit");
        ActivityInfo info=aIService.selectByPrimaryKey(id);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime=sdf.format(info.getStartTime());
        String endTime=sdf.format(info.getEndTime());
        List<Product> productList=productService.selectByActivtiyId(info.getId());
        mav.addObject("info", info);
        mav.addObject("prodList", productList);
        mav.addObject("startTime", startTime);
        mav.addObject("endTime", endTime);       
        return mav;
    }
    
    /**
     * 校验商品数目
     * @param productId
     * @param newTotal
     * @return
     */
    @RequestMapping(value="checkProductNumber")
    @ResponseBody
    public Map<String, Object> checkProductNumber(Long productId,Long newTotal) {
        logger.info("校验商品数目");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>(16);
        try {            
            map=productService.checkProductNumber(productId,newTotal);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("校验商品数目失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "校验商品数目失败！");
        }
        return map;
    }
    
    /**
     * 编辑活动
     * @param file1
     * @param rs
     * @return
     */
    @RequestMapping(value="edit")
    @ResponseBody
    public Map<String, Object> editActivity(String jsonStrings,HttpServletRequest request) {
        logger.info("编辑活动");
        Map<String,Object> map = new ConcurrentHashMap<String, Object>(16);
        try {            
            map=aIService.editActivity(jsonStrings,request);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("编辑活动失败！",e.getMessage());
            map.put("flag", false);
            map.put("msg", "编辑活动失败！");
        }
        return map;
    }
    
    /**
     * 支付明细列表页
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("lookPayDetails")
    public ModelAndView lookPayDetails(Long id) {
        ModelAndView mav=new ModelAndView("/activity/payDetails");
        ActivityInfo info=aIService.selectByPrimaryKey(id);
        String accountValid=info.getAccountValid();
        mav.addObject("activityId",id);
        mav.addObject("accountValid",accountValid);
        return mav;
    }
    
    /**
     * 分页查询支付明细
     * @param managerUser
     * @param pageView
     * @return
     */
    @RequestMapping("queryPayDetails")
    @ResponseBody
    public PageView queryPayDetailsByPage(PageView pageView,BuyerInfoDto info) {
        logger.info("分页查询支付明细---");
        return  buyerInfoService.queryPayDetailsByPage(pageView,info);
    }
    
    /**
     * 跳转发起退款页面
     * @param managerUser
     * @param pageNow
     * @return
     */
    @RequestMapping("refundOrder")
    public ModelAndView refundOrder(String buyInfoIds) {
        ModelAndView mav=new ModelAndView("/activity/refundOrder");
        mav.addObject("buyInfoIds",buyInfoIds);
        return mav;
    }
    
    /**
     * 发起退款并处理相应逻辑
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="refundOrderInfo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> refundOrderInfo(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>(16);
        logger.info("退款订单并处理相应逻辑-----");
        try {
            map=orderInfoService.refundOrderInfo(request, response);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("发起退款异常:" + e.getMessage());
            map.put("flag", false);
            map.put("message", "发起退款异常!");
        }
       return map; 
    }
    
    /**
     * 查看退款明细
     * @param model
     * @param resourcesId
     * @param type
     * @return
     */
    @RequestMapping(value="getRefundDetailByOrderNo")
    public ModelAndView getRefundDetailById(HttpServletRequest request,HttpServletResponse response,String orderNo){
        ModelAndView mav=new ModelAndView("/activity/orderRefundDetail");
        try{
            // 通过商户订单号查询退款订单
            OrderRefundInfo orderRefundInfo = orderRefundInfoService.selectByOrderNo(orderNo);
            //通过退款订单中的商户订单号查询支付订单
            OrderInfo orderInfo=orderInfoService.getOrderInfoByOrderNo(orderNo);
            if(orderRefundInfo.getRefundRecvAccout()==null ||"".equals(orderRefundInfo.getRefundRecvAccout())){
                orderRefundInfoService.updateRefundDetail(orderRefundInfo,request,response);   
            }
            mav.addObject("orderRefundInfo",orderRefundInfo);
            mav.addObject("orderInfo",orderInfo);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("查看退款明细异常",e.getMessage());
        }        
        return mav;
    }
    
    /**
     * 导出活动列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelect")
    public Map<String, Object> exportSelect(String ids, HttpServletRequest request, HttpServletResponse response){
        logger.info("导出活动列表-----");
        return  aIService.exportSelect(ids, request, response);
    }
    
    /**
     * 导出支付明细
     * @return
     */
    @ResponseBody
    @RequestMapping(value="exportSelectPayInfo")
    public Map<String, Object> exportSelectPayInfo(Long activityId, HttpServletRequest request, HttpServletResponse response){
        logger.info("导出支付明细-----");
        return  aIService.exportSelectPayInfo(activityId, request, response);
    }
    
    
    /**
     * 活动封账
     * @param deleteIds
     * @return
     */
     @RequestMapping("closeAccount")
     @ResponseBody
     public Map<String, Object> closeAccount(String deleteIds) {
         logger.info("活动封账-----");
         return aIService.closeAccount(deleteIds);
     }
}
