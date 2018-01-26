<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>联系我们</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>
<body ontouchstart>
      <div class="weui-cells_form">	 	
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">招聘电话</label>
			</div>
			<div class="weui-cell__bd">
			 <span>0551-23658974</span>
		   </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">招聘邮箱</label>
			</div>
			<div class="weui-cell__bd">
			  <span>hfhr@xdf.cn</span>
		   </div>
		</div>
	</div> 
</body>
<script type="text/javascript" src="<%=basePath%>/static/weui/js/jquery-weui.js"></script>
</html>