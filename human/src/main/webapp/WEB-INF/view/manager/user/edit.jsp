<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title>编辑用户</title>
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
			<form class="layui-form" id="editForm" action="" method="post" >
				<input type="hidden" name="id" value="${user.id}">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="required" value="${user.name}" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">业务电话:</label>
					<div class="layui-input-inline">
						<input type="text" name="busPhone"  placeholder="请输入工作号码"  value="${user.busPhone}"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属机构:</label>
					<input name="companyName"  type="hidden">
					<div class="layui-input-inline">
						<select name="companyId" id='companyId' lay-filter="companyId"  lay-verify="required" >
						<option value="">请选择所属机构</option>
						<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}"  <c:if test="${com.companyId eq user.companyId}">selected</c:if>>${com.companyName}</option>
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
						<c:forEach var="hOrg" items="${hrOrgList}">
								<option value="${hOrg.id}"  <c:if test="${hOrg.id eq user.deptId}">selected</c:if>>${hOrg.name}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">邮箱地址:</label>
					<div class="layui-input-inline">
						<input type="text" name="emailAddr"  placeholder="请输入邮箱"   value="${user.emailAddr}"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">员工电话:</label>
					<div class="layui-input-inline">
						<input type="text" name="empPhone" placeholder="请输入员工私人号码"   value="${user.empPhone}"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/user_edit.js"></script>
		<script>
		
		</script>
	</body>
</html>