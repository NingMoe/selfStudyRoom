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
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="companyTable">
            </table>
            <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加机构
			</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/manager/js/company_list.js"></script>
    <script type="text/javascript">
    	initTable();
    </script>
</body>
</html>