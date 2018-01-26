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
	    <script src="<%=basePath%>/static/common/bootstrap/js/bootstrap-table-editable.js"></script>
<style type="text/css">
</style>
<body style="padding:20px;">
	<div>
		<fieldset class="layui-elem-field layui-box">
		<legend style="color: #1AA094;">基本信息</legend>
			<input type="hidden" id="id" name="id" value="${dic.id }">
			<input type="hidden" id="type" name="type" value="${dic.type }">
			<div class="layui-form-item">
				<label class="layui-form-label">KEY</label>
				<div class="layui-input-inline">
					<input type="text" id="key" name="key" value="${dic.key }" readonly="readonly" class="layui-input">
				</div>
				<label class="layui-form-label">名称</label>
				<div class="layui-input-inline">
					<input type="text" id="name" name="name" value="${dic.name }" class="layui-input">
				</div>
			</div>
		</fieldset>
		
		<fieldset class="layui-elem-field layui-box">
		<legend style="color: #1AA094;">数据信息</legend>
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="dicDataTable">
            </table>
             <div id="toolbar">
            	<button onClick="add()" type="button"
				class="layui-btn">新增
				</button>
				<button onClick="save()" type="button"
				class="layui-btn">保存
				</button>
            </div>
        </div>
        </fieldset>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/basic/dic/dic_edit.js"></script>
    <script type="text/javascript">
    initTable('${dic.type }'); 
    </script>
</body>
</html>