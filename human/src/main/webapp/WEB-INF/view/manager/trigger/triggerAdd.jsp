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
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">任务分组</label>
					<div class="layui-input-inline">
					<input type="text" id="jobGroup" name="jobGroup" lay-verify="required" value="DEFAULT" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">任务名称</label>
					<div   class="layui-input-inline">
					<input type="text" id="jobName" name="jobName" lay-verify="required"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">是否并发</label>
					<div class="layui-input-inline">
						<select name="isConcurrent" id="isConcurrent" style="width: 150px;" lay-verify="required">
		    				<option value="1">是</option>
		    				<option value="0">否</option>
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">表达式</label>
					<div   class="layui-input-inline">
					<input type="text" id="cronExpression" name="cronExpression" lay-verify="required"  placeholder="样例:0 0 00 * * ?" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">类名</label>
					<div   class="layui-input-inline">
					<input type="text" id="targetObject"  name="targetObject" placeholder="spring注解的bean" lay-verify="required"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">方法名</label>
					<div   class="layui-input-inline">
					<input type="text" id="targetMethod" name="targetMethod" placeholder="执行的方法" lay-verify="required"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label"  style="width: 150px;">描述</label>
					<div class="layui-input-inline">
					<input type="text" id="description"  name="description"   autocomplete="off" class="layui-input">					
					</div>
				</div>			
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="tj">立即提交</button>
						<button type="reset"  class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/manager/js/managerTaskAdd.js"></script>
</html>