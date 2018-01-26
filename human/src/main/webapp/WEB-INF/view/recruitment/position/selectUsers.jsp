<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
</head>
<body style="padding:20px;">
	<form class="layui-form" id="addForm">
		<div class="layui-form-item layui-form-text">
			<blockquote class="layui-elem-quote">
			此处输入面试官邮箱，一行一个邮箱；	
			</blockquote>
			<div class="layui-input-inline" style="width:550px;">
				<textarea class="layui-textarea layui-hide" id="users" name="users" lay-verify="content">
				</textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<button type="button" id="tj" class="layui-btn">立即提交</button>
			<button type="button" onclick="closeFrame();" class="layui-btn">取消</button>
		</form>
	</form>
	<script type="text/javascript">
	layui.use(['form','layedit'], function(){
		var form = layui.form(),layer = layui.layer,layedit = layui.layedit;
		var editIndex = layedit.build('users',{tool: []});
		$("#tj").bind("click",function(){
			var users = layedit.getContent(editIndex);
			users = $(users).map(function(){
				var _v = this.innerHTML.replace(/<[^>]+>|&nbsp;/g,"");
				if(_v){
					return _v;
				}
			}).get().join();
			
		});
	});
	</script>
</body>
</html>