<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>项目经验</title>
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
		<input id="flag" type="hidden" name="flag" value="${flag }">
		<div class="weui-btn-area">
			<a id="showTooltips" class="weui-btn weui-btn_primary" href="<%=basePath%>/front/resume/addProjectExperience.html?resumeSeekerId=${resumeSeekerId }&originalFlag=${originalFlag }&resumeId=${resumeId }&positionId=${positionId}">添加项目经验</a>
		</div>
		
		<c:if  test="${!empty rpeList}">
			<div class="page__bd">
			<c:forEach var="rwh" items="${rpeList }" varStatus="status">
				  <a href="<%=basePath%>/front/resume/editProjectExperience.html?ehId=${rwh.id}&resumeSeekerId=${resumeSeekerId }&resumeId=${resumeId }&positionId=${positionId}">
						<div class="weui-form-preview">
							<div class="weui-form-preview__hd" style="line-height: 1.5em;">
								<div class="weui-form-preview__item" style="text-align: left;">
									<div>
										<span style="font-size: 20px;">${rwh.projectName }</span> 
										<c:if test="${!empty rwh.endTime}">
											<span class="page__desc2" >
												<fmt:formatDate value="${rwh.startTime }" pattern="yyyy-MM"/>
												-
												<fmt:formatDate value="${rwh.endTime }"   pattern="yyyy-MM"/>
											</span>
									   </c:if>
									   <c:if test="${empty rwh.endTime}">
											<span class="page__desc2" >
												<fmt:formatDate value="${rwh.startTime }" pattern="yyyy-MM"/>-至今
											</span>
									  </c:if>
									</div>
								</div>
								<div class="weui-form-preview__item"
									style="text-align: left; margin-top: 3px;">
									<span class="page__desc3">${rwh.companyName }</span> 
								</div>
							</div>
						</div>
		            </a>
	           </c:forEach> 	            
			</div>
		</c:if>	
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/projectExperience.js"></script>
</html>