<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>联系电话</title>
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
	<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${rs.id }">
	<input id="positionId" type="hidden" name="positionId" value="${positionId }">
	<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
	  <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd">
        <label class="weui-label">手机号</label>
	    </div>
	    <div class="weui-cell__bd">
	      <c:if test="${empty rb}">
	       <input class="weui-input" style="text-align:left;" type="tel" name="phone" id="phone" value="${rs.phone }"  readonly/>
	      </c:if>
	      <c:if test="${!empty rb}">
	       <input class="weui-input" style="text-align:left;" type="tel" name="phone" id="phone" value="${rb.telephone }"  readonly/>
	      </c:if>
	    </div>
	    <div class="weui-cell__ft">
	      <button class="weui-vcode-btn" onclick="updateTelephone();">修改</button> 
	    </div>
	  </div>
	  
	   <div class="weui-cell weui-cell_vcode update" style="display:none;">
        <div class="weui-cell__hd">
        <label class="weui-label">新手机号</label>
	    </div>
	    <div class="weui-cell__bd">
	      <input class="weui-input" style="text-align:left;" type="tel" placeholder="请输入新手机号" name="newTelephone" id="newTelephone">
	    </div>
	    <div class="weui-cell__ft">
	      <button class="weui-vcode-btn" onclick="validate();" id="get_msg_btn" >发送验证码</button>
	    </div>
	  </div>
	  
	  <div class="weui-cell update" style="display:none;">
			<div class="weui-cell__hd">
			<label class="weui-label">验证码</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="code" placeholder="请输入验证码" type="text" id="code">
		   </div>
	 </div>
	      
	 <div class="weui-btn-area update" style="display:none;">
         <a id="showTooltips" class="weui-btn weui-btn_primary" href="javascript:save();">保存</a>
     </div>	
     
 </div>
 <div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
 </div>
</body>
<script  type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript"  src="<%=basePath%>/static/front/js/resume/telephone.js"></script>
</html>