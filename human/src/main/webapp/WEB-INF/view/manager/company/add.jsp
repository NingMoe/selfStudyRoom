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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	</head>
	<body style="padding:20px;">
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">机构名称</label>
					<div class="layui-input-inline">
						<input type="text" name="companyName" lay-verify="required" placeholder="请输入机构名称" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">机构ID</label>
					<div class="layui-input-inline">
						<input type="text" name="companyId" lay-verify="required" placeholder="请输入机构ID" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">短信ID</label>
					<div class="layui-input-inline">
					<input type="text" name="messageId" placeholder="请输入短信ID" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="compa">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/company_add.js"></script>
		<script>
		</script>
	</body>
</html>