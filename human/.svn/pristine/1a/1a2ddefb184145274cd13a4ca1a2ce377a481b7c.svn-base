<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
	<script type="text/javascript">
		var jsBasePath = '<%=basePath %>';
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
	<script type="text/javascript" src="<%=basePath %>/static/binding/js/teacherbinding.js"></script>
</head>
<body>
	<div class="body">
		<div class="binding_head">
			<div class="binding_head_pro">SMART&nbsp;WORK</div>
			<div class="binding_head_work">微信绑定页面</div>
		</div>
		<div class="binding_body">
			<div class="binding_body_email">
				<div class="binding_body_email_left"><img class="binding_body_img" alt="" src="<%=basePath %>/static/binding/img/email.png"/></div>
				<div class="binding_body_email_center"><input id="email_binding" class="binding_body_input" type="text" placeholder="请输入邮箱前缀"/></div>
				<div class="binding_body_email_right"><span class="binding_body_houhzui">@xdf.xn</span></div>
			</div>
			<div class="binding_body_user">
				<div class="binding_body_user_left"><img class="binding_body_img" alt="" src="<%=basePath %>/static/binding/img/user.png"/></div>
				<div class="binding_body_user_center"><input id="user_binding" class="binding_body_input" type="password" placeholder="请输入密码"/></div>
				<div class="binding_body_user_right"><span class="binding_body_houhzui">默认为身份证后六位</span></div>
			</div>
			<div class="binding_body_btn" onclick="binding();">
				绑定
			</div>
		</div>
		<div class="binding_foot">
			<div class="binding_foot_notes_left">注：</div>
			<div class="binding_foot_notes_right">本系统仅限新东方内部员工使用，其他用户无法完成绑定。如使用有问题，请联系信息管理部</div>
		</div>
	</div>
</body>
</html>