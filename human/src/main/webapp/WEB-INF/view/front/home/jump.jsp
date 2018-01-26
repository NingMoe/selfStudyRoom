<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转中...</title>
<script src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js"></script>
<script type="text/javascript">
var city = remote_ip_info.city;
location.href="<%=basePath%>/front/home/index.html?areaName="+city;
</script>
</head>
<body>
</body>
</html>