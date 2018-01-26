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
		<input id="testId" type="hidden" value="${ltsa.testId }">
		<input id="testName" type="hidden" value="${ltsa.testName }">
		<input id="classCode" type="hidden" value="${ltsa.classCode }">
		<input id="studentId" type="hidden" value="${ltsa.studentId }">
		<input id="paperId" type="hidden" value="${ltsa.paperId }">
		<input id="xh" type="hidden" value="${question.xh }">
		<input id="total_question_num" type="hidden" value="${ltsa.total_question_num }">
		<input id="current_num" type="hidden" value="${question.tmNum }">
		<div id="quesDiv1">
			<blockquote class="layui-elem-quote layui-text" style="margin-left: 12%;">
			  <span>${testName }</span> <span style="margin-left:5%;">第${question.tmNum }题</span>
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
			
			    <div class="layui-form-item" style="margin-top: 20px;margin-bottom: 10px;">
					<label class="layui-form-label" style="width:49%;text-align: left;padding:9px 0px;">学生评价：&nbsp;&nbsp;</label>
				</div>
				<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
					<div class="layui-input-inline" style="width:100%;margin-right: 0px">
						<textarea name="answerComment" id="answerComment" class="layui-textarea contentTa" >${sques.testAnswer.answerComment}</textarea>
					</div>
				</div>
				<div class="layui-form-item" style="margin-bottom: 10px;">
					<label class="layui-form-label" style="width: 16%;">系统判分结果：</label>
					<label class="layui-form-label" style="width: 8%;">准确：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" value="${sques.testAnswer.accuracySogou }" class="layui-input" readonly="readonly"  style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">完整：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" value="${sques.testAnswer.integritySogou }" class="layui-input" readonly="readonly" style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">流利：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" value="${sques.testAnswer.fluencySogou }" class="layui-input" readonly="readonly" style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">总分：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" value="${sques.testAnswer.overallSogou }" class="layui-input" readonly="readonly"  style="width: 100%"> 
					</div>
				</div>
				<div class="layui-form-item pgzydiv" style="margin-bottom: 10px;">
					<input type="hidden" name="qaId" value="${sques.testAnswer.id }">
					<label class="layui-form-label" style="width: 16%;">人工判分结果：</label>
					<label class="layui-form-label" style="width: 8%;">准确：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" name="accuracy_teacher" value="${sques.testAnswer.accuracyTeacher }"   class="layui-input"  style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">完整：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" name="integrity_teacher" value="${sques.testAnswer.integrityTeacher }" class="layui-input"  style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">流利：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" name="fluency_teacher" value="${sques.testAnswer.fluencyTeacher }" class="layui-input"  style="width: 100%"> 
					</div>
					<label class="layui-form-label" style="width: 8%;">总分：</label>
					<div class="layui-input-inline" style="width:8%;margin-right: 0px">
						<input type="text" name="overall_teacher" value="${sques.testAnswer.overallTeacher }" class="layui-input"  style="width: 100%"> 
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
			<c:if test="${question.tmNum gt 1}">
				<button class="layui-btn" onclick="pgzy('2');">上一题</button>
			</c:if>
			<c:if test="${!empty question.nextXh }">
				<button class="layui-btn" onclick="pgzy('1');">下一题</button>
			</c:if>
			<c:if test="${empty question.nextXh }">
				<button class="layui-btn" id="closeBtn">提交</button>
			</c:if>
			</div>
		</div>
	</div>
<script type="text/javascript">
layui.use(['form','element'], function(){
	var form = layui.form,element=layui.element;
	$("#closeBtn").bind("click",function(){
		pgzy("3");
	});
});	

function pgzy(flag){
	if(flag=="1"){
		jyfs() && saveData(flag);
	}
	if(flag=="2"){
		tiaozhuan(flag);
	}
}

function saveData(flag){
	var data = [];
	$(".pgzydiv").each(function(){
		var answer = {};
		answer.accuracy_teacher = $(this).find("input[name='accuracy_teacher']").val();
		answer.integrity_teacher = $(this).find("input[name='integrity_teacher']").val();
		answer.fluency_teacher = $(this).find("input[name='fluency_teacher']").val();
		answer.overall_teacher = $(this).find("input[name='overall_teacher']").val();
		answer.answerComment=$(this).parent().find("#answerComment").val();
		answer.id = $(this).find("input[name='qaId']").val();
		answer.classCode=$("#classCode").val();
		answer.testId=$("#testId").val();
		answer.studentId=$("#studentId").val();
		answer.flag=flag;
		data.push(answer);
	});
	var total_question_num =$("#total_question_num").val();
	var current_num =$("#current_num").val();
	
	var details = JSON.stringify(data);
	var index =layer.load(3, {shade: [0.3]});
	$.post(jsBasePath+"/lstTestStudentAnswer/pgMultiTest.html",{"jstr":details},function(data,status){
		  layer.close(index); 
		  if(data.flag==false){
			  layer.alert(data.message,{icon:2});
		  }else{
			  if(current_num<total_question_num){
				  tiaozhuan(flag);	
			  }else{
				  if(current_num<total_question_num){
					  tiaozhuan(flag);
				  }else{
					  layer.alert("评分成功！",{icon:1},function(){
		  					parent.location.reload(); 
		  					closeFrame();
		  				});
				  }
			  }
		  }
	  },"json");
	return false;
}



function tiaozhuan(flag){
	var studentId = $("#studentId").val();
	var testId = $("#testId").val();
	var testName = $("#testName").val();
	var classCode = $("#classCode").val();
	var paperId=$("#paperId").val();
	var xh = $("#xh").val();
	var url = jsBasePath + "/lstTestStudentAnswer/correctTest.html?flag="+flag+"&studentId="+studentId
	  +"&testId="+testId+"&classCode="+classCode+"&testName="+testName+"&xh="+xh;
	location.href = jsBasePath + "/lstTestStudentAnswer/correctTest.html?flag="+flag+"&paperId="+paperId+"&studentId="+studentId+"&testId="+testId+"&classCode="+classCode+"&testName="+testName+"&xh="+xh;
}

function jyfs(){
	var flag = true;
	$(".pgzydiv").each(function(){
		var accuracy_teacher = $(this).find("input[name='accuracy_teacher']").val();
		var integrity_teacher = $(this).find("input[name='integrity_teacher']").val();
		var fluency_teacher = $(this).find("input[name='fluency_teacher']").val();
		var overall_teacher = $(this).find("input[name='overall_teacher']").val();
		
		if($.trim(accuracy_teacher)=="" || $.trim(integrity_teacher)=="" || $.trim(fluency_teacher)=="" || $.trim(overall_teacher)=="" ){
			layer.alert("请填写完评分再提交",{icon:2});
			flag = false;
			return false;
		}
	});
	return flag;
}
</script>
</body>
</html>