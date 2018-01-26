<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html style="background-color: #F0F0F0; width: 100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<meta name="format-detection" content="telephone=no"/>
	<title>新东方入班水平测试</title>
	<link rel="stylesheet" href="<%=basePath %>/static/jzbTest/weixin/css/view2.css">
</head>
<body >
	<div class="main_body">
		<div style="padding-left: 10px; padding-right: 10px;">
			<div style="padding-top: 20px; font-weight: 700;">
				<div style="width: 100%; overflow: hidden;">
					<div class="main_table_head">姓名</div>
					<div class="main_table_head">年级</div>
					<div class="main_table_head">科目</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_head" style="width:20%;">测试结果</div>
					<div class="main_table_head">错题</div>
				</div>
			</div>
			<div style="width: 100%;  overflow: hidden;">
				<div>
					<div class="main_table_r">g</div>
					<div class="main_table_r">初二</div>
					<div class="main_table_r">物理</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_r" style="width:20%;">通过</div>
					<div class="main_table_r main_table_r_btn">错题</div>
				</div>
				<div>
					<div class="main_table_r">g</div>
					<div class="main_table_r">初二</div>
					<div class="main_table_r">物理</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_r" style="width:20%;">通过</div>
					<div class="main_table_r main_table_r_btn">错题</div>
				</div><div>
					<div class="main_table_r">g</div>
					<div class="main_table_r">初二</div>
					<div class="main_table_r">物理</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_r" style="width:20%;">通过</div>
					<div class="main_table_r main_table_r_btn">错题</div>
				</div><div>
					<div class="main_table_r">g</div>
					<div class="main_table_r">初二</div>
					<div class="main_table_r">物理</div>
					<div class="main_table_head">报名</div>
					<div class="main_table_r" style="width:20%;">通过</div>
					<div class="main_table_r main_table_r_btn">错题</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>