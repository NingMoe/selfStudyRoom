<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<%@include file="/WEB-INF/view/common/taglib.jsp"%>
<link href="<%=basePath %>/static/zTree/css/zTreeStyle.css" rel="stylesheet" type="text/css" />
<script src="<%=basePath %>/static/zTree/js/jquery.ztree.core-3.5.min.js" type="text/javascript"></script>
<script src="<%=basePath %>/static/zTree/js/jquery.ztree.excheck-3.5.min.js" type="text/javascript"></script>
<script type="text/javascript">
var userId=${userId};
</script>
</head>
<body style="padding:10px">
	<div style="width:90%;  margin:0 auto; border:1px solid #ccc; overflow:auto;  " id="treeDiv">
		<ul id="tree" class="ztree"></ul>
	</div>
	<div align="center" style="margin-top: 10px;">
				<button class="layui-btn"  onclick="getfun();">保存</button>
				<button class="layui-btn layui-btn-primary"  onclick="closeFrame();">返回</button>
	</div>
	<script type="text/javascript" src="<%=basePath %>/static/manager/js/user_cfgDept.js"></script>
</body>
</html>