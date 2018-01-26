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
				<label class="layui-form-label" style="width:13%;" value="">试卷名称:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:60%;">
					<input type="text" id="name" name="name"   lay-verify="required"
						 class="layui-input" placeholder="请输入试卷名称">
				</div>	
			</div>
			<div class="layui-form-item">	
				<label class="layui-form-label" style="width:13%;">试卷来源:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:60%;">
					   <select name="sourceType" id="sourceType" lay-verify="required" >
					        <option value="">请选择</option>
	    					<c:forEach var="source" items="${paperSources}">
		       	              <option value="${source.dataValue }">${source.name}</option>
		                    </c:forEach>
	    				</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label" style="width:13%;">年份:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:60%;" >
					   <select name="year" id="year" lay-verify="required">
					        <option value="">请选择</option>
	    					<c:forEach var="year" items="${years}">
		       	              <option value="${year.name }">${year.name}</option>
		                    </c:forEach>
	    				</select>
				</div>
			</div>	
			<c:forEach items="${lists}" var="list">
			<div class="layui-form-item questionNum">	
				<label class="layui-form-label" style="width:13%;" value="">${list.name}:</label>
				<div class="layui-input-inline" style="width:60%;">
					<input type="text" id="${list.id}" name="${list.id}"  lay-verify="number" 
						 class="layui-input" placeholder="题数">
				</div>
			</div>
			</c:forEach>
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
	src="<%=basePath%>/static/lstbasepaper/add.js"></script>
</html>