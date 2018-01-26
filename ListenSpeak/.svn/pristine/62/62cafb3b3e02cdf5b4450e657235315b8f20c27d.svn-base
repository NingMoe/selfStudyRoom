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
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">字典KEY</label>
					<div class="layui-input-inline">
						<input type="text" name="key" id="key" lay-verify="required" placeholder="请输入字典KEY" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">字典名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name" lay-verify="required" placeholder="请输入字典名称" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">字典类型</label>
					<div class="layui-input-inline">
					<select name="type" id="type">
    					<option value="0">普通</option>
    					<option value="1">编码</option>
      				</select>
      				</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">键值唯一</label>
					<div class="layui-input-inline">
					<select name="isValueUnique" id="isValueUnique">
    					<option value="0">否</option>
    					<option value="1">是</option>
      				</select>
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
		 <script type="text/javascript" src="<%=basePath %>/static/basic/dic/dic_add.js"></script>
		<script>
		</script>
	</body>
</html>