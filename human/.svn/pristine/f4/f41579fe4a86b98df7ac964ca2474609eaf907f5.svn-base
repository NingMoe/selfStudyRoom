<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>Smart Work 改变你的工作方式</title>
	<meta name="renderer" content="webkit">	
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">	
	<meta name="apple-mobile-web-app-capable" content="yes">	
	<meta name="format-detection" content="telephone=no">	
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/framework/css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
	<h1>
		 <strong>Smart Work</strong>
		 <em>改变你的工作方式</em>
	</h1>
	
	<div class="layui-user-icon larry-login">
		 <input type="text" placeholder="账号" class="login_txtbx" id="username"  />
	</div>
	<div class="layui-pwd-icon larry-login">
		 <input type="password" placeholder="密码" class="login_txtbx"  id="password"/>
	</div>
<%--     <div class="layui-val-icon larry-login">
    	<div class="layui-code-box">
    		<input type="text" id="code" name="code" lay-verify="code" placeholder="验证码" maxlength="4" class="login_txtbx">
            <img src="<%=basePath %>/manager/verCode.html" alt="" class="verifyImg" id="verifyImg" onclick="refresh();">
    	</div>
    </div> --%>
    <div class="layui-submit larry-login"  lay-submit>
    	<input type="button" value="立即登陆" class="submit_btn"/>
    </div>
    <div class="layui-login-text">
    	<p>© 2016-2017 合肥新东方信息管理部 版权所有</p>
        <p>皖xxxxxx <a href="Mailto:zhangjian20@xdf.cn">zhangjian20@xdf.cn</a></p>
    </div>
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