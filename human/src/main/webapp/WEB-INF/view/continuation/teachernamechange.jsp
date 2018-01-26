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
	<script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-editable.js"></script>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/teachernamechange.js"></script>
</head>
<body >
<div class="main-wrap">
	<div class="y-role">
        <!--工具栏-->
        <!--/工具栏-->
        <!--文字列表-->
        <table class="tableList"  id="processDefTable">
        </table>
        <!-- <div id="toolbar">
      		<button onClick="addcontinueteachername();" type="button" class="layui-btn">
			<li class="fa fa-plus-square"></li>
			&nbsp;新增
			</button>
        </div> -->
    </div>
</div>
</body>
</html>