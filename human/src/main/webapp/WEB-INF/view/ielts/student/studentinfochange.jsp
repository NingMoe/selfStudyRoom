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
	<script type="text/javascript" src="<%=basePath %>/static/ielts/student/studentinfochange.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">学生姓名:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<input name="change_student_name" id="change_student_name" style="width: 160px;" type="text" placeholder="请输入学生姓名" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">手机号:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<input name="change_student_phone" id="change_student_phone" style="width: 160px;" type="text" placeholder="请输入手机号" class="layui-input">
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">学校:</label>
						<div class="layui-input-inline">
							<input name="change_school" id="change_school" style="width: 160px;" type="text" placeholder="请输入学校" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">年级:</label>
						<div class="layui-input-inline">
							<input name="change_grade" id="change_grade" style="width: 160px;" type="text" placeholder="请输入年级" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%;">
						<label class="layui-form-label" style="width: 100px;">是否计划学员:</label>
						<div class="layui-input-block" style="width: 80%" id="class_type_checkbox">
							<input name="radio_is_planning" checked="" type="radio"  value="1"  title="是">
							<input name="radio_is_planning" type="radio" value="0"  title="否">
						</div>
					</div>
				</div>
					
   			    <div class="layui-form-item">
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%;">
						<label class="layui-form-label" style="width: 100px;">班级类型:</label>
						<div class="" style="width: 80%" id="class_type_checkbox">
							<c:forEach items="${classtype.list }" var="class_type">
								<input name="classid" lay-skin="primary" type="checkbox" value="${ class_type.id}"  title="${ class_type.class_type_name}">
							</c:forEach>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%;">
						<label class="layui-form-label" style="width: 100px;">教材:</label>
						<div class="layui-input-inline" style="width: 80%">
							<c:forEach items="${book.list }" var="bookinfo">
								<input name="bookid" lay-skin="primary" type="checkbox" value="${ bookinfo.id}"  title="${ bookinfo.book_name}">
							</c:forEach>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">指导教师:<span style="color: red"></span></label>
						<div class="layui-input-inline">
							<input name="change_advisor" id="change_advisor" style="width: 160px;" type="text" placeholder="请输入指导教师" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label"  style="width: 100px;">学管:<span style="color: red"></span></label>
						<div class="layui-input-inline">
							<input name="change_student_manager" id="change_student_manager" style="width: 160px;" type="text" placeholder="请输入学管" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">代课教师:<span style="color: red"></span></label>
						<div class="layui-input-inline">
							<input name="change_teachermails" id="change_teachermails" style="width: 350px;" type="text" placeholder="请输入代课教师" class="layui-input">
							<span style="color: red">请填写邮箱前缀(即邮箱@符号前拼音加数字的部分),多个代课教师用英文分号   ; 隔开</span>
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="student_info_change" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>