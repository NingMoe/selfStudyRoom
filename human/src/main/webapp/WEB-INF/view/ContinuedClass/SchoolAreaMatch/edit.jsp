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
			  <input type="hidden" name="ruleId" id="ruleId" value="${ruleId}">
			  <input type="hidden" id="saId"  value='${sa.id}'>
			  <input type="hidden" id="samList"  value='${samList}'>			  
			  <div class="layui-form-item">
				<label class="layui-form-label">所属机构:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="hrCompanyId" id="hrCompanyId" disabled="disabled">
							<option value="${sa.hrCompanyId}">${sa.companyName}</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">校区名称:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="schoolAreaId" id="schoolAreaId" disabled="disabled">
							<option value="${sa.id }">${sa.name }</option>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">临近校区:<font color="red">*</font></label>
				  <div class="layui-input-inline" style="width:500px;margin-left:110px;">
                   <c:forEach var="schoolArea" items="${saList}">
                     <input type="checkbox" name="matchName"  title="${schoolArea.name}"  value="${schoolArea.id }"/>
                   </c:forEach>
				  </div>					
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button type="button" class="layui-btn" id="tj">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/ContinuedClass/SchoolAreaMatch/edit.js"></script>
</html>