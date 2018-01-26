<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head style="font-size: 100px;">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;width=device-width; initial-scale=1.0;">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>我的卡卷</title>
<meta charset="UTF-8">
<meta name="keywords" content="新东方合肥学校">
<%@include file="/WEB-INF/view/activity/frontWeixin/taglib.jsp" %>
<link rel="stylesheet" href="<%=basePath %>/static/common/font/iconfont.css"  />
<link rel="stylesheet" href="<%=basePath %>/static/xdfStudent/wechat/style/less.css">
<link rel="stylesheet" href="<%=basePath %>/static/bootstrap/css/bootstrap.css">
<style>
	p{margin:0!important}
</style>	
</head>
<body>
   <c:if  test="${!empty cardList}">
				<div class="main2">
					<table class="table">
						<thead>
							<th style="width:40%">卡卷名称</th>  	
							<th style="width:30%">购买数量</th>
							<th style="width:30%">交易状态</th>
						</thead>
						<tbody>
						  <c:forEach var="card" items="${cardList }" varStatus="status">
								<tr>
									<td>
										<p>${card.productName }</p>
									</td>
									<td>
										<p>${card.buyTotal }</p>
									</td>
									<td>
									    <c:if test="${card.buyState eq '1'}">
											<p>已支付</p>
										</c:if>
										<c:if  test="${card.buyState eq '4'}">
											<p>退款中</p>
										</c:if>
										<c:if  test="${card.buyState eq '5'}">
											<p>退款成功</p>
										</c:if>
									</td>
								</tr>
						  </c:forEach>
						</tbody>
					</table>
				</div>
		</c:if>
		<c:if  test="${empty cardList}">
			<p style="text-align:center;margin-top:25%">暂无任何卡卷</p>
		</c:if>
</body>
<script type="text/javascript">
	var deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>
</html>