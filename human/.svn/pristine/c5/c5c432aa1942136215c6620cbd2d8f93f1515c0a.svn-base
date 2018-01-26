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
 <div class="alertFrom" style="height:200px;">
			<form class="layui-form" id="addForm" action="" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="ruleId" id="ruleId" value="${cf.ruleId}">
	         <input type="hidden" name="type" id="type" value="${cf.type}">
			  	<div class="layui-form-item">
					<label class="layui-form-label" style="width:100px;">开课时间</label>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="开课时间" lay-verify="required" id="classStartDate" name="classStartDate">
					</div>
				</div>
						
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="tj" style="margin-right:20px;">保存</button>
						<button type="button" class="layui-btn" onclick="cancle()" >取消</button>
					</div>
				</div>
			</form>
		</div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/importData/filterStudentsByDate.js"></script>
</html>