<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>个人信息</title>
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
	<div class="weui-cells_form" style='margin-bottom:60px'>
	<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${rs.id }">
	<input id="headUrl" type="hidden" name="headUrl" value="${rb.headUrl}">
	<input id="positionId" type="hidden" name="positionId" value="${positionId }">	
	<input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
	    <div class="sc-main">
    		<div class="sc-imgbox">
            	<c:if  test="${empty rb.headUrl}">
				  <img id="photoImg" style="width:120px;height:120px;" src="<%=basePath%>/static/front/image/photo.jpg"/>
				  <input class="ulbstyle" type="file" name="photo" id="photo" />
				 </c:if>			
				<c:if  test="${!empty rb.headUrl}">
				   <c:if test="${fn:startsWith(rb.headUrl, 'http')}">
				      <img id="photoImg" style="width:120px;height:120px;" src="${rb.headUrl }"/>
				      <input class="ulbstyle" type="file" name="photo" id="photo" />
				   </c:if>
				   <c:if test="${!fn:startsWith(rb.headUrl, 'http')}">
				      <img id="photoImg" style="width:120px;height:120px;" src="${filepath }${rb.headUrl }"/>
				      <input class="ulbstyle" type="file" name="photo" id="photo" />
				   </c:if>		
				</c:if>         
            </div>                
        </div>
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">姓名</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="name" id="name" placeholder="请输入姓名" type="text" value="${rs.name }" readonly/>
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">性别</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="sex" id="sex">
					<option value="">请选择</option>
					<option value="M" <c:if test="${rb.sex eq 'M'}">selected="selected"</c:if>>男</option>
					<option value="F" <c:if test="${rb.sex eq 'F'}">selected="selected"</c:if>>女</option>
				</select>
		   </div>
		</div>

		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">出生年月</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="birthDate"  id="birthDate" value="${rb.birthDate }">
			</div>
		</div>

	   <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">婚姻状况</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="marriage" id="marriage">
				    <option value="">请选择</option>
					<option value="1"<c:if test="${rb.marriage eq '1'}">selected="selected"</c:if>>未婚</option>
					<option value="2"<c:if test="${rb.marriage eq '2'}">selected="selected"</c:if>>已婚</option>
				</select>
		   </div>
		</div>
				
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">所在城市</label>
			</div>
			<div class="weui-cell__bd">
			 <input class="weui-input"  style="text-align:left;" name="locationCity"  id="locationCity" placeholder="请输入城市" type="text" value="${rb.locationCity }">
		    </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">联系邮箱</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="email" id="email" placeholder="请输入邮箱" type="text" value="${rb.email }" >
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
<script type="text/javascript" src="<%=basePath %>/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="application/javascript" src="<%=basePath%>/static/front/js/fastDelivery/fastclick.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/personal.js"></script>
</html>