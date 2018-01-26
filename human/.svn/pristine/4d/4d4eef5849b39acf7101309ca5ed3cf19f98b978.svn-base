<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>编辑教育经历</title>
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
		<input id="rehId" type="hidden" name="rehId" value="${reh.id }">
		<input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
		<input id="positionId" type="hidden" name="positionId" value="${positionId }">	
	    <input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学校</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="schoolName" id="schoolName" placeholder="请输入学校" type="text"  value="${reh.schoolName }">
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">学历</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="education"  id="education">
				    <option value="">请选择</option>
					<c:forEach items="${eduList }" var="edu">
	    					<option value="${edu.name }"<c:if test="${reh.education eq edu.name}">selected="selected"</c:if>>${edu.name }</option>
	    		    </c:forEach>
				</select>
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">专业</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="major" placeholder="请输入专业"  id="major"  type="text"  value="${reh.major }" >
		   </div>
		</div>

	   <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">入学时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="startTime" style="text-align:left;" id="startTime" value="${startTime }">
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">毕业时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" style="text-align:left;" id="endTime" value="${endTime }">
			</div>
		</div>
		
	   <div class="weui-btn-area">
         <a  class="weui-btn weui-btn_mini weui-btn_primary" style="width:45%;margin-right:10px;" href="javascript:save();">保存</a>
         <a  class="weui-btn weui-btn_mini weui-btn_warn"    style="width:45%;margin-right:10px;"  href="javascript:deleteById();">删除</a>
       </div>	
	</div>
	<div style="margin-bottom:0px;">
	  <%@include file="/WEB-INF/view/front/home/foot.jsp" %>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>/static/jquery/jquery-weui.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="application/javascript" src="<%=basePath%>/static/front/js/fastDelivery/fastclick.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/editEducationHistory.js"></script>
</html>