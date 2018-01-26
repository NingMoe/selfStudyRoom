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
<style>
.layui-colla-item :first-child {border :1px solid #ddd !important;}
</style>
<body style="padding: 20px;">
	<div class="layui-form">
		<input id="testId"  type="hidden" value="${ltsa.testId }">
		<input id="testName" type="hidden" value="${ltsa.testName }">
		<input id="classCode" type="hidden" value="${ltsa.classCode }">
		<input id="studentId" type="hidden" value="${ltsa.studentId }">
		<input id="paperId" type="hidden" value="${ltsa.paperId }">
		<input id="xh" type="hidden" value="${question.xh }">
		<input id="total_question_num" type="hidden" value="${ltsa.total_question_num }">
		<input id="current_num" type="hidden" value="${question.tmNum }">
		<input id="qaId" type='hidden' value="${question.testAnswer.id}">	
		<div id="quesDiv1">
			<blockquote class="layui-elem-quote layui-text" style="margin-left: 12%;">
			  <span>${zuoyeName }</span> <span style="margin-left:5%;">第${question.tmNum }题</span>
			</blockquote>
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
			
			
			
<!-- 			<div class="layui-form-item" style="margin-bottom: 10px;"> -->
				
<!-- 			</div> -->
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<div class="layui-input-inline" id="recordDiv" style="width:80%;padding:8px 10px; margin-left: 12%;margin-right: 0px;">
				<label class="layui-form-label" style=";padding:9px 0px;">学生答案：&nbsp;&nbsp;</label>
					<div id="audioDiv" class="layui-input-inline" style="width:30%;">
						<audio src="${fileurl }${question.answer.student_answer}" controls="controls"></audio>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top: 20px;margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">学生评价：&nbsp;&nbsp;</label>
			</div>
			
			<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
				<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
					<textarea name="answerComment" id="answerComment" class="layui-textarea" lay-verify="required" >${question.testAnswer.answerComment}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width: 16%;">系统判分结果：</label>
				<label class="layui-form-label" style="width: 8%;">准确：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.testAnswer.accuracySogou }" class="layui-input" readonly="readonly" style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">完整：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.testAnswer.integritySogou }" class="layui-input" readonly="readonly" style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">流利：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.testAnswer.fluencySogou }" class="layui-input" readonly="readonly"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">总分：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" style="border:1px solid #ddd" value="${question.testAnswer.overallSogou }" readonly="readonly" class="layui-input"  style="width: 100%"> 
				</div>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width: 16%;">人工判分结果：</label>
				<label class="layui-form-label" style="width: 8%;">准确：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="accuracyTeacher" value="${question.testAnswer.accuracyTeacher }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">完整：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="integrityTeacher" value="${question.testAnswer.integrityTeacher }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">流利：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="fluencyTeacher"  value="${question.testAnswer.fluencyTeacher }"  class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">总分：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="overallTeacher" value="${question.testAnswer.overallTeacher }" class="layui-input"  style="width: 100%"> 
				</div>
			</div>
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
		}if(flag=="3"){
			jyfs() && saveData(flag);
		}
		return true;
	}
	
	function saveData(flag){
		var data = {};
		data.accuracyTeacher = $("#accuracyTeacher").val();
		data.integrityTeacher = $("#integrityTeacher").val();
		data.fluencyTeacher = $("#fluencyTeacher").val();
		data.overallTeacher = $("#overallTeacher").val();
		data.id = $("#qaId").val();
		data.classCode=$("#classCode").val();
		data.testId=$("#testId").val();
		data.studentId=$("#studentId").val();
		data.answerComment=$("#answerComment").val();
		data.flag=flag;

		var total_question_num =$("#total_question_num").val();
		var current_num =$("#current_num").val();
		
		if($.trim(data.accuracyTeacher)!="" &&$.trim(data.integrityTeacher)!="" && $.trim(data.fluencyTeacher)!="" && $.trim(data.overallTeacher)!="" ){
			var index =layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/lstTestStudentAnswer/pgSimpleTest.html",data,function(data,status){
				  layer.close(index); 
				  if(data.flag==false){
					  layer.alert(data.message,{icon:2});
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
			  },"json");
		}else{
			layer.alert("请填写完评分再提交",{icon:2});
		}
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
		  +"&testId="+testId+"&classCode="+classCode+"&testName="+testName+"&xh="+xh+"&paperId="+paperId;
		location.href = url;	
	}
	
	function jyfs(){
		var flag = true;
		var accuracyTeacher = $("#accuracyTeacher").val();
		var integrityTeacher = $("#integrityTeacher").val();
		var fluencyTeacher = $("#fluencyTeacher").val();
		var overallTeacher = $("#overallTeacher").val();
		if($.trim(accuracyTeacher)=="" || $.trim(integrityTeacher)=="" || $.trim(fluencyTeacher)=="" || $.trim(overallTeacher)=="" ){
			layer.alert("请填写完评分再提交",{icon:2});
			flag = false;
		}
		return flag
	}
	</script>
</body>
</html>