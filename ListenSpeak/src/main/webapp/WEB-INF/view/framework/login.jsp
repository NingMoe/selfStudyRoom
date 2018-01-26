<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>SPT英语口语测评系统</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/framework/css/less.css">
</head>
<body class="login" style="background-image: url(<%=basePath %>/static/framework/images/bg.jpg);">
	<div class="login-top" style="margin-top:1.9rem !important;">
		<p class="logo">SPT</p>
		<p class="login-ttiltle">英语口语测评系统</p>
		<div class="clearfix"></div>
	</div>
	<div class="login-main">
		<div class="login-user">
			<img src="<%=basePath %>/static/framework/images/1_03.jpg" alt=""><input id="username" type="text" placeholder="请输入您的账号...">
		</div>
		<div class="login-password">
			<img src="<%=basePath %>/static/framework/images/1_06.png" alt=""><input id="password" type="password" placeholder="请输入您的密码...">
		</div>
		<div class="login-button">
			<input type="button" class="submit_btn" value="登录">
			<input type="button" id="xsdl" value="学生账号登录">
			<p onclick="changePassWrod();">忘记密码？</p>
		</div>
	</div>
	<div class="login-bottom" style="position:relative;margin-top:380px;">
		<p>北京新东方教育科技（集团）有限公司</p>
		<p>经营许可证编号：北京ICP05067667|京ICP060601号|京公网安备1101084985</p>
		<p>Copyright 2016-2017 Neworiental Corporation,   All Right Reserved</p>
		<p>©2011-2012 新东方 版权所有</p>
	</div>
<script type="text/javascript" src="<%=basePath %>/static/framework/js/login.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.particleground.min.js"></script>
<script type="text/javascript">
//防止内部iframe跳转到登陆页面
if(window !=top){  
    top.location.href=location.href;  
}  
</script>
</body>
</html>