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
				<input id="deptId" type="hidden" name="deptId" value="${dept.id }">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:22%;">部门名称</label>
					<div class="layui-input-inline" style="width:50%;">
						<input type="text" id="name" name="name" lay-verify="required" value="${dept.name }" class="layui-input">
					</div>
				</div>	
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:22%;">所属学校</label>
					<div class="layui-input-inline" style="width:50%;">
						<input type="text" id="companyName" name="companyName" value="${dept.companyName }" class="layui-input" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top: 50px;">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="dept">保存</button>
					</div>
				</div>
			</form>
		<script type="text/javascript" src="<%=basePath %>/static/jzbTest/dept/edit.js"></script>
	</body>
</html>