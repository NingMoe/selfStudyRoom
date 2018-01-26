<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<body>
	<div class="alertFrom" style="border:none;">
		<form class="layui-form" id="addForm" action="" method="post">
		<c:choose>
		 <c:when test="${size > 0}">  
			<c:forEach items="${list}" var ="li" varStatus="j" >
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:20%;" value="">试卷${j.index+1}：</label>
					<div class="layui-input-inline" style="width:60%;">
						<input type="text" id="name" name="name"   lay-verify="required"
							 class="layui-input" placeholder="" value="${li.name}">
					</div>	
				</div>
			</c:forEach>
		</c:when>
		 <c:otherwise> 
			 该题还未被使用
		 </c:otherwise>
		</c:choose>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/lstbasepaper/add.js"></script>
</html>