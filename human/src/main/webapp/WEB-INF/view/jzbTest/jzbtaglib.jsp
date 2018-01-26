<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>

<script src="<%=basePath %>/static/jquery/jquery-1.11.1.min.js"></script>
<script src="<%=basePath %>/static/layer/layer.js"></script>
<script src="<%=basePath %>/static/layui/layui.js"></script>
<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
<script type="text/javascript">
var jsBasePath = '<%=basePath %>';
</script>