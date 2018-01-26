<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<title>确认签到</title>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/mySign.css">
    <link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/iconfont.css">
</head>
<body>
<input type="hidden" name="activityTime" id="activityTime" value="${activityTime }">
    <div class="content1" <c:if test="${empty hasSignList }">style='height:100%;'</c:if>>  
	     <h3>请确认签到</h3>
	     <ul class="list1">
	     	<c:forEach var="signInfo" items="${noSignList }" varStatus="status">
		       <li class="">
		         <div class="border clearfix">
			          <div class="list1Left">
			            <c:if test="${!empty signInfo.deptorschool }">
			               <p>${signInfo.name }-${signInfo.deptorschool }</p>
			            </c:if>
			            <c:if test="${empty signInfo.deptorschool }">
			               <p>${signInfo.name }</p>
			            </c:if>
			            <p>${signInfo.telephone }</p>
			          </div>
		          <button class="" onclick="sign('${signInfo.id }');">签&nbsp;到</button>
		         </div>
		       </li>
		    </c:forEach>
	     </ul>
    </div>
    <!--已签到的 -->
    <c:if test="${!empty hasSignList }">
	    <div class="content2">
		     <ul class="list2">
		        <c:forEach var="signInfo" items="${hasSignList }" varStatus="status">
				       <li class="">
				         <div class="border clearfix">
					          <div class="list2Left f_left">
					            <c:if test="${!empty signInfo.deptorschool }">
				                  <p>${signInfo.name }-${signInfo.deptorschool }</p>
				               </c:if>
				               <c:if test="${empty signInfo.deptorschool }">
				                  <p>${signInfo.name }</p>
				               </c:if>
				                  <p>${signInfo.telephone }</p>
					          </div>
					          <p class="f_right"><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"  value="${signInfo.signTime}"/>已签到</p>
				          </div>
				       </li> 
			     </c:forEach>      
		     </ul>
	    </div>
    </c:if>
</body>
<script type="text/javascript" src="<%=basePath%>/static/sign/frontWeiXin/js/mySign.js"></script>
</html>