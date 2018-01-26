<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	   <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">任务名称:</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" id=roleName 
						autocomplete="off" class="layui-input">
				</div>
				</div>
			<div class="layui-inline">
				<button onClick="initTable()" type="button"
				class="layui-btn"><li class="fa fa-search"></li>
				&nbsp;搜索
			</button>
			</div>
		</div>
		</fieldset>		
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="managerTaskTable">
            </table>
            <div id="toolbar">
            	<button onClick="return jobAdd()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增任务
			</button>			
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="<%=basePath %>/static/manager/js/managerTask.js"></script>
</html>