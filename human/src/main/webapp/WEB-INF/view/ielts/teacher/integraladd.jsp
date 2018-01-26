<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增学员信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/teacher/integraladd.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">

				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教师姓名:</label>
						<div class="layui-input-inline">
							<label class="layui-form-label" style="" id="add_teacher_name" ></label>
						</div>
					</div>
					
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">邮箱:</label>
						<div class="layui-input-inline">
							<label class="layui-form-label" style="" id="add_email" ></label>
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">TKT模块1:</label>
						<div class="layui-input-inline">
							<input name="add_tkt_sourceone" id="add_tkt_sourceone" style="width: 160px;" type="text" placeholder="请输入TKT模块1" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">TKT模块2:</label>
						<div class="layui-input-inline">
							<input name="add_tkt_sourcetwo" id="add_tkt_sourcetwo" style="width: 160px;" type="text" placeholder="请输入TKT模块2" class="layui-input">
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">TKT模块3:</label>
						<div class="layui-input-inline">
							<input name="add_tkt_sourcethree" id="add_tkt_sourcethree" style="width: 160px;" type="text" placeholder="请输入TKT模块3" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">TKT模块4:</label>
						<div class="layui-input-inline">
							<input name="add_tkt_sourcefour" id="add_tkt_sourcefour" style="width: 160px;" type="text" placeholder="请输入TKT模块4" class="layui-input">
						</div>
					</div>
				</div>

				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">雅思成绩:</label>
						<div class="layui-input-inline">
							<input name="add_ielts_source" id="add_ielts_source" style="width: 160px;" type="text" placeholder="请输入雅思成绩" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">雅思到期时间:</label>
						<div class="layui-input-inline">
							<input name="add_ielts_time_valid" id="add_ielts_time_valid" style="width: 160px;" type="text" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">出勤次数:</label>
						<div class="layui-input-inline">
							<input name="add_abb_num" id="add_abb_num" style="width: 160px;" type="text" placeholder="请输入出勤次数" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">缺勤次数:</label>
						<div class="layui-input-inline">
							<input name="add_duty_num" id="add_duty_num" style="width: 160px;" type="text" placeholder="请输入缺勤次数" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教研文章:</label>
						<div class="layui-input-inline">
							<input name="add_artcle_num" id="add_artcle_num" style="width: 160px;" type="text" placeholder="请输入教研文章" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教学分享:</label>
						<div class="layui-input-inline">
							<input name="add_share_num" id="add_share_num" style="width: 160px;" type="text" placeholder="请输入教学分享" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">运营支持:</label>
						<div class="layui-input-inline">
							<input name="add_operate_num" id="add_operate_num" style="width: 160px;" type="text" placeholder="请输入运营支持" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教学投诉:</label>
						<div class="layui-input-inline">
							<input name="add_complaint_num" id="add_complaint_num" style="width: 160px;" type="text" placeholder="请输入教学投诉" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">教学反馈:</label>
						<div class="layui-input-inline">
							<input name="add_feedback_num" id="add_feedback_num" style="width: 160px;" type="text" placeholder="请输入教学反馈" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="teacher_info_add" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	</body>
</html>