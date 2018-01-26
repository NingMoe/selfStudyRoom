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
		<input type="hidden" id="id" name="id" value="${jzb.id}"   
						class="layui-input">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">部门:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="dept" id="dept">
							<option value="${dep}">${dept}</option>
					</select>
				</div>
				<label class="layui-form-label" style="width: 10%;">科目:</label>
				<div class="layui-input-inline"  style="width: 30%;">
					<select name="subject" id="subject"  lay-filter="subjects">
						<option value="">请选择</option>
						<c:forEach items="${subjects}" var="subjects">
							<option value="${subjects.name} " <c:if test="${jzb.subject eq subjects.dataValue}">selected="selected"</c:if>>${subjects.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label" style="width: 10%;">班型:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="classType" id="classType">
						<option value="">请选择</option>
						<c:forEach items="${classTypes}" var="classType">
							<option value="${classType.name }"  <c:if test="${jzb.classType eq classType.dataValue}">selected="selected"</c:if>>${classType.name}</option>
						</c:forEach>
					</select>
			</div>
				<label class="layui-form-label" style="width: 10%;">年级:</label>
				<div class="layui-input-inline " style="width: 30%;">
					<select name="grade" id="grade">
						<option value="">请选择</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade.gradeName }" <c:if test="${jzb.grade eq grade.id}">selected="selected"</c:if>>${grade.gradeName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 10%;">知识点:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;">
					<input type="text" id="knowledge" name="knowledge" value="${jzb.knowledge}"
						lay-verify="required" class="layui-input">
				</div>
				<label class="layui-form-label" style="width: 10%;">是否为复杂题型<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;" >
					<select  id="mulit" lay-filter="mulit">
							<option value="2" <c:if test="${jzb.titleNum ne '1' }">selected="selected"</c:if>>复杂</option>
							<option value="1" <c:if test="${jzb.titleNum eq '1' }">selected="selected"</c:if>>简单</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label title" style="width: 10%;">题目数量:<font color="red">*</font></label>
				<div class="layui-input-inline title" style="width: 30%;">
					<input type="text" id="titleNum" name="titleNum" value="${jzb.titleNum}"
						lay-verify="required" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/jzbTest/TestConfig/edit.js"></script>
</html>