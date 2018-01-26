<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>部门管理</title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	   <%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	    <%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<style type="text/css">
</style>
<body >
	<input id="deptEdit"
	<sec:authorize ifAnyGranted='ROLE_button_dept_manager'>
		value="1"
	</sec:authorize>
	<sec:authorize ifNotGranted='ROLE_button_dept_manager'>
		value="0"
	</sec:authorize>
 	type="hidden">
	<div class="main-wrap">
		<div class="y-role">
            <table id="deptTable">
            </table>
            <div id="toolbar">
            	<button type="button" id="dAdd" onclick="addDept()" class="layui-btn"><li class="fa fa-add"></li>&nbsp;新增部门</button>
            </div>
        </div>
    </div>
    <script src="<%=basePath %>/static/jzbTest/dept/list.js" type="text/javascript"></script>
    <script type="text/javascript">
    layui.use(['form'], function(){
  	  var form = layui.form();
      initTable();
    });
    </script>
</body>
</html>