<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head style="font-size: 100px;">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8;width=device-width; initial-scale=1.0;">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
<title>登录</title>
<meta charset="UTF-8">
<meta name="keywords" content="新东方合肥学校">
<%@include file="/WEB-INF/view/activity/frontWeixin/taglib.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/static/activity/frontWeixin/css/style.css">
</head>
<body style="background: #009688;">
<div class="shouye">
	<p class="shouye_p">我的优惠券</p>
	<div><input  name="name"  id="name"  placeholder="请输入姓名"  class="denglu_name"></div>
	<div><input  name="telephone" id="telephone" placeholder="请输入手机号" class="denglu_shouji"><button class="denglu_button" id="get_msg_btn" onclick="return validate();">获取验证码</button></div>
	<div><input  name="code" id="msg" placeholder="请输入验证码" class="denglu_name"></div>
	<div><button class="denglu_button2" onclick="return login();">登录</button></div>
</div>
</body>
<script type="text/javascript">
	var  deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
</script>	
<script type="text/javascript" src="<%=basePath%>/static/activity/frontWeixin/js/login.js"></script>
</html>