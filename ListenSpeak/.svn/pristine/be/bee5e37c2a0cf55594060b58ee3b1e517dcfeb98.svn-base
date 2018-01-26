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
			<form class="layui-form" id="addForm" action="" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">角色名:</label>
					<div class="layui-input-inline">
						<input type="text" name="roleName" lay-verify="required" placeholder="请输入角色名" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">角色名不能与已有角色重复</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">说明:</label>
					<div class="layui-input-inline">
						<textarea   name="roleDesc"  placeholder="简单描述角色作用"   autocomplete="off" class="layui-textarea"></textarea>
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
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/role_add.js"></script>
		<script>
		</script>
	</body>
</html>