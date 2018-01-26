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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %></head>
	</head>
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post" >
			 <input type="hidden" name="activityId" id="activityId" value="${activityId}">
				<div class="layui-form-item">
				<label class="layui-form-label">姓名:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入姓名" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">手机号码:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="telephone" lay-verify="required" autocomplete="off" placeholder="请输入手机号码" class="layui-input">
					</div>
				</div>
				
			    <div class="layui-form-item">
				<label class="layui-form-label">身份证号码:</label>
					<div class="layui-input-block">
						<input type="text" name="cardNo"  autocomplete="off"  placeholder="请输入身份证号码" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">性别:</label>
					<div class="layui-input-block">
						<select name="sex" id="sex">
							<option value="">请选择</option>
							<option value="M">男</option>
							<option value="F">女</option>
	    			   </select>
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">邮箱:</label>
					<div class="layui-input-block">
						<input type="text" name="email"  autocomplete="off"  placeholder="请输入邮箱" class="layui-input">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">部门/学校:</label>
					<div class="layui-input-block">
						<input type="text" name="deptorschool"  autocomplete="off"  placeholder="请输入部门/学校" class="layui-input">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">日期:</label>
					<div class="layui-input-block">
						<input type="text" name="exportDate"  autocomplete="off"  placeholder="请输入日期" class="layui-input">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本1:</label>
					<div class="layui-input-block">
						<input type="text" name="text1"  autocomplete="off"  placeholder="请输入备用文本1" class="layui-input">
					</div>
				</div>
								
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本2:</label>
					<div class="layui-input-block">
						<input type="text" name="text2"  autocomplete="off"  placeholder="请输入备用文本2" class="layui-input">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本3:</label>
					<div class="layui-input-block">
						<input type="text" name="text3"  autocomplete="off"  placeholder="请输入备用文本3" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/sign/addInfo.js"></script>
</html>