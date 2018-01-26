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
	<link href="<%=basePath%>/static/common/bootstrap/css/bootstrap-table-fixed-columns.css" rel="stylesheet">
<style type="text/css">
</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
		<div class="layui-form-item"  style="margin-bottom:5px;">
		    <label class="layui-form-label" style="width:10%;">活动名称</label>
    		<div class="layui-input-inline" style="width:10%;">
    			<input type="text" id="activityName"  autocomplete="off" class="layui-input">    
    		</div>
    		
    		<label class="layui-form-label" style="width:10%;">所属部门</label>
    		<div class="layui-input-inline" style="width:10%;">
    			  <input type="text" id="deptName"  autocomplete="off" class="layui-input">   
    		</div>
    		
		    <label class="layui-form-label" style="width:10%;">开始时间</label>
			<div class="layui-input-inline" style="width:10%;">
				<input class="layui-input" placeholder="开始日期" id="startTime">			
			</div>
				
		    <label class="layui-form-label" style="width:10%;">结束时间</label>
			<div class="layui-input-inline" style="width:10%;">
				<input class="layui-input" placeholder="结束日期" id="endTime">			
			</div>
			
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			    </button>
			</div>
		</div>
	</div>
		</form>
		</fieldset>
		<div class="y-role">
            <!--工具栏-->
             <div id="toolbar">
            	<button onClick="return activityAdd()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新建活动
			   </button>
			   <button onClick="return  batchExport()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量导出汇总
			   </button>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="activityTable">
            </table>
        </div>
    </div> 
    <div id="qr" style="display:none;">
	    <span id="qrspan"></span>
	    <div style="margin-left:70px;">
		   <image id="schoolqrcode" src="" style="width:180px;height:180px;"/><br>
		   <a id="qra" style="margin-left:55px;" href="">下载二维码</a><font style="color: red;"></font>
		 </div>
	</div>   
</body>
<script type="text/javascript" src="<%=basePath %>/static/activity/list.js"></script>
</html>