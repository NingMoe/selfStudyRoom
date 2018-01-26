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
	<body style="padding:20px;height:780px;position:relative;">
			<form class="layui-form" style="height:100%;">
				<input type="hidden" id="id" name="id" value="${position.id }">
				<div class="layui-form-item" pane="">
					<label class="layui-form-label">系统设置</label>
					<div class="layui-input-inline" style="width:300px;">
    					 <input type="checkbox" id="isMsAllow" name="isMsAllow" lay-skin="primary" value="1" 
    					<c:if test="${position.isMsAllow eq 1 }">checked="checked"</c:if>
    					 title="是否流程结束后安排入职">
    					  <input type="checkbox" id="isDsAllow" name="isDsAllow" lay-skin="primary" value="1" 
    					<c:if test="${position.isDsAllow eq 1 }">checked="checked"</c:if>
    					 title="允许导师查看面试流程与评价">
    					 
    					 <input type="checkbox" id="isInnerAllow" name="isInnerAllow" lay-skin="primary" value="1" 
    					<c:if test="${position.isInnerAllow eq 1 }">checked="checked"</c:if>
    					 title="允许内部推荐员工查看面试流程与评价">
					</div>
				</div>
				<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label">观察员</label>
					<div class="layui-form-mid layui-word-aux">
					<button type="button" id="btn_gcy" class="layui-btn layui-btn-small cy">添加观察员</button>
					</div>
    			</div>
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;">
    				<label class="layui-form-label"></label>
    				<div id="gcyMsgDiv" style="width:800px;">
    				<c:forEach items="${position.watchers }" var="watcher">
    					<div style="width:100px;" class="layui-input-inline btnDiv">
    					<label>${watcher.watcherName }</label> 
    					<button type="button" onclick="delWatcher('${watcher.watcherId}',event);" class="layui-btn layui-btn-primary layui-btn-small">
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
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label">入职邮件密送人员</label>
					<div class="layui-form-mid layui-word-aux">
					<button type="button" id="btn_ms" class="layui-btn layui-btn-small cy">添加密送人员</button>
					</div>
    			</div>
    			<div class="layui-form-item" style="margin-bottom: 5px;">
    				<label class="layui-form-label"></label>
    				<div id="msMsgDiv" style="width:800px;">
    				<c:forEach items="${position.msUsers }" var="ms">
    					<div style="width:100px;" class="layui-input-inline btnDiv">
    					<label>${ms.msName }</label> 
    					<button type="button" onclick="delMsUser('${ms.msId}',event);" class="layui-btn layui-btn-primary layui-btn-small">
    					<i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					</div>
    				</c:forEach>
    				</div>
    			</div>
    			
    			<div class="layui-form-item" id="ms_search" style="display:none;">
    				<label class="layui-form-label"></label>
    				<div class="layui-input-inline">
    					<input type="text" name="msId" class="layui-input">
    				</div>
    				<div class="layui-input-inline">
    					<button type="button" id="btn_qr_ms" class="layui-btn cy" >确定</button>
    				</div>
    			</div>
    			
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;">
					<label class="layui-form-label">人力确认</label>
					<div class="layui-form-mid layui-word-aux">
					<button type="button" id="btn_rlqr" class="layui-btn layui-btn-small cy">添加人力确认人员</button>
					</div>
    			</div>
    			
    			<div class="layui-form-item" style="margin-bottom: 5px;">
    				<label class="layui-form-label"></label>
    				<div id="rlqrMsgDiv" style="width:800px;">
    				<c:forEach items="${position.hrUsers }" var="hrUser">
    					<div style="width:100px;" class="layui-input-inline btnDiv">
    					<label>${hrUser.userName }</label> 
    					<button type="button" onclick="delHrUser('${hrUser.userId}',event);" class="layui-btn layui-btn-primary layui-btn-small">
    					<i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    					</div>
    				</c:forEach>
    				</div>
    			</div>
    			
    			<div class="layui-form-item" id="rlqr_search" style="display:none;">
    				<label class="layui-form-label"></label>
    				<div class="layui-input-inline">
    					<input type="text" name="userId" class="layui-input">
    				</div>
    				<div class="layui-input-inline">
    					<button type="button" id="btn_qr_rlqr" class="layui-btn cy">确定</button>
    				</div>
    			</div>
    			
    			<div class="layui-form-item layui-form-text">
    				<label class="layui-form-label">职位别名</label>
    				<div class="layui-input-block" style="width:400px;">
    					<textarea placeholder="请输入内容" id="aliasDesc" name="aliasDesc" class="layui-textarea">${position.aliasDesc }</textarea>
    				</div>
    				<div class="layui-input-block" style="color:red;">多个别名以回车键隔开</div>
    			</div>
    			
    			<div class="layui-form-item layui-form-text">
    				<label class="layui-form-label">不接受学历</label>
    				<div class="layui-input-block" style="width:400px;">
    					<textarea placeholder="请输入内容" id="nodegreeDesc" name="nodegreeDesc" class="layui-textarea">${position.nodegreeDesc }</textarea>
    				</div>
    				<div class="layui-input-block" style="color:red;">多个学历以回车键隔开</div>
    			</div>
                
				<div class="layui-form-item" style="position:absolute;left:0;bottom:0">
					<div class="layui-input-block" >
						<button type="button" id="bc" class="layui-btn">保存</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
			</form>
		<script type="text/javascript">
		layui.use(['form'], function(){
			var form = layui.form();
		});
		
		var vadidateAlias = function(aliasDesc){
			var result = true;
			var aliases = aliasDesc.replace(/\n/g, ","); 
			var aliaArr = aliases.split(",");
			for(var i = 0 ;i<aliaArr.length;i++){
				if(aliaArr[i].trim() == "" || typeof(aliaArr[i]) == "undefined"){
					aliaArr.splice(i,1);
					i= i-1;
				}
			}
			aliaArr = aliaArr.sort();
			for(var i = 0; i < aliaArr.length - 1; i++){
				if (aliaArr[i].trim() == aliaArr[i+1].trim()){
					layer.alert("别名中存在重复",{icon:2});
					result = false;
					return false;
				}
			}
			return result;
		}
		
		var vadidateNodegrees = function(nodegreeDesc){
			var result = true;
			var aliases = nodegreeDesc.replace(/\n/g, ","); 
			var aliaArr = aliases.split(",");
			for(var i = 0 ;i<aliaArr.length;i++){
				if(aliaArr[i].trim() == "" || typeof(aliaArr[i]) == "undefined"){
					aliaArr.splice(i,1);
					i= i-1;
				}
			}
			aliaArr = aliaArr.sort();
			for(var i = 0; i < aliaArr.length - 1; i++){
				if (aliaArr[i].trim() == aliaArr[i+1].trim()){
					layer.alert("不接受简历中存在重复",{icon:2});
					result = false;
					return false;
				}
			}
			return result;
		}
		
		var saveConfig = function(aliasDesc){
			var aliasDesc = $("#aliasDesc").val();
			var nodegreeDesc = $("#nodegreeDesc").val();
			var id = $("#id").val();
			var isInnerAllow = $("#isInnerAllow").is(":checked")?"1":"0";
			var isDsAllow = $("#isDsAllow").is(":checked")?"1":"0";
			var isMsAllow = $("#isMsAllow").is(":checked")?"1":"0";
			var aliases = aliasDesc.replace(/\n/g, ","); 
			var nodegrees = nodegreeDesc.replace(/\n/g, ","); 
			var data = {};
			data.id=id;
			data.isInnerAllow=isInnerAllow;
			data.isDsAllow=isDsAllow;
			data.isMsAllow=isMsAllow;
			data.aliases = aliases;
			data.aliasDesc = aliasDesc;
			data.nodegrees = nodegrees;
			data.nodegreeDesc = nodegreeDesc;
			$.ajax({
				url : jsBasePath+"/recruit/position/editSuperConfig.html",
				data:data,
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						layer.alert(res.message,{icon:1},function(){
							closeFrame();
						});
					}
				}
			});
		}
		
		$("#bc").bind("click",function(){
			var aliasDesc = $("#aliasDesc").val();
			var nodegreeDesc = $("#nodegreeDesc").val();
			vadidateAlias(aliasDesc) && vadidateNodegrees(nodegreeDesc) && saveConfig();
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
				layer.alert("请填写观察员的账号（邮箱）");
			}
		});
		
		$("#btn_rlqr").bind("click",function(){
 			$("#rlqr_search").show();
 			$("#ms_search").hide();
 			$("#gcy_search").hide();
		});
		
		$("#btn_qr_rlqr").bind("click",function(){
			var userId = $("#rlqr_search").find("input[name='userId']").val();
			if(!!userId){
				tjHrUser(userId);
			}else{
				layer.alert("请填写人力确认人员的账号（邮箱）");
			}
		});
		
		
		$("#btn_ms").bind("click",function(){
 			$("#ms_search").show();
 			$("#gcy_search").hide();
 			$("#rlqr_search").hide();
		});
		
		$("#btn_qr_ms").bind("click",function(){
			var msId = $("#ms_search").find("input[name='msId']").val();
			if(!!msId){
				tjMsUser(msId);
			}else{
				layer.alert("请填写入职邮件的账号（邮箱）");
			}
		});
		
		
		
		var tjWatcher = function(watcherId){
			var positionId = $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/addPositionWatcher.html",
				data:{
					"watcherId":watcherId,
					"positionId":positionId
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						var watcher = res.watcher;
						$("#gcy_search").find("input[name='watcherId']").val("");
						var msgDiv = $("#gcyMsgDiv");
						var btn = '<div style="width:100px;" class="layui-input-inline btnDiv"><label>'+watcher.watcherName+'</label> <button type="button" onclick="delWatcher(\''+watcher.watcherId+'\',event)" class="layui-btn layui-btn-primary layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
						msgDiv.append(btn);
						$("#gcy_search").hide();
					}
				}
			});
		};
		
		var tjHrUser = function(userId){
			var positionId = $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/addHrUser.html",
				data:{
					"userId":userId,
					"positionId":positionId
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						var user = res.hrUser;
						$("#rlqr_search").find("input[name='userId']").val("");
						var msgDiv = $("#rlqrMsgDiv");
						var btn = '<div style="width:100px;" class="layui-input-inline btnDiv"><label>'+user.userName+'</label> <button type="button" onclick="delHrUser(\''+user.userId+'\',event)" class="layui-btn layui-btn-primary layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
						msgDiv.append(btn);
						$("#rlqr_search").hide();
					}
				}
			});
		};
		
		var delHrUser = function(userId,event){
			var event = event||window.event;
			var src = event.target || event.srcElement;
			var positionId= $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/delHrUser.html",
				data:{
					"userId":userId,
					"positionId":positionId
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
		
		
		
		var delWatcher = function(watcherId,event){
			var event = event||window.event;
			var src = event.target || event.srcElement;
			var positionId= $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/delWatcher.html",
				data:{
					"watcherId":watcherId,
					"positionId":positionId
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
		
		
		var tjMsUser = function(userId){
			var positionId = $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/addPositionMsUser.html",
				data:{
					"msId":userId,
					"positionId":positionId
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						var user = res.msUser;
						$("#ms_search").find("input[name='msId']").val("");
						var msgDiv = $("#msMsgDiv");
						var btn = '<div style="width:100px;" class="layui-input-inline btnDiv"><label>'+user.msName+'</label> <button type="button" onclick="delMsUser(\''+user.msId+'\',event)" class="layui-btn layui-btn-primary layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
						msgDiv.append(btn);
						$("#ms_search").hide();
					}
				}
			});
		};
		
		var delMsUser = function(userId,event){
			var event = event||window.event;
			var src = event.target || event.srcElement;
			var positionId= $("#id").val();
			$.ajax({
				url : jsBasePath+"/recruit/position/delMsUser.html",
				data:{
					"msId":userId,
					"positionId":positionId
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