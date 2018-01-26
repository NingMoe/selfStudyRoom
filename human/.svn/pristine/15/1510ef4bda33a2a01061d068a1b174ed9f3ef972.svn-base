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
			 <input type="hidden" name="ruleId" id="ruleId" value="${cf.ruleId}">
	         <input type="hidden" name="type" id="type" value="${cf.type}">
				<div class="layui-form-item">
				<label class="layui-form-label">班号:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="classCode" lay-verify="required" autocomplete="off" placeholder="请输入班号" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">班级名称:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="className" lay-verify="required" autocomplete="off" placeholder="请输入班级名称" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">所属机构:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="hrCompanyId" id="hrCompanyId" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					 <option value="${company.companyId }">${company.companyName }</option>
	    					</c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">校区:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="schoolAreaName" id="schoolAreaName" lay-verify="required">
							<option value="">请选择</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">年级:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="grade" lay-verify="required" autocomplete="off" placeholder="请输入年级" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">科目:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="subject" lay-verify="required" autocomplete="off" placeholder="请输入科目" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">教师:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="teacherName" lay-verify="required" autocomplete="off" placeholder="请输入教师" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">部门:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="deptName"  lay-verify="required" placeholder="请输入部门" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">是否扩科:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="kuokeFlag" id="kuokeFlag" lay-verify="required">
							<option value="">请选择</option>
							<option value="0">是</option>
							<option value="1">否</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">是否尖子:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="topFlag" id="topFlag" lay-verify="required">
							<option value="">请选择</option>
							<option value="0">是</option>
							<option value="1">否</option>
							<option value="2">超</option>
	    			   </select>
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
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/importData/add.js"></script>
</html>