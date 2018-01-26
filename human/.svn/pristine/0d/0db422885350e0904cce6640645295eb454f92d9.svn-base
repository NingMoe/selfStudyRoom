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
</head>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">难度(0.1):</label>
					<div class="layui-input-inline">
						<input type="text" id="one" name="one" value=""
							class="layui-input">
					</div>
				<label class="layui-form-label">难度(0.2):</label>
					<div class="layui-input-inline">
						<input type="text" id="two" name="two" value=""
							class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">难度(0.3):</label>
					<div class="layui-input-inline">
						<input type="text" id="three" name="three" value=""
							class="layui-input">
					</div>
				<label class="layui-form-label">难度(0.4):</label>
					<div class="layui-input-inline">
						<input type="text" id="four" name="four" value=""
							class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">难度(0.5):</label>
					<div class="layui-input-inline">
						<input type="text" id="five" name="five" value=""
							class="layui-input">
					</div>
				<label class="layui-form-label">难度(0.6):</label>
					<div class="layui-input-inline">
						<input type="text" id="six" name="six" value=""
							class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">难度(0.7):</label>
					<div class="layui-input-inline">
						<input type="text" id="seven" name="seven" value=""
							class="layui-input">
					</div>
				<label class="layui-form-label">难度(0.8):</label>
					<div class="layui-input-inline">
						<input type="text" id="eight" name="eight" value=""
							class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">难度(0.9):</label>
					<div class="layui-input-inline">
						<input type="text" id="nith" name="nith" value=""
							class="layui-input">
					</div>
				<label class="layui-form-label">难度(1):</label>
					<div class="layui-input-inline">
						<input type="text" id="" name="ten" value=""
							class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	//监听提交
  form.on('submit(demo1)', function(data){
	  var data=$("#addForm").serializeArray();
		closeFrame();
		return false;
  });

});	


</script>
</html>