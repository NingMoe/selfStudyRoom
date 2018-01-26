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
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post" >
			  <input type="hidden" name="id" value="${ccr.id}">
				<div class="layui-form-item">
				<label class="layui-form-label">规则名称:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="ruleName" lay-verify="required" autocomplete="off" placeholder="请输入规则名称" class="layui-input" value="${ccr.ruleName}">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">规则部门:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="deptName"  lay-verify="required" placeholder="请输入规则对应部门" autocomplete="off" class="layui-input" value="${ccr.deptName}">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/ContinuedClassRule/edit.js"></script>
</html>