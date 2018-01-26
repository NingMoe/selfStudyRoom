<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title>修改密码</title>
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
			<form class="layui-form" id="editForm" action="" method="post" >
			<div class="layui-form-item">
					<label class="layui-form-label">手机号:</label>
					<div class="layui-input-inline">
						<input type="text" name="phone" id="phone" lay-verify="required"   placeholder="请输入手机号"  onblur="checkPhone();" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机验证码:</label>
					<div class="layui-input-inline">
						<input type="text" name="code"  id="msg" placeholder="请输入验证码"  lay-verify="required" autocomplete="off" class="layui-input">						
					</div>
					<div class="layui-input-inline">
					   <input type="button" id="get_msg_btn" class="layui-btn" onclick ="return validate();" value="点击发送">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">设置新密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="passWord"  id="passWord"  lay-verify="pass"   placeholder="请输入新密码"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认新密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="subPassword" lay-verify="subPassword" placeholder="请再输入新密码" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请再如入一次新密码</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		 <script type="text/javascript" src="<%=basePath %>/static/framework/js/changePassWord.js"></script>
		<script>
		
		</script>
	</body>
</html>