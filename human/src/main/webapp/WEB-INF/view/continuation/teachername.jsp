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
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/teachername.js"></script>
</head>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<div class="layui-form">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">班号</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" type="text" id="class_code">
			    </div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">班级名称</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" type="text" id="class_name">
			    </div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 130px;">自定义教师名称</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" type="text" id="teacher_name">
			    </div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">已经配置名称</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<select id="is_add" class="layui-input">
			    		<option value="">全部</option>
			    		<option value="1">已配置</option>
			    		<option value="0">未配置</option>
			    	</select>
			    </div>
			</div>
			  
			  <div class="layui-inline" style="margin: 0 auto 10px auto">
					<button onClick="initTable()" type="button"	class="layui-btn">
					<li class="fa fa-search"></li>
					&nbsp;搜索
			  		</button>
			  </div>
		</div>
		</div>
	</fieldset>
		
	<div class="y-role">
        <!--工具栏-->
        <!--/工具栏-->
        <!--文字列表-->
        <table class="tableList"  id="processDefTable">
        </table>
        <div id="toolbar">
      		<button onClick="addcontinueteachername();" type="button" class="layui-btn">
			<li class="fa fa-plus-square"></li>
			&nbsp;新增
			</button>
        </div>
    </div>
	</div>
</body>
</html>