<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<script type="text/javascript">
	var jsBasePath = '<%=basePath %>';
	var email_addr = "${sessionScope.binding_email_addr}";
	var openid = "${sessionScope.binding_openid}";
	var re_url = "${sessionScope.re_url}";
</script>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
	<title>教师绑定</title>
	<!-- load css -->
	<link href="<%=basePath%>/static/binding/css/teacherbinding.css" rel="stylesheet">
	<script src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
	<script src="<%=basePath %>/static/layer/layer.js"></script>
	<script src="<%=basePath %>/static/layui/layui.js"></script>
	<script type="text/javascript">
		function unbundling(){
			$.ajax({
				url : jsBasePath+"/wechat/binding/deletebindinginfo.html",
				type : "POST",
				dataType : "json",
				data : {
					openid : openid
				},
				success : function(data){
					layer.msg(data.message);
				},
				error : function(date){
					alert("网络出错，请重新发送。");
				}
			});
		}
	</script>
</head>
<body>
	<div class="body">
		<div><button onclick="unbundling();">解绑</button></div>
		${sessionScope.binding_email_addr}
		${sessionScope.binding_openid}
		${sessionScope.re_url}
	</div>
</body>
</html>