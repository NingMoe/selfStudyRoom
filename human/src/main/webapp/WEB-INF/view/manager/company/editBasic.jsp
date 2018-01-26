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
	<body style="padding:20px;">
		<div class="alertFrom">
			<form class="layui-form" id="editForm" action="" method="post">
				<input type="hidden" name="companyId" value="${company.companyId }">
				<div class="layui-form-item">
					<label class="layui-form-label">机构名称</label>
					<div class="layui-input-inline">
						<input type="text" name="companyName" lay-verify="required" value="${company.companyName }" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">机构ID</label>
					<div class="layui-input-inline">
						<input type="text" name="newCompanyId" lay-verify="required" value="${company.companyId }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">短信ID</label>
					<div class="layui-input-inline">
					<input type="text" name="messageId" value="${company.messageId }" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="compa">保存</button>
					</div>
				</div>
			</form>
		</div>
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/company_edit.js"></script>
		<script>
		</script>
	</body>
</html>