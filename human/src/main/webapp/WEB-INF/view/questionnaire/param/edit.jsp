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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
		<style type="text/css">
		</style>
		</head>
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="editForm" action="" method="post" >
			<input type="hidden" name="id" value="${id}">
				<div class="layui-form-item">
					<label class="layui-form-label">参数key:</label>
					<div class="layui-input-inline">
						<input type="text" name="key" lay-verify="let_num_un" autocomplete="off"  value="${keyValue}"   <c:if test="${keyValue == 'email' || keyValue == 'phonetel'}">readonly="readonly"</c:if> placeholder="请输入参数key" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">参数名:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="required" placeholder="请输入参数名"  value="${paramName}"autocomplete="off" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序:</label>
					<div class="layui-input-inline">
						<input type="text" name="sort"  placeholder="请输入排序"  lay-verify="number" value="${keySort}" autocomplete="off" class="layui-input" value="0">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>
		<script>
layui.use('form', function(){
  var form = layui.form();
  //监听提交
  form.on('submit(demo1)', function(data){
	  var index = layer.load(3, {shade: [0.3]});
		$.post(jsBasePath+"/questionnaire/param/edit.html",$("#editForm").serializeArray(),function(data,status){
			layer.close(index); 
			if(data.flag==false){
				layer.alert(data.message,{icon:2});
			}else{
				layer.alert(data.message,{icon:1},function(){
					parent.location.reload(); 
					closeFrame();
				});
			}
		},"json");
		return false;
  });
});
		</script>
	</body>
</html>