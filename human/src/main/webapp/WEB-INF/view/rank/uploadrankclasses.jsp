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
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
   			    <div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">上传文件:</label>
					<div class="layui-input-inline">
						<input name="file" lay-type="file" class="layui-upload-file" type="file"> 
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">模板下载:</label>
					<div class="layui-input-inline">
						<button class="layui-btn" onclick="downloadexcel();">下载</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	    <script type="text/javascript" src="<%=basePath %>/static/rank/uploadrankclasses.js"></script>
	</body>
</html>