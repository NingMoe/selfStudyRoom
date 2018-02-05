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
		<input type="hidden" id="qId" name="qId" value="${question.id }">
		<input type="hidden" id="qCode" name="qCode" value="${question.qCode }">
		<input type="hidden" id="qDept" name="qDept" value="${question.qDept }">
		<input type="hidden" id="qKnowledgeId" name="qKnowledgeId" value="${question.qKnowledge }">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">班级类型</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qClasstype" id="qClasstype" lay-filter="qClasstype"
					lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="cl" items="${classTypes }">
						<option  value="${cl.dataValue }"
						<c:if test="${question.qClasstype eq cl.dataValue}">selected="selected"</c:if>
						>${cl.name }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷年级</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qGrade" id="qGrade" lay-filter="qGrade"
					lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="grade" items="${grades }">
						<option value="${grade.id }"
						<c:if test="${question.qGrade eq grade.id}">selected="selected"</c:if>
						>${grade.gradeName }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷科目</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qSubject" id="qSubject" lay-filter="qSubject"
					lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="subejct" items="${subjects }">
						<option value="${subejct.dataValue }"
						<c:if test="${question.qSubject eq subejct.dataValue}">selected="selected"</c:if>
						>${subejct.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">知识点</label>
			<div class="layui-input-inline" style="width: 45%;"  id="seZsd">
				<select name="qKnowledge" id="qKnowledge" lay-filter="qKnowledge"
					lay-verify="required">
					<option value="0">请选择</option>
				</select>

			</div>
			<div class="layui-input-inline" style="width: 15%;display:none;" id="cusTomZsd">
				<input type="text" id="cus_qKnowledge" name="cus_qKnowledge"
					class="layui-input" placeholder="自定义知识点">
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
				<input type="text" id="qRemark" name="qRemark" value="${question.qRemark }" class="layui-input" style="width:100%">
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
		<div id="quesDiv1">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label" style="width: 11%;">题干</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea name="qContent" id="qContent" cols="100" rows="8"
						style="width: 100%; height:400px;">${question.qContent }</textarea>
				</div>
			</div>
			<c:forEach items="${question.answers }" var="answer">
				<div class="layui-form-item qsan1">
					<div class="layui-input-inline">
						<label class="layui-form-label" style="width: 11%; float: right;">${answer.xh }</label>
					</div>
					<div class="layui-input-inline" style="width:300px;">
						<input type="text" name="aContent" class="layui-input" value="${answer.aContent }">
					</div>
					<div class="layui-input-inline" style="width:120px;">
						<input type="file" id="file${answer.xh }" name="file${answer.xh }" lay-type="file" lay-type="images" 
							class="layui-upload-file" lay-title="上传图片" style="width:100%"> 
					</div>
					<div class="layui-input-inline"  style="width:100px;">
						<input type="radio" name="isCorrect" 
						<c:if test="${answer.isCorrect eq '1'}">checked="checked"</c:if>
						title="正确" value="${answer.xh }">
					</div>
					<div class="layui-input-inline" id="file${answer.xh }span"  style="width:50px;
					<c:if test="${empty answer.aImg}">display:none;</c:if>
					">
						<label class="layui-form-label tpyl" style="float: right;color:red">已上传</label>
					</div>
					<div class="layui-input-inline" style="width:50px;">
						<button id="file${answer.xh }Button" 
						<c:if test="${empty answer.aImg}">style="display:none;"</c:if>
						 class="layui-btn layui-btn-small delPic"><i class="layui-icon"></i></button>
						 <input type="hidden" name="asId" value="${answer.id }">
						 <input type="hidden" name="imgFlag" <c:if test="${!empty answer.aImg}">value="1"</c:if>>
						 
						<img width=255px height=200px style="display:none;" 
						<c:if test="${!empty answer.aImg}">src="${fileurl }${answer.aImg }"</c:if>
						 id="file${answer.xh }Img"/>
					</div>
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
	var editor,addIndex;
	</script>
	<script type="text/javascript"
		src="<%=basePath%>/static/jzbTest/question/simple_edit.js"></script>
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
			editor = K.create('textarea[name="qContent"]', {
				cssPath : '<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>/kindeditor/fileUpload.html',  
				fileManagerJson : '<%=basePath%>/kindeditor/fileManager.html',  
				allowFileManager : true,
				items :  ['source','undo', 'redo', '|','justifyleft', 'justifycenter', 'justifyright', '|', 'image','audio']
				     });
			prettyPrint();
		});
		
		$(".tpyl").on("click",function(event){
			  var img = $(this).parent().next().find("img");
			  index1 = layer.open({
				  title: false,
				  	type: 1,
					skin:'layer-box',
					area: [img.width(),img.height()],
				 	content: img //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
				});
		});
	</script>
</body>
</html>