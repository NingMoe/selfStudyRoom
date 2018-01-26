<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
		<title>编辑用户</title>
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
				<input type="hidden" name="id" id="userId" value="${user.id}">
				<div class="layui-form-item">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name" lay-verify="required" value="${user.name}" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label">性别:</label>
					<div class="layui-input-inline">
						<select name="sex" id='sex'>
						    <option value="">请选择</option>
							<option value="1"<c:if test="${user.sex eq '1'}">selected="selected"</c:if>>男</option>
							<option value="2"<c:if test="${user.sex eq '2'}">selected="selected"</c:if>>女</option>
						</select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">学科:</label>
					<div class="layui-input-inline">
						<select name="subject" id='subject' lay-verify="required">
							<c:forEach var="dicData" items="${subjectList }">
		       	              <option value="${dicData.dataValue }"<c:if test="${user.subject eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
		                    </c:forEach>
						</select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年级:</label>
					<div class="layui-input-inline">
						  <select name="grade" id='grade' lay-verify="required">
						    <c:forEach var="dicData" items="${gradeList }">
		       	              <option value="${dicData.dataValue }"<c:if test="${user.grade eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
		                    </c:forEach>							
						 </select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">学校类型:</label>
					<div class="layui-input-inline">
						  <select name="type" id='type' lay-filter="type" lay-verify="required">
						    <option value="">请选择</option>
							<option value="1"<c:if test="${user.type eq '1'}">selected="selected"</c:if>>新东方学校</option>
							<option value="2"<c:if test="${user.type eq '2'}">selected="selected"</c:if>>公立学校</option>
						 </select>						
					</div>
				</div>

                 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item accept" style="display:inline;"> 
				　</c:if>
				  <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item accept" style="display:none;"> 
				　</c:if>
					<label class="layui-form-label">学校名称:</label>					
					<div class="layui-input-inline">
						<select name="school" id='school'>
						    <c:if test="${user.type eq '1'}">
							    <c:forEach items="${xdfSchoolList }" var="dicData">						       
			    					<option value="${dicData.dataValue }" <c:if test="${user.school eq dicData.dataValue}">selected="selected"</c:if>>${dicData.name }</option>
			    				</c:forEach>
		    				</c:if>
		    				<c:if test="${user.type eq '2'}">
							    <c:forEach items="${xdfSchoolList }" var="dicData">						       
			    					<option value="${dicData.dataValue }">${dicData.name }</option>
			    				</c:forEach>
		    				</c:if>
					    </select>
					</div>
				</div>
				
				 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>
					<label class="layui-form-label">省份:</label>
					<div class="layui-input-inline">
						<select name="schoolProvince" id="schoolProvince" lay-filter="schoolProvinceId">
						    <c:if test="${user.type eq '1'}">
							    <option value="">请选择</option>
								<c:forEach var="area" items="${areaInfo }">
			       	              <option value="${area.id }">${area.areaName }</option>
			                    </c:forEach>
		    				</c:if>
							<c:if test="${user.type eq '2'}">
							    <option value="">请选择</option>
								<c:forEach var="area" items="${areaInfo }">
			       	              <option value="${area.id }" <c:if test="${school.schoolProvince eq area.id}">selected="selected"</c:if>>${area.areaName }</option>
			                    </c:forEach>
		    				</c:if>
	    			   </select>
					</div>
				</div>

				 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>
					<label class="layui-form-label">城市:</label>
					<div class="layui-input-inline">
						<select id="schoolCity" name="schoolCity" lay-filter="schoolCityId">
							<c:if test="${user.type eq '1'}">
								<option value="">请选择</option>
							</c:if>
							<c:if test="${user.type eq '2'}">
								<option value="${school.schoolCity }">${school.schoolCityName }</option>
							</c:if>
	    			   </select>
					</div>
				</div>
				
				 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>
					<label class="layui-form-label">行政区:</label>
					<div class="layui-input-inline">
						<select id="schoolArea" name="schoolArea" lay-filter="schoolAreaId">
							<c:if test="${user.type eq '1'}">
								<option value="">请选择</option>
							</c:if>
							<c:if test="${user.type eq '2'}">
								<option value="${school.schoolArea }">${school.schoolAreaName }</option>
							</c:if>
	    			   </select>
					</div>
				</div>
				
				 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>
					<label class="layui-form-label">学校类型:</label>
					<div class="layui-input-inline">
						<select id="schoolType" name="schoolType" lay-filter="schoolType">
							 <option value="">请选择</option>
							 <option value="0"<c:if test="${school.schoolType eq '0'}">selected="selected"</c:if>>学前</option>
							 <option value="1"<c:if test="${school.schoolType eq '1'}">selected="selected"</c:if>>小学</option>
							 <option value="2"<c:if test="${school.schoolType eq '2'}">selected="selected"</c:if>>中学</option>
							 <option value="3"<c:if test="${school.schoolType eq '3'}">selected="selected"</c:if>>大学</option>
							 <option value="4"<c:if test="${school.schoolType eq '4'}">selected="selected"</c:if>>国外院校</option>
							 <option value="5"<c:if test="${school.schoolType eq '5'}">selected="selected"</c:if>>其它</option>		
	    			   </select>
					</div>
				</div>
				
                 <c:if test="${user.type eq '1'}">   
				　　  <div class="layui-form-item send" style="display:none;"> 
				　</c:if>
			     <c:if test="${user.type eq '2'}">   
				　　  <div class="layui-form-item send" style="display:inline;"> 
				　</c:if>
					<label class="layui-form-label">学校名称:</label>
					<div class="layui-input-inline">
						<select id="schoolName" name="schoolName">
						    <c:if test="${user.type eq '1'}">
								<option value="">请选择</option>
							</c:if>
							<c:if test="${user.type eq '2'}">
								<option value="${school.id}">${school.schoolName}</option>
							</c:if>					
	    			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="<%=basePath %>/static/manager/js/user_edit.js"></script>
	</body>
</html>