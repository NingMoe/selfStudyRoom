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
				<div class="layui-form-item">
				<label class="layui-form-label">选择省份:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="schoolProvince" id="schoolProvince" lay-filter="schoolProvinceId" lay-verify="required">
							<option value="">请选择</option>
							<c:forEach var="area" items="${areaInfo }">
		       	              <option value="${area.id }">${area.areaName }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">选择城市:<font color="red">*</font></label>
					<div class="layui-input-block" >
						<select id="schoolCity" name="schoolCity" lay-filter="schoolCityId" lay-verify="required">
							<option value="">请选择</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">选择行政区:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select id="schoolArea" name="schoolArea" lay-verify="required">
							<option value="">请选择</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">学校类型:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select id="schoolType" name="schoolType" lay-verify="required">
							 <option value="">请选择</option>
							 <option value="0">学前</option>
							 <option value="1">小学</option>
							 <option value="2">中学</option>
							 <option value="3">大学</option>
							 <option value="4">国外院校</option>
							 <option value="5">其它</option>		
	    			   </select>
					</div>
				</div>
												
				<div class="layui-form-item">
				<label class="layui-form-label">学校名称:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="schoolName"  autocomplete="off" placeholder="请输入学校名称" lay-verify="required" class="layui-input">
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
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbSchool/add.js"></script>
</html>