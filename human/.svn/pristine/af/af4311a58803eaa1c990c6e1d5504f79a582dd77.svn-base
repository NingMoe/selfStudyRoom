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
	.layui-form-label {
	width: 110px !important;
}
	</style>
</head>	
<body >
 <div class="main-wrap">
 <fieldset class="layui-elem-field" style="padding: 15px;" id="initTable2">
	<legend data-toggle="collapse" data-parent="initialize" href="#initialize"  style="color: #1AA094;">初始化&nbsp;<li class="fa fa-angle-double-down" id="ic1"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="initialize">
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%;">班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="classCode" name="classCode" value="${params.classCode}" autocomplete="off" placeholder="请输入班号" class="layui-input">
				</div>
      		<div class="layui-inline">
					<button onClick=" return initTable2()"type="button "
					class="layui-btn layui-btn-warm"><li class="fa fa-search"></li>
					&nbsp;初始化
				    </button>
			   </div>
      		</div>
	</div>
		</form>
		</fieldset>
  <fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">考试列表&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
	  <div class="layui-form-item" hidden="true">			
		<input type="text" id="search" hidden="true"  class="layui-input" value="search">			
	</div>	
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%;">姓名</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="name"  autocomplete="off" placeholder="请输入学生姓名" class="layui-input">
				</div>
				<label class="layui-form-label" style="width:10%;">学号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="code" name="code"  autocomplete="off" placeholder="请输入学号" class="layui-input">
				</div>
				<label class="layui-form-label" style="width:10%;">班号</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="sClassCode" name="sClassCode"  autocomplete="off" placeholder="请输入班号" class="layui-input">
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
            <!--文字列表-->
            <table class="tableList"  id="ccrTable">
            </table>
        </div>
    </div>    
</body>
<sec:authorize ifAnyGranted='ROLE_PY_BJ' >
	<script type="text/javascript" src="<%=basePath %>/static/northamerica/examineelist/list.js"></script>
</sec:authorize>
<sec:authorize ifNotGranted="ROLE_PY_BJ">
	<script type="text/javascript" src="<%=basePath %>/static/northamerica/examineelist/list_not.js"></script>
</sec:authorize>	
</html>