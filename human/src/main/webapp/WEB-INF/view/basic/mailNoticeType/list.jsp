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
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<form class="layui-form" >
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">机构:</label>
				<div class="layui-input-inline" >
					<select id="companyId"    lay-filter="companyId">
					<option value="">请选择</option>
					<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}">${com.companyName}</option>
					</c:forEach>
					</select>
				</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">部门:</label>
					<div class="layui-input-inline" >
								<select id="deptId" lay-search="">
									<option value="">请选择</option>
								</select>
					</div>
				</div>
				<div class="layui-inline">
				<label class="layui-form-label"style="width: 100px;">类型名称:</label>
				<div class="layui-input-inline" >
					<input type="text" id="temName" class="layui-input">
				</div>
			</div>
			<div class="layui-inline">
				<label class="layui-form-label" style="width: 100px;">状态:</label>
				<div class="layui-input-inline" >
					<select id="isValid"   >
						<option value="">请选择</option>
						<option value="0">有效</option>
						<option value="1">禁用</option>
					</select>
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
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="mailNoticeTypeTable">
            </table>
            <div id="toolbar">          
	            <button onClick="add();" type="button"
					class="layui-btn"><li class="fa fa-plus"></li>
					&nbsp;新增类型
				</button>
				<button  class='layui-btn layui-btn-danger' onclick='return bath_delTem();'><i class='fa fa-minus-square'></i>&nbsp;批量禁用</button >
            </div>
        </div>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/js/mailNoticeType/mailNotice_list.js"></script>
</body>
</html>