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
			 <input type="hidden" name="id"  value="${jzbGrade.id}">			 			 
			    <div class="layui-form-item">
				<label class="layui-form-label">选择部门:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select name="deptId" id="deptId" lay-filter="deptId" lay-verify="required">
							<option value="">请选择</option>
	    					<c:forEach var="jzbDept" items="${jzbDepts }">
		       	              <option value="${jzbDept.id }"<c:if test="${jzbGrade.deptId eq jzbDept.id}">selected="selected"</c:if>>${jzbDept.name }</option>
		                    </c:forEach>
	    			    </select>
					</div>
				</div>
								
				<div class="layui-form-item">
				<label class="layui-form-label">学校类型:<font color="red">*</font></label>
					<div class="layui-input-block">
						<select id="schoolType" name="schoolType" lay-verify="required">
							 <option value="">请选择</option>
							 <option value="0"<c:if test="${jzbGrade.schoolType eq '0'}">selected="selected"</c:if>>学前</option>
							 <option value="1"<c:if test="${jzbGrade.schoolType eq '1'}">selected="selected"</c:if>>小学</option>
							 <option value="2"<c:if test="${jzbGrade.schoolType eq '2'}">selected="selected"</c:if>>中学</option>
							 <option value="3"<c:if test="${jzbGrade.schoolType eq '3'}">selected="selected"</c:if>>大学</option>
							 <option value="4"<c:if test="${jzbGrade.schoolType eq '4'}">selected="selected"</c:if>>国外院校</option>
							 <option value="5"<c:if test="${jzbGrade.schoolType eq '5'}">selected="selected"</c:if>>其它</option>		
	    			   </select>
					</div>
				</div>
												
				<div class="layui-form-item">
				<label class="layui-form-label">年级名称:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="text" name="gradeName"  autocomplete="off" placeholder="请输入年级名称" lay-verify="required" class="layui-input" value="${jzbGrade.gradeName}">
					</div>
				</div>
				
				
				<div class="layui-form-item">
				<label class="layui-form-label">排序权重:<font color="red">*</font></label>
					<div class="layui-input-block">
						<input type="number" name="sort"   autocomplete="off"  placeholder="请输入排序权重,值越大排序越往后"  lay-verify="required|number" class="layui-input" value="${jzbGrade.sort}">
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
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbGrade/edit.js"></script>
</html>