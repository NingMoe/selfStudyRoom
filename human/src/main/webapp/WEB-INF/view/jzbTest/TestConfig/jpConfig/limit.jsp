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
.layui-form-label {
	width: 38%;
}
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<input type="hidden" id="id" name="id" value="${jzb.id}"
				class="layui-input">
			<div class="layui-form-item">
				<label class="layui-form-label">每天最多答题:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="dayTimes" name="dayTimes"
						value="${jzb.dayTimes}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">每月最多答题:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="monthTimes" name="monthTimes"
						lay-verify="required" value="${jzb.monthTimes}"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">本试卷每人最多答题:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="totalTimes" name="totalTimes"
						lay-verify="required" value="${jzb.totalTimes}"
						class="layui-input">
				</div>
			</div>
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
			var index = layer.load(3, {
				shade : [ 0.3 ]
			});
			$.post(jsBasePath + "/jzbTest/jpConfig/limit.html", $("#addForm")
					.serializeArray(), function(data, status) {
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