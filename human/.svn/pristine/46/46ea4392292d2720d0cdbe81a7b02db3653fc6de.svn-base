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
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
			 <input id="id" name="id" type="hidden" value="${area.id }">
			 <input type="hidden" name="areaLevel" value="${area.areaLevel }">																
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">省份名称</label>
					<div class="layui-input-inline">
						<input type="text" name="areaName" id="areaName" lay-verify="required" placeholder="请输入省份名称" autocomplete="off" class="layui-input" value="${area.areaName }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>																	
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="reMail">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body> 
<script type="text/javascript" src="<%=basePath %>/static/basic/js/areaInfo/update.js"></script>
</html>
