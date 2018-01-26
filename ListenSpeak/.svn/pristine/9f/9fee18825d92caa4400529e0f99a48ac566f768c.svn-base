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
	<div class="layui-form">
		<input id="zuoyeId" type="hidden" value="${zuoyeId }">
		<input id="zuoyeName" type="hidden" value="${zuoyeName }">
		<div id="quesDiv1">
			<blockquote class="layui-elem-quote layui-text" style="margin-left: 12%;">
			  <span>${zuoyeName }</span> <span style="margin-left:5%;">第${question.tmNum }题</span>
			</blockquote>
			<c:if test="${!empty question.zdmessage}">
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width:50%;text-align: left;margin-left: 12%;padding:9px 0px;">指导语：&nbsp;&nbsp;</label>
				</div>
				<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
					<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
						<textarea name="zdmessage" id="zdmessage" class="layui-textarea" readonly="readonly">${question.zdmessage }</textarea>
					</div>
				</div>
			</c:if>
			
			<c:if test="${!empty question.topic }">
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width:30%;margin-left: 12%;text-align: left;padding:9px 0px;">大题干：&nbsp;&nbsp;</label>
					<c:if test="${!empty question.topicTime }">
					<label class="layui-form-label" style="width:22%;padding:9px 0px;">题干熟悉时间(单位为秒)&nbsp;&nbsp;</label>
					<div class="layui-input-inline" style="width:20%;margin-right: 0px">
						<input type="text" id="topicTime" name="topicTime" class="layui-input" readonly="readonly" value="${question.topicTime }" style="width: 100%"> 
					</div>
					</c:if>
				</div>
			</c:if>
			
			<c:if test="${!empty question.topic }">
			<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
				<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
					<textarea name="topic" id="topic" class="layui-textarea topicTa" readonly="readonly">${question.topic }</textarea>
				</div>
			</div>
			</c:if>
			
			<c:forEach items="${question.questionList }" var="sques" varStatus="status">
			<div class="layui-collapse" lay-accordion="" style="width:74%;margin-left: 11%;margin-top:4px;">
                <div class="layui-colla-item">
                	<h2 class="layui-colla-title">小题${status.index+1 }</h2>
                	<div class="layui-colla-content">
				<div id="qu${status.index+1 }">
				<input type="hidden" name="parseAudio" value="${sques.parseAudio }">
				<input type="hidden" name="id" value="${sques.id }">
				
			    <div class="layui-form-item" style="margin-top: 20px;margin-bottom: 10px;">
					<label class="layui-form-label" style="width:49%;text-align: left;padding:9px 0px;">题干：&nbsp;&nbsp;</label>
					<c:if test="${!empty sques.contentTime }">
					<label class="layui-form-label" style="width:29%;padding:9px 0px;">题干熟悉时间(单位为秒)&nbsp;&nbsp;</label>
					<div class="layui-input-inline" style="width:22%;margin-right: 0px">
						<input type="text" name="contentTime" class="layui-input" readonly="readonly" value="${sques.contentTime }" style="width: 100%"> 
					</div>
					</c:if>
				</div>
			
				<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
					<div class="layui-input-inline" style="width:100%;margin-right: 0px">
						<textarea name="content" class="layui-textarea contentTa" readonly="readonly">${sques.content }</textarea>
					</div>
				</div>
				
				
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width:49%;text-align:left;padding:9px 0px;">
						<c:if test="${!empty sques.parseText}">
							解析：&nbsp;&nbsp;
						</c:if>
						<c:if test="${empty sques.parseText}">
							解析：该题型无文本解析&nbsp;&nbsp;
						</c:if>
					</label>
					<c:if test="${!empty sques.answerTime }">
					<label class="layui-form-label" style="width:29%;padding:9px 0px;">作答时间(单位为秒)&nbsp;&nbsp;</label>
					<div class="layui-input-inline" style="width:22%;margin-right: 0px">
						<input type="text" name="answerTime" class="layui-input" readonly="readonly" value="${sques.answerTime }"  style="width: 100%">  
					</div>
					</c:if>
				</div>
				<c:if test="${!empty sques.parseText }">
				<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
					<div class="layui-input-inline" style="width:100%;margin-right: 0px">
						<textarea name="parseText" class="layui-textarea parseTextTa" readonly="readonly">${sques.parseText }</textarea>
					</div>
				</div>
				</c:if>
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width:49%;text-align: left;padding:9px 0px;">音频解析：&nbsp;&nbsp;</label>
				</div>
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<div class="layui-input-inline" id="recordDiv" style="width:90%;height:71px;padding:8px 10px;margin-right: 0px;">
						<%-- <div class="layui-input-inline" style="width:80px;">
							<img id="record${status.index+1 }" class="strecord"  style="width:55px;cursor: pointer;" src="<%=basePath%>/static/question/image/luyin.png" width="10px">
						</div>
						<div class="layui-input-inline" style="width:80px;margin-left:40px;">
							<img id="stop${status.index+1 }" style="width:55px;cursor: pointer;" src="<%=basePath%>/static/question/image/stop.png" width="10px">
						</div> --%>
						<div id="audioDiv${status.index+1 }" class="detailAudio" class="layui-input-inline" style="width:46%;float:left; margin-left:40px;margin-top: 14px;">
							<c:if test="${!empty sques.parseAudio}">
								<audio src="${fileurl }${sques.parseAudio}" controls="controls"></audio>
							</c:if>
						</div>
					</div>
				</div>
				</div>
				</div>
				</div>
			</div>
			</c:forEach>
		</div>
	<div class="layui-form-item">
			<div class="layui-input-block" style="margin-left: 12%">
			<c:if test="${empty question.nextXh }">
				<button class="layui-btn" id="closeBtn">关闭</button>
			</c:if>
			<c:if test="${!empty question.nextXh }">
				<button class="layui-btn" onclick="nextQues('${question.xh}')">下一题</button>
			</c:if>
			</div>
		</div>
	</div>
<script type="text/javascript">
layui.use(['form','element'], function(){
	var form = layui.form,element=layui.element;
	$("#closeBtn").bind("click",function(){
		closeFrame();
	});
});	

function nextQues(xh){
	var zuoyeId = $("#zuoyeId").val();
	var zuoyeName = $("#zuoyeName").val();
	location.href = "<%=basePath %>/zuoye/toYulan.html?zuoyeId="+zuoyeId+"&currentSort="+xh+"&zuoyeName="+zuoyeName;
}
</script>
</body>
</html>