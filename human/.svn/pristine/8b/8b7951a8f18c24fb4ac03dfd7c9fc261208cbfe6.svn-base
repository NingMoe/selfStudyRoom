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
			 <input type="hidden" name="gradeSubjectId"  id="gradeSubjectId" value="${jzbGradeSubjectClass.gradeSubjectId}">
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">班级季度:</label>
					<div class="layui-input-inline">
						<select name="sQuarter" id="sQuarter" >
							<option value="">请选择</option>	    					
					        <option value="1">第一季度</option>
					        <option value="2">第二季度</option>
					        <option value="3">第三季度</option>
					        <option value="4">第四季度</option>
	    			    </select>
					</div>
				</div>
							
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">管理部门:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select id="sManagedeptcodes" name="sManagedeptcodes" lay-verify="required">
							 <option value="">请选择</option>	
							<c:forEach var="dicData" items="${deptList }">
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
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
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
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
		       	              <option value="${dicData.dataValue }">${dicData.name }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label" style="width: 150px;">班号开头相似:</label>
					<div class="layui-input-inline">
						<input type="text" id="containClassNumber" name="containClassNumber" autocomplete="off"  placeholder="班号开头相似,多个以英文逗号隔开" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label" style="width: 150px;">班号开头排除:</label>
					<div class="layui-input-inline">
						<input type="text" id="noContainClassNumber" name="noContainClassNumber" autocomplete="off"  placeholder="班号开头排除,多个以英文逗号隔开" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label" style="width: 150px;">班级名称关键字（包含）:</label>
					<div class="layui-input-inline">
						<input type="text" id="containClassName" name="containClassName" autocomplete="off"  placeholder="班级名称包含关键字,多个以英文逗号隔开" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label" style="width: 150px;">班级名称关键字（不包含）:</label
					<div class="layui-input-inline">
						<input type="text" id="noContainClassName" name="noContainClassName" autocomplete="off"  placeholder="班级名称排除关键字,多个以英文逗号隔开" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">开课起始日期:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input  id="startDateTime" name="startDateTime" lay-verify="required" class="layui-input" placeholder="开始日期" >
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">开课结束日期:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input  id="endDateTime" name="endDateTime"  lay-verify="required" class="layui-input" placeholder="结束日期" >
					</div>
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label"style="width: 150px;">考试结果:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<select name="isPass" id="isPass" lay-verify="required">
							<option value="">请选择</option>	    					
					        <c:forEach items="${levels }" var="level">
					        	<option value="${level.dataValue }">${level.name }</option>
					        </c:forEach>
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
<script type="text/javascript" src="<%=basePath %>/static/jzbTest/jzbGradeSubjectClass/add.js"></script>
</html>