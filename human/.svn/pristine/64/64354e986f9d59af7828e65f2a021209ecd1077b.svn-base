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
		<div class="alertFrom layui-form">
				<input type="hidden" id="seekerId" value="${cs.seekerId}"/>
				<input type="hidden" id="smsTo" value="${cs.smsTo}"/>
				<div class="layui-form-item layui-form">
					<label class="layui-form-label">模版:</label>
					<div class="layui-input-inline">
						<select lay-filter="smsTem"  id="temName" >
						<option value="">请选择短信模版</option>
						<c:forEach var="st" items="${stList}">
								<option value="${st.id}">${st.temName}</option>
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
						<button class="layui-btn"  onclick="subSendMsg();">立即发送</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
		</div>
	<script type="text/javascript">
	var smsTemId;
		layui.use(['form'], function() {
			var form = layui.form();
			  form.on('select(smsTem)', function(data){
				  if(smsTemId==data.value){
					  return;
				  }
				  smsTemId=data.value;
				  $("#temDesc").val("");
				  <c:forEach items="${stList}" var="a"> 
				     if(smsTemId==${a.id}){
				    	 $("#temDesc").val("${a.temDesc}");
				     }
				</c:forEach>
					return false;
				}); 
		});
		
		function subSendMsg(){
			var temDesc=$.trim($("#temDesc").val());
			if(temDesc==""){
				layer.alert("请输入短信内容!");
				return false;
			}
			 var index = layer.load(3, {shade: [0.3]});
				$.post(jsBasePath+"/recruit/acceptance/sendMsg.html",{
					smsTo:$("#smsTo").val(),
					smsComment:temDesc,
					memo:$("#temName").val(),
					seekerId:$("#seekerId").val()
				},function(data,status){
					layer.close(index); 
					if(data.flag==false){
						layer.alert("发送失败!");
					}else{
						layer.alert("发送成功!",{icon:1},function(){
							parent.location.reload(); 
							closeFrame();
						});
					}
				},"json");
			return true;
		}
		</script>
</body>
</html>