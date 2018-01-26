<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
</head>
<body>
<div class="public-topbg">
		<div class="public-top">
			<p class="logos">SPT</p>
			<p class="public-word">英语口语测评系统</p>
			<p style="cursor: pointer;" onclick="back();" class="public-return"><&nbsp返回</p>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="registration-main">
		<div style="text-align: center;" class="registflow">
			<p style="color: #000">修改密码</p>
			<div class="clearfix"></div>
		</div>
		<div class="registbiao">
			<div class="regist-phone">
				<img class="tiao" src="<%=basePath %>/static/studentpc/images/5_07.png" alt="">
				<input type="password" id="old_password" placeholder="请输入您的原密码">
				<div class="clearfix"></div>
			</div>
			<div class="regist-phone">
				<img class="tiao" src="<%=basePath %>/static/studentpc/images/5_07.png" alt="">
				<input type="password" id="new_password" placeholder="请输入您的新密码">
				<div class="clearfix"></div>	
			</div>
			<div class="setpass-pass">
				<img class="tiao" src="<%=basePath %>/static/studentpc/images/5_07.png" alt="">
				<input type="password" id="new_password2" placeholder="请确认您的密码">
				<div class="clearfix"></div>	
			</div>
			<div class="regist-login">
				<input type="button" id="change_password" value="确认修改">
			</div>
		</div>
	</div>
	<div class="public-bottom">
		<p>北京新东方教育科技（集团）有限公司</p>
		<p>经营许可证编号：北京ICP05067667|京ICP060601号|京公网安备1101084985</p>
		<p>Copyright 2016-2017 Neworiental Corporation,   All Right Reserved</p>
		<p>©2011-2012 新东方 版权所有</p>
	</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/changePassword.js"></script>
</html>