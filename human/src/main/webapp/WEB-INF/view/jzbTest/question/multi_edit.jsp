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
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/themes/default/default.css" />
<link rel="stylesheet"
	src="<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css" />
</head>
<body style="padding: 20px;">
	<div class="layui-form">
		<input type="hidden" id="qCode" name="qCode" value="${question.qCode }">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">班级类型</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input value="${question.classTypeName }" class="layui-input" readonly="readonly">
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷年级</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input value="${question.gradeName }" class="layui-input" readonly="readonly">
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷科目</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input value="英语" class="layui-input" readonly="readonly">
			</div>
		</div>
		
		<div class="layui-form-item" id="yyZsd">
			<label class="layui-form-label" style="width: 11%;">知识点</label>
			<div class="layui-input-inline" style="width: 45%;">
				<input value="${question.qKnowledge }" class="layui-input" readonly="readonly">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">适用月份</label>
			<div class="layui-input-inline" style="width: 45%;">
				<select name="qMonth" id="qMonth" lay-filter="qMonth"
					lay-verify="required" multiple="multiple" lay-search="">
					<option value="">请选择</option>
					<option value="1"
					<c:if test="${fn:contains(question.qMonth,'1')}">selected="selected"</c:if>
					>1月</option>
					<option value="2"
					<c:if test="${fn:contains(question.qMonth,'2')}">selected="selected"</c:if>
					>2月</option>
					<option value="3"
					<c:if test="${fn:contains(question.qMonth,'3')}">selected="selected"</c:if>
					>3月</option>
					<option value="4"
					<c:if test="${fn:contains(question.qMonth,'4')}">selected="selected"</c:if>
					>4月</option>
					<option value="5"
					<c:if test="${fn:contains(question.qMonth,'5')}">selected="selected"</c:if>
					>5月</option>
					<option value="6"
					<c:if test="${fn:contains(question.qMonth,'6')}">selected="selected"</c:if>
					>6月</option>
					<option value="7"
					<c:if test="${fn:contains(question.qMonth,'7')}">selected="selected"</c:if>
					>7月</option>
					<option value="8"
					<c:if test="${fn:contains(question.qMonth,'8')}">selected="selected"</c:if>
					>8月</option>
					<option value="9"
					<c:if test="${fn:contains(question.qMonth,'9')}">selected="selected"</c:if>
					>9月</option>
					<option value="A"
					<c:if test="${fn:contains(question.qMonth,'A')}">selected="selected"</c:if>
					>10月</option>
					<option value="B"
					<c:if test="${fn:contains(question.qMonth,'B')}">selected="selected"</c:if>
					>11月</option>
					<option value="C"
					<c:if test="${fn:contains(question.qMonth,'C')}">selected="selected"</c:if>
					>12月</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">难度系数</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qDifficulty" lay-verify="required" id="qDifficulty">
					<option value="">请选择</option>
					<c:forEach items="${diffs }" var="diff">
						<option value="${diff.dataValue }"
					<c:if test="${question.qDifficulty eq diff.dataValue}">selected="selected"</c:if>
					>${diff.name }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">题目分数</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input type="text" id="qScore" name="qScore" class="layui-input"
					lay-verify="required" value="${question.qScore }">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">备注</label>
			<div class="layui-input-inline" style="width:45%;">
				<input type="text" id="qRemark" name="qRemark" class="layui-input" style="width:100%" value="${question.qRemark }" >
			</div>
		</div>
		<!-- 	
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 11%;">题型</label>
					<div class="layui-input-inline" style="width:50%;">
						<input type="radio" name="qType" title="常规单选" lay-filter="qType" value="1" checked="checked">
						<input type="radio" name="qType" title="短文（阅读理解、完型填空）" lay-filter="qType" value="2">
					</div>
				</div> -->
		<div id="quesDiv2">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label" style="width: 11%;">短文介绍</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea name="qBrief" id="qBrief" cols="100"
						style="width: 100%; height:400px;">${question.qMainDesc }</textarea>
				</div>
			</div>
			<c:forEach items="${question.questions }" var="ques" varStatus="qs">
			<div class="addTimu multi">
				<input type="hidden" name="questionId" value="${ques.id }">
				<blockquote class="layui-elem-quote"
					style="padding: 3px; background-color: #efefef; border: 1px solid #efefef; margin: 0px auto; width: 85%"></blockquote>
				<div class="layui-form-item layui-form-text"
					style="margin-top: 5px;">
					<label class="layui-form-label" style="width: 11%;">问题${qs.index+1 }</label>
					<div class="layui-input-inline" style="width: 60%;">
						<textarea class="layui-textarea" name="multi_content">${ques.qContent }</textarea>
					</div>
				</div>
				<c:forEach items="${ques.answers }" var="answer" varStatus="st">
					<div class="layui-form-item qsan2">
						<div class="layui-input-inline">
							<label class="layui-form-label" style="width: 11%; float: right;">${answer.xh }</label>
						</div>
						<div class="layui-input-inline" style="width: 400px;">
							<input type="text" name="multi_aContent" class="layui-input" value="${answer.aContent }">
						</div>
						<div class="layui-input-inline">
							<input type="radio" name="multi_correct_${qs.index }" 
							<c:if test="${answer.isCorrect eq '1'}">checked="checked"</c:if>
							title="正确答案" value="${answer.xh }" >
						</div>
					</div>
				</c:forEach>
			</div>
			</c:forEach>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="tj">保存</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var editor,editIndex;
	</script>
	<script type="text/javascript"
		src="<%=basePath%>/static/jzbTest/question/multi_edit.js"></script>
	<script charset="utf-8"
		src="<%=basePath%>/static/kinder-eitor/kindeditor-all.js"></script>
	<script charset="utf-8"
		src="<%=basePath%>/static/kinder-eitor/lang/zh-CN.js"></script>
	<script charset="utf-8"
		src="<%=basePath%>/static/kinder-eitor/plugins/code/prettify.js"></script>
	<script type="text/javascript">
		KindEditor.ready(function(K) {
			K.lang({
			 	audio : 'MP3'
			});
			 K.options.htmlTags['audio'] = ['src','controls','autoplay','type'];
			 K.options.htmlTags['source'] = ['src','controls','autoplay','type'];	
			
			editor = K.create('textarea[name="qBrief"]', {
				cssPath : '<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>/kindeditor/fileUpload.html',  
				fileManagerJson : '<%=basePath%>/kindeditor/fileManager.html',  
				allowFileManager : true,
				items :  ['source','undo', 'redo', '|','justifyleft', 'justifycenter', 'justifyright', '|', 'image','audio']
				     });
			prettyPrint();
		});
	</script>
</body>
</html>