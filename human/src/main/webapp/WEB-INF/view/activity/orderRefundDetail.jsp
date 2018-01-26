<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<html lang="zh-CN">
<head>
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/common-css.jsp" %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1,target-densitydpi=medium-dpi">
	<link rel="stylesheet" href="<%=basePath %>/static/bootstrap/css/bootstrap.css">
<style type="text/css">
      input{font-size: 12px}
</style>
</head> 
<body>
<br/>
<br/>  
		<table class="table" height="100" width="70%" border="0" cellpadding="0" cellspacing="1"
			align="center">
			<tr>
				<td height="30"
					 colspan="2">
					<div align="center">
					<font color="#6d757c" size="6" ><b>退款明细详情</b></font>
					</div>
				</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								商户订单号：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						${orderRefundInfo.orderNo}
						</div>
					</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								微信支付单号：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						${orderRefundInfo.transactionId}
						</div>
					</td>
			</tr>
			<tr>	
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								支付时间：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						 <fmt:formatDate  type="both" value="${orderInfo.payTime}" />						
						</div>
					</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								商户退款单号：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						${orderRefundInfo.orderRefundNo}
						</div>
					</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1">
								微信退款单号：
						</div>
					</td>
					<td>
						<div align="left" class="STYLE1"  style="padding-left:10px;">
							${orderRefundInfo.refundId}
						</div>
					</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1">
								退款申请时间：
						</div>
					</td>
					<td>
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						  <fmt:formatDate  type="both" value="${orderRefundInfo.createTime}" />							
						</div>
					</td>
			</tr>
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								退款状态：
						</div>
					</td>															
					<td>
						<div align="left" class="STYLE1" style="padding-left:10px;">
						<c:if test="${orderRefundInfo.orderRefundState eq '1'}">退款中</c:if>
						<c:if test="${orderRefundInfo.orderRefundState eq '2'}">退款成功</c:if>
						<c:if test="${orderRefundInfo.orderRefundState eq '3'}">退款失败</c:if>
						<c:if test="${orderRefundInfo.orderRefundState eq '4'}">转入代发</c:if>											
						</div>
					</td>
			</tr>
			<c:if test="${orderRefundInfo.orderRefundState eq '2'}">
			
			  <c:if test="${orderRefundInfo.sucessesTime ne null}">
			   <tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1">
								退款成功时间：
						</div>
					</td>
					<td>
						<div align="left" class="STYLE1"  style="padding-left:10px;">
							${orderRefundInfo.sucessesTime}
						</div>
					</td>
			   </tr>
			 </c:if>						
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								退款渠道：
						</div>
					</td>
					<td>
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						<c:if test="${orderRefundInfo.refundChannel eq  'ORIGINAL'}">原路退款</c:if>　　
						<c:if test="${orderRefundInfo.refundChannel eq  'BALANCE'}">退回到余额</c:if>　
						</div>
					</td>
			</tr>			
			<tr>	
					<td height="30"width="20%" >
						<div align="right" class="STYLE1" >
								退款入账账户：
						</div>
					</td>
					<td >
						<div align="left" class="STYLE1"  style="padding-left:10px;">
						${orderRefundInfo.refundRecvAccout}
						</div>
					</td>
			</tr>
			</c:if>				
			<tr>
					<td colspan="2" style="padding: 10px">
						<div align="center">
			 				<input class="btn" id="backBt" type="button" value="　返　回　" class="input_btn_style1" onclick="closeFrame();"/>
		 				</div>
					</td>
				</tr>
		</table>
</body>
<script type="text/javascript" src="<%=basePath %>/static/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
  function closeFrame(){
	  var index = parent.layer.getFrameIndex(window.name); //获取当前窗体索引
	  parent.layer.close(index); //执行关闭
  }
  </script>
</html>