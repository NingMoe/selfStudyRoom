<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>选择城市</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=basePath%>/static/front/css/city.css">
<style>
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #dedede;  
}

::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #dedede;
}

input:-ms-input-placeholder,
textarea:-ms-input-placeholder {
    color: #dedede;
}

input::-webkit-input-placeholder,
textarea::-webkit-input-placeholder {
    color: #dedede;
}	
</style>
<title>切换城市</title>
</head>
<body style="background:f2f2f2">
    <div style="background:#ffffff;width:100%;line-height:40px" class="hei">
    	<p>当前定位城市：<span style="color:#33cc99">${selectedArea.areaName }</span></p>
    </div>
    <div style="width:100%;">
    	<p>更多城市：</p>
    </div>
	<div style="width:100%;" class="cm_c3">
		<ul style="list-style: none;">
		<c:forEach var="list" items="${areaMap }" varStatus="status">
			 <li class="city"">
			 	 	<div class=" ${list.key }" style="width:25px;height:25px;display:inline-block;float:left;"></div>
			 	 	<div style="float:left;width:250px;">
					 	<c:forEach var="area" items="${list.value }">
							<a class="citySelect" style="color: #333;margin-right: 1em;cursor: pointer;text-decoration: none;" onclick="toSetArea('${area.id}');">${area.areaName }</a>
					 	</c:forEach>
			 	</div>
			 	<div style="clear:both"></div>
			 </li>
		 </c:forEach> 
		 </ul>
	</div>
</body>
</html>  