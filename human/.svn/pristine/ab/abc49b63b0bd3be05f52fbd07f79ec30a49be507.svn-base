<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>求职意向</title>
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/common/font/iconfont.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/static/weui/weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/weui/jquery-weui.min.css">
<link rel="stylesheet" href="<%=basePath%>/static/front/css/index.css">
<style type="text/css">
.weui-select{padding-left:0;}
input::-webkit-input-placeholder { /* WebKit browsers */ text-align: left; } 
input:-moz-placeholder { /* Mozilla Firefox 4 to 18 */ text-align: left; } 
input:-ms-input-placeholder { /* Internet Explorer 10 */ text-align: left; } 
input::placeholder { text-align: left; } 
</style>
</head>
<body ontouchstart>
	<div class="weui-cells_form">
	<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
	<input id="resumeIntentionId" type="hidden" name="resumeIntentionId" value="${ri.id }">
	<input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
	<input id="flag" type="hidden" name="flag" value="${flag }">
	<input id="positionId" type="hidden" name="positionId" value="${positionId }">
	<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">期望工作地点</label>
			</div>
			<div class="weui-cell__bd">
				<input class="weui-input" style="text-align:left;" type="text" placeholder="请输入期望工作地点" name="expectWorkPlace" id="expectWorkPlace" value="${ri.expectWorkPlace }"/>
		   </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">期望薪金</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  name="minSalary" id="minSalary"  pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${ri.minSalary }">K &nbsp;&nbsp;-
			<input class="weui-input"  name="maxSalary" id="maxSalary"  pattern="[0-9]*" type="number" style="width:15%;text-align:left;" value="${ri.maxSalary }">K
		    </div>
		</div>
	   <div class="weui-btn-area">
         <a id="showTooltips" class="weui-btn weui-btn_primary" href="javascript:save()">保存</a>
       </div>	
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/interntion.js"></script>
</html>