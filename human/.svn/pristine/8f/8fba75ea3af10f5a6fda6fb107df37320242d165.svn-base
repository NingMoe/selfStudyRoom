<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>语言能力</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
</head>
<body ontouchstart>
	<div class="weui-cells">
		<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
		<input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
		<input id="flag" type="hidden" name="flag" value="${flag }">
		<div class="weui-btn-area">
			<a id="showTooltips" class="weui-btn weui-btn_primary" href="<%=basePath%>/front/resume/addLanguage.html?resumeSeekerId=${resumeSeekerId }&originalFlag=${originalFlag }&resumeId=${resumeId }&positionId=${positionId}">添加外语能力认证</a>
		</div>
		
		<c:if  test="${!empty rehList}">
		  <c:forEach var="reh" items="${rehList }" varStatus="status">
		       <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/editLanguage.html?ehId=${reh.id}&resumeSeekerId=${resumeSeekerId }&resumeId=${resumeId }&positionId=${positionId}">
				    <div class="weui-cell__bd">
				      <p>${reh.name }</p>
				    </div>
				    <div class="weui-cell__ft">
				      <p>${reh.describes }</p>
				    </div>
			   </a>
		   </c:forEach>  
		 </c:if>     
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/language.js"></script>
</html>