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
			<form class="layui-form" id="addForm" action="" method="post">
				<input type="hidden"  name="parentId"   value="${resource.parentId}"/>
				<input type="hidden"  name="type"   value="${resource.type}"/>
				<div class="layui-form-item">
					<label class="layui-form-label" style="white-space:nowrap;">上级资源名称:</label>
					<div class="layui-input-inline ">
						<label class="layui-form-label" style="color:#1AA094; white-space:nowrap;">${resource.parentName}</label>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源编码:</label>
					<div class="layui-input-inline">
						<input type="text" name="resKey" lay-verify="let_num_un" placeholder="请输入资源key" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源名称:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" lay-verify="required"   placeholder="请输入资源名称"  autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源URL:</label>
					<div class="layui-input-inline">
						<input type="text" name="resUrl"   autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序权重:</label>
					<div class="layui-input-inline">
						<input type="number" name="sort"  lay-verify="number"  autocomplete="off" class="layui-input" value="0">
					</div>
					<div class="layui-form-mid layui-word-aux">值越大排序越往后</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">图标:</label>
					<div class="layui-input-inline">
						<input type="text" name="iconUrl"   autocomplete="off" class="layui-input" >
					</div>
					<div class="layui-form-mid layui-word-aux"><a href="http://www.fontawesome.com.cn/faicons/" target="_blank">点此打开图标库</a></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源类型:</label>
					<div class="layui-input-inline">
						<label  class="layui-form-label" style="color:#1AA094; white-space:nowrap;"><c:if test="${resource.type eq '0'}">目录</c:if><c:if test="${resource.type eq '1'}">菜单</c:if><c:if test="${resource.type eq '2'}">按扭</c:if>
				</label>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">资源描述:</label>
					<div class="layui-input-inline">
						<textarea   name="rescDesc"   placeholder="简单描述资源作用"  autocomplete="off" class="layui-textarea"></textarea>
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
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/resource_add.js"></script>
		<script>
		</script>
	</body>
</html>