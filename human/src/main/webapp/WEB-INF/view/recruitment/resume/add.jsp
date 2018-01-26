<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title></title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body style="padding:20px;">
			<form class="layui-form" id="addForm">
				<div class="layui-form-item" style="margin-top:15px">
					<label class="layui-form-label" style="width:10%;">姓名</label>
					<div class="layui-input-inline" style="width:55%;">
						<input type="text" id="name" name="name" lay-verify="required" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 20px">
					<label class="layui-form-label" style="width:10%;">手机</label>
					<div class="layui-input-inline" style="width:55%;">
    					<input type="text" id="phone" name="phone" lay-verify="required" class="layui-input">
    				</div>
				</div>
				<div class="layui-form-item" style="margin-top:30px">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="resume-add">立即提交</button>
					</div>
				</div>
			</form>
		<script type="text/javascript" src="<%=basePath %>/static/recruitment/resume/add.js"></script>
	</body>
</html>