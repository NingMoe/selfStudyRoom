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
			 <input type="hidden" name="id"  value="${jzbGradeSubjectClass.id}">
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">班级季度:</label>
					<div class="layui-input-inline">
						<select name="sQuarter" id="sQuarter" >
							<option value="">请选择</option>	    					
					        <option value="1"<c:if test="${jzbGradeSubjectClass.sQuarter eq '1'}">selected="selected"</c:if>>第一季度</option>
					        <option value="2"<c:if test="${jzbGradeSubjectClass.sQuarter eq '2'}">selected="selected"</c:if>>第二季度</option>
					        <option value="3"<c:if test="${jzbGradeSubjectClass.sQuarter eq '3'}">selected="selected"</c:if>>第三季度</option>
					        <option value="4"<c:if test="${jzbGradeSubjectClass.sQuarter eq '4'}">selected="selected"</c:if>>第四季度</option>
	    			    </select>
					</div>
				</div>
							
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">管理部门:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select id="sManagedeptcodes" name="sManagedeptcodes" lay-verify="required">
							 <option value="">请选择</option>	
							<c:forEach var="dicData" items="${deptList }">
		       	              <option value="${dicData.dataValue }"<c:if test="${jzbGradeSubjectClass.sManagedeptcodes eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">年级名称:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select id="sProjectcode" name="sProjectcode" lay-verify="required">
							 <option value="">请选择</option>	
							 <c:forEach var="dicData" items="${gradeList }">
		       	              <option value="${dicData.dataValue }"<c:if test="${jzbGradeSubjectClass.sProjectcode eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">科目名称:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select id="sClasssubject" name="sClasssubject" lay-verify="required">
							 <option value="">请选择</option>	
							<c:forEach var="dicData" items="${subjectList }">
		       	              <option value="${dicData.dataValue }"<c:if test="${jzbGradeSubjectClass.sClasssubject eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label" style="width: 150px;">班级名称关键字:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input type="text" id="sClassname" name="sClassname" autocomplete="off"  placeholder="班级名称关键字,例如:尖子" lay-verify="required" class="layui-input" value="${jzbGradeSubjectClass.sClassname}">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">开课起始日期:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input  id="startDateTime" name="startDateTime" lay-verify="required" class="layui-input" placeholder="开始日期" value="${jzbGradeSubjectClass.startDateTime}">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">开课结束日期:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input  id="endDateTime" name="endDateTime"  lay-verify="required" class="layui-input" placeholder="结束日期" value="${jzbGradeSubjectClass.endDateTime}">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">考试结果:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select name="isPass" id="isPass" lay-verify="required">
							<option value="">请选择</option>	    					
					        <option value="0"<c:if test="${jzbGradeSubjectClass.isPass eq '0'}">selected="selected"</c:if>>通过</option>
					        <option value="1"<c:if test="${jzbGradeSubjectClass.isPass eq '1'}">selected="selected"</c:if>>未通过</option>
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
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbGradeSubjectClass/edit.js"></script>
</html>