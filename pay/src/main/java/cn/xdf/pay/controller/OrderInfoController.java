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
import cn.xdf.pay.domain.OrderInfo;
import cn.xdf.pay.domain.WechatPayParams;
import cn.xdf.pay.service.OrderInfoService;
import cn.xdf.pay.util.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="订单操作接口",tags={"OrderInfoController"})
@RestController
@RequestMapping("/orderInfo")
public class OrderInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderInfoController.class);
	
	@Autowired
	private OrderInfoService orderInfoService;
			
	/**
	 * 微信支付统一下单接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信支付统一下单接口", notes="微信支付统一下单接口")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "appId", value = "调用系统appId", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "appKey", value = "调用系统appKey", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "orderJson", value = "订单json字符串", required = true, dataType = "String", paramType="query")
	})
	@RequestMapping(value="/unifiedOrder",method=RequestMethod.POST)
	public ResponseInfo<OrderInfo> unifiedOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderJson",required =true)String orderJson,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信支付统一下单开始----------");
		ResponseInfo<OrderInfo> responseInfo=new ResponseInfo<OrderInfo>();
		try{
			Map<String,Object> map=this.orderInfoService.unifiedOrder(appId,appKey,orderJson,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("公众号支付统一下单成功");
				responseInfo.setData((OrderInfo) map.get("orderInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付统一下单异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信支付统一下单接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	
	/**
	 * 微信支付查询订单接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信支付查询订单接口", notes="微信支付查询订单接口")
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
	@RequestMapping(value="/queryOrder",method=RequestMethod.POST)
	public ResponseInfo<OrderInfo> queryOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderNo",required =true)String orderNo,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信支付查询订单开始----------");
		ResponseInfo<OrderInfo> responseInfo=new ResponseInfo<OrderInfo>();
		try{
			Map<String,Object> map=this.orderInfoService.queryOrder(appId,appKey,orderNo,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信支付查询订单成功");
				responseInfo.setData((OrderInfo) map.get("orderInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付查询订单异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信支付查询订单接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	
	/**
	 * 微信支付关闭订单接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信支付关闭订单接口", notes="微信支付关闭订单接口")
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
	@RequestMapping(value="/closeOrder",method=RequestMethod.POST)
	public ResponseInfo<OrderInfo> closeOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderNo",required =true)String orderNo,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信支付关闭订单开始----------");
		ResponseInfo<OrderInfo> responseInfo=new ResponseInfo<OrderInfo>();
		try{
			Map<String,Object> map=this.orderInfoService.closeOrder(appId,appKey,orderNo,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信支付关闭订单成功");
				responseInfo.setData((OrderInfo) map.get("orderInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付关闭订单异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信支付关闭订单接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	
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
	
}
