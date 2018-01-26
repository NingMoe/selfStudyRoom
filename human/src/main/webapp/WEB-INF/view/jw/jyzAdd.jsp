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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
</head>
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
			<input type="hidden" name="areaLevel" value="2">																		
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">教研组</label>
					<div class="layui-input-inline">
						<select name="jyz" id="jyz" lay-filter="jyzs" style="width: 150px;"  lay-verify="required" multiple="multiple" lay-search="">
							<option value="">请选择</option>
		   					<c:forEach items="${jyzs }" var="jyz">
		   						<option value="${jyz.name }">${jyz.name }</option>
		   					</c:forEach>
		     			</select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">输入邮箱</label>
					<div class="layui-input-inline">
						<input type="text" name="email" id="email" lay-verify="required" placeholder="请输入员工邮箱(不需要@xdf.cn)" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>	
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">姓名</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name" class="layui-input" lay-verify="required" readonly="readonly">
					</div>
				</div>	
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">所在部门</label>
					<div class="layui-input-inline">
						<input type="text" name="dept" id="dept" class="layui-input" lay-verify="required" readonly="readonly">
					</div>
				</div>	
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="jyz">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body> 
<script type="text/javascript" src="<%=basePath %>/static/jw/jyzAdd.js"></script>
</html>