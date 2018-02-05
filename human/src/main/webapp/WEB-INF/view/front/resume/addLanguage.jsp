<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>添加语言能力</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<style type="text/css">
.weui-select{padding-left:0!important;}
input::-webkit-input-placeholder { /* WebKit browsers */ text-align: left; } 
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ text-align: left; } 
input:-ms-input-placeholder { /* Internet Explorer 10 */ text-align: left; } 
input::placeholder { text-align: left; } 
</style>
</head>
<body ontouchstart>     
	<div class="weui-cells_form">
	    <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
	    <input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
	    <input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
	    <input id="positionId" type="hidden" name="positionId" value="${positionId }">
	   <div class="sc-main">
    		<div class="sc-imgbox">
            	<img id="languagePhotoImg" style="width:120px;height:120px;" src="<%=basePath%>/static/front/image/photo.jpg">
            	<input class="ulbstyle" type="file" name="languagePhoto" id="languagePhoto" />          
            </div>                
       </div>
        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">认证名称</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="name" id="name" style="text-align:left;">
				    <option value="">请选择</option>
					<c:forEach items="${languageList }" var="language">
	    					<option value="${language.name }">${language.name }</option>
	    		    </c:forEach>
				</select>
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label"><label class="weui-label">认证成绩</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="describes" id="describes" placeholder="请输入成绩或描述" type="text" style="text-align:left;">
		   </div>
		</div>
		
	   <div class="weui-btn-area">
         <a id="showTooltips" class="weui-btn weui-btn_primary" href="javascript:save();">保存</a>
       </div>	
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/addLanguage.js"></script>
</html>