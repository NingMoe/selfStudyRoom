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
	<form class="layui-form" action="">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in" id="collapseOne">
    		<label class="layui-form-label" style="width:150px;">选择省份</label>
    		<div class="layui-input-inline">
    			<select name="schoolProvince" id="schoolProvince" lay-filter="schoolProvince" style="width: 150px;">
    				<option value="">请选择</option>
    				<c:forEach var="area" items="${areaInfo }">
		       	      <option value="${area.id }">${area.areaName }</option>
		           </c:forEach>
      			</select>
    		</div>
    		
    		<label class="layui-form-label" style="width:150px;">选择城市</label>
    		<div class="layui-input-inline">
    			<select name="parentAreaCode" id="parentAreaCode" style="width: 150px;">
    				<option value="">请选择</option>
      			</select>
    		</div>
			<button id="searchBtn" type="button" class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
             <div id="toolbar">
            	<button onClick="areaLevel()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;添加数据
			   </button>
            </div>
            <!--文字列表-->
            <table class="tableList"  id="areaTable"  lay-filter="area">
            </table>
        </div>
    </div>
</body>
<script type="text/html" id="areaBar">
			<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="indexTpl">
    {{d.LAY_TABLE_INDEX+1}}
</script>
<%-- <script type="text/javascript" src="<%=basePath %>/static/basic/js/areaInfo/basicRelation.js"></script> --%>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/areaInfo/areaList.js"></script>
</script>  
</html>

