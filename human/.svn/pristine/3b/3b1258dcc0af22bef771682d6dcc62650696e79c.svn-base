<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>内推链接</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
</head>	
<body >
	<div class="weui-cells">
	  <div class="weui-cell">
	    <div style="font-size: 0.7rem;margin:0px auto;word-wrap:break-word;width: 90%;">将以下链接或者二维码推荐给求职者，求职者使用该链接或者二维码进行简历投递后，内推人就是您</div>
	  </div>

	  <div class="weui-cell">
	    <div style="font-size: 0.7rem;margin:0px auto;word-wrap:break-word;width: 90%;">${enrollUrl }<br><font style="color: red;">(长按复制后直接分享，请勿分享访问后的链接)</font></div>
	  </div>
	
	  <div class="weui-cell">
	    	<image id="schoolqrcode" style="width:200px;margin:0px auto;" src="<%=basePath %>/wechat/binding/getInsideQr.html?enrollUrl=${enrollUrl }" 
	  </div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>