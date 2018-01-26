<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
<style>
.mrTopicTime{display:none}
.time{display:none}
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<input id="id" name="id" type="hidden" value="${questionType.id }">
			<input id="lTypeE" name="lType" type="hidden" value="${questionType.lType }">
			<input id="isNeedGuideE" name="isNeedGuide" type="hidden" value="${questionType.isNeedGuide }">
			<input id="isNeedEssayE" name="isNeedEssay" type="hidden" value="${questionType.isNeedEssay }">
			<div class="layui-form-item">
				<label style="width: 15%" class="layui-form-label">题型名称:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="name" name="name" lay-verify="required" class="layui-input" value="${questionType.name }">
				</div>
				<label style="width: 15%" class="layui-form-label">科目:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="subjectDesc" name="subjectDesc" readonly="readonly" value="英语" class="layui-input"> 
					<input type="hidden" id="subject" name="subject" readonly="readonly" value="1">
				</div>
			</div>
			<div class="layui-form-item">
				<label style="width: 15%" class="layui-form-label">需要指导语:<font color="red">*</font></label>
				<div style="width:25%" class="layui-input-inline">
					<select name=isNeedGuide id="isNeedGuide" lay-verify="required" lay-filter="isNeedGuide"
						<option value="">请选择</option> 
						<option 
						<c:if test="${questionType.isNeedGuide eq '1'}">
						selected = "selected" 
						</c:if>
						value="1">是</option>
						<option value="2"
						<c:if test="${questionType.isNeedGuide eq '2'}">
						selected = "selected" 
						</c:if>
						>否</option>
					</select>
				</div>
				<label style="width: 15%" class="layui-form-label">需要文本解析:<font color="red">*</font></label>
				<div style="width: 25%" class="layui-input-inline">
					<select name="isNeedParse" id="isNeedParse" lay-verify="required" >
						<option value="">请选择</option> 
						<option 
						<c:if test="${questionType.isNeedParse eq '1'}">
						selected = "selected" 
						</c:if>
						value="1">是</option>
						<option value="2"
						<c:if test="${questionType.isNeedParse eq '2'}">
						selected = "selected" 
						</c:if>
						>否</option>
					</select>
				</div>
			</div>
			
			<div class="layui-form-item ">
				<label style="width: 15%" class="layui-form-label">大题类:<font color="red">*</font></label>
				<div style="width: 25%" class="layui-input-inline">
					<select name="isNeedEssay" id="isNeedEssay" lay-verify="required" lay-filter="isNeedEssay">
						<option value="2"
						<c:if test="${questionType.isNeedEssay eq '2'}">
						selected = "selected" 
						</c:if>
						>否</option>
						<option 
						<c:if test="${questionType.isNeedEssay eq '1'}">
						selected = "selected" 
						</c:if>
						value="1">是</option>
					</select>
				</div>
				<label style="width: 15%" class="layui-form-label">用途:<font color="red">*</font></label>
				<div style="width: 25%" class="layui-input-inline">
					<select name="lType" id="lType" lay-verify="required" lay-filter="lType">
						<option value="1" <c:if test="${ questionType.lType  eq '1'}">selected = "selected"</c:if>>练习</option>
						<option value="2"  <c:if test="${ questionType.lType  eq '2'}">selected = "selected"</c:if>>考试</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item time" >
				<label style="width: 15%" class="layui-form-label mrTopicTime"  >默认短文时间:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="mrTopicTime" name="mrTopicTime"<c:if test="${ questionType.mrTopicTime  ne 0}">value="${questionType.mrTopicTime }"</c:if>  class="layui-input mrTopicTime"   >
				</div>
				<label style="width: 15%"  class="layui-form-label mrContentTime">默认题干熟悉时间:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="mrContentTime"  name="mrContentTime" <c:if test="${ questionType.mrContentTime  ne 0}">value="${questionType.mrContentTime }"</c:if> class="layui-input mrContentTime"  >
				</div> 
				<label style="width: 15%" class="layui-form-label mrAnswerTime" >默认答题时间:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="mrAnswerTime" name="mrAnswerTime" <c:if test="${ questionType.mrAnswerTime  ne 0}"> value="${questionType.mrAnswerTime }"</c:if>   class="layui-input mrAnswerTime" >
				</div>
			</div>
			<div class="layui-form-item ">
				<label style="width: 15%" class="layui-form-label">题数:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width:25%">
					<input type="text" id="questionNum" name="questionNum" value="1" class="layui-input" lay-verify="required" value="${questionType.questionNum }">
				</div>
			</div>
			<div class="layui-form-item mr_zdmessage">
				<label style="width: 15%" class="layui-form-label">默认指导语:</label>
				<div style="width:65%" class="layui-input-inline">
					<textarea name="mr_zdmessage" id="mr_zdmessage" class="layui-textarea mr_zdmessage">${questionType.mrZdmessage }</textarea>
				</div>
			</div>
			<div class="layui-form-item ">
				<label style="width: 15%" class="layui-form-label">备注:</label>
				<div style="width:65%" class="layui-input-inline">
					<textarea name="remark" id="remark" class="layui-textarea">${questionType.remark }</textarea>
				</div>
			</div>
		
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="edit">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
			</div>
		</div>
	</form>
	</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/questionType/edit.js"></script>
</html>