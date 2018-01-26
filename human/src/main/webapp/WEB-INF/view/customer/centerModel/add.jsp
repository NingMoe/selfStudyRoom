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
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
</head>
<body style="padding: 10px;">
	<div class="alertFrom">
	<form class="layui-form" id="editForm" action="" method="post">
		<div class="layui-form">
			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 84px;">模块名称:</label>
				<div class="layui-input-inline">
					<input name="modelName" style="width: 180px;" type="text"
						placeholder="请输入模块名称" class="layui-input" lay-verify="required">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label" style="width: 84px;">模块排序:</label>
				<div class="layui-input-inline">
					<input name="sort" id="sort" style="width: 180px;" type="text"
						placeholder="请输入模块排序" class="layui-input" lay-verify="required|number">
				</div>
			</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button  class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</div>
			</form>
		</div>
		<script type="text/javascript">
	    	layui.use(['form', 'layedit','upload'], function(){
	    		var form = layui.form()
	    		  ,layer = layui.layer
	    		  ,layedit = layui.layedit;
	    		form.on('submit(demo1)', function(data) {
	    			var index = layer.load(3, {
	    				shade : [ 0.3 ]
	    			});
	    			$.post(jsBasePath + "/customer/centerModel/add.html",
	    					$("#editForm").serializeArray(), function(data, status) {
	    						layer.close(index);
	    						if (data.flag == false) {
	    							layer.alert(data.msg, {
	    								icon : 2
	    							});
	    						} else {
	    							parent.initTable();
	    							layer.confirm(data.msg + ',是否继续新增？', {
	    								btn : [ '是', '否' ]
	    							}, function(i) {
	    								layer.close(i);
	    								location.reload() 
	    							}, function() {
	    								closeFrame();
	    							});
	    						}
	    					}, "json");
	    			return false;
	    		});
	    	});
		</script>
	</body>
</html>