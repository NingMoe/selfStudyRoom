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
<body style="padding:10px;height:400px;position:relative;">
<form class="layui-form">
	<input type="hidden" name="ids" id="ids" value="${param.ids }">
	<div class="layui-form-item layui-form-text" style="margin-top:20px;">
		<label class="layui-form-label" style="width:120px;">时间</label>
		<div class="layui-input-inline" style="width:235px;">
			<input class="layui-input" lay-verify="required" id="interviewTime" 
			name="interviewTime" placeholder="面试时间" 
			onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
		</div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:120px;">地点</label>
		<div class="layui-input-inline" style="width:235px;">
		     <input type="text" id="interviewLocation" name="interviewLocation" lay-verify="required" class="layui-input">
		</div>
	</div>   
	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:120px;">面试官</label>
		<div class="layui-input-inline" style="width:235px;">
			<input type="text" id="interviewer" name="interviewer" lay-verify="required" class="layui-input">
		</div>
	</div>
	
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:120px;">备注</label>
		<div class="layui-input-inline" style="width:235px;">
			<textarea id="interviewRemark" name="interviewRemark" class="layui-textarea"></textarea>
		</div>
	</div>
			
	<!-- <div class="layui-form-item">
		<label class="layui-form-label" style="width:120px;"></label>
		<div class="layui-input-inline">
		     <input type="checkbox" id="isSendMsg" name="isSendMsg" lay-skin="primary" 
		     value="1" title="同时发送短信提醒" lay-filter="rencai">
		</div>
	</div> -->
	
	<div class="layui-form-item" style="position:absolute;left:80px;bottom:0;margin-bottom:5px;">
		<div class="layui-input-block" >
			<button type="button" id="tj" class="layui-btn">确定</button>
			<button type="button" id="qx" class="layui-btn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/positive/arrangement.js"></script>
</body>
</html>