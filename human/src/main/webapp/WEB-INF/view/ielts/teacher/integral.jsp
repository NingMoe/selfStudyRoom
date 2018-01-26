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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/teacher/integral.js"></script>
<body >
	<div class="main-wrap">
		<fieldset class="layui-elem-field" style="padding: 15px;" id="serachFrom">
			<div class="layui-form">
				<legend data-toggle="collapse" data-parent="serachFrom" href="#collapseOne"  style="color: #1AA094;">检索&nbsp;<li class="fa fa-angle-double-down" id="ic"></li></legend>
				<div class="layui-form-item collapse in" id="collapseOne">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">教师姓名</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input type="text" name="teacher_name" id="teacher_name"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">教师邮箱</label>
						<div class="layui-input-inline" style="width: 150px;">
							 <input type="text" name="teacher_mail" id="teacher_mail"
								autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">统计开始时间</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input id="left_integral_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 120px;">统计结束时间</label>
						<div class="layui-input-inline" style="width: 150px;">
							<input id="right_integral_time" type="text" name="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
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
            
				<button onClick="upstudentinfo()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入教师
				</button>
				
				<button onClick="upstudentinfomatchclass()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入赛课
				</button>
				
				<button onClick="deletestudentinfo()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量删除
				</button>
				
				<sec:authorize ifAllGranted='ROLE_ielts_date_teacherinfo'>
				<button onClick="uplastteacehrinfo()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入往期数据
				</button>
				</sec:authorize>
				
				<sec:authorize ifAllGranted='ROLE_ielts_date_matchclass'>
				<button onClick="upstudentinfomatchclassdate()" type="button" class="layui-btn">
				<li class="fa fa-plus-square"></li>
				&nbsp;批量导入往期赛课
				</button>
				</sec:authorize>
				
            </div>
        </div>
    </div>
</body>
</html>