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
		<input id="zuoye_id" type="hidden" value="${answer.zuoye_id }">
		<input id="zuoye_name" type="hidden" value="${answer.zuoye_name }">
		<input id="class_code" type="hidden" value="${answer.class_code }">
		<input id="student_id" type="hidden" value="${answer.student_id }">
		<input id="xh" type="hidden" value="${question.xh }">
		<input id="qaId" type="hidden" value="${question.answer.id }">
		<input id="total_question_num" type="hidden" value="${answer.total_question_num }">
		<input id="current_num" type="hidden" value="${question.tmNum }">
		
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
			
			
			
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">学生答案：&nbsp;&nbsp;</label>
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
						<audio src="${fileurl }${question.answer.student_answer}" controls="controls"></audio>
					</div>
				</div>
			</div>
			<div class="layui-form-item" style="margin-top: 20px;margin-bottom: 10px;">
				<label class="layui-form-label" style="width:30%;text-align: left;margin-left: 12%;padding:9px 0px;">学生评价：&nbsp;&nbsp;</label>
			</div>
			
			<div class="layui-form-item layui-form-text" style="margin-bottom: 10px;">
				<div class="layui-input-inline" style="width:72%;margin-left: 12%;margin-right: 0px">
					<textarea name="answer_comment" id="answer_comment" class="layui-textarea" lay-verify="required" >${question.answer.answer_comment}</textarea>
				</div>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width: 16%;">系统判分结果：</label>
				<label class="layui-form-label" style="width: 8%;">准确：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.answer.accuracy_sogou }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">完整：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.answer.integrity_sogou }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">流利：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.answer.fluency_sogou }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">总分：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" value="${question.answer.overall_sogou }" class="layui-input"  style="width: 100%"> 
				</div>
			</div>
			<div class="layui-form-item" style="margin-bottom: 10px;">
				<label class="layui-form-label" style="width: 16%;">人工判分结果：</label>
				<label class="layui-form-label" style="width: 8%;">准确：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="accuracy_teacher" value="${question.answer.accuracy_teacher }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">完整：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="integrity_teacher" value="${question.answer.integrity_teacher }" class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">流利：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="fluency_teacher"  value="${question.answer.fluency_teacher }"  class="layui-input"  style="width: 100%"> 
				</div>
				<label class="layui-form-label" style="width: 8%;">总分：</label>
				<div class="layui-input-inline" style="width:8%;margin-right: 0px">
					<input type="text" id="overall_teacher" value="${question.answer.overall_teacher }" class="layui-input"  style="width: 100%"> 
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
			saveData("1");
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
		var data = {};
		data.accuracy_teacher = $("#accuracy_teacher").val();
		data.integrity_teacher = $("#integrity_teacher").val();
		data.fluency_teacher = $("#fluency_teacher").val();
		data.overall_teacher = $("#overall_teacher").val();
		data.zuoye_id = $("#zuoye_id").val();
		data.class_code = $("#class_code").val();
		data.student_id = $("#student_id").val();
		data.answer_comment=$("#answer_comment").val();
		data.id = $("#qaId").val();

		var total_question_num =$("#total_question_num").val();
		var current_num =$("#current_num").val();
		total_question_num==current_num && (data.isEnd =1);
		
		if($.trim(data.accuracy_teacher)!="" &&$.trim(data.integrity_teacher)!="" && $.trim(data.fluency_teacher)!="" && $.trim(data.overall_teacher)!="" ){
			var index =layer.load(3, {shade: [0.3]});
			$.post(jsBasePath+"/zuoyeScore/pgSimpleZuoye.html",data,function(data,status){
				  layer.close(index); 
				  if(data.flag==false){
					  layer.alert(data.message,{icon:2});
				  }else{
					  if(current_num<total_question_num){
						  tiaozhuan(flag);
					  }else{
						  closeFrame();
					  }
					 
				  }
			  },"json");
		}else{
			layer.alert("请填写完评分再提交",{icon:2});
		}
		return false;
	}
	
	function tiaozhuan(flag){
		var student_id = $("#student_id").val();
		var zuoye_id = $("#zuoye_id").val();
		var zuoye_name = $("#zuoye_name").val();
		var class_code = $("#class_code").val();
		var xh = $("#xh").val();
		var url = jsBasePath + "/zuoyeScore/topgzy.html?flag="+flag+"&student_id="+student_id
		  +"&zuoye_id="+zuoye_id+"&class_code="+class_code+"&zuoyeName="+zuoye_name+"&xh="+xh;
		location.href = url;	
	}
	
	function jyfs(){
		var flag = true;
		var accuracy_teacher = $("#accuracy_teacher").val();
		var integrity_teacher = $("#integrity_teacher").val();
		var fluency_teacher = $("#fluency_teacher").val();
		var overall_teacher = $("#overall_teacher").val();
		if($.trim(accuracy_teacher)=="" || $.trim(integrity_teacher)=="" || $.trim(fluency_teacher)=="" || $.trim(overall_teacher)=="" ){
			layer.alert("请填写完评分再提交",{icon:2});
			flag = false;
		}
		return flag
	}
	</script>
</body>
</html>