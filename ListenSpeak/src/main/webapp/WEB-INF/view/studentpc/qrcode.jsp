<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html style="height: 100%;">
<head>
<meta charset="UTF-8">
<title>我的测试(测试中)</title>
<style type="text/css">
   iframe{
   display: block;
    margin: 0 auto;  
   }
   .public-bottom{
     position: relative !important;
     margin-top:240px;
   }
</style>
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta charset="UTF-8">
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/studentpc/css/less.css">
</head>
<body>
	<input type="hidden" value="${openAppId}"/>
	<div class="public-topbg">
		<div class="public-top">
			<p class="logos">SPT</p>
			<p class="public-word">英语口语测评系统</p>
			<p class="public-return"><&nbsp返回</p>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="codelogin-main">
		<div id="wxCode"></div>
		<p>打开微信扫描上方二维码登录</p>
		<p>*注：系统目前只支持学生使用PC端进行登录</p>
	</div>
	<div class="public-bottom">
		<p>北京新东方教育科技（集团）有限公司</p>
		<p>经营许可证编号：北京ICP05067667|京ICP060601号|京公网安备1101084985</p>
		<p>Copyright 2016-2017 Neworiental Corporation,   All Right Reserved</p>
		<p>©2011-2012 新东方 版权所有</p>
	</div>
</body>
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<script type="text/javascript">
var obj = new WxLogin({
	id:"wxCode", 
    appid: "${openAppId}", 
    scope: "snsapi_login", 
    redirect_uri: encodeURIComponent("http://school.hf.xdf.cn/studentpc/studentregister/userLogin.html"),
    state:null
});
</script>
</html>