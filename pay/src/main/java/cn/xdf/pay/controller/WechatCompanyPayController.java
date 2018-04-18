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
import cn.xdf.pay.domain.CompanyOrderInfo;
import cn.xdf.pay.service.CompanyOrderInfoService;
import cn.xdf.pay.util.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="微信企业付款操作接口",tags={"WechatCompanyPayController"})
@RestController
@RequestMapping("/weChatCompanyPay")
public class WechatCompanyPayController {
	
	private static Logger logger = LoggerFactory.getLogger(WechatCompanyPayController.class);
	
	@Autowired
	private CompanyOrderInfoService companyOrderInfoService;
			
	/**
	 * 微信企业付款到零钱接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信企业付款到零钱接口", notes="用于企业付款到微信用户零钱")
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
	public ResponseInfo<CompanyOrderInfo> unifiedOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderJson",required =true)String orderJson,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------微信企业付款到零钱开始----------");
		ResponseInfo<CompanyOrderInfo> responseInfo=new ResponseInfo<CompanyOrderInfo>();
		try{
			Map<String,Object> map=this.companyOrderInfoService.unifiedOrder(appId,appKey,orderJson,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信企业付款到零钱成功");
				responseInfo.setData((CompanyOrderInfo) map.get("companyOrderInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------微信企业付款到零钱异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信企业付款到零钱接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	/**
	 * 微信企业付款到零钱查询接口
	 * @param appId
	 * @param appKey
	 * @param orderJson
	 * @return
	 */
	@ApiOperation(value="微信企业付款到零钱查询接口", notes="用于查询企业付款到微信用户零钱")
	@ApiResponses({ 
		@ApiResponse(code = CommonStatus.OK, message = "操作成功"),
        @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
        @ApiResponse(code = CommonStatus.UNAUTHORIZED, message = "调用系统权限不足"), 
		@ApiResponse(code = CommonStatus.FORBIDDEN, message = "参数验证不通过")
	})
	@ApiImplicitParams({
		@ApiImplicitParam(name = "appId", value = "调用系统appId", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "appKey", value = "调用系统appKey", required = true, dataType = "String", paramType="query"),
		@ApiImplicitParam(name = "orderNo", value = "商户订单号", required = true, dataType = "String", paramType="query")
	})
	@RequestMapping(value="/queryOrder",method=RequestMethod.POST)
	public ResponseInfo<CompanyOrderInfo> queryOrder(@RequestParam(name="appId",required =true)String appId,
			                                 @RequestParam(name="appKey",required =true)String appKey,
			                                 @RequestParam(name="orderNo",required =true)String orderNo,
			                                 HttpServletRequest request, HttpServletResponse response){
		logger.info("---------查询微信企业付款到零钱开始----------");
		ResponseInfo<CompanyOrderInfo> responseInfo=new ResponseInfo<CompanyOrderInfo>();
		try{
			Map<String,Object> map=this.companyOrderInfoService.queryOrder(appId,appKey,orderNo,request,response);
			if((Boolean) map.get("flag")){
				responseInfo.setRtnCode(CommonStatus.OK);
				responseInfo.setRtnMsg("微信企业付款到零钱查询成功");
				responseInfo.setData((CompanyOrderInfo) map.get("companyOrderInfo"));
			}else{
				responseInfo.setRtnCode(Integer.valueOf(map.get("code").toString()));
				responseInfo.setRtnMsg(map.get("message").toString());
			}			
		}catch(Exception e){
			e.printStackTrace();
			logger.error("---------查询微信企业付款到零钱异常----------", e.getMessage());
			responseInfo.setRtnCode(CommonStatus.EXCEPTION);
			responseInfo.setRtnMsg("微信企业付款到零钱查询接口异常,异常原因:"+e.getMessage());
		}		
		return responseInfo;
	}
	
	
}
