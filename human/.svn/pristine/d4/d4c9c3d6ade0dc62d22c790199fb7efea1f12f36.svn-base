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
	<body >
		<div class="layui-form-item" style="margin-top:20px;">
			<label class="layui-form-label">部署名称:</label>
			<div class="layui-input-inline">
				<input type="text" id="delpoyName" name="delpoyName" lay-verify="required" style="width:140px" 
				placeholder="请输入部署名称" autocomplete="off" class="layui-input">
			</div>
			
			<label class="layui-form-label">上传ZIP:</label>
			<div class="layui-input-inline">
				<input type="file" id="file1" name="file" lay-type="file" class="layui-upload-file" lay-title="选择流程定义" style="width:160px"> 
			</div>
			
			<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" id="tj">立即提交</button>
					</div>
				</div>
		</div>
		<script>
		layui.use('upload', function(){
			
			var delpoyName = $("#delpoyName").val();
			var s  = layui.upload({
				url: jsBasePath+'/bpm/processDef/add.html',
				//data:data,
				isAuto:false,
				change:function(file){
					$(file).parent("form").parent().parent().find(".layer-tip").remove();
					$(file).parent("form").parent().parent().append('<div class="layer-tip">'+file.value+'</div>');
				},
				success: function(res){ //上传成功后的回调
					console.log(res);
					if(!res.flag){
						layer.alert(res.message,{icon:2});
					}else{
						layer.alert(res.message,{icon:1},function(){
							closeFrame();
						});
					}
				}
			});
			 $("#tj").bind("click",function(){
				var data = {"delpoyName":$("#delpoyName").val()};
				s.action([$("#file1")[0]],"file",data);
			}); 
		});
		</script>
	</body>
</html>