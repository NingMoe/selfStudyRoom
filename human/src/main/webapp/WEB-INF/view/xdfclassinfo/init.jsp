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
.layui-form-label{
width:110px;
}
.layui-form-label {
    width: 112px;
}
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">班号:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="sClassCode" id="sClassCode" lay-verify="remark"
						placeholder="批量导入班级时请将班号用英文逗号（,）隔开" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit=""  lay-filter="demo1">初始化</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
layui.use([ 'form' ], function() {
	var form = layui.form();
	form.on('submit(demo1)', function(data) {
		var sClassCode=$("#sClassCode").val();
		 var index = layer.load(3, {shade: [0.3]});
		 
		 var str="{\"MessageId\":\"\",\"RouteKey\":\"AAAAA\",\"ReplyTimes\":0,\"ReplyMax\":10,\"BodyJson\":\"[{'nSchoolId':25,'sCode':'"+sClassCode+"'}]\"}";
			$.post(jsBasePath+'/jw/xdf/class/init.html',{"sClassCode":sClassCode},function(data,status){
				layer.close(index); 
			  if(data.flag!=true){
				  layer.alert("更新数据失败",{icon:2});
			  }else{
				  layer.alert("更新数据成功",{icon:1},function(){
					  parent.location.reload(); 
					  closeFrame();
				  });
			  }
			},"json");
			return false;
		
	});
	});
</script>
</html>