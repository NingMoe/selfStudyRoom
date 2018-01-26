<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
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
	<link rel="stylesheet" type="text/css" href="<%=basePath %>/static/framework/css/login.css" media="all">
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login">
	<h1>
		 <strong>SPT英语口语测评系统(学生端)</strong>
	</h1>
	
	<div class="layui-user-icon larry-login">
		 <input type="text" placeholder="请输入手机号" class="login_txtbx" id="username"  />
	</div>
	<div class="layui-pwd-icon larry-login">
		 <input type="password" placeholder="请输入密码" class="login_txtbx"  id="password"/>
	</div>
    <div class="layui-submit larry-login"  lay-submit>
    	<input type="button" value="立即登陆" class="submit_btn"/>
    </div>
    <div class="layui-login-text">
    	<p>北京新东方教育科技（集团）有限公司 </p>
        <p>经营许可证编号：北京ICP05067667|京ICP060601号|京公网安备1101084985</p>
        <p>©2016-2017 Neworiental Corporation,All Right Reserved </p>
        <p>©2016-2017新东方 版权所有</p>
    </div>
</div>
<script type="text/javascript" src="<%=basePath %>/static/jquery/jquery.particleground.min.js"></script>
<script type="text/javascript">
//防止内部iframe跳转到登陆页面
if(window !=top){  
    top.location.href=location.href;  
}  
/**
 * 登陆
 */
function login(){
	var userName=$.trim($("#username").val());
	var userPassword=$.trim($("#password").val());
	if(userName.length==0||userPassword.length==0){
		layer.msg("用户名或密码不能为空", {icon: 5})
		return;
	}
	$.post(jsBasePath+"/student/loginCheck.html",{userName:userName,userPassword:userPassword},function (data,status){
		if(data.flag){
			 window.location=jsBasePath+"/manager/index.html";
		}else{
			layer.msg(data.message, {icon: 5});
		}
		refresh();
	},"json");
}


$(function(){
	 $(".layui-canvs").particleground({
		    dotColor: '#5cbdaa',
		    lineColor: '#5cbdaa'
		  });
});
	//登录链接测试，使用时可删除
	$(".submit_btn").click(function(){
		login();
	});
	
		//按回车键
	    document.onkeydown=function mykeyDown(e){  
	    //compatible IE and firefox because there is not event in firefox  
	    e = e||event;  
	    if(e.keyCode == 13){
	    	login();
	    }
	    }
</script>
</body>
</html>