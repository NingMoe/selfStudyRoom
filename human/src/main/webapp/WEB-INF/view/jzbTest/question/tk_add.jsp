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
		<input type="hidden" id="deptId" name="deptId" value="${deptId }">
		<input type="hidden" id="isMulti" name="isMulti" value="0">
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">班级类型</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qClasstype" id="qClasstype" lay-filter="qClasstype"
					lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="cl" items="${classTypes }">
						<option value="${cl.dataValue }">${cl.name }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷年级</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qGrade" id="qGrade" lay-filter="qGrade" lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="grade" items="${grades }">
						<option value="${grade.id }">${grade.gradeName }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">试卷科目</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qSubject" id="qSubject" lay-filter="qSubject"
					lay-verify="required">
					<option value="">请选择</option>
					<c:forEach var="subejct" items="${subjects }">
						<option value="${subejct.dataValue }">${subejct.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="layui-form-item" id="zsdChoose">
			<label class="layui-form-label" style="width: 11%;">知识点选择</label>
			<div class="layui-input-inline" style="width: 45%;">
				<input type="radio" name="zsdType" title="系统查询选择" lay-filter="zsdType" value="1" checked="checked">
				<input type="radio" name="zsdType" title="自定义" lay-filter="zsdType" value="2">
			</div>
		</div>
		

		<div class="layui-form-item" id="fyyZsd">
			<label class="layui-form-label" style="width: 11%;">知识点</label>
			<div class="layui-input-inline" style="width: 45%;" id="seZsd">
				<select name="qKnowledge" id="qKnowledge" lay-filter="qKnowledge"
					lay-verify="required">
					<option value="">请选择</option>
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
					<option value="1">1月</option>
					<option value="2">2月</option>
					<option value="3">3月</option>
					<option value="4">4月</option>
					<option value="5">5月</option>
					<option value="6">6月</option>
					<option value="7">7月</option>
					<option value="8">8月</option>
					<option value="9">9月</option>
					<option value="A">10月</option>
					<option value="B">11月</option>
					<option value="C">12月</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">难度系数</label>
			<div class="layui-input-inline" style="width: 15%;">
				<select name="qDifficulty" lay-verify="required" id="qDifficulty">
					<option value="">请选择</option>
					<c:forEach items="${diffs }" var="diff">
						<option value="${diff.dataValue }">${diff.name }</option>
					</c:forEach>
				</select>
			</div>

			<label class="layui-form-label" style="width: 11%;">题目分数</label>
			<div class="layui-input-inline" style="width: 15%;">
				<input type="text" id="qScore" name="qScore" class="layui-input"
					lay-verify="required" value="5">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label" style="width: 11%;">备注</label>
			<div class="layui-input-inline" style="width:45%;">
				<input type="text" id="qRemark" name="qRemark" class="layui-input" style="width:100%">
			</div>
		</div>

		<div id="quesDiv1">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label" style="width: 11%;">题干</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea name="qContent" id="qContent" cols="100" rows="8"
						style="width: 100%; height:400px;"></textarea>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label tkda" style="width: 11%;">填空答案1</label>
				<div class="layui-input-inline" style="width:55%;">
					<textarea name="tkAnswer" class="layui-textarea" ></textarea>
				</div>
				<div class="layui-input-inline" style="width: 20%;">
					<button class="layui-btn layui-btn-small addDa" onclick="addTkda(this);">添加答案</button>
				</div>
			</div>
		</div>
		
		<div id="quesDiv2" style="display: none;">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label" style="width: 11%;">短文介绍</label>
				<div class="layui-input-inline" style="width: 65%;">
					<textarea name="qBrief" id="qBrief" cols="100"
						style="width: 100%; height:400px;"></textarea>
				</div>
			</div>
			<div class="multi">
			
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit="" lay-filter="tj">保存</button>
			</div>
		</div>
		
		<div id="answerBf" style="display: none;">
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label tkda" style="width: 11%;"></label>
				<div class="layui-input-inline" style="width:55%;">
					<textarea name="tkAnswer" class="layui-textarea"></textarea>
				</div>
				<div class="layui-input-inline" style="width: 20%;">
					<button class="layui-btn layui-btn-small" onclick="addTkda(this);">添加答案</button>
					<button class="layui-btn layui-btn-small delDa" onclick="delTkda(this);" style="margin-left: 15px;">删除答案</button>
				</div>
			</div>
		</div>
		
		<div id="multiBf" style="display: none;">
			<div class="addTk">
				<blockquote class="layui-elem-quote"
					style="padding: 3px; background-color: #efefef; border: 1px solid #efefef; margin: 0px auto; width: 85%"></blockquote>
				<div class="layui-form-item layui-form-text"
					style="margin-top: 5px;">
					<label class="layui-form-label tg" style="width: 11%;">题干</label>
					<div class="layui-input-inline" style="width: 60%;">
						<textarea class="layui-textarea" name="multi_content"></textarea>
					</div>
				</div>
				
				<div class="layui-form-item layui-form-text"
					style="margin-top: 5px;">
					<label class="layui-form-label da" style="width: 11%;">答案</label>
					<div class="layui-input-inline" style="width: 60%;">
						<textarea class="layui-textarea" name="tkAnswer"></textarea>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var editor1,editor2,addIndex;
	</script>
	<script type="text/javascript"
		src="<%=basePath%>/static/jzbTest/question/tk_add.js"></script>
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
			
			editor1 = K.create('textarea[name="qContent"]', {
				cssPath : '<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>/kindeditor/fileUpload.html',  
				fileManagerJson : '<%=basePath%>/kindeditor/fileManager.html',  
				allowFileManager : true,
				items : ['source','undo', 'redo', '|','justifyleft', 'justifycenter', 'justifyright', '|', 'image','audio']
			});
				
			editor2 = K.create('textarea[name="qBrief"]', {
				cssPath : '<%=basePath%>/static/kinder-eitor/plugins/code/prettify.css',
				uploadJson : '<%=basePath%>/kindeditor/fileUpload.html',  
				fileManagerJson : '<%=basePath%>/kindeditor/fileManager.html',  
				allowFileManager : true,
				items : ['source','undo', 'redo', '|','justifyleft', 'justifycenter', 'justifyright', '|', 'image','audio']
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
		
		function addTkda(obj){
			var tmSize = $("#quesDiv1").find(".tkda").size();
			var daDiv = $("#answerBf").clone(true);
			daDiv.find(".tkda").html("填空答案"+(tmSize+1));
			$("#quesDiv1").append(daDiv.html());
		}
		
		function delTkda(obj){
			$(obj).parent().parent().remove();
		}
	</script>
</body>
</html>