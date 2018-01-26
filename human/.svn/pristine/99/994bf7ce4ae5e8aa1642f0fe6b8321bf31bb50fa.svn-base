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
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %></head>
<body >
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form">
			<div class="layui-form-item collapse in" id="collapseOne">
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 86px;">班号 </label>
					<div class="layui-input-inline" style="width: 92px;">
						<input type="text" name="name" id="class_code"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 86px;">班级名称 </label>
					<div class="layui-input-inline" style="width: 92px;">
						<input type="text" name="name" id="class_name"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 86px;">教师名称 </label>
					<div class="layui-input-inline" style="width: 92px;">
						<input type="text" name="name" id="teacher_name"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<button id="sr" type="button"	class="layui-btn">
						<li class="fa fa-search"></li>
						&nbsp;搜索
				  	</button>
				</div>
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<button id="back_btn" type="button"	class="layui-btn">
						<li class="fa fa-search"></li>
						&nbsp;返回
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
            	<button onClick="addview();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;新增规则
				</button>
				<button onClick="uploadexcel();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入
				</button>
				<button onClick="downloadexcel();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;导出全部
				</button>
				<button onClick="removeselect();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量删除
				</button>
				<button onClick="removeall();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;全部删除
				</button>
            </div>
        </div>
    </div>
    <!-- layui.use -->
    <script type="text/javascript" src="<%=basePath %>/static/rank/rankclasses.js"></script>
</body>
</html>