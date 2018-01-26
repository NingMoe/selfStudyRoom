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
			<form class="layui-form" id="addForm" action="" method="post" enctype="multipart/form-data">
			 <input type="hidden" name="ruleId" id="ruleId" value="${cc.ruleId}">
			  	<div class="layui-form-item">
					<label class="layui-form-label">上传文件:</label>
					<div class="layui-input-block">
						<input type="file" id="fileUploadExecl" name="file" lay-title="上传套餐数据文件(execl)"> 
						<span><a onclick="downLoadExcel();"><font color="red">模板下载</font></a></span>
					</div>
				</div>
						
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button type="button" class="layui-btn" onclick="uploadExcel()" style="margin-right:20px;">保存</button>
						<button type="button" class="layui-btn" onclick="cancle()" >取消</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/jquery-easyui-1.4/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/importCombinationData/batchAdd.js"></script>
</html>