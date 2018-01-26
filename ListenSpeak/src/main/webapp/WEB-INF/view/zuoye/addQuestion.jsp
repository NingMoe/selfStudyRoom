<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<!-- load css -->
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link rel="stylesheet" href="<%=basePath %>/static/zuoye/question.css">
</head>
<body>
<div class="layui-form">
	<input type="hidden" id="zuoyeId" value="${param.id }">
	<div class="layui-collapse" lay-accordion="" style="width: 90%;margin: 10px auto;">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">已选题目</h2>
			<div class="layui-colla-content layui-show">
			  	<div style="width: 90%;margin: 0px auto;">
			  		<table class="layui-table" id="zuoyeQuestionTable" lay-filter="zyQuestion"></table>
			  	</div>
		  	</div>
		</div>
	</div>
	
	<div class="layui-collapse" lay-accordion="" style="width: 90%;margin: 10px auto;">
		<div class="layui-colla-item">
			<h2 class="layui-colla-title">可选题目</h2>
			<div class="layui-colla-content layui-show">
				<div class="layui-form-item" style="margin-bottom:20px;">
				<label class="layui-form-label" style="width:7%;">年级:</label>
				<div class="layui-input-inline" style="width:12%;">
					<select name="grade" id="grade">
						<option value="">请选择</option>
						<c:forEach items="${grades }" var="grade">
						<option value="${grade.dataValue }">${grade.name }</option>
						</c:forEach>
					</select>
				</div>
				<label class="layui-form-label" style="width:7%;">难度:</label>
				<div class="layui-input-inline" style="width:12%;">
					<select name="difficulty" id="difficulty">
						<option value="">请选择</option>
						<option value="A">简单</option>
						<option value="B">中等</option>
						<option value="C">较难</option>
					</select>
				</div>		
				<label class="layui-form-label" style="width:7%;">题型:</label>
				<div class="layui-input-inline" style="width:12%;">
					<select name="questionType" id="questionType">
						<option value="">请选择</option>
						<c:forEach items="${questionTypes }" var="questionType">
						<option value="${questionType.id }">${questionType.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="layui-input-inline">
					<button id="searchBtn" class="layui-btn">搜索</button>
				</div>
				</div>
			<div style="width: 90%;margin: 0px auto;">
				<div id="questionContent" class="qc"></div>
				<div id="questionPage"></div>
				
			<!-- <table class="layui-table" id="questionTable" lay-filter="question"></table> -->
			</div>
		</div>
			</div>
			</div>
</div>
</body>
<script type="text/html" id="diffTpl">
 {{ d.difficulty == 'A' ? '简单' : (d.difficulty == 'B' ? '中等' : '较难') }}
</script>

<script type="text/html" id="zuoyequestionbar">
 <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a> 
 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sctm">删除题目</a>
</script>
<script type="text/javascript" src="<%=basePath %>/static/zuoye/addQuestion.js"></script>
</html>