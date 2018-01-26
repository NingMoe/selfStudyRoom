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
				<div class="layui-form-item">
				   <label class="layui-form-label" style="width: 100px;">活动主题:</label>
					<div class="layui-input-inline" >
						<input type="text" id="activityName" name="activityName" autocomplete="off"  placeholder="请输入活动名称" lay-verify="required" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
								
				<div class="layui-form-item">
				   <label class="layui-form-label" style="width: 100px;">开始时间:</label>
					<div class="layui-input-inline">
						<input  id="startTime" name="startTime" autocomplete="off"  placeholder="请输入开始时间" lay-verify="required" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label"style="width: 100px;">结束时间:</label>
					<div class="layui-input-inline" >
						<input  id="endTime" name="endTime" autocomplete="off"  placeholder="请输入结束时间" lay-verify="required" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
															
				<div class="layui-form-item">
				    <label class="layui-form-label"style="width: 100px;">发送短信:</label>					
					<div class="layui-input-inline">
						<input type="checkbox" name="isSend" value="1" lay-filter="isSend" title="是否发送短信" >											
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
<script type="text/javascript" src="<%=basePath %>/static/sign/add.js"></script>
</html>