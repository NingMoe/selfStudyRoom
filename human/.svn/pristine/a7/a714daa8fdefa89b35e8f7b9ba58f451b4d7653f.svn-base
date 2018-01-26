<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<title>签到详情</title>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/signDetails.css">
    <link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/iconfont.css">
</head>
<body>
    <div class="title">
	     <p>${signActivity.activityName }</p>
	     <p>${signActivity.signTotal }/${signActivity.total }&nbsp;&nbsp;(${signActivity.checkRate }%)</p>
    </div>
    <div class="contentlist">
     <ul class="list">
          <c:forEach var="signInfo" items="${signInfoList }" varStatus="status">
		      <li onclick="serachDeptSignDetails('${signActivity.id }','${signInfo.deptorschool }');">
			        <div class="department">
			        <p><span>${signInfo.deptorschool }</span>&nbsp;&nbsp;<span>(${signInfo.signTotal }/${signInfo.total }&nbsp;,&nbsp;${signInfo.checkRate }%)</span></p>
			        </div>
			        <div class="layui-progress" style="margin-top: 0.1rem;height: .1rem;">
					  <div class="layui-progress-bar layui-bg-red" lay-percent="20%" style="width: ${signInfo.checkRate }%;height: .1rem;"></div>
					</div>
		      </li>
	      </c:forEach>
     </ul>
    </div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/sign/frontWeiXin/js/signDetails.js"></script>
<script type="text/javascript" >
 function refresh(){
	 window.location.reload();
 }
 window.setTimeout(refresh, 5000);
</script>
</html>