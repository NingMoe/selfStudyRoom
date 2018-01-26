<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="renderer" content="webkit">	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- load css -->
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<%@include file="/WEB-INF/view/common/bootstrapTable.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/ielts/manager/changeclasstype.js"></script>
</head>
<body >
	<div class="alertFrom">
		<div class="layui-form">
		
			<div class="layui-form-item">
				<div class="layui-inline" style="margin: 0 auto 10px auto">
					<label class="layui-form-label" style="width: 100px;">班级类型:<span style="color: red">*</span></label>
					<div class="layui-input-inline">
						<input name="classtype_change_text" id="classtype_change_text" lay-verify="not_null" style="width: 160px;" type="text" placeholder="请输入班级类型" class="layui-input">
					</div>
				</div>
			</div>
			
			<div class="layui-form-item">
				<div class="layui-input-block" >
					<button id="classtype_change" class="layui-btn">立即提交</button>
				</div>
			</div>
			
		</div>
		
	</div>
</body>
</html>