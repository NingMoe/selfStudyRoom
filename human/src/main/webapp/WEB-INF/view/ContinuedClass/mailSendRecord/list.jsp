<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>	    
<style type="text/css">
</style>
</head>
<body>
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item">
		         <label class="layui-form-label" style="width:10%;">规则名称</label>
				 <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="ruleName"  autocomplete="off" class="layui-input">
				 </div>
				 				    
				 <label class="layui-form-label" style="width:10%;">收件人地址</label>
				 <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="recipientsTo"  autocomplete="off" class="layui-input">
				 </div>
				 
				 <label class="layui-form-label" style="width:10%;">收件人姓名</label>
				 <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="teacherName"  autocomplete="off" class="layui-input">
				 </div>				 				 	
		</div>
		<div class="layui-form-item">
		        <label class="layui-form-label" style="width:10%;">班号</label>
				 <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="classCode"  autocomplete="off" class="layui-input">
				 </div>
				 
		         <label class="layui-form-label" style="width:10%;">发件人地址</label>
				 <div class="layui-input-inline" style="width:10%;">
					<input type="text" id="sender"  autocomplete="off" class="layui-input">
				 </div>
			
			     <label class="layui-form-label" style="width:10%;">发送状态</label>
			     <div class="layui-input-inline" style="width:10%;">
					<select name="state" id="state">
	          			<option value="">请选择</option>
	          			<option value="0">发送成功</option>
			            <option value="1">发送失败</option>
	        		</select>
				</div>	   	
		</div>	
		<div class="layui-form-item">              				    
				<label class="layui-form-label" style="width:10%;">发送时间</label>
				<div class="layui-input-inline" style="width:10%;">
				    <input class="layui-input" placeholder="开始日期" id="startTime">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline" style="width:10%;">
					<input class="layui-input" placeholder="截止日期" id="endTime">					
				</div>	
				<button onClick="initTable()" type="button"
						class="layui-btn"><li class="fa fa-search"></li>
					   &nbsp;搜索
				</button>
		  </div>
		</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="mailSendRecordTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/mailSendRecord/list.js"></script>
</html>