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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/teacher/integralup.js"></script>
</head>
<body style="padding:10px;">
	<div class="alertFrom">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 84px;">选择文件:</label>
				<div class="layui-input-inline" style="width: 125px;">
					<input id="fileexcel" name="file" lay-type="file" type="file" class="layui-upload-file">
				</div>
				<div class="layui-input-inline" style="width: 100px;">
					<label class="layui-form-label" style="width: 84px;" id="filename"></label>
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 84px;">模板下载:</label>
				<div class="layui-input-inline">
					<button class="layui-btn" onclick="downloadexcel()">下载</button>
				</div>
			</div>
			
			<div class="layui-form-item">
				<div class="layui-input-block" >
					<button class="layui-btn" lay-submit="" id="teacher_info_tj">提交</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>