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
			<input type="hidden" id="code" name="code" value="${code}">
			<div class="layui-form-item">
				<label class="layui-form-label">考试类型</label>
				<div class="layui-input-inline">
					<input type="text" id="type" name="type" value="" class="layui-input">
				</div>
				<label class="layui-form-label">考试阶段</label>
				<div class="layui-input-inline">
					<input type="text" id="stage" name="stage" value="" class="layui-input">
				</div>
				<div class="layui-inline">
					<button onClick="initTable()" type="button"
					class="layui-btn"><li class="fa fa-search"></li>
					&nbsp;搜索
				    </button>
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
				<button onClick=" return updateexcle('预备')" type="button"
				class="layui-btn">预备批量新增
				</button>
				<button onClick=" return updateexcle('基础')" type="button"
				class="layui-btn">基础批量新增
				</button>
				<button onClick=" return updateexcle('强化')" type="button"
				class="layui-btn">强化批量新增
				</button>
				<button onClick=" return updateexcle('冲刺')" type="button"
				class="layui-btn">冲刺批量新增
				</button>
				<button onClick=" return bath_del()" type="button"
				class="layui-btn">批量删除
				</button>
            </div>
        </div>
        </fieldset>
    </div>
    <script type="text/javascript" src="<%=basePath %>/static/northamerica/examineelist/detail_add.js"></script>
     <script type="text/javascript">
     var addIndex = 100000000;
    initTable(); 
    </script>
</body>
</html>