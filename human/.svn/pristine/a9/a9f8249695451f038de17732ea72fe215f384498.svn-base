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
			 <input type="hidden" name="id" value="${rk.id}">
			   <div class="layui-form-item">
				<label class="layui-form-label">招聘网站:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="website" id="website" lay-filter="website" lay-verify="required">
							<option value="">请选择</option>							
							<c:forEach items="${websiteList }" var="website">
	    					  <option value="${website.dataValue }" <c:if test="${rk.website eq website.dataValue}">selected="selected"</c:if>>${website.name }</option>
	    					</c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">所属模块:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="modularId" id="modularId" lay-verify="required">
						    <c:forEach items="${rmList }" var="resumeModular">
	    					  <option value="${resumeModular.id }" <c:if test="${rk.modularId eq resumeModular.id}">selected="selected"</c:if>>${resumeModular.name }</option>
	    					</c:forEach>							
	    			   </select>
					</div>
				</div>
								
				<div class="layui-form-item">
				<label class="layui-form-label">关键词:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请请输入关键词" class="layui-input" value="${rk.name }">
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
<script type="text/javascript" src="<%=basePath %>/static/basic/js/resumeKeyword/edit.js"></script>
</html>