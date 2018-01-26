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
		<style>
			.cy{
				background-color:#5FB878
			}
		</style>
	</head>
	<body style="padding:20px;height:300px;position:relative;">
			<form class="layui-form" style="height:100%;">
				<input type="hidden" id="deptId" name="deptId" value="${dept.id }">
				<input type="hidden" id="company" name="company" value="${dept.company }">
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">所属学校</label>
					<div class="layui-input-inline" style="width:300px;">
    					 <input class="layui-input" type="text" id="companyName" name="companyName" value="${dept.companyName }" readonly="readonly">
					</div>
				</div>
				
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">所属部门</label>
					<div class="layui-input-inline" style="width:300px;">
    					 <input class="layui-input" type="text" id="deptName" name="deptName" value="${dept.name }" readonly="readonly">
					</div>
				</div>
				<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label">管理员</label>
					<div class="layui-form-mid layui-word-aux">
					<button type="button" id="btn_gly" class="layui-btn layui-btn-small cy">添加管理员</button>
					</div>
    			</div>
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;padding-left: 50px">
    				<div id="glyMsgDiv" style="width:600px;">
    				<c:forEach items="${managers }" var="manager">
    					<div style="margin-left:10px;margin-top:10px;float:left" class="btnDiv">
    					<label>${manager.realName }</label> 
    					<button type="button" onclick="delManager('${manager.email}',event);" class="layui-btn layui-btn-primary layui-btn-small">
    					<i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					</div>
    				</c:forEach>
    				</div>
    			</div>
    			
    			<div class="layui-form-item" id="gly_search" style="display:none;">
    				<label class="layui-form-label"></label>
    				<div class="layui-input-inline">
    					<input type="text" name="managerId" class="layui-input">
    				</div>
    				<div class="layui-input-inline">
    					<button type="button" id="btn_qr_gly" class="layui-btn cy" >确定</button>
    				</div>
    			</div>
			</form>
		<script type="text/javascript">
		layui.use(['form'], function(){
			var form = layui.form();
		});
		
		$("#btn_gly").bind("click",function(){
 			$("#gly_search").show();
		});
		
		$("#btn_qr_gly").bind("click",function(){
			var managerId = $("#gly_search").find("input[name='managerId']").val();
			if(!!managerId){
				tjManager(managerId);
			}else{
				layer.alert("请填写管理员员的账号（邮箱）");
			}
		});
		
		var tjManager = function(managerId){
			var deptId = $("#deptId").val();
			var deptName = $("#deptName").val();
			$.ajax({
				url : jsBasePath+"/jzbTest/dept/addGly.html",
				data:{
					"deptId":deptId,
					"deptName":deptName,
					"email":managerId
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						var manager = res.manager;
						$("#gly_search").find("input[name='managerId']").val("");
						var msgDiv = $("#glyMsgDiv");
						var btn = '<div style="margin-left:10px;margin-top:10px;float:left;" class="btnDiv"><label>'+manager.realName+'</label> <button type="button" onclick="delManager(\''+manager.email+'\',event)" class="layui-btn layui-btn-primary layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
						msgDiv.append(btn);
						$("#gly_search").hide();
					}
				}
			});
		};
		
		
		var delManager = function(email,event){
			var event = event||window.event;
			var src = event.target || event.srcElement;
			var deptId= $("#deptId").val();
			$.ajax({
				url : jsBasePath+"/jzbTest/dept/delManager.html",
				data:{
					"deptId":deptId,
					"email":email
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						layer.alert(res.message,{icon:1},function(index){
							$(src).parents(".btnDiv").remove();
							layer.close(index);
						});
					}
				}
			});
		}; 
		
		</script>
	</body>
</html>