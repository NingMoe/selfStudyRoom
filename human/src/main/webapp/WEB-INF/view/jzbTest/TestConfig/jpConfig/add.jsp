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
				<label class="layui-form-label" style="width: 10%;">部门:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="dept" id="dept">
							<option value="${dep}">${dept}</option>
					</select>
				</div>
				<label class="layui-form-label" style="width: 10%;">科目:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="subject" id="subject"  lay-filter="subjects">
						<option value="">请选择</option>
						<c:forEach items="${subjects}" var="subjects">
							<option value="${subjects.dataValue}">${subjects.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label" style="width: 10%; ">班型:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="classType" id="classType">
						<option value="">请选择</option>
						<c:forEach items="${classTypes}" var="classType">
							<option value="${classType.dataValue }">${classType.name}</option>
						</c:forEach>
					</select>
			</div>
				<label class="layui-form-label" style="width: 10%;">年级:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="grade" id="grade">
						<option value="">请选择</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade.id }">${grade.gradeName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/jzbTest/TestConfig/jpConfig/add.js"></script>
</html>