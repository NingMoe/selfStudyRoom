<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>图片简历</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/diyUpload/css/diyUpload.css">
<link rel="stylesheet" href="<%=basePath%>/static/diyUpload/css/webuploader.css">
</head>
<body ontouchstart>
        <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
		<input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
		<input id="flag" type="hidden" name="flag" value="${flag }">
		<input id="positionId" type="hidden" name="positionId" value="${positionId }">
		<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
        <div class="weui-panel weui-panel_access">			  
			   <c:if  test="${empty rpList}">
				   <div class="weui-panel__hd">
					   <h1>
					           请选择相册里的纸质简历图片上传(支持多张)</br>
					           请确保图片清晰以保证HR可以清楚查看您的简历
					   </h1>
					</div>
			   </c:if>
			   <c:if  test="${!empty rpList}">
				   <div class="weui-panel__hd">
					 <p style="text-align:center;">我的图片简历</p>
				    </div>
				  <c:forEach var="rp" items="${rpList }" varStatus="status">
					  <img id="photoImg${status.count}" style="width:80px;height:100px;" src="${filepath }${rp.path }"/>
				  </c:forEach>
			  </c:if>		   
			  <div class="weui-panel__bd">
				   <div id="box">
					     <div id="test" class="webuploader-container">
							 <div class="webuploader-pick">上传图片简历</div>
							 <div id="rt_rt_1bgn4l4aahaa14098a91r901ps1"
								style="position: absolute; top: 100px; left: 100px; width: 126px; height: 42px; overflow: hidden; bottom: auto; right: auto;">
								<input class="webuploader-element-invisible" name="file"
									multiple="multiple" accept="image/*" type="file"> <label
									style="opacity: 0; width: 100%; height: 100%; display: block; cursor: pointer; background: rgb(255, 255, 255) none repeat scroll 0% 0%;"></label>
							 </div>
					   </div>
			       </div>
			  </div>
       </div>
    <div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/diyUpload/js/webuploader.html5only.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/diyUpload/js/diyUpload.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/photoResume.js"></script>
</html>