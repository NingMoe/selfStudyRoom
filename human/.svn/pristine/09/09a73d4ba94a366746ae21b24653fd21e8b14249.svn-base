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
<body style="padding:10px;height:340px;position:relative;">
<form class="layui-form">
	<input type="hidden" name="positiveId" id="positiveId" value="${interview.positiveId }">
	<div class="layui-form-item" style="margin-top: 20px;">
		<label class="layui-form-label" style="width:30%;">转正面谈记录</label>
		<div class="layui-input-inline" style="width:60%;">
			<textarea id="interviewComment" name="interviewComment" class="layui-textarea">${interview.interviewComment }</textarea>
		</div>
	</div>
			
	<div class="layui-form-item">
		<label class="layui-form-label" style="width:30%;">面试是否通过</label>
		<div class="layui-input-inline" style="width:60%;">
			<input type="radio" name="interviewResult" title="通过" 
	   		<c:if test="${interview.interviewResult eq '1' }">checked="checked"</c:if> 
	   		lay-filter="refuse" value="1">
	   		<input type="radio" name="interviewResult" title="不通过" 
	   		<c:if test="${interview.interviewResult eq '2' }">checked="checked"</c:if> 
	   		lay-filter="refuse" value="2">
		</div>
	</div>
	<div class="layui-form-item" id="refuse" style="display:none;">		
	<label class="layui-form-label" style="width:30%;">不通过原因</label>
		<div class="layui-input-inline" style="width:60%;">
			<input type="text" id="refuseReason" name="refuseReason" class="layui-input" value="${interview.refuseReason }">
		</div>
	</div>
	<div class="layui-form-item" style="position:absolute;left:80px;bottom:0;margin-bottom:5px;">
		<div class="layui-input-block" >
			<button type="button" id="tj" class="layui-btn">确定</button>
			<button type="button" id="qx" class="layui-btn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="<%=basePath %>/static/recruitment/positiveInterview/interview.js"></script>
</body>
</html>