<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="width: 100%;background-color: #F0F0F0;">
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>新东方入班水平测试</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view9.css">
</head>
<body >
	<div class="main_body">
		<div style="margin-top: 15px;">
			<div style="padding: 0 1rem;">
			<c:if  test="${empty jpqList}">没有错误题目</c:if>
			<c:if  test="${!empty jpqList}">
				<div style="width: 100%;padding-bottom: 15px;">
					<div style="font-weight: 700;">错误题目</div>
					<c:forEach items="${jpqList }" var="jpq" varStatus="status">
						<c:if  test="${jpq.qType eq '1'}">
							<div>
								<div style="margin-top: 5px; color: #ff9800;">题目${status.index+1 }</div>
								<div>${jpq.qContent }</div>
								<div style="margin-top: 10px;"><div style="width: 30%; float: left;">您的选项：</div>
								<span style="color: #ff9800;">${jpq.xh2 }.${jpq.aContent2 }</span>
								<c:if test="${!empty jpq.aImg2 }">
									<img src="${fileurl }${jpq.aImg2}">
								</c:if>
								</div>
								<div style="margin-top: 3px;"><div style="width: 30%; float: left;">正确选项：</div>
								<span style="color: #7eccbf;">${jpq.xh1 }.${jpq.aContent1 }</span>
								<c:if test="${!empty jpq.aImg1 }">
									<img src="${fileurl }${jpq.aImg1}">
								</c:if>
								</div>	
							</div>
						</c:if>
						<c:if  test="${jpq.qType eq '2'}">
							<div>
								<div style="margin-top: 5px; color: #ff9800;">题目${status.index+1 }</div>
									<div style="width: 100%;background-color: #fff; min-height:5rem; white-space: normal; word-break: break-all; word-wrap: break-word; padding-bottom: 15px; letter-spacing: 1px;">
										${jpq.qMainDesc }
									</div>
								</div>
							<div>
							<c:forEach var="jpqError" items="${jpq.errorList }" varStatus="status1">
								<div style="margin-top: 3px;">问题${status1.index+1}：${jpqError.qContent }</div>
								<div style="margin-top: 10px;"><div style="width: 30%; float: left;">您的选项：</div><span style="color: #ff9800;">${jpqError.xh2 }.${jpqError.aContent2 }</span></div>
								<div style="margin-top: 3px;"><div style="width: 30%; float: left;">正确选项：</div><span style="color: #7eccbf;">${jpqError.xh1 }.${jpqError.aContent1 }</span></div>
								<br>
							</c:forEach>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>