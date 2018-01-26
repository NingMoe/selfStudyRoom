<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>工作经历</title>
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
				<a id="showTooltips" class="weui-btn weui-btn_primary" href="<%=basePath%>/front/resume/addWorkHistory.html?resumeSeekerId=${resumeSeekerId }&originalFlag=${originalFlag }&resumeId=${resumeId }&positionId=${positionId}">添加工作实习经历</a>
			</div>
			
		<c:if  test="${!empty rwhList}">
			<div class="page__bd">
			<c:forEach var="rwh" items="${rwhList }" varStatus="status">
				  <a href="<%=basePath%>/front/resume/editWorkHistory.html?ehId=${rwh.id}&resumeSeekerId=${resumeSeekerId }&resumeId=${resumeId }&positionId=${positionId}">
						<div class="weui-form-preview">
							<div class="weui-form-preview__hd" style="line-height: 1.5em;">
								<div class="weui-form-preview__item" style="text-align: left;">
									<div>
										<span style="font-size: 20px;">${rwh.position }</span> 
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
									<span class="page__desc3">${rwh.companyName }</span> &nbsp;&nbsp; 
									<span class="page__desc3" style="float:right;">
										<c:if test="${rwh.workType eq '0'}">全职</c:if>
										<c:if test="${rwh.workType eq '1'}">兼职</c:if>
										<c:if test="${rwh.workType eq '2'}">实习</c:if>
									</span>
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
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/workHistory.js"></script>
</html>