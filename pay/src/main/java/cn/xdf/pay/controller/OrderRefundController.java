package cn.xdf.pay.controller;

import java.util.List;
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
import cn.xdf.pay.domain.OrderRefundInfo;
import cn.xdf.pay.service.OrderRefundService;
import cn.xdf.pay.util.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value="退款单操作接口",tags={"OrderRefundController"})
@RestController
@RequestMapping("/orderRefundInfo")
public class OrderRefundController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundController.class);
	
	@Autowired
	private OrderRefundService orderRefundService;
			
	/**
	 * 微信支付申请退款接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信支付申请退款接口", notes="微信支付申请退款接口")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "appId", value = "调用系统appId", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "appKey", value = "调用系统appKey", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "orderRefundJson", value = "退款单json字符串", required = true, dataType = "String", paramType="query")
	})
	@RequestMapping(value="/refund",method=RequestMethod.POST)
	public ResponseInfo<OrderRefundInfo> refundOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderRefundJson",required =true)String orderRefundJson,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信支付申请退款开始----------");
		ResponseInfo<OrderRefundInfo> responseInfo=new ResponseInfo<OrderRefundInfo>();
		try{
			Map<String,Object> map=this.orderRefundService.refundOrder(appId,appKey,orderRefundJson,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信申请退款成功");
				responseInfo.setData((OrderRefundInfo) map.get("orderRefundInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付申请退款异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信支付申请退款接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	/**
	 * 微信支付查询退款接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value="微信支付查询退款接口", notes="微信支付查询退款接口")
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
	@RequestMapping(value="/queryRefundOrder",method=RequestMethod.POST)
	public ResponseInfo<List<OrderRefundInfo>> queryRefundOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderNo",required =true)String orderNo,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信支付查询退款开始----------");
		ResponseInfo<List<OrderRefundInfo>> responseInfo=new ResponseInfo<List<OrderRefundInfo>>();
		try{
			Map<String,Object> map=this.orderRefundService.queryRefundOrderOrder(appId,appKey,orderNo,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信支付查询退款成功");
				responseInfo.setData((List<OrderRefundInfo>) map.get("refundList"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信支付查询退款异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信支付查询退款接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
}
