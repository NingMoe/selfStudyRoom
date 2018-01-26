、<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
.download{
    display: inline-block;
    height: 38px;
    line-height: 38px;
    padding: 0 18px;
    background-color: #009688;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 14px;
    border: none;
    border-radius: 2px;
    cursor: pointer;
    opacity: .9;
    width: 11%;
    margin-left: -8%;}
</style>
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
		<input type="hidden" name="id" id="id" value="${sa.id}">
		<input type="hidden" name="code" id="code" value="${sa.code}">
		<input type="hidden" name="sClassCode" id="sClassCode" value="${sa.sClassCode}">
			<div class="layui-form-item">
				<label class="layui-form-label">学生姓名:</label>
				<div class="layui-input-inline">
					<input type="text" id="stuName" name="stuName" value="${sa.stuName }"  readonly="readonly" lay-verify="required"
						class="layui-input">
				</div>
				<label class="layui-form-label" style="width:7%;margin-left:4%">学校</label>
				<div class="layui-input-inline">
					<input type="text" id="schoolName" name="schoolName" lay-verify="required"
						value="${sa.schoolName }" class="layui-input">
				</div>
<!-- 				<div class="layui-input-inline" > -->
<!-- 					<select name="schoolName" id="schoolName" lay-verify="required"> -->
<!-- 						<option value="">请选择</option> -->
<%-- 	   					<c:forEach items="${schoolName}" var="sn"> --%>
<%-- 	   						<option value="${sn.name }"  <c:if test="${sa.schoolName  eq sn.name  }">selected="selected"</c:if>>${sn.name }</option> --%>
<%-- 	   					</c:forEach> --%>
<!-- 	     			</select> -->
<!-- 				</div> -->
			</div>
			<div class="layui-form-item">	
				<label class="layui-form-label">规划师:</label>
				<div class="layui-input-inline">
					<input type="text" id="planner" name="planner" lay-verify="required"
						value="${sa.planner }" class="layui-input">
				</div>
				<label class="layui-form-label" style="width:7%;margin-left:4%">学生来源</label>
				<div class="layui-input-inline" >
					<select name="stuOrigin" id="stuOrigin" lay-verify="required">
						<option value="">请选择</option>
	   					<c:forEach items="${Origin}" var="Ori">
	   						<option value="${Ori.name }"  <c:if test="${sa.stuOrigin  eq Ori.name  }">selected="selected"</c:if>>${Ori.name }</option>
	   					</c:forEach>
	     			</select>
				</div>
			</div>
			<div class="layui-form-item">
					<label class="layui-form-label">学生家长:</label>
					<div class="layui-input-inline">
						<input type="text" id="stuPar" name="stuPar" lay-verify="required"
							value="${sa.stuPar }" class="layui-input">
					</div>
					<label class="layui-form-label">学生电话:</label>
					<div class="layui-input-inline">
						<input type="text" id="stuPhone" name="stuPhone" lay-verify="phone";
							value="${sa.stuPhone }" class="layui-input">
					</div>
			</div>
			<div class="layui-form-item">		
					<label class="layui-form-label">阅卷账号及密码:</label>
					<div class="layui-input-inline">
						<input type="text" id="marAcc" name="marAcc"
							value="${sa.marAcc }" class="layui-input">
					</div>
					<label class="layui-form-label" style="width: 150px; margin-left:-4%;">是否提交测试</label>
					<div class="layui-input-inline">
						<select name="isSub" id="isSub"  style="width: 150px;" readonly="readonly">
		    				<option value="">请选择</option>
		    				<option value="是" <c:if test="${'是'  eq sa.isSub }">selected="selected"</c:if>>是</option>
		    				<option value="否" <c:if test="${'否'  eq sa.isSub }">selected="selected"</c:if>>否</option>
      					</select>
					</div>
			</div>	
			<div class="layui-form-item">	
					<label class="layui-form-label">备注:</label>
					<div class="layui-input-inline">
						<input type="text" id="remark" name="remark"
							value="${sa.remark }" class="layui-input">
					</div>
					<label class="layui-form-label">测试附件:</label>
					<div class="layui-input-inline">
						<input type="text" id="testAcce" name="testAcce"
							value="${sa.testAcce }" class="layui-input" lay-verify="required">
					</div>
					<div class="layui-input-inline">
						<input name="file" lay-type="file" value="上传" class="layui-upload-file" type="file"> 
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
	src="<%=basePath%>/static/stuadmin/edit.js"></script>
</html>