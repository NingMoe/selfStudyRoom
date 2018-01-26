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
		<div class="registflow">
			<p id="first_p" class="registflow-act">1.注册账号</p>
			<img src="<%=basePath %>/static/studentpc/images/4_03.png" alt="">
			<p id="second_p">2.设置密码</p>
			<img src="<%=basePath %>/static/studentpc/images/4_03.png" alt="">
			<p id="three_p">3.完善信息</p>
			<div class="clearfix"></div>
		</div>
		<div id="first_div" class="registbiao">
			<div class="regist-phone">
				<img src="<%=basePath %>/static/studentpc/images/4_07.png" alt="">
				<input type="text" id="phone" placeholder="请输入手机号码...">
				<div class="clearfix"></div>
			</div>
			<div class="regist-duanxin">
				<div class="duanxin-input">
					<img src="<%=basePath %>/static/studentpc/images/4_16.png" alt="">
					<input type="text" id="short_msg" placeholder="请输入短信验证码">
				</div>
				<div class="regist-num" id="get_short_msg_btn">
					获取验证码
				</div>
			</div>
			<div class="regist-login">
				<input type="button" id="msgverif" value="下一步">
			</div>
		</div>
		<div id="second_div" class="registbiao yincang">
			<div class="regist-phone">
				<img class="tiao" src="<%=basePath %>/static/studentpc/images/5_07.png" alt="">
				<input type="password" id="password" placeholder="请输入您的密码">
				<div class="clearfix"></div>	
			</div>
			<div class="setpass-pass">
				<img class="tiao" src="<%=basePath %>/static/studentpc/images/5_07.png" alt="">
				<input type="password" id="password2" placeholder="请确认您的密码">
				<div class="clearfix"></div>	
			</div>
			<div class="regist-login">
				<input type="button" id="password_repeat" value="下一步">
			</div>
		</div>
		<div id="three_div" class="registbiao yincang">
			<div class="perfectmess-name">
				<p>姓名</p>
				<input id="name" type="text" placeholder="请输入您的姓名">
				<div class="clearfix"></div>	
			</div>
			<div class="perfectmess-sex">
				<p class="sextitle">性别</p>
				<div id="sex_nan" class="sexradio">
					<img src="<%=basePath %>/static/studentpc/images/6_03.jpg" alt="">
					<p>男</p>
  				</div>
  				<div id="sex_nv" class="sexradio2">
  					<img src="<%=basePath %>/static/studentpc/images/6_05.png" alt="">
  					<p>女</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="regist-login">
				<input type="button" id="login_btn" value="确认注册">
			</div>
		</div>
		<div class="regist-alert" id="phoneMsgDiv" style="display: none;">
					<img src="<%=basePath %>/static/studentpc/images/4_10.png" alt="">
					<p id="phoneMsg">该手机号已注册，请返回登录</p>
				</div>
	</div>
	<div class="public-bottom">
		<p>北京新东方教育科技（集团）有限公司</p>
		<p>经营许可证编号：北京ICP05067667|京ICP060601号|京公网安备1101084985</p>
		<p>Copyright 2016-2017 Neworiental Corporation,   All Right Reserved</p>
		<p>©2011-2012 新东方 版权所有</p>
	</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/studentpc/js/register.js"></script>
</html>