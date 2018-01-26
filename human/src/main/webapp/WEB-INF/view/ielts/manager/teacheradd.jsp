<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增学员信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/manager/teacheradd.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教师姓名:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<input name="add_teacher_name" id="add_teacher_name" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入教师姓名" class="layui-input">
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教师邮箱前缀:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<input name="teacher_mail_addr" id="teacher_mail_addr" style="width: 160px;" type="text" placeholder="请输入教师邮箱前缀" class="layui-input">
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="teacher_info_add" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	</body>
</html>