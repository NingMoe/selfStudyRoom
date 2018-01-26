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
	table {
		table-layout: fixed;
	}
	
	table tr td {
		text-overflow: ellipsis; /* for IE */
		-moz-text-overflow: ellipsis; /* for Firefox,mozilla */
		overflow: hidden;
		white-space: nowrap;
	}
</style>
</head>	
<body >
 <div class="main-wrap">
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">数据管理&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
	  <div class="layui-form-item" hidden="true">			
		<input type="text" id="search" hidden="true"  class="layui-input" value="search">			
	</div>	
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%;">学生姓名</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="stName"  autocomplete="off" placeholder="请输入学生姓名" class="layui-input">
				</div>
				<label class="layui-form-label" style="width:10%;">学员班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="stclassNo"  autocomplete="off" placeholder="请输入学员班号" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:150px;">剩余课时</label>
    			<div class="layui-input-inline">
    			<select name="symbol" id="symbol" style="width: 150px;">
    				<option value="">请选择</option>
    					<option value="&gt;">大于0</option>
      			</select>
    			</div>
      		</div>
      		<div class="layui-form-item">
      		<label class="layui-form-label" style="width:10%;">添加人</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="applyName" autocomplete="off"  placeholder="请输入申请人" class="layui-input">
				</div>
				<div class="layui-inline">
					<button onClick="initTable(2)" type="button"
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
             <sec:authorize ifAnyGranted='ROLE_SJ_BJ'>
            	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增数据
			   </button>
			   <button onClick="return updateexcel()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量导入
			   </button>
			   <button onClick="return bath_del()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;批量删除
			   </button>
			</sec:authorize>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
<sec:authorize ifNotGranted="ROLE_SJ_BJ">
	<script type="text/javascript" src="<%=basePath %>/static/datamanger/list_bid.js"></script>
</sec:authorize>	
<sec:authorize ifAnyGranted='ROLE_SJ_BJ' >
<script type="text/javascript" src="<%=basePath %>/static/datamanger/list.js"></script>
</sec:authorize>
</html>