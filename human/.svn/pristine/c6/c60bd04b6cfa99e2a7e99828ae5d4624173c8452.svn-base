<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport"/>
	<title>部门签到详情</title>
	<meta name="keywords" content="新东方合肥学校">
	<%@include file="/WEB-INF/view/sign/frontWeiXin/taglib.jsp" %>
	<link rel="stylesheet" href="<%=basePath %>/static/layui/css/layui.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/deptSignDetails.css">
    <link rel="stylesheet" href="<%=basePath%>/static/sign/frontWeiXin/css/iconfont.css">
</head>
<body>
   <div class="title">
     	<p>${deptorschool }-${signActivity.activityName }签到详情</p>
     	<div>
     	  <input type="text" placeholder="请输入姓名查询" id="serachId">    	  
     	  <button style="font-size:.16rem" onclick="serachInfo('${signActivity.id }','${deptorschool }');">搜索</button>
     	</div>
    </div>
    <c:if test="${!empty noSignList }">
	    <!-- 未签到的 -->
	    <div>
		     <ul class="list1">
		         <c:forEach var="signInfo" items="${noSignList }" varStatus="status">
			       <li class="" style="margin-top: 0.1rem">
			          <div class="border clearfix">
						   <div class="list1Left">
							   <c:if test="${!empty signInfo.deptorschool }">
				                  <p>${signInfo.name }-${signInfo.deptorschool }</p>
				               </c:if>
				               <c:if test="${empty signInfo.deptorschool }">
				                  <p>${signInfo.name }</p>
				               </c:if>
							      <p>${signInfo.telephone }</p >
						   </div>
						   <button class="" onclick="sign('${signInfo.id }');">签&nbsp;到</button>
			           </div>
			        </li>
			     </c:forEach>
		     </ul>
	    </div>
    </c:if>
    
    <c:if test="${!empty hasSignList }">
	     <!-- 已签到的 -->   
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
				          <div class="list2Right f_right clearfix">
				          	<p class=""><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium"  value="${signInfo.signTime}"/>已签到</p>
				          	<p class="" style="color:#FC6655" onclick="revoke('${signInfo.id }');">撤销</p>
				          </div>
			          </div>
			       </li> 
			   </c:forEach>         
		     </ul>
	    </div> 
    </c:if>
</body>
<script type="text/javascript" src="<%=basePath%>/static/sign/frontWeiXin/js/deptSignDetails.js"></script>
</html>