<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	var binding_reurl = "${sessionScope.binding_reurl}";
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>binding页</title>
<%@include file="/WEB-INF/view/wechat/public/public.jsp" %>
<script src="<%=basePath %>/static/wechat/student/binding.js"></script>
</head>
<body>
<input type="text" id="phone">
<input type="text" id="short_msg">
<input type="password" id="password" style="display: none" >
<input type="password" id="password2" style="display: none">
<input type="button" id="get_short_msg_btn" value="获取验证码">
<input type="button" id="login_btn" value="绑定">
</body>
</html>