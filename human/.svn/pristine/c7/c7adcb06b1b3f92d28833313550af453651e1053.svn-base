<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	 <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	 <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>	    
<style type="text/css">
</style>
<body style="padding:20px;height:10px;position:relative;">
<form class="layui-form" id="addForm" action="" method="post">
   <input type="hidden" id="flowId" name="flowId" value="${hrResumeFlow.id }">	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试时间</label>
		<div class="layui-input-inline">
			<input class="layui-input" placeholder="开始日期" lay-verify="required" id="interviewTime" name="interviewTime" value="${hrResumeFlow.interviewTime}">
		</div>
	</div>
	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试地点</label>
		<div class="layui-input-inline">
			<input class="layui-input" placeholder="面试地点" lay-verify="required" id="interviewLocation" name="interviewLocation" value="${hrResumeFlow.interviewLocation}">
		</div>
	</div>
	
<!-- 	<div class="layui-form-item"> -->
<!-- 		<label class="layui-form-label" style="width:100px;"></label> -->
<!-- 		<div class="layui-input-inline"> -->
<!-- 			<input type="checkbox" id="isNotice" name="isNotice" lay-skin="primary" value="1" title="短信通知应聘者"> -->
<!-- 		</div> -->
<!-- 	</div> -->
	
	<div class="layui-form-item" style="left:10px;">
		<div class="layui-input-block" >
			<button class="layui-btn" lay-submit="" lay-filter="tj">确定</button>
			<button class="layui-btn" onclick="cancle()">取消</button>
		</div>
	</div>
</form>
</body>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/interview/edit.js"></script>
</html>