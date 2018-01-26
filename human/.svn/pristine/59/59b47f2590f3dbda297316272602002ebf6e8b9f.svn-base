<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>内部推荐</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
</head>
<body ontouchstart>
	<div class="weui-cells_form">
	    <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
		<input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
		<input id="positionId" type="hidden" name="positionId" value="${positionId }">
		<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">推荐人邮箱</label>
			</div>
			<div class="weui-cell__bd">
			 <c:if  test="${empty insideRecommend}">
			   <input class="weui-input"  name="insideRecommend" id="insideRecommend" placeholder="请输入邮箱" type="text" style="text-align:left;" value="${rb.insideRecommend }">
			  </c:if>
			 <c:if test="${!empty insideRecommend}">
			   <input class="weui-input"  name="insideRecommend" id="insideRecommend" placeholder="请输入邮箱" type="text" style="text-align:left;" value="${insideRecommend }" readonly>
			 </c:if>
		    </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">与推荐人关系</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  name="insideRelation" id="insideRelation"  placeholder="请输入关系" type="text" style="text-align:left;" value="${rb.insideRelation }">
		   </div>
		</div>
		
	   <div class="weui-btn-area">
         <a id="showTooltips" class="weui-btn weui-btn_primary" href="javascript:save();">保存</a>
       </div>	
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/insideRecommend.js"></script>
</html>