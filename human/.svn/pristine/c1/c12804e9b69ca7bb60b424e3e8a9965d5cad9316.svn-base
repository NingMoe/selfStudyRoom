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
			 <input type="hidden" name="id"  value="${cc.id}">
				<div class="layui-form-item">
				<label class="layui-form-label">所属机构:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="hrCompanyId" id="hrCompanyId" lay-filter="comid">
							<option value="">请选择</option>
							<c:forEach items="${companyList }" var="company">
	    					 <option value="${company.companyId }" <c:if test="${sa.hrCompanyId eq company.companyId}">selected="selected"</c:if>>${company.companyName }</option>
	    					</c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">校区:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="schoolAreaName" id="schoolAreaName" lay-verify="required">
							<option value="${sa.name }">${sa.name }</option>
	    			    </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">年级:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="grade" lay-verify="required" autocomplete="off" placeholder="请输入年级" class="layui-input" value="${cc.grade }">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">组合:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="combinationData" lay-verify="required" autocomplete="off" placeholder="请输入组合班号,多个班号以逗号隔开" class="layui-input" value="${cc.combinationData }">
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
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/importCombinationData/edit.js"></script>
</html>