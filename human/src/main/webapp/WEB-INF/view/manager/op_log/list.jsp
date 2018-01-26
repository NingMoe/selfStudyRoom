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
		       <div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">操作帐号</label>
					<div class="layui-input-inline" >
						<input type="text" id="opUser" name="opUser"  autocomplete="off" class="layui-input">
					</div>
				</div>				
			    <div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">操作类型</label>
					<div class="layui-input-inline">			
						<select id="opType" name="opType">
							<option value="">请选择</option>
							<option value="0">查询</option>
							<option value="1">新增</option>
							<option value="2">修改</option>
							<option value="3">删除</option>
							<option value="4">导入</option>
							<option value="5">导出</option>
							<option value="6">上传</option>
							<option value="7">下载</option>							
	    				</select>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 100px;">操作模块</label>
					<div class="layui-input-inline" >
						<input type="text" id="opDesc"  name="opDesc"  autocomplete="off" class="layui-input">
					</div>
				</div>
                <div class="layui-inline">
				  <label class="layui-form-label" style="width: 100px;">操作时间</label>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="开始日期" id="startTime" name="startTime">
						</div>
						<div class="layui-form-mid">-</div>
						<div class="layui-input-inline">
							<input class="layui-input" placeholder="截止日期" id="endTime"  name="endTime">
						</div>
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
            <!--文字列表-->
            <table class="tableList"  id="userOperationTable">
            </table>
        </div>
    </div>    
</body>
<script type="text/javascript" src="<%=basePath %>/static/manager/js/userOperation_list.js"></script>
</html>