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
	<body style="padding:20px;">
			<form class="layui-form" id="addForm">
				<input type="hidden" id="resumeId" name="resumeId" value="${resumeId }">
				<input type="hidden" id="userName" name="userName" value="${userName }">  
				<div class="layui-form-item" style="margin-top:15px">
					<label class="layui-form-label" style="width:15%;">沟通记录</label>
					<div class="layui-input-inline" style="width:60%;">
						<input type="text" id="content" name="content" lay-verify="required" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item" style="margin-top:30px">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="tj">立即提交</button>
					</div>
				</div>
				<div class="layui-text" style="width: 85%;">
		             <table class="layui-table" lay-skin="nob" style="margin-left: 10%;">
		                <colgroup>
		                	<col width="150">
		                	<col width="100">
		                	<col width="80">
		                </colgroup>
		                <tbody id="cbb">
		                	<c:forEach items="${remarks }" var="remark">
		                	<tr><td>${remark.content }</td><td>${remark.createUserName }</td><td><fmt:formatDate type="both" value="${remark.createTime }" /></td></tr>
		                	</c:forEach>
		                </tbody>
		                </table>
              </div>
			</form>
		<script type="text/javascript">
		layui.use(['form'], function(){
			var form = layui.form(),layer = layui.layer;
			form.on('submit(tj)', function(data){
				var content=$.trim($("input[name='content']").val());
				if(!!content){
					$.post(jsBasePath+"/recruit/resume/addComment.html",{resumeId:$("#resumeId").val(),content:content},function(data,status){
						$("#content").val("");
						var curDate = getNowFormatDate();
						var userName = $("#userName").val();
						$("#cbb").prepend("<tr><td>"+content+"</td><td>"+userName+"</td><td>"+curDate+"</td></tr>");
					},"json");
				}else{
					layer.alert("请填写沟通内容",{icon:2});
					return;
				}
				return false;
			});
		});
		
		function getNowFormatDate() {
		    var date = new Date();
		    var seperator1 = "-";
		    var seperator2 = ":";
		    var month = date.getMonth() + 1;
		    var strDate = date.getDate();
/* 		    if (month >= 1 && month <= 9) {
		        month = "0" + month;
		    }
		    if (strDate >= 0 && strDate <= 9) {
		        strDate = "0" + strDate;
		    } */
		    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
		            + " " + date.getHours() + seperator2 + date.getMinutes()
		            + seperator2 + date.getSeconds();
		    return currentdate;
		}
		</script>
	</body>
</html>