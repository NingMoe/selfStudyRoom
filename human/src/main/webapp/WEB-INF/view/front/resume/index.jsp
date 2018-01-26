<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>简历</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
</head>
<body ontouchstart>
	<div class="weui-cells">
	   <div class="sc-main">
    		<div class="sc-imgbox">            
				 <c:if  test="${empty rb.headUrl}">
				  <img id="photoImg" style="width:80px;height:100px;" src="<%=basePath%>/static/common/images/hxr_photo.jpg"/>
				 </c:if>			
				<c:if  test="${!empty rb.headUrl}">
				   <c:if test="${fn:startsWith(rb.headUrl, 'http')}">
				      <img id="photoImg" style="width:80px;height:100px;" src="${rb.headUrl }"/>
				   </c:if>
				   <c:if test="${!fn:startsWith(rb.headUrl, 'http')}">
				      <img id="photoImg" style="width:80px;height:100px;" src="${filepath }${rb.headUrl }"/>
				   </c:if>		
				</c:if>					        
            </div>
            <c:if  test="${empty rb}">
            <p style="text-align:center;">${resumeSeeker.name }</p> 
            </c:if> 
            <c:if  test="${!empty rb}">
             <p style="text-align:center;">${rb.name }</p> 
            </c:if>               
        </div>
	  <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/personal.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>个人信息<span class="page__desc6">必填</span></p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	    <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getTelephone.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>联系电话<span class="page__desc6">必填</span></p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	    <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getInterntion.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>求职意向</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	  <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getEducationHistory.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>教育经历<span class="page__desc6">必填</span></p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	  
	   <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getLanguage.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>语言能力</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	   <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getWorkHistory.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>工作/实习经历</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	   <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getProjectExperience.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>项目经验</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	   <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getInsideRecommend.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>内部推荐</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	  <a class="weui-cell weui-cell_access" href="<%=basePath%>/front/resume/getPhotoResume.html?resumeSeekerId=${resumeSeeker.id}&positionId=${positionId}&resumeId=${resumeId}">
	    <div class="weui-cell__bd">
	      <p>图片简历</p>
	    </div>
	    <div class="weui-cell__ft">
	    </div>
	  </a>
	  
	  <c:if  test="${!empty positionId}"> 	             
       <div class="weui-btn-area">
        <input id="positionId" type="hidden" name="positionId" value="${positionId }">
        <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeeker.id }">
         <a id="showTooltips" class="weui-btn weui-btn_primary" href="javascript:save();">简历投递</a>
       </div>	             
      </c:if>
      <div style="height:60px;"></div>       
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/index.js"></script>
</html>