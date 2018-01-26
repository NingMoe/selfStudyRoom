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
<input type="hidden" id="id" value="${zuoye.id }">
	<div class="alertFrom">
		<div class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">作业标题<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 274px;">
					<input type="text" id="zuoyeName" name="zuoyeName"  lay-verify="required" 
						 class="layui-input" value="${zuoye.name }">
				</div>
			</div>
			
			<div class="layui-form-item">
				<label class="layui-form-label">提交时间</label>
				<div class="layui-input-inline" style="width: 274px;">
					<input type="text" id="subTime" name="subTime" value='${fn:substring(zuoye.subTime, 0, 19)}' class="layui-input" lay-verify="required">
				</div>
			</div>
			
				
			<div class="layui-form-item" >
				<label class="layui-form-label">选择班级:</label>
				<div class="layui-input-block" style="width: 70%">
					<c:forEach items="${classList }" var="item">
					<div class="layui-input-inline" style="width:175px;">
						<input type="checkbox" name="classCodes" 
						<c:if test="${fn:contains(zuoye.classStr,item.classCode)}">checked="checked"</c:if>
						value="${item.classCode }" title="${item.className }" lay-filter="classCodes">
					</div>
					</c:forEach>
		         </div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button  class="layui-btn" lay-submit=""  lay-filter="zyadd">保存</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
layui.use(['form','laydate','layer','element'], function(){
	var laydate = layui.laydate, form = layui.form,layer = layui.layer,element = layui.element;
	laydate.render({ 
		elem: '#subTime',type: 'datetime',calendar: true,format:"yyyy-MM-dd HH:mm:ss"
	});
	
	form.on('submit(zyadd)', function(data){
		var index =layer.load(3, {shade: [0.3]});
		if($("input[name='classCodes']:checked").length){
			var classStr = $("input[name='classCodes']:checked").map(function(){return this.value;}).get().join();
			$.post(jsBasePath+"/zuoye/edit.html",{
				id :$("#id").val(),
				name : $("#className").val(),
				subTime : $("#subTime").val(),
				classStr : classStr
			},function(data,status){
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
		}else{
			layer.alert("请选择要发布的班级",{icon:2});
		}
		return false;
	});
});
</script>
</html>