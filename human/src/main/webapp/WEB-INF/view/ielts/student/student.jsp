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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/student/student.js"></script>
	
</head>
<body >
<input id="manager_enroll"
	<sec:authorize ifNotGranted='ROLE_ielts_manager_enroll'>
	value = "0"
	</sec:authorize>
	<sec:authorize ifAnyGranted='ROLE_ielts_manager_enroll'>
	value = "1"
	</sec:authorize>
 type="hidden">
	<div class="main-wrap">
	<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
		<div class="layui-form">
		<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
		<div class="layui-form-item collapse in" id="collapseOne">
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">学生姓名</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="name" id="student_name"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">手机号</label>
				<div class="layui-input-inline" style="width: 150px;">
					 <input type="text" name="name" id="student_phone"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">指导老师</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="name" id="advisor"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<br/>
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">学管</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="name" id="student_manager"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
				<label class="layui-form-label" style="width: 120px;">学校</label>
				<div class="layui-input-inline" style="width: 150px;">
					<input type="text" name="name" id="school"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			
			<div class="layui-inline" style="margin: 0 auto 10px auto">
			    <label class="layui-form-label" style="width: 120px;">是否计划学员</label>
			    <div class="layui-input-inline" style="width: 150px;">
			      <select id="is_planning" name="interest" lay-filter="aihao">
			        <option value="">全部</option>
			        <option value="1">是</option>
			        <option value="0">否</option>
			      </select>
			    </div>
			  </div>
			  
			  <div class="layui-inline" style="margin: 0 auto 10px auto">
					<button onClick="initTable()" type="button"	class="layui-btn">
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
            	<button onClick="addstudentinfo();" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;新增学员信息
				</button>
				<sec:authorize ifNotGranted='ROLE_ielts_up_excel'>
					<button onClick="upstudentinfo();" type="button" class="layui-btn">
					<li class="fa fa-plus-square"></li>
					&nbsp;批量导入
					</button>
				</sec:authorize>
				<sec:authorize ifNotGranted='ROLE_ielts_delstudentinfo'>
					<button onClick="deletestudentinfo();" type="button" class="layui-btn">
					<li class="fa fa-plus-square"></li>
					&nbsp;批量删除
					</button>
				</sec:authorize>
            </div>
        </div>
    </div>
</body>
</html>