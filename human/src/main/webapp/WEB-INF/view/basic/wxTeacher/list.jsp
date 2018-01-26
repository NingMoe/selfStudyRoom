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
	
	.fixtd{
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
	<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">教师中心菜单管理&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
	 <form class="layui-form" >
	  <div class="layui-form-item collapse in" id="collapseOne">
	  <div class="layui-form-item" hidden="true">			
		<input type="text" id="search" hidden="true"  class="layui-input" value="search">			
	</div>	
		<div class="layui-form-item">	
				<label class="layui-form-label" style="width:10%;">菜单名称</label>
				<div class="layui-input-inline" style="width:10%;">
					<input type="text" id="name" class="layui-input">
				</div>
				
				<label class="layui-form-label" style="width:150px;">菜单模块</label>
    			<div class="layui-input-inline">
    			<select name="category" id="category" style="width: 150px;">
    				<option value="">请选择</option>
    				<c:forEach items="${modules }" var="module">
    					<option value="${module.name }">${module.name }</option>
    				</c:forEach>
      			</select>
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
           <%--   <sec:authorize ifAnyGranted='ROLE_SJ_BJ'> --%>
            	<button onClick="return add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增数据
			   </button>
			<%-- </sec:authorize> --%>
            </div>
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="menuTable">
            </table>
        </div>
    </div>    
</body>
<%-- <sec:authorize ifNotGranted="ROLE_SJ_BJ">
	<script type="text/javascript" src="<%=basePath %>/static/datamanger/list_bid.js"></script>
</sec:authorize>	
<sec:authorize ifAnyGranted='ROLE_SJ_BJ' >
<script type="text/javascript" src="<%=basePath %>/static/datamanger/list.js"></script>
</sec:authorize> --%>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/wxTeacher/list.js"></script>
</html>