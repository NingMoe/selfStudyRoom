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
</head>
<body >
	<div class="main-wrap">
  		<div class="y-role">
            <!--/工具栏-->
            <div id="toolbar">
            	<button onClick="areaLevel();" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			   </button>
            </div>
            <!--文字列表-->
            <table class="tableList"  id="areaTable" lay-filter="province">
            </table>
            
        </div>
    </div>
</body>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<script type="text/html" id="proviceBar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		</script>
<%-- <script type="text/javascript" src="<%=basePath %>/static/basic/js/areaInfo/basicRelation2.js"></script> --%>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/areaInfo/provinceList.js">
</script>
</html>