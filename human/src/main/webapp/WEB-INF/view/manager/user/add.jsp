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
			<form class="layui-form" id="addUserForm" action="" method="post" >
				<div class="layui-form-item">
					<label class="layui-form-label">帐号:</label>
					<div class="layui-input-inline">
						<input type="text" name="loginName" lay-verify="let_num_un" autocomplete="off" placeholder="请输入帐号" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="userPassword" lay-verify="pass"   placeholder="请输入密码"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="subPassword" lay-verify="subPassword" placeholder="请再输入密码" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请再如入一次密码</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">业务电话:</label>
					<div class="layui-input-inline">
						<input type="text" name="busPhone"  placeholder="请输入工作号码" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属机构:</label>
					<input name="companyName"  type="hidden">
					<div class="layui-input-inline">
						<select name="companyId" id='companyId' lay-filter="companyId"  lay-verify="required" >
						<option value="">请选择所属机构</option>
						<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}">${com.companyName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属部门:</label>
					<input name="deptName"  type="hidden">
					<div class="layui-input-inline">
					<select name="deptId"  id="deptId"lay-verify="required">
						<option value="">请选择所属部门</option>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱地址:</label>
					<div class="layui-input-inline">
						<input type="text" name="emailAddr"  placeholder="请输入邮箱" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">员工电话:</label>
					<div class="layui-input-inline">
						<input type="text" name="empPhone" placeholder="请输入员工私人号码" autocomplete="off" class="layui-input">
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
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/user_add.js"></script>
		<script>
		</script>
	</body>
</html>