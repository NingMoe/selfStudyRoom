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
		<div class="layui-form-item" style="margin-top:38px;">
			<label class="layui-form-label" style="width:20%;">题型选择</label>
			<div class="layui-input-inline" style="width:200px;">
				<select name="tkAnswer" id="tkAnswer" lay-verify="required" style="width:100%;">
					<option value="1" selected="selected">选择题</option>
					<option value="2">填空题</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item" style="margin-top: 50px;">
			<div class="layui-input-block">
				<button class="layui-btn" id="tj">确定</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	 layui.use(['form'], function(){
		 var form = layui.form();
		 $("#tj").click(function(){
			 var tkAnswer = $("#tkAnswer").val();
			 $("#isTk",parent.document).val(tkAnswer);
			 $("#hiddenAddBtn",parent.document).click();
		 });
	 });
	</script>
</body>
</html>