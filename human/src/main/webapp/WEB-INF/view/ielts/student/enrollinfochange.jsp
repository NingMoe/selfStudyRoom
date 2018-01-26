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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/student/enrollinfochange.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">考试时间：<span style="color: red">*</span> </label>
						<div class="layui-input-inline" style="width: 160px;">
							<input id="change_test_time" type="text" name="change_test_time" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">总分:</label>
						<div class="layui-input-inline">
							<input name="change_total" id="change_total" style="width: 160px;" type="text" placeholder="请输入总分" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">听力:</label>
						<div class="layui-input-inline">
							<input name="change_listening" id="change_listening" style="width: 160px;" type="text" placeholder="请输入听力" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">阅读:</label>
						<div class="layui-input-inline">
							<input name="change_reading" id="change_reading" style="width: 160px;" type="text" placeholder="请输入阅读" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">写作:</label>
						<div class="layui-input-inline">
							<input name="change_writing" id="change_writing" style="width: 160px;" type="text" placeholder="请输入写作" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">口语:</label>
						<div class="layui-input-inline">
							<input name="change_oral" id="change_oral" style="width: 160px;" type="text" placeholder="请输入口语" class="layui-input">
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">是否达标:</label>
						<div class="layui-input-inline">
							<select name="change_is_target" id="change_is_target" style="width: 160px;" class="layui-input">
								<option value="">未知</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="enroll_info_change" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	</body>
</html>