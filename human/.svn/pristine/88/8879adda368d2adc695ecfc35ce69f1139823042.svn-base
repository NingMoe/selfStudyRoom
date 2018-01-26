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
<body style="padding:20px;height:360px;position:relative;">
<form class="layui-form">
	<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
	<input id="flowId" type="hidden" name="flowId" value="${flowId }">
	<input id="flowCode" type="hidden" name="flowCode" value="${flowCode }">
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试部门</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="dept" id="dept" lay-filter="dept" >
				<option value="">请选择</option>
				<c:forEach items="${orgs }" var="org">
					<option value="${org.id }">${org.name }</option>
				</c:forEach>
			</select>
		</div>
	</div>
			
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试职位</label>
		<div class="layui-input-inline" style="width:300px;">
			<select name="process" id="process" lay-verify="required">
				<option value="">请选择</option>
			</select>
		</div>
	</div>
	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试时间</label>
		<div class="layui-input-inline">
			<input class="layui-input" lay-verify="required" placeholder="面试时间" name="interviewTime" id="interviewTime"
			onclick="layui.laydate({elem: this,format: 'YYYY-MM-DD hh:mm:ss'})"
			>
		</div>
	</div>
	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;">面试地点</label>
		<div class="layui-input-inline">
			<input class="layui-input" lay-verify="required" placeholder="面试地点" id="interviewLocation" name="interviewLocation" >
		</div>
	</div>
	
	<!-- <div class="layui-form-item">
		<label class="layui-form-label" style="width:100px;"></label>
		<div class="layui-input-inline">
			<input type="checkbox" id="isNotice" name="isNotice" lay-skin="primary" value="1" title="短信通知应聘者">
		</div>
	</div> -->
	
	<div class="layui-form-item" style="position:absolute;left:60px;bottom:0">
		<div class="layui-input-block" >
			<button id="tj" class="layui-btn" lay-submit="" lay-filter="tj">保存</button>
			<button type="button" id="qx" class="layui-btn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/elimination/changePosition.js"></script>
</body>
</html>