<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title></title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
		<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
		<script type="text/javascript" src="<%=basePath %>/static/continuation/js/classes.js"></script>
</head>
<body >
	<div class="main-wrap">
	<form class="layui-form" action="">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in">
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">学员姓名</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" id="student_name" name="student_name">
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">学员号</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" id="student_code" name="student_code">
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">教师邮箱</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input class="layui-input" id="email_addr" name="email_addr">
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">最小修改时间</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input id="update_time_left" name="update_time_left" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">最大修改时间</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<input id="update_time_right" name="update_time_right" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">修改类型</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<select id="update_type" name="update_type" class="layui-input">
			    		<option value="">请选择</option>
			    		<option value="1">新增</option>
			    		<option value="2">开启同步</option>
			    		<option value="3">关闭同步</option>
			    	</select>
			    </div>
    		</div>
    		
    		<div class="layui-inline">
    			<label class="layui-form-label" style="width: 120px;">是否同步</label>
			    <div class="layui-input-inline" style="width: 150px;">
			    	<select id="is_add" name="is_add" class="layui-input">
			    		<option value="">请选择</option>
			    		<option value="1">是</option>
			    		<option value="0">否</option>
			    	</select>
			    </div>
    		</div>
    		
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<button id="sr" type="button"	class="layui-btn">
				<li class="fa fa-search"></li>&nbsp;搜索
				</button>
			</div>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="processDefTable">
            </table>
            <div id="toolbar">
            	<button onClick="people_classes_add();" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增人班关系
			   </button>
			   <button onClick="classes_classes_add();" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增班班关系
			   </button>
			   <button onClick="select_syn();" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量同步
			   </button>
			   <button onClick="select_not_syn();" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量撤销同步
			   </button>
            </div>
        </div>
    </div>
</body>
</html>