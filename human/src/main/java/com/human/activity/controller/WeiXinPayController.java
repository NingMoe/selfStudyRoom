package com.human.activity.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.human.activity.entity.ActivityInfo;
import com.human.activity.entity.BuyerInfo;
import com.human.activity.entity.BuyerInfoDto;
import com.human.activity.entity.FrontOrderInfoParams;
import com.human.activity.entity.OrderInfo;
import com.human.activity.entity.Product;
import com.human.activity.service.ActivityInfoService;
import com.human.activity.service.BuyerInfoService;
import com.human.activity.service.OrderInfoService;
import com.human.activity.service.ProductService;
import com.human.basic.entity.SmsRecord;
import com.human.basic.service.SmsTempService;
import com.human.order.entity.AccessTokenRequestHandler;
import com.human.order.entity.PrepayIdRequestHandler;
import com.human.order.utils.ConstantUtil2;
import com.human.order.utils.TenpayUtil;
import com.human.order.utils.WXUtil;
import com.human.order.utils.XMLUtil;


/**
 * 微信支付服务端
 * @author 刘威
 * @dateTime 2016-10-09
 */
@Controller
@RequestMapping("/activity/weixinPay/")
public class WeiXinPayController{
	
	private final Logger logger = LogManager.getLogger(WeiXinPayController.class);
	
	@Resource
	private OrderInfoService orderInfoService;
		
	@Resource
    private ActivityInfoService  aIService;
    
    @Resource
    private ProductService  productService;
    
    @Resource
    private SmsTempService smsService;
    
    @Resource
    private BuyerInfoService buyerInfoService;
    
    
    /**
     * 进入抢卷页面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("index")
    public ModelAndView toIndex(Long activityId) {
        ModelAndView mav = new ModelAndView("/activity/frontWeixin/index"+activityId);
        ActivityInfo info=aIService.selectByPrimaryKey(activityId);
        List<Product> productList=productService.selectByActivtiyId(info.getId());
        mav.addObject("info", info);
        mav.addObject("prodList", productList); 
        mav.addObject("price", productList.get(0).getPrice());
        return mav;
    }
    
    
    /**
     * 首页发送手机号短信验证码
     * @param telNumber 手机号
     * @return 发送验证码的结果
     */
    @ResponseBody
    @RequestMapping(value="sendCode")
    public Map<String,Object>sendCode(String telNumber,String type){
        Map<String,Object> map = new HashMap<String, Object>();    
        try{
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setSendTel(telNumber);
            smsRecord.setCompany("25");
            smsRecord.setSmsType("1");
            String code = "";
            Random rd = new Random();
            for(int i = 0; i < 6; i++){
                code += rd.nextInt(9);
            }
            if("1".equals(type)){
                smsRecord.setSendComment("验证码:("+code+"),新东方合肥学校微信支付验证码,请注意安全性,有效期3分钟!");
            }else{
                smsRecord.setSendComment("验证码:("+code+"),新东方合肥学校查看卡卷登录手机验证码,请注意安全性,有效期3分钟!"); 
            }            
            Integer sendResult = smsService.sendMessage(smsRecord);
            if(sendResult.intValue()!=1){
                map.put("flag", false);
                map.put("message", "验证码发送失败!");
                return map ;
            }
            map.put("flag", true);            
            map.put("message", "验证码已发送");    
        }catch(Exception e){
           e.printStackTrace(); 
           logger.error("发送手机号短信验证码失败!", e.getMessage());
           map.put("flag", false);
           map.put("msg", "手机号短信验证码发送失败!");
        }       
        return map;
    }
	

    /**
     * 获取活动的有效截止时间
     * @param request
     * @param activityId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="getTime",method=RequestMethod.POST)
    public Map<String, Object> getTime(HttpServletRequest request,Long activityId){
        Map<String, Object> map = new HashMap<String, Object>();
        //通过ID查询活动的有效截止时间
        ActivityInfo activityInfo=aIService.selectByPrimaryKey(activityId);
        Date endTime=activityInfo.getEndTime();
        Date currDate=new Date();
        if(endTime.before(currDate)){
            map.put("flag", false);
            map.put("message", "活动已经结束!");
            return map;
        }else{
            map.put("flag", true);
        }
        return map;
        
    }
    
    /**
     * 进入查看我的卡卷登录页面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("toMyCardInfo")
    public ModelAndView toMyCardInfo() {
        ModelAndView mav = new ModelAndView("/activity/frontWeixin/login");
        return mav;
    }
    
    /**
     * 查看我的卡卷登录校验
     * @param fops
     * @param request
     * @return
     */
    @RequestMapping(value="checkLoginInfo")
    @ResponseBody
    public Map<String, Object> checkLoginInfo(FrontOrderInfoParams fops,HttpServletRequest request ){ 
        logger.info("查看我的卡卷登录校验----");
        return buyerInfoService.checkLoginInfo(fops, request);   
    }
    
    /**
     * 进入我的卡卷页面
     * @param model
     * @param videoTypeIds
     * @return
     */
    @RequestMapping("lookMyCard")
    public ModelAndView lookMyCard(BuyerInfo info) {
        ModelAndView mav = new ModelAndView("/activity/frontWeixin/myCard");
        List<BuyerInfoDto> cardList=buyerInfoService.selectMyCard(info);
        mav.addObject("cardList", cardList); 
        return mav;
    }
    
    
	/**
	 * 微信支付通知回调
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="notify")	
	public String weixinNotify(HttpServletRequest request,HttpServletResponse response){
	     logger.info("微信支付通知回调------");
		 return orderInfoService.weixinNotify(request, response);
    }

	
	/**
  	 * 手机端微信统一下单	
  	 * @param request
  	 * @param response
  	 * @return
  	 * @throws Exception
  	 */
	@Transactional
	@RequestMapping(value="payOrderInfo")
	public ModelAndView payOrderInfo(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		long orderInfoId=Long.parseLong(request.getParameter("orderInfoId"));
		System.out.println("orderInfoId==="+orderInfoId);
		OrderInfo orderInfo=orderInfoService.selectByPrimaryKey(orderInfoId);
		String prepayId=orderInfo.getPrepayId();
		System.out.println("prepayId==="+prepayId);
		BuyerInfo buyerInfo=buyerInfoService.selectByOrderNo(orderInfo.getOrderNo());
		Long activityId=0L;
		if(buyerInfo!=null){
		    activityId=buyerInfo.getActivityId();
		}
		System.out.println("activityId==="+activityId);
		ModelAndView mav = new ModelAndView("/activity/frontWeixin/orderInfo"+activityId);
		//防止用户刷新页面导致多次请求
		if("".equals(prepayId)||null==prepayId){
			// 第一步：第三方发起微信授权登录请求，微信用户允许授权第三方应用后，微信会拉起应用或重定向到第三方网站，并且带上授权临时票据code参数
			String code = request.getParameter("code");
			System.out.println("code ==="+code );
			// 获取微信用户openid
			String openid = AccessTokenRequestHandler.getOpenId(code);
			//商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调起支付	
			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类
			if (!"".equals(openid)){
				String noncestr = WXUtil.getNonceStr();
				String body="合肥新东方外语培训学校-"+orderInfo.getOrderName();	
				String total_fee=Integer.toString(orderInfo.getOrderCost().multiply(new BigDecimal(100.00)).setScale(1, BigDecimal.ROUND_HALF_UP).intValue());			
				String spbill_create_ip=WXUtil.toIpAddr(request);
				String time_start=TenpayUtil.getCurrTime();//订单生成时间
				String out_trade_no=orderInfo.getOrderNo();
				// 设置获取prepayid支付参数
				prepayReqHandler.setParameter("appid", ConstantUtil2.APP_ID);//公众账号ID
				prepayReqHandler.setParameter("mch_id", ConstantUtil2.PARTNER);//商户号
				prepayReqHandler.setParameter("nonce_str", noncestr);//随机字符串
				prepayReqHandler.setParameter("body",body);//商品描述
				prepayReqHandler.setParameter("out_trade_no",out_trade_no); //商家订单号   	
				prepayReqHandler.setParameter("total_fee",total_fee);//总金额
				prepayReqHandler.setParameter("spbill_create_ip",spbill_create_ip);//终端IP
				prepayReqHandler.setParameter("time_start",time_start);//订单生成时间			
				prepayReqHandler.setParameter("notify_url",ConstantUtil2.NOTIFY_URL);//通知地址
				prepayReqHandler.setParameter("trade_type","JSAPI");//交易类型
				prepayReqHandler.setParameter("openid",openid);//微信用户openid	
				String sign = prepayReqHandler.createMd5Sign();	// 生成获取预支付签名
				prepayReqHandler.setParameter("sign", sign);					
				String gateUrl = ConstantUtil2.GATEURL;
				prepayReqHandler.setGateUrl(gateUrl);
				String msgxml = prepayReqHandler.sendAndGetXml();
				System.out.println("微信下单成功后返回的xml数据------"+msgxml);
				String prepayid ="";
				if(!"".equals(msgxml)){
					Map msgMap = XMLUtil.parseXmlToList2(msgxml);
					prepayid =(String) msgMap.get("prepay_id");			
				}
				if(!"".equals(prepayid)){
					//第一步：更新业务订单信息
					orderInfo.setTradeType("JSAPI");
					orderInfo.setPayType(0);
					orderInfo.setOpenid(openid);
					orderInfo.setPrepayId(prepayid);
					orderInfo.setTimeStart(TenpayUtil.getTimestamp(time_start));
					int i=orderInfoService.updateByPrimaryKeySelective(orderInfo);
					if(i==1){		
					    //向页面传参
						addParam(orderInfo,activityId,mav);
					}else{
						mav.addObject("flag", false);
						mav.addObject("message", "更新业务订单失败!");
					}				
				}else{
					mav.addObject("flag", false);
					mav.addObject("message", "微信统一下单获取prepayid失败！");
				}
			}else{
				mav.addObject("flag", false);
				mav.addObject("message", "取微信用户openid失败！");
			}
		}else{
			System.out.println("取消之后再次发起支付===============");
			//向页面传参
			addParam(orderInfo,activityId,mav);		  
		}		
		return mav;
	}

	
	/**
	 * 向页面传参
	 */
	@SuppressWarnings("deprecation")
	public void addParam(OrderInfo orderInfo,long activityId,ModelAndView mav){		
	    // 拼接重定向的URL
		String redirect_uri = ConstantUtil2.REDIRECT_URI + "?orderInfoId=" + orderInfo.getId();
		redirect_uri = URLEncoder.encode(redirect_uri);
		mav.addObject("redirect_uri", redirect_uri);
		mav.addObject("appid", ConstantUtil2.APP_ID);
		mav.addObject("orderInfo", orderInfo);
		mav.addObject("index", "2");
		mav.addObject("activityId", activityId);
		mav.addObject("flag",true);		
	}
	
}