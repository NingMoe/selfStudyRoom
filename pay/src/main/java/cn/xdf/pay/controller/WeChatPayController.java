package cn.xdf.pay.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cn.xdf.pay.constant.CommonStatus;
import cn.xdf.pay.domain.WechatPayParams;
import cn.xdf.pay.service.OrderBillService;
import cn.xdf.pay.service.OrderInfoService;
import cn.xdf.pay.util.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="微信支付操作接口",tags={"WeChatPayController"})
@RestController
@RequestMapping("/weChatPay")
public class WeChatPayController {
	
	private static Logger logger = LoggerFactory.getLogger(WeChatPayController.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
	
	@Autowired
	private OrderBillService orderBillService;
		
	/**
	 * 获取微信支付参数接口
	 * 用于微信公众号支付及小程序支付
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="获取微信支付参数接口", notes="用于微信公众号支付及小程序支付")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "appId", value = "调用系统appId", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "appKey", value = "调用系统appKey", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "orderNo", value = "订单号", required = true, dataType = "String", paramType="query")
	})
	@RequestMapping(value="/getWeChatPayParams",method=RequestMethod.POST)
	public ResponseInfo<WechatPayParams> getWeChatPayParams(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderNo",required =true)String orderNo,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------获取微信支付参数开始----------");
		ResponseInfo<WechatPayParams> responseInfo=new ResponseInfo<WechatPayParams>();
		try{
			Map<String,Object> map=this.orderInfoService.getWeChatPayParams(appId,appKey,orderNo,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("获取微信支付参数成功");
				responseInfo.setData((WechatPayParams)map.get("wechatPayParams"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------获取微信支付参数异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("获取微信支付参数异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}

	
	/**
	 * 微信支付通知回调
	 * @return
	 */
	@ApiOperation(value="微信支付通知回调接口", notes="用于微信支付通知回调")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@RequestMapping(value="notify",method=RequestMethod.GET)	
	public String weChatNotify(HttpServletRequest request,HttpServletResponse response){
	     logger.info("------微信支付通知回调------");
	     String retMsg="";
	     try{
	    	 retMsg=this.orderInfoService.weChatNotify(request, response);
	     }catch(Exception e){
	    	e.printStackTrace();
			logger.error("---------微信支付通知回调异常----------", e.getMessage());
	     }
		 return retMsg;
    }
	
	
	/**
	 * 微信下载对账单接口
	 * @return
	 */
	@ApiOperation(value="微信下载对账单接口", notes="用于下载微信对账单")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@RequestMapping(value="downLoadWxOrderBill",method=RequestMethod.GET)	
	public void downLoadWxOrderBill(HttpServletRequest request,HttpServletResponse response){
	     logger.info("------微信支付通知回调------");
	     try{
	    	 this.orderBillService.downLoadWxOrderBill(request,response);;
	     }catch(Exception e){
	    	e.printStackTrace();
			logger.error("---------微信支付通知回调异常----------", e.getMessage());
	     }
    }
	
}
