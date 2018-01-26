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
				<label class="layui-form-label" style="width: 10%;">部门:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="dept" id="dept">
							<option value="${dep}">${dept}</option>
					</select>
				</div>
				<label class="layui-form-label" style="width: 10%;">科目:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="subject" id="subject"  lay-filter="subjects">
						<option value="">请选择</option>
						<c:forEach items="${subjects}" var="subjects">
							<option value="${subjects.name}">${subjects.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label" style="width: 10%;">班型:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="classType" id="classType">
						<option value="">请选择</option>
						<c:forEach items="${classTypes}" var="classType">
							<option value="${classType.name }">${classType.name}</option>
						</c:forEach>
					</select>
			</div>
				<label class="layui-form-label" style="width: 10%;">年级:</label>
				<div class="layui-input-inline" style="width: 30%;">
					<select name="grade" id="grade">
						<option value="">请选择</option>
						<c:forEach items="${grades}" var="grade">
							<option value="${grade.gradeName }">${grade.gradeName }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="layui-form-item" >
			<label class="layui-form-label" style="width: 10%;">知识点:<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;">
					<input type="text" id="knowledge" name="knowledge" value=""
						lay-verify="required" class="layui-input">
				</div>
				<label class="layui-form-label" style="width: 10%;">是否为复杂题型<font color="red">*</font></label>
				<div class="layui-input-inline" style="width: 30%;" >
					<select  id="mulit" lay-filter="mulit">
							<option value="1">简单</option>
							<option value="2">复杂</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item" >
				<label class="layui-form-label title" style="width: 10%;">题目数量:<font color="red">*</font></label>
				<div class="layui-input-inline title"  style="width: 30%;">
					<input type="text" id="titleNum" name="titleNum" value="1" lay-varify="required"
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
	layui.use(['form','laydate'], function(){
	var form = layui.form(),laydate = layui.laydate,layer = layui.layer,layedit = layui.layedit;
	$(".title").hide();
	//监听提交
  form.on('submit(demo1)', function(data){
	  var mulit=$("#mulit").val();
	  var titleNum=$("#titleNum").val();
	  if(mulit=='2'){
		  if(!(titleNum>1)){
			  layer.alert("复杂题型题目数量必须大于1",{icon:2});
			  return false;
		  }
	  }
	  var index =layer.load(3, {shade: [0.3]});
	  $.post(jsBasePath+"/jzbTest/jkPoint/add.html",$("#addForm").serializeArray(),function(data,status){
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
//监听change事件
  form.on('select(mulit)', function(data){
	  if("2"==data.value){
		  $(".title").show();
		  $("#titleNum").val("");
	  }else{
		  $(".title").hide();
		  $("#titleNum").val(1);
	  }
	}); 
});	
	</script>
</html>