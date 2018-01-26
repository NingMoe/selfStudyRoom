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
			<input type="hidden" name="id" value="${st.id}">
					<div class="layui-form-item">
					<label class="layui-form-label">所属机构:</label>
					<div class="layui-input-inline">
						<select name="temCompany" id='temCompany' lay-filter="temCompany"  lay-verify="required" >
						<option value="">请选择所属机构</option>
						<c:forEach var="com" items="${companyList}">
								<option value="${com.companyId}"  <c:if test="${com.companyId eq st.temCompany}">selected</c:if>>${com.companyName}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">所属部门:</label>
					<div class="layui-input-inline">
					<select name="temDept"  id="temDept"lay-verify="required">
						<option value="">请选择所属部门</option>
						<c:forEach var="hOrg" items="${hrOrgList}">
								<option value="${hOrg.id}"  <c:if test="${hOrg.id eq st.temDept}">selected</c:if>>${hOrg.name}</option>
						</c:forEach>
					</select>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">模版名称:</label>
					<div class="layui-input-block">
						<input type="text" name="temName" lay-verify="required" placeholder="请输入模版名"   value="${st.temName}" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">参数集合:</label>
					<div class="layui-btn-group">
						<c:forEach  var="sp"  items="${smsParam }">
						 	<button type="button" style="margin-top: 5px;"  onclick='insertParameter(this)' class="layui-btn layui-btn-normal layui-btn-small" key="${sp.paraCode }">${sp.paraName }</button>
						</c:forEach>
						<button type="button" style="margin-top: 5px;"  onclick='insertParameter(this)'  key="\n" class="layui-btn layui-btn-normal layui-btn-small" >换行</button>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">短信内容:</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入短信内容" class="layui-textarea"  id="temDesc" name="temDesc" lay-verify="required" >${st.temDesc}</textarea>
					</div>
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label"></label>
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="edit">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>
		 <script type="text/javascript" src="<%=basePath %>/static/basic/js/sms/sms_edit.js"></script>
		<script>
		
		</script>
	</body>
</html>