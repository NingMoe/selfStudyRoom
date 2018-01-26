<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<%@include file="/WEB-INF/view/common/taglib.jsp"%></head>
</head>
<style>
.layui-form-label{
width:110px;
}
.layui-form-label {
    width: 112px;
}
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">班号</label>
				<div class="layui-input-inline">
					<input type="text" id="sClassCode" name="sClassCode" value="${xdf.sClassCode }"  readonly="readonly"
						class="layui-input">
				</div>
				<label class="layui-form-label">班级名称</label>
				<div class="layui-input-inline">
					<input type="text" id="sClassName" name="sClassName" value="${xdf.sClassName }"  readonly="readonly"
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">	
				<label class="layui-form-label">授课老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="sAllTearcherName" name="sAllTearcherName"  readonly="readonly"
						value="${xdf.sAllTeacherName}" class="layui-input">
				</div>
				<label class="layui-form-label">教研组:</label>
				<div class="layui-input-inline">
					<input type="text" id="sClassTypeName" name="sClassTypeName"  readonly="readonly"
						value="${xdf.sClassTypeName }" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
					<label class="layui-form-label">区域:</label>
					<div class="layui-input-inline">
						<input type="text" id="sAreaAddress" name="sAreaAddress"  readonly="readonly"
							value="${xdf.sAreaAddress }" class="layui-input">
					</div>
					<label class="layui-form-label">上课校区:</label>
					<div class="layui-input-inline">
						<input type="text" id="sPrintAddress" name="sPrintAddress"  readonly="readonly"
							value="${xdf.sPrintAddress }" class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">		
					<label class="layui-form-label">上课时间:</label>
					<div class="layui-input-inline">
						<input type="text" id="sPrintTime" name="sPrintTime"  readonly="readonly"
							value="${xdf.sPrintTime }" class="layui-input">
					</div>
					<label class="layui-form-label">当前系统人数:</label>
					<div class="layui-input-inline">
						<input type="text" id="nCurrentCount" name="nCurrentCount"  readonly="readonly"
							value="${xdf.nCurrentCount }" class="layui-input">
					</div>
			</div>	
			<div class="layui-form-item">
					<label class="layui-form-label" style="width:7%;margin-left:3%">班级水平</label>
					<div class="layui-input-inline" >
						<select name="sLevel" id="sLevel">
		   					<c:forEach items="${classLevel}" var="classLevel">
		   						<option value="${classLevel.dataValue }"  <c:if test="${xp.s_level  eq classLevel.name  }">selected="selected"</c:if>>${classLevel.name }</option>
		   					</c:forEach>
		     			</select>
					</div>	
					<label class="layui-form-label">备注:</label>
					<div class="layui-input-inline">
						<input type="text" id="remark" name="remark" 
							value="${xp.remark}" class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">	
					<label class="layui-form-label">是否允许新生报名</label>
					<div class="layui-input-inline">
					<c:if test="${true eq xdf.bCanRegister}">
						<input type="text" id="bCanRegister" name="bCanRegister" readonly="readonly"
							value="是" class="layui-input">
					</c:if>
					<c:if test="${false eq xdf.bCanRegister}">
						<input type="text" id="bCanRegister" name="bCanRegister" readonly="readonly"
							value="是" class="layui-input">
					</c:if>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/xdfclassinfo/edit.js"></script>
</html>