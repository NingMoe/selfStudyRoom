<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<%@include file="/WEB-INF/view/common/taglib.jsp" %>
<link href="<%=basePath %>/static/zTree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>/static/zTree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="<%=basePath %>/static/zTree/js/jquery.ztree.exedit-3.5.min.js" type="text/javascript"></script>
<link href="<%=basePath %>/static/customer/css/relation_select.css" rel="stylesheet" type="text/css" />
</head>
<body >
	<div style="height:500px;" class="alertFrom layui-form" >
		<div class="layui-form-item" style="width:49%;height:500px; text-align:left; border:1px solid #1AA094; overflow:auto; float: left;margin-left: 5px; ">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn"  onclick="save();">保存变更</button>
						<button  class="layui-btn layui-btn-primary"  onclick="refresh();">重置</button>
					</div>
				</div>
		</div>
		<script src="<%=basePath %>/static/customer/js/relation_select.js" type="text/javascript"></script>
</body>
</html>