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
	<body style="padding:20px;height:600px;position:relative;">
			<form class="layui-form" style="height:100%;">
				<input type="hidden" id="menuId" name="menuId" value="${param.menuId}">
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 84px;">菜单名称:</label>
					<div class="layui-input-inline">
						<input name="name" id="name" style="width: 180px;" type="text" class="layui-input" lay-verify="required" value="${param.menuName}" readonly="readonly">
					</div>
				</div>
				
				<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label">用户</label>
					<div class="layui-form-mid layui-word-aux">
					<button type="button" id="btn_gcy" class="layui-btn layui-btn-small cy">添加用户</button>
					</div>
    			</div>
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;">
    				<label class="layui-form-label"></label>
    				<div id="gcyMsgDiv" style="width:800px;">
    				<c:forEach items="${mus }" var="mu">
    					<div style="width:100px;" class="layui-input-inline btnDiv">
    					<label>${mu.userName }</label> 
    					<button type="button" onclick="delWatcher('${mu.userId}',event);" class="layui-btn layui-btn-primary layui-btn-small">
    					<i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					</div>
    				</c:forEach>
    				</div>
    			</div>
    			
    			<div class="layui-form-item" id="gcy_search" style="display:none;">
    				<label class="layui-form-label"></label>
    				<div class="layui-input-inline">
    					<input type="text" name="watcherId" class="layui-input">
    				</div>
    				<div class="layui-input-inline">
    					<button type="button" id="btn_qr_gcy" class="layui-btn cy" >确定</button>
    				</div>
    			</div>
			</form>
		<script type="text/javascript">
		layui.use(['form'], function(){
			var form = layui.form();
		});
		
		$("#qx").bind("click",function(){
			closeFrame();
		});
		
		
		$("#btn_gcy").bind("click",function(){
 			$("#gcy_search").show();
 			$("#rlqr_search").hide();
 			$("#ms_search").hide();
		});
		
		$("#btn_qr_gcy").bind("click",function(){
			var watcherId = $("#gcy_search").find("input[name='watcherId']").val();
			if(!!watcherId){
				tjWatcher(watcherId);
			}else{
				layer.alert("请填写用户的账号（邮箱）");
			}
		});
		
		
		var tjWatcher = function(userId){
			var menuId = $("#menuId").val();
			$.ajax({
				url : jsBasePath+"/basic/wxTeacher/saveMenuUser.html",
				data:{
					"userId":userId,
					"menuId":menuId
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						var user = res.mu;
						$("#gcy_search").find("input[name='watcherId']").val("");
						var msgDiv = $("#gcyMsgDiv");
						var btn = '<div style="width:100px;" class="layui-input-inline btnDiv"><label>'+user.userName+'</label> <button type="button" onclick="delWatcher(\''+user.userId+'\',event)" class="layui-btn layui-btn-primary layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
						msgDiv.append(btn);
						$("#gcy_search").hide();
					}
				}
			});
		};
		
		
		var delWatcher = function(userId,event){
			var event = event||window.event;
			var src = event.target || event.srcElement;
			var menuId= $("#menuId").val();
			$.ajax({
				url : jsBasePath+"/basic/wxTeacher/delMenuUser.html",
				data:{
					"userId":userId,
					"menuId":menuId
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