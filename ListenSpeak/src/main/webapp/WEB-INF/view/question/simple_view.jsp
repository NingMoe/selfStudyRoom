<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<body style="padding: 20px;">
	<form class="layui-form">
		<input type="hidden" id="id" name="id" value="${question.id }">
		<input type="hidden" id="parseAudio" name="parseAudio" value="${question.parseAudio }">
		<div id="quesDiv1">
			<c:if test="${!empty question.zdmessage}">
				<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width:22%;text-align: left;margin-left: 12%;padding:9px 0px;">指导语：&nbsp;&nbsp;</label>
					<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
						<textarea name="zdmessage" id="zdmessage" class="layui-textarea" lay-verify="required" readonly="readonly">${question.zdmessage }</textarea>
					</div>
				</div>
			</c:if>
			
			<div class="layui-form-item" style="margin-top: 20px;margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">题干：&nbsp;&nbsp;</label>
				<c:if test="${!empty question.contentTime}">
				<label class="layui-form-label" style="width:20%;padding:9px 0px;">题干熟悉时间(单位为秒)&nbsp;&nbsp;</label>
				<div class="layui-input-inline" style="width:22%;margin-right: 0px">
					<input type="text" id="contentTime" name="contentTime" class="layui-input" value="${question.contentTime }" readonly="readonly" lay-verify="required" style="width: 100%"> 
				</div>
				</c:if>
			</div>
			
			<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
				<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
					<textarea name="content" id="content" class="layui-textarea" lay-verify="required" readonly="readonly">${question.content }</textarea>
				</div>
			</div>
			
			
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">
					<c:if test="${!empty question.parseText}">
						解析：&nbsp;&nbsp;
					</c:if>
					<c:if test="${empty question.parseText}">
						解析：该题型无文本解析&nbsp;&nbsp;
					</c:if>
				</label>
				<c:if test="${questionType.lType eq '2' }">
				<label class="layui-form-label" style="width:20%;padding:9px 0px;">作答时间(单位为秒)&nbsp;&nbsp;</label>
				<div class="layui-input-inline" style="width:15%;margin-right: 0px">
					<input type="text" id="answerTime" name="answerTime" lay-verify="required" readonly="readonly" class="layui-input" value="${question.answerTime }" style="width: 100%">  
				</div>
				</c:if>
			</div>
			<c:if test="${!empty question.parseText}">
			<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
				<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
					<textarea name="parseText" id="parseText" lay-verify="required" class="layui-textarea" readonly="readonly">${question.parseText }</textarea>
				</div>
			</div>
			</c:if>
			
			
			
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">音频解析：&nbsp;&nbsp;</label>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<div class="layui-input-inline" id="recordDiv" style="width:80%;height:71px;padding:8px 10px; margin-left: 12%;margin-right: 0px;">
					<%-- <div class="layui-input-inline" style="width:80px;">
						<img id="record" style="width:55px;cursor: pointer;" src="<%=basePath%>/static/question/image/luyin.png" width="10px">
					</div>
					<div class="layui-input-inline" style="width:80px;margin-left:40px;">
						<img id="stop" style="width:55px;cursor: pointer;" src="<%=basePath%>/static/question/image/stop.png" width="10px">
					</div> --%>
					<div id="audioDiv" class="layui-input-inline" style="width:30%;margin-left:40px;margin-top: 14px;">
						<audio src="${fileurl }${question.parseAudio}" controls="controls"></audio>
					</div>
					 
				</div>
			</div>
		
		</div>
		<!-- 
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="tj">保存</button>
			</div>
		</div> -->
	</form>
	<script type="text/javascript">
	layui.use(['form','element'], function(){
		var form = layui.form,element=layui.element;
	});	
	</script>
</body>
</html>