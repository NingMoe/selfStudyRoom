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
				<label class="layui-form-label" style="width: 10%;">班级名称:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;">
					<input type="text" id="className" name="className"  lay-verify="required" 
						 class="layui-input">
				</div>
				<label class="layui-form-label" style="width: 10%;">年级:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;">
							<select name="grade" id="grade" lay-verify="required" >
								<option value="">请选择</option>
								<c:forEach items="${grade }" var="gra" >
									<option value="${gra.dataValue }">${gra.name }</option>
								</c:forEach>
							</select>
						</div>
			</div>
			<div class="layui-form-item" >
				<label class="layui-form-label" style="width: 10%;">科目:</label>
				<div class="layui-input-inline" style="width: 30%;">
							<select name="subject" id="subject" >
<!-- 								<option value="">请选择</option> -->
								<c:forEach items="${subject}" var="sub">
									<option value="${sub.dataValue }">${sub.name }</option>
								</c:forEach>
							</select>
				</div>
				<label class="layui-form-label" style="width: 10%;">失效时间:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;">
					<input class="layui-input" placeholder="失效时间" id="validTime"  lay-verify="required"
						readonly="readonly" name="validTime">
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
	src="<%=basePath%>/static/lstclass/add.js"></script>
</html>