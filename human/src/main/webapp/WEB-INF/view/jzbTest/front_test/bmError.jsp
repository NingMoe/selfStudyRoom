<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="background-color: #F0F0F0; width: 100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<%@include file="/WEB-INF/view/jzbTest/jzbtaglib.jsp" %>
	<title>新东方在线测试</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view1.css">
</head>	
<body>
	<div class="main_body">
		<div class="main_head">
			<span><font color="red">${message }</font></span>
		</div>
	</div>
</body>
</html>