<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<title>签到首页</title>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/index.css">
    <link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/iconfont.css">
</head>
<style>
 .btn{
    width:1rem;
	display: block;
	margin: 0 auto;
	margin-top: .2rem;
	background: #fff;
	height: .4rem;
	border-radius: 4px;
	padding: 0 7px;
	color:#FC6655;
	margin-bottom:20px;
 }
</style>
<body>
<input type="hidden" name="activityId" id="activityId" value="${signActivity.id }">
<input type="hidden" name="activityTime" id="activityTime" value="${signActivity.activityTime }">
    <div class="logo">
       <img src="<%=basePath%>/static/sign/frontWeiXin/image/1.png" alt="">
       <p>${signActivity.activityName }</p>
    </div>
    <div class="inputnum">
     	<p>请输入手机/身份证后4位</p>
     	<input type="text" id="telOrCardNo" onblur="return sign();">
     	<button class="btn" onclick="return sign();">确认签到</button>
    </div>
    <div class="bottom">
    	<p onclick="signDetails();">签到详情</p>
    	<p><i class="iconfont" style="vertical-align:top;font-size: .2rem">&#xe65b;</i>&nbsp;<fmt:formatDate  pattern="yyyy-MM-dd HH:mm" value="${signActivity.startTime}"/>&nbsp;&nbsp;-&nbsp;&nbsp;<fmt:formatDate  pattern="yyyy-MM-dd HH:mm" value="${signActivity.endTime}"/></p>
    </div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/sign/frontWeiXin/js/index.js"></script>
</html>
