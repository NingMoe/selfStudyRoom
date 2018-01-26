<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<title>签到成功</title>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/signSuccess.css">
    <link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/iconfont.css">
</head>
<body>
<input type="hidden" name="activityTime" id="activityTime" value="${activityTime }">
<input type="hidden" name="signInfoId" id="signInfoId" value="${signInfo.id }">
     <div class="people">
        <p class="name">${signInfo.name }</p>
      <c:if test="${!empty signInfo.deptorschool }">
        <p class="department">${signInfo.deptorschool }</p>
      </c:if>  
    </div>
    <div class="succeed"><p><i class="iconfont" style="font-size: 0.3rem">&#xe6ff;</i>&nbsp;签到成功</p></div>
    <div class="bottom">
    <div class="btbox">
     <button onclick="sign();">继续签到</button>&nbsp;&nbsp;<span onclick="revoke();">撤销</span>
    </div>
    </div>	
</body>
<script type="text/javascript" src="<%=basePath%>/static/sign/frontWeiXin/js/signSuccess.js"></script>
<script type="text/javascript" >
window.setTimeout(sign, 5000);
</script>
</html>