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
			.clearfix{float:right !important}
		</style>
	</head>
	<body style="padding:20px;">
			<form class="layui-form">
				<input type="hidden" id="positionId" name="positionId" value="${positionId }">
				<input type="hidden" id="type" name="type" value="${type }">
				<input type="hidden" id="currentProcessDef" name="currentProcessDef" value="${currentProcessDef.id }">
				<input type="hidden" id="oldProcessDef" name="oldProcessDef" value="${oldProcessDef }">
				
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">标准流程选择</legend>
				<div class="layui-form-item">
					<label class="layui-form-label">面试轮数</label>
					<div class="layui-input-inline" style="width:400px;">
						<c:forEach items="${processDefs }" var="processDef">
						<input type="radio" name="processDef" title="${processDef.name }" 
						<c:if test="${currentProcessDef.id eq processDef.id }">checked="checked"</c:if>
						lay-filter="processDef" value="${processDef.id }">
						</c:forEach>
	    			</div>
				</div>
				</fieldset>
				
				<fieldset class="layui-elem-field layui-box">
                <legend style="color: #1AA094;">流程配置</legend>
                <c:forEach items="${currentProcessDef.nodes }" var="node" varStatus="status">
                <div class="layui-collapse processConfig" lay-accordion="">
                	<div class="layui-colla-item">
                		<h2 class="layui-colla-title">${node.nodeName }</h2>
                		<input type="hidden" name="activityName" value="${node.nodeName }">
                		<div class="layui-colla-content">
                			<div class="layui-form-item">
	                			<label class="layui-form-label">自定义名称</label>
								<div class="layui-input-inline">
								<input type="text" id="customNodeName" name="customNodeName" lay-verify="required" class="layui-input" value="${node.nodeName }">
	    						</div>
    						</div>
    						<div class="layui-form-item">
    							<label class="layui-form-label">面试官</label>
    							<div class="layui-form-mid layui-word-aux">
    						 	<button type="button"  class="layui-btn layui-btn-small msgtj">添加面试官</button><span style="color: red;margin-left: 20px;">请输入面试官邮箱用户名进行查询</span>
    							</div>
    						</div>
    						<div class="layui-form-item">
	    						<label class="layui-form-label"></label>
	    						<div class="msgDiv" style="width:800px;">
	    						<c:forEach items="${node.users }" var="user">
	    							<div style="width:100px;" class="layui-input-inline del">
										<label>${user.name }</label> 
										<button type="button" onclick="delUser('${user.id}',event)" class="layui-btn layui-btn-small">
										<i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</div>
	    						</c:forEach>
	    						</div>
	    					</div>
    					
		    				<div class="layui-form-item msgsearch" style="display:none;">
		    					<label class="layui-form-label"></label>
		    					<div class="layui-input-inline">
		    						<input type="text" name="email" class="layui-input">
		    					</div>
		    					<div class="layui-input-inline">
		    						<input type="hidden" name="nodeType1" value="${node.nodeType }">
		    						<input type="hidden" name="nodeId" value="${node.nodeId }">
		    						<input type="hidden" name="role" value="${node.assigneeExp }">
		    						<button type="button" class="layui-btn msgqd">确定</button>
		    					</div>
		    					
		    				</div>
		    			 	<div class="layui-form-item">
		    					<label class="layui-form-label">通过人数设置</label>
								<div class="layui-input-inline">
									<input type="radio" name="signType${node.nodeName }" checked="checked" title="绝对数" lay-filter="signType" value="0">
									<input type="radio" name="signType${node.nodeName }" title="百分比" lay-filter="signType" value="1">
		    					</div>
		    					<label class="layui-form-label typeLabel">至少同意人数</label>
								<div class="layui-input-inline">
									<input type="text" id="signQuantity" name="signQuantity" lay-verify="required" class="layui-input">
		    					</div>
		    					<div class="layui-form-mid layui-word-aux">绝对数时请填写1,2... 百分比时请填入50,60...</div>
		            		</div>
		            		
		            		<div class="layui-form-item">
		    					<label class="layui-form-label">不通过人数设置</label>
								<div class="layui-input-inline">
									<input type="radio" name="nopassSignType${node.nodeName }" checked="checked" title="绝对数" lay-filter="nopassSignType" value="0">
									<input type="radio" name="nopassSignType${node.nodeName }" title="百分比" lay-filter="nopassSignType" value="1">
		    					</div>
		    					<label class="layui-form-label typeLabel">至少不同意人数</label>
								<div class="layui-input-inline">
									<input type="text" id="nopassSignQuantity" name="nopassSignQuantity" lay-verify="required" class="layui-input">
		    					</div>
		    					<div class="layui-form-mid layui-word-aux">绝对数时请填写1,2... 百分比时请填入50,60...</div>
		            		</div>
		            		<!-- <div class="layui-form-item">
		    					<label class="layui-form-label">接收超时</label>
								<div class="layui-input-inline">
									<input type="checkbox" name="notify1" value="1" title="开启" lay-filter="notify1">
		    					</div>
		    					<label id="typeLabel" class="layui-form-label notify1" style="display:none;">超时时间</label>
								<div class="layui-input-inline notify1" style="display:none;">
									<input type="text" name="notify1Quan" placeholder="单位为分钟" lay-verify="required" class="layui-input">
		    					</div>
		            		</div>
		            		<div class="layui-form-item">
		    					<label class="layui-form-label">办理超时</label>
								<div class="layui-input-inline">
									<input type="checkbox" name="notify2" value="1" title="开启" lay-filter="notify2">
		    					</div>
		    					<label id="typeLabel" class="layui-form-label notify2" style="display:none;">超时时间</label>
								<div class="layui-input-inline notify2" style="display:none;">
									<input type="text" name="notify2Quan" placeholder="单位为分钟" class="layui-input">
		    					</div>
		            		</div> -->
		            	 	<div class="layui-form-item">
							    <label class="layui-form-label">视频任务包</label>
							    <div class="layui-input-inline">
							    	<input type="text" name="videoTask" lay-verify="required" class="layui-input">
							    </div>
						    </div> 
		             		<div class="layui-form-item layui-form-text" style="margin-top:20px;">
							    <label class="layui-form-label">文字任务包</label>
							    <div class="layui-input-block" style="width:80%;">
							      <textarea class="layui-textarea layui-hide" id="textTask${status.index }" name="textTask" lay-verify="textTask">
							      </textarea>
							    </div>
						    </div>
						    <div class="layui-form-item">
						    	<label class="layui-form-label">图片任务包</label>
								<div class="layui-input-inline" style="width:265px">
									<input type="file" id="file${status.index }" name="file${status.index }" lay-type="file" 
									class="layui-upload-file" lay-title="选择本环节图片任务" style="width:200px"> 
									<button id="file${status.index }Button" type="button" style="display:none;" class="layui-btn layui-btn-small delPic"><i class="layui-icon"></i></button>
									<img width=255px height=200px id="file${status.index }Img" style="display:none;"/>
								</div>
							</div>
							<div class="layui-form-item">
						<label class="layui-form-label">打分项选择</label>
						<div class="layui-input-inline" style="width:75%;">
							<c:forEach items="${positionScoreItems }" var="item">
							<div class="layui-input-inline" style="width:175px;">
							<input type="checkbox" name="scoreItem" value="${item.name }" title="${item.name }" lay-filter="score">
							</div>
							</c:forEach>
						</div>
            		</div>
						</div>
					</div>
				</div>
                </c:forEach>
                </fieldset>
                
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button type="button" id="bc" class="layui-btn">保存</button>
						<button type="button" id="qx" class="layui-btn">取消</button>
					</div>
				</div>
			</form>
		<script type="text/javascript">
		 $("#qx").bind("click",function(){
			closeFrame();
		});

		var delUser = function(id,event){
			var event = event || window.event;
			var src = event.target||event.srcElement;
			$.ajax({
				url : jsBasePath+"/recruit/position/delUser.html",
				data:{
					"id":id
				},
				dataType:"json",
				type:"post",
				success:function(res){
					if(!res.flag){
						layer.alert(res.message);
					}else{
						layer.alert(res.message,{icon:1},function(index){
							$(src).parents(".del").remove();
							layer.close(index);
						});
					}
				}
			});
		} 
		
		$(".delPic").bind("click",function(){
			var img = $(this).parent().find("img");
			var objFile = $(this).parent().find("input[type='file']").get(0);
			
			if(!+[1,]){
				objFile.select();
				document.execCommand('Delete');
			}else{
				objFile.value = "";
			}
			img.attr("src","").hide();
			$(this).hide();
			
		 });
		 
		layui.use(['element','form', 'layedit','upload'], function(){
			var form = layui.form(),layer = layui.layer,layedit = layui.layedit,element = layui.element();
			var textTaskNo = $("textarea[name='textTask']").size();
			var editArr = [];
			for(var i=0;i<textTaskNo;i++){
				editArr.push(layedit.build('textTask'+i,{tool: []}));
			}
			
			$(".layui-colla-content").eq(0).addClass("layui-show");
			
			$(".msgtj").bind("click",function(){
				$(this).parent().parent().siblings(".msgsearch").show();
			});
			
			
			$(".msgqd").bind("click",function(){
				var email = $(this).parent().prev().find("input[name='email']").val();
			    var nodeId = $(this).siblings("input[name='nodeId']").val();
			    var role = $(this).siblings("input[name='role']").val();
				if(!!email){
					tjUser(email,nodeId,role,this);
				}else{
					layer.alert("请填写面试官的账号（邮箱）");
				}
			});
			
			form.on('radio(processDef)', function(data){
				var positionId = $("#positionId").val();
				var type = $("#type").val();
				if(data.value != $("#currentProcessDef").val()){
					location.href = jsBasePath+"/recruit/position/toProcessConfig.html?positionId="+positionId+"&type="+type+"&processDefId="+data.value;
				}
			});
			
			form.on('radio(signType)', function(data){
				if(data.value=="0"){
					$(this).parent().siblings(".typeLabel").html("至少同意人数");
				}
				if(data.value=="1"){
					$(this).parent().siblings(".typeLabel").html("至少同意百分比");
				}
			});
			
			form.on('radio(nopassSignType)', function(data){
				if(data.value=="0"){
					$(this).parent().siblings(".typeLabel").html("至少不同意人数");
				}
				if(data.value=="1"){
					$(this).parent().siblings(".typeLabel").html("至少不同意百分比");
				}
			});
			
			form.on('checkbox(notify1)', function(data){
				if(this.checked){
					$(this).parent().siblings(".notify1").show();
				}else{
					$(this).parent().next().next().find("input[name='notify1Quan']").val("");
					$(this).parent().siblings(".notify1").hide();
				}
			});
			
			form.on('checkbox(notify2)', function(data){
				if(this.checked){
					$(this).parent().siblings(".notify2").show();
				}else{
					$(this).parent().next().next().find("input[name='notify2Quan']").val("");
					$(this).parent().siblings(".notify2").hide();
				}
			});
			
			form.on('checkbox(score)', function(data){
				if(this.checked){
					$(this).next().after('<div class="layui-input-inline clearfix" style="width:45px;"><input type="text" value="5" name="scoreItemValue" placeholder="分值" class="layui-input" style="width:45px;"></div>');
				}else{
					$(this).next().next().remove();
				}
			});
			
			var s = layui.upload({
				url: jsBasePath+'/recruit/position/addProcessConfig.html',
				isAuto:false,
				change:function(file){
					var id = $(file).attr("id");
					$("#"+id+"Img").attr('src',getObjectURL(file.files[0])).show();
					$("#"+id+"Button").show();
				},
				success: function(res){ //上传成功后的回调
					if(!res.flag){
						layer.alert(res.message,{icon:2});
					}else{
						layer.alert(res.message,{icon:1},function(){
							closeFrame();
						});
					}
				}
			});
			
			$("#bc").bind("click",function(){
				if(vadidateConfig()){
					var data = getData();
					var oldProcessDef = $("#oldProcessDef").val();
					if(oldProcessDef && oldProcessDef != data.processDef){
						layer.confirm("该职位已经配置过该标准流程，确认更改配置？", function(index){
							layer.close(index);
							data.oldProcessDef = oldProcessDef;
							data = JSON.stringify(data).replace(/\"/g,"\'");
							s.action($("input[type='file']").get(),"file",{"jsonStr":data});
						});
					}else{
						data = JSON.stringify(data).replace(/\"/g,"\'");
						s.action($("input[type='file']").get(),"file",{"jsonStr":data});
					}
				}
			});
			
			var tjUser = function(email,nodeId,role,src){
				var positionId = $("#positionId").val();
				var type = $("#type").val();
				$.ajax({
					url : jsBasePath+"/recruit/position/addUser.html",
					data:{
						"positionId":positionId,
						"type":type,
						"email":email,
						"nodeId":nodeId,
						"role":role
					},
					dataType:"json",
					type:"post",
					success:function(res){
						if(!res.flag){
							layer.alert(res.message);
						}else{
							var user = res.user;
							$(src).parent().prev().find("input[name='email']").val("");
							var msgDiv = $(src).parent().parent().prev().find(".msgDiv");
							var btn = '<div style="width:100px;" class="layui-input-inline del"><label>'+user.name+'</label> <button type="button" onclick="delUser('+user.id+',event)" class="layui-btn layui-btn-small"><i class="layui-icon"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>';
							msgDiv.append(btn);
							$(src).parents(".msgsearch").hide();
						}
					}
				});
			};
			
			var getData = function(){
				var data = {};
				var positionId = $("#positionId").val();
				var type = $("#type").val();
				data.positionId = positionId;
				var processDef = $("#currentProcessDef").val()
				data.processDef = processDef;
				data.type = type;
				nodeConfigs = [];
				var scoreItems = [];
				$(".processConfig").each(function(index,e){
					var nodeConfig = {};
					var nodeId = $(this).find("input[name='nodeId']").val();
					nodeConfig.nodeId = nodeId;
					var customNodeName = $(this).find("input[name='customNodeName']").val();
					nodeConfig.customNodeName = customNodeName;
					var nodeType = $(this).find("input[name='nodeType1']").val();
					nodeConfig.nodeType = nodeType;
					var signType = $(this).find("input[type='radio']:checked").eq(0).val();
					nodeConfig.signType = signType;
					var signQuantity = $(this).find("input[name='signQuantity']").val();
					nodeConfig.signQuantity = signQuantity;
					
					var nopassSignType = $(this).find("input[type='radio']:checked").eq(1).val();
					nodeConfig.nopassSignType = nopassSignType;
					var nopassSignQuantity = $(this).find("input[name='nopassSignQuantity']").val();
					nodeConfig.nopassSignQuantity = nopassSignQuantity;
					/*var notify1 = $(this).find("input[name='notify1']:checked").val();
					nodeConfig.notify1 = notify1;
					var notify2 = $(this).find("input[name='notify2']:checked").val();
					nodeConfig.notify2 = notify2;
					var notify1Quan = $(this).find("input[name='notify1Quan']").val();
					nodeConfig.notify1Quan = notify1Quan;
					var notify2Quan = $(this).find("input[name='notify2Quan']").val();
					nodeConfig.notify2Quan = notify2Quan; */
					nodeConfig.textTask = layedit.getContent(editArr[index]);
					nodeConfig.videoTask = $(this).find("input[name='videoTask']").val();
					nodeConfigs.push(nodeConfig);
					
					$(this).find("input[type='checkbox'][name='scoreItem']:checked").each(function(){
						var scoreItem = {};
						scoreItem.nodeId = nodeId;
						scoreItem.scoreItem = this.value;
						scoreItem.itemValue = $.trim($(this).next().next().find("input").val());
						scoreItems.push(scoreItem);
					});
				});
				data.configList = nodeConfigs;
				data.scoreItemList = scoreItems;
				return data;
			};
			
			var vadidateConfig = function(){
				var flag = true;
				$(".processConfig").each(function(index,e){
					var nodeName = $(this).find("input[name='activityName']").val();
					var customNodeName = $(this).find("input[name='customNodeName']").val();
					if(!customNodeName){
						layer.alert("请填写自定义节点名称");
						flag = false;
						return false;
					}
					
					var msg = $(this).find(".msgDiv").find("div");
					var msgSize = msg.size();
					if(msgSize==0){
						layer.alert("请选择"+nodeName+"面试官");
						flag = false;
						return false;
					}
					
					var signType = $(this).find("input[type='radio']:checked").eq(0).val();
					console.log(signType);
					var signQuantity = $(this).find("input[name='signQuantity']").val();
					console.log(signQuantity);
					signQuantity = $.trim(signQuantity);
					if(!signQuantity){
						layer.alert(nodeName+"面试同意数未设置");
						flag = false;
						return false;
					}
					if(isNaN(parseInt(signQuantity))){
						layer.alert(nodeName+"面试同意数配置有错误，请输入数字");
						flag = false;
						return false;
					}
					if(signType=="0"&&parseInt(signQuantity)>msgSize){
						layer.alert(nodeName+"绝对数不能超过面试官");
						flag = false;
						return false;
					} 
					
					var nopassSignType = $(this).find("input[type='radio']:checked").eq(1).val();
					console.log(nopassSignType);
					var nopassSignQuantity = $(this).find("input[name='nopassSignQuantity']").val();
					console.log(nopassSignQuantity);
					nopassSignQuantity = $.trim(nopassSignQuantity);
					if(!nopassSignQuantity){
						layer.alert(nodeName+"面试不同意数未配置");
						flag = false;
						return false;
					}
					if(isNaN(parseInt(nopassSignQuantity))){
						layer.alert(nodeName+"面试不同意配置有错误，请输入数字");
						flag = false;
						return false;
					}
					if(nopassSignType=="0"&&parseInt(nopassSignQuantity)>msgSize){
						layer.alert(nodeName+"绝对数不能超过面试官");
						flag = false;
						return false;
					}
					/* var notify1quan = $(this).find("input[name='notify1Quan']");
					if(notify1quan.is(":visible")){
						var notify1 = $.trim(notify1quan.val());
						if(notify1==""){
							layer.alert(nodeName+"接收超时分钟数不能为空");
							flag = false;
							return false;
						}else{
							if(isNaN(parseInt(notify1))){
								layer.alert(nodeName+"接收超时分钟数输入有错误");
								flag = false;
								return false;
							}
						}
					}
					
					var notify2quan = $(this).find("input[name='notify2Quan']");
					if(notify2quan.is(":visible")){
						var notify2 = $.trim(notify2quan.val());
						if(notify2==""){
							layer.alert(nodeName+"办理超时分钟数不能为空");
							flag = false;
							return false;
						}else{
							if(isNaN(parseInt(notify2))){
								layer.alert(nodeName+"办理超时分钟数输入有错误");
								flag = false;
								return false;
							}
						}
					} */
					var scoreFlag = true;
					$(this).find("input[name='scoreItemValue']").each(function(){
						var scoreItemValue = $.trim(this.value);
						var item = $(this).parent().prev().prev().val();
						if(scoreItemValue==""){
							layer.alert(nodeName+""+item+"项分值不能为空");
							flag = false;
							scoreFlag = false;
							return false;
						}else{
							if(isNaN(parseInt(scoreItemValue))){
								layer.alert(nodeName+""+item+"项分值输入有错误");
								flag = false;
								scoreFlag = false;
								return false;
							}
						}
					});
					if(!scoreFlag){
						return false;
					}
				});
				return flag;
			};
			
			var getObjectURL = function(file) {
				var url = null;
				if (window.createObjectURL != undefined) {
					url = window.createObjectURL(file)
				} else if (window.URL != undefined) {
					url = window.URL.createObjectURL(file)
				} else if (window.webkitURL != undefined) {
					url = window.webkitURL.createObjectURL(file)
				}
				return url
			}; 
		});
		
		
		</script>
	</body>
</html>