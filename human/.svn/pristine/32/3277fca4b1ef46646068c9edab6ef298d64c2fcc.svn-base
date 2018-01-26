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
<style>
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<input type="hidden" id="id" name="id" value="${jzb.id}"
				class="layui-input">
			<div class="layui-form-item ">
				<label class="layui-form-label">卷首温馨提示:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="headMessage" id="headMessage" 
						lay-verify="headMessage" placeholder="" class="layui-textarea">${jzb.headMessage}</textarea>
				</div>
			</div>
			<c:forEach items="${messages }" var="mess" >
				<div class="layui-form-item message">
				<label class="layui-form-label">${mess.name}:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="${mess.dataValue}" id="message"  placeholder="" class="layui-textarea">${mess.message}</textarea>
				</div>
			</div>
			</c:forEach>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	layui.use([ 'form', 'laydate' ], function() {
		var form = layui.form(), laydate = layui.laydate;
		//监听提交
		form.on('submit(demo1)', function(data) {
			var alldatas={};
			var data = [];
			$(".message").each(function(){
			var mess={};
			mess.mainConfigId=$("#id").val();
			mess.message=$(this).find("#message").val();
			mess.dicId=$(this).find("#message").attr("name");
			data.push(mess);
			})
			var index = layer.load(3, {
				shade : [ 0.3 ]
			});
			var id=$("#id").val();
			var headMessage=$("#headMessage").val();
			alldatas.Data=data;
			var jstr=JSON.stringify(alldatas);
			$.post(jsBasePath + "/jzbTest/jpConfig/testPaper.html",{id:id,headMessage:headMessage,jstr:jstr}, function(data, status) {
				layer.close(index);
				if (data.flag == false) {
					layer.alert(data.message, {
						icon : 2
					});
				} else {
					layer.alert(data.message, {
						icon : 1
					}, function() {
						parent.location.reload();
						closeFrame();
					});
				}
			}, "json");
			return false;
		});
	});
</script>
</html>