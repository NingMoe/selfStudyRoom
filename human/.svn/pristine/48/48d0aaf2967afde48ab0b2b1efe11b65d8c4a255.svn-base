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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/manager/teacher.js"></script>
</head>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<div class="layui-form">
			<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
			<div class="layui-form-item collapse in" id="collapseOne">
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 120px;">教师姓名</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="name" id="teacher_name"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 120px;">教师邮箱</label>
					<div class="layui-input-inline" style="width: 150px;">
						 <input type="text" name="name" id="teacher_mail"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				  
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<button onClick="initTable();" type="button"	class="layui-btn">
					<li class="fa fa-search"></li>
					&nbsp;搜索
				  	</button>
				</div>
			</div>
		</div>
		</fieldset>
		
		<div class="y-role">
            <!--工具栏-->
            <!--/工具栏-->
            <!--文字列表-->
            <table class="tableList"  id="processDefTable">
            </table>
            <div id="toolbar">
            	<button onClick="addteacher()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;新增教师信息
				</button>
				<button onClick="upteacher()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入
				</button>
				<button onClick="deletesteacher()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量删除
				</button>
            </div>
        </div>
    </div>
</body>
</html>