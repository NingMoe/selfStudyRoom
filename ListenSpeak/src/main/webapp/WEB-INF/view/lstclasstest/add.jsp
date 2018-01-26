<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:20%">考试名称:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:60%">
					<input type="text" id="name" name="name"  lay-verify="required" placeholder="请输入考试名称"
						 class="layui-input">
				</div>
			</div>
<!-- 			<div class="layui-form-item"> -->
<!-- 				<label class="layui-form-label" style="width:20%">考试时长:<font color="red">*</font></label> -->
<!-- 				<div class="layui-input-inline" style="width:60%"> -->
<!-- 					<input type="text" id="continueTime" name="continueTime" placeholder="请输入考试时长"  lay-verify="required"  -->
<!-- 						 class="layui-input"> -->
<!-- 				</div> -->
<!-- 			</div> -->
			<div class="layui-form-item" >
				<label class="layui-form-label" style="width:20%">结束时间:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:60%">
					<input class="layui-input" placeholder="请输入结束时间" id="endTime"  lay-verify="required"
						readonly="readonly" name="endTime">
				</div>
			</div>
			<div class="layui-form-item" >
				<label class="layui-form-label" style="width:20%">班级:<font color="red">*</font></label>
				<div class="layui-input-block" id="class" name="class">
					<c:forEach items="${classList}" var="list">
      					<input type="checkbox" name="${list.classCode}" title="${list.className}">
      				</c:forEach>
   				 </div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button  class="layui-btn" lay-submit=""  lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/lstclasstest/add.js"></script>
</html>