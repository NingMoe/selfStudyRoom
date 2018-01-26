<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>图书馆登录</title>
    <script src="<%=basePath %>/static/teacherservice/weixin/book/js/jquery.js"></script>
    <link rel="stylesheet" href="<%=basePath %>/static/teacherservice/weixin/book/css/index.css">
</head>
<body class="login-bg">
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';
</script>
<input id="openid" type="text" value="123123"/>
	<div class="login-box">
        <div class="email"><input id="userName" type="text" placeholder="邮箱/用户名"><p>@xdf.cn</p><div style="clear:both"></div></div>
        <div class="idnum"><input id="userPassword" type="text" placeholder="身份证号码后六位"><div style="clear:both"></div></div>
    </div>
    	<input class="login-login" type="button" onclick="logincheck();" value="绑定员工信息">
<script type="text/javascript">
function logincheck(){
	var userName  = $("#userName").val();
	var userPassword = $("#userPassword").val();
	var openid = "aaa";
	if(userName == ''){
		alert("请输入用户名");
		return;
	}
	if(userPassword == ''){
		alert("请输入密码");
		return;
	}
	if(openid == ''){
		alert("openid未获取到");
		return;
	}
	$.ajax({
		url : jsBasePath+"/wechat/binding/library/logincheck.html",
		type : "POST",
		dataType : "json",
		data : {
			userName : userName,
			userPassword : userPassword,
			openid : openid
		},
		success : function(date){
			if(date.flag){
				window.location.href = jsBasePath + "/wechat/binding/library/indexview.html";
			}else{
				alert(date.message);
			}
		},
		error : function(date){
			alert("网络出错，请重新发送。");
		}
	});
}
</script>
</body>
</html>

