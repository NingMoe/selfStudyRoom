<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<title>添加工作经历</title>
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
	<div class="weui-cells_form" style="margin-bottom:60px;">
	    <input id="resumeSeekerId" type="hidden" name="resumeSeekerId" value="${resumeSeekerId }">
	    <input id="originalFlag" type="hidden" name="originalFlag" value="${originalFlag }">
	    <input id="resumeId" type="hidden" name="resumeId" value="${resumeId }">
	    <input id="positionId" type="hidden" name="positionId" value="${positionId }">
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">公司名称</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="companyName" id="companyName" placeholder="请输入公司名称" type="text" >
		   </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">岗位名称</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  style="text-align:left;" name="position" id="position"  placeholder="请输入岗位名称" type="text" >
		   </div>
		</div>

        <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">工作性质</label>
			</div>
			<div class="weui-cell__bd">
				<select class="weui-select" style="text-align:left;" name="workType" id="workType" >
					<option value="0">全职</option>
					<option value="1">兼职</option>
					<option value="2">实习</option>
				</select>
		   </div>
		</div>

	   <div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">开始时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="startTime" id="startTime" >
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label" for="">结束时间</label>
			</div>
			<div class="weui-cell__bd">
			<input type="text" class="weui-input Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align:left;"  name="endTime" id="endTime"  >
			</div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">职位薪金</label>
			</div>
			<div class="weui-cell__bd">
			<input class="weui-input"  name="minSalary" id="minSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;">K &nbsp;&nbsp;-
			<input class="weui-input"  name="maxSalary" id="maxSalary" pattern="[0-9]*" type="number" style="width:15%;text-align:left;">K
		    </div>
		</div>
		
		<div class="weui-cell">
			<div class="weui-cell__hd">
			<label class="weui-label">工作描述</label>
			</div>
			<div class="weui-cell__bd">
			 <textarea class="weui-textarea" style="text-align:left;" placeholder="请输入工作描述" rows="5" name="describes" id="describes" ></textarea>
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
<script type="text/javascript" src="<%=basePath %>/static/My97DatePickerBeta/My97DatePicker/WdatePicker.js"></script>
<script type="application/javascript" src="<%=basePath%>/static/front/js/fastDelivery/fastclick.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/front/js/resume/addWrokHistory.js"></script>
</html>