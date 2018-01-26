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
	</head>
	<body>
		<div class="layui-form" style="margin: 10px;">
		<div class="layui-tab layui-tab-card row" lay-filter="tabChange">
				<ul class="layui-tab-title"> 
					<li class="layui-this">准备发送</li>
					<li>发送记录</li>
				</ul>
				<div class="layui-tab-content">
				<div class="layui-tab-item layui-show">
				<input type="hidden" id="sendTel"  value="${sr.sendTel}"/>
				<input type="hidden" id="seekerId"  value="${sr.seekerId}"/>
				<input type="hidden" id="userName"  value="${userName}"/>
				<input type="hidden" id="resumeId"  value="${sr.resumeId}"/>
					<div class="layui-form-item layui-form">
					<label class="layui-form-label">模版:</label>
					<div class="layui-input-inline">
						<select lay-filter="smsTem"  id="temName" >
						<option value="">请选择短信模版</option>
						<c:forEach var="st" items="${stList}">
								<option value="${st.id}" v="${st.temDesc}">${st.temName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">短信内容:</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入短信内容" class="layui-textarea"  id="temDesc" name="temDesc" lay-verify="required" ></textarea>
					</div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label"></label>
					<div class="layui-input-block" >
						<button class="layui-btn"  onclick="sendMsgNoLogin();">立即发送</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
				</div>
				<div class="layui-tab-item">
					<c:forEach var="sr"  items="${srList}">
						<div class="layui-form-item" style="margin-bottom: 0px;border-bottom: 1px solid #34A8FF;color: #34A8FF;">
							发送人:<font style="color:red;">${sr.sendName}</font> 接收号码:<font
style="color:red;">${sr.sendTel}</font> 发送状态:<c:if test="${sr.state==1}"><font
								style="color:green;">发送成功</font> 发送时间:<font
								style="color:red;"><fmt:formatDate value='${sr.sendTime}' pattern='yyyy-MM-dd HH:mm:ss'/></font></c:if>
								<c:if test="${sr.state!=1}">
								  发送状态:<font style="color:red;">发送失败</font>
								</c:if>
						</div>
						<div class="layui-form-item">${sr.sendComment}</div>
					</c:forEach>
			</div>
		</div>
		</div>
	<script type="text/javascript">
	var smsTemId;
		layui.use(['form','element'], function() {
			var form = layui.form(),element = layui.element();
			  form.on('select(smsTem)', function(data){
				  if(smsTemId==data.value){
					  return;
				  }
				  var h=$("#temName").find("option:selected").attr("v");
				  smsTemId=data.value;
				  $("#temDesc").val(h);
			/* 	  <c:forEach items="${stList}" var="a"> 
				     if(smsTemId==${a.id}){
				    	 var h="${a.temDesc}"+"";
				    	 $("#temDesc").val(h);
				     }
				</c:forEach> */
					return false;
				}); 
		});
		
		function sendMsgNoLogin(){
			var temDesc=$.trim($("#temDesc").val());
			if(temDesc==""){
				layer.alert("请输入短信内容!");
				return false;
			}
			 var index = layer.load(3, {shade: [0.3]});
				$.post(jsBasePath+"/free/resume/sendMsg.html",{
					sendTel:$("#sendTel").val(),
					sendComment:temDesc,
					seekerId:$("#seekerId").val(),
					resumeId:$("#resumeId").val(),
					memo:$("#temName").val(),
					userName:$("#userName").val()
				},function(data,status){
					layer.close(index); 
					if(data.flag==false){
						layer.alert("发送失败!");
					}else{
						layer.alert("发送成功!",{icon:1},function(){
						location.reload();
							/* closeFrame(); */
						});
					}
				},"json");
			return true;
		}
		</script>
</body>
</html>