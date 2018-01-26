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
			<form class="layui-form" id="editForm" action="" method="post" >
			 <input type="hidden" name="id" id="id" value="${info.id}">
			 <input type="hidden" name="activityId" id="activityId" value="${info.activityId}">
				<div class="layui-form-item">
				<label class="layui-form-label">姓名:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" autocomplete="off" placeholder="请输入姓名" class="layui-input" value="${info.name }">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">手机号码:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="telephone" lay-verify="required" autocomplete="off" placeholder="请输入手机号码" class="layui-input" value="${info.telephone }">
					</div>
				</div>
				
			    <div class="layui-form-item">
				<label class="layui-form-label">身份证号码:</label>
					<div class="layui-input-block">
						<input type="text" name="cardNo"  autocomplete="off"  placeholder="请输入身份证号码" class="layui-input" value="${info.cardNo }">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">性别:</label>
					<div class="layui-input-block">
						<select name="sex" id="sex">
							<option value="">请选择</option>
							<option value="M" <c:if test="${info.sex eq 'M'}">selected="selected"</c:if>>男</option>
							<option value="F" <c:if test="${info.sex eq 'F'}">selected="selected"</c:if>>女</option>
	    			   </select>
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">邮箱:</label>
					<div class="layui-input-block">
						<input type="text" name="email"  autocomplete="off"  placeholder="请输入邮箱" class="layui-input" value="${info.email }">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">部门/学校:</label>
					<div class="layui-input-block">
						<input type="text" name="deptorschool"  autocomplete="off"  placeholder="请输入部门/学校" class="layui-input" value="${info.deptorschool }">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">日期:</label>
					<div class="layui-input-block">
						<input type="text" name="exportDate"  autocomplete="off"  placeholder="请输入日期" class="layui-input" value="${info.exportDate }">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本1:</label>
					<div class="layui-input-block">
						<input type="text" name="text1"  autocomplete="off"  placeholder="请输入备用文本1" class="layui-input" value="${info.text1 }">
					</div>
				</div>
								
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本2:</label>
					<div class="layui-input-block">
						<input type="text" name="text2"  autocomplete="off"  placeholder="请输入备用文本2" class="layui-input" value="${info.text2 }">
					</div>
				</div>
				
			   <div class="layui-form-item">
			   <label class="layui-form-label">备用文本3:</label>
					<div class="layui-input-block">
						<input type="text" name="text3"  autocomplete="off"  placeholder="请输入备用文本3" class="layui-input" value="${info.text3 }">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">是否签到:</label>
					<div class="layui-input-block">
						<select name="isSign" id="isSign" lay-filter="isSign">
							<option value="1" <c:if test="${info.isSign eq '1'}">selected="selected"</c:if>>是</option>
							<option value="2" <c:if test="${info.isSign eq '2'}">selected="selected"</c:if>>否</option>
	    			   </select>
					</div>
				</div>
				
				<c:if test="${info.isSign eq '1'}">   
				　　  <div class="layui-form-item" id="signTimeDiv"> 
				</c:if>
				<c:if test="${info.isSign eq '2'}">   
				　　  <div class="layui-form-item" style="display:none;" id="signTimeDiv"> 
				</c:if>
				<label class="layui-form-label">签到时间:</label>
					<div class="layui-input-block">
						<input  id="signTime" name="signTime" autocomplete="off"  placeholder="请输入签到时间" class="layui-input" value="${signTime}">
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
<script type="text/javascript" src="<%=basePath %>/static/sign/editInfo.js"></script>
</html>