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
	<form class="layui-form">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;
			<li class="fa fa-angle-double-down" id="ic"></li>
		</legend>
		<div class="layui-form-item collapse in" id="collapseOne">
    		<label class="layui-form-label" style="width:8%;">所属部门</label>
			<div class="layui-input-inline">
 				<select name="dept" id="dept" lay-verify="required" style="width:12%;">
 				<option value="">请选择</option>
 				<c:forEach items="${orgs }" var="org">
 				<option value="${org.id }">${org.name }</option>
 				</c:forEach>
 				</select>
			</div>
    		
    		<label class="layui-form-label" style="width:8%;">职位状态</label>
    		<div class="layui-input-inline">
    			<select name="status" id="status" style="width:12%;">
    				<option value="">请选择</option>
    				<option value="1" selected="selected">启用</option>
    				<option value="0">禁用</option>
      			</select>
    		</div>
    		
    		<label class="layui-form-label" style="width:8%;">对外发布</label>
    		<div class="layui-input-inline">
    			<select name="isRelease" id="isRelease" style="width:12%;">
    				<option value="">请选择</option>
    				<option value="1">已发布</option>
    				<option value="0">未发布</option>
      			</select>
    		</div>
			<button onClick="initTable()" type="button"
			class="layui-btn"><li class="fa fa-search"></li>&nbsp;搜索</button>
		</div>
  		</fieldset>
  		</form>
  		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="positionTable">
            </table>
            <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn"><li class="fa fa-plus-square"></li>
				&nbsp;新增职位
			</button>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/recruitment/position/list.js"></script>
</body>
</html>