<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
	<title>我的授课班级</title>
	<script type="text/javascript">
	var  deviceWidth = document.documentElement.clientWidth;
	if(deviceWidth >750) deviceWidth =750;
	document.documentElement.style.fontSize = deviceWidth /7.5+'px';
	</script>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<script type="text/javascript" src="<%=basePath%>/static/continuation/js/classespeopleaddbyphone.js"></script>
	<link rel="stylesheet" href="<%=basePath %>/static/continuation/css/less1.css">
</head>
<body style="padding: 10px;">
	<div class="main">
		<div class="main-name">
			<input type="text" id="student_name" placeholder="请输入姓名">
		</div>
		<div class="main-name">
			<input type="text" id="phone" placeholder="请输入学员手机号">
		</div>
		<div class="main-name">
			<input type="text" id="student_code" placeholder="请输入学员号">
		</div>
		<div class="main-class">
			<input type="text" id="new_class_code" placeholder="请输入续班班号">
		</div>
		<p>多个班号以逗号隔开</p>
	</div>
	<div class="bottom">
		<input class="button-red" id="classes_people_add" type="button" value="添加">
	</div>
</body>
</html>