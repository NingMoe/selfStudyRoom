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
			<form class="layui-form" id="addUserForm" action="" method="post" >
				<div class="layui-form-item">
					<label class="layui-form-label">手机号:</label>
					<div class="layui-input-inline">
						<input type="text" name="phone" id="phone" lay-verify="required" autocomplete="off" placeholder="请输入手机号" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name"  lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="userPassword" lay-verify="pass"   id="userPassword" placeholder="请输入密码"  autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请填写6到12位密码</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="subPassword" lay-verify="subPassword" placeholder="请再输入密码" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请再如入一次密码</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">性别:</label>
					<div class="layui-input-inline">
						<select name="sex" id='sex'>
						    <option value="">请选择</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">学科:</label>
					<div class="layui-input-inline">
						<select name="subject" id='subject' lay-verify="required">
							<c:forEach items="${subjectList }" var="subject">
		    					<option value="${subject.dataValue }">${subject.name }</option>
		    				</c:forEach>
						</select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">年级:</label>
					<div class="layui-input-inline">
						  <select name="grade" id='grade' lay-verify="required">
						    <option value="">请选择</option>
							<c:forEach items="${gradeList }" var="grade">
		    					<option value="${grade.dataValue }">${grade.name }</option>
		    				</c:forEach>
						 </select>						
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">学校类型:</label>
					<div class="layui-input-inline">
						  <select name="type" id='type' lay-filter="type" lay-verify="required">
						    <option value="">请选择</option>
							<option value="1">新东方学校</option>
							<option value="2">公立学校</option>
						 </select>						
					</div>
				</div>

				<div class="layui-form-item accept"  style="display:none;">
					<label class="layui-form-label">学校名称:</label>					
					<div class="layui-input-inline">
						<select name="school" id='school'>
						    <c:forEach items="${xdfSchoolList }" var="xdfSchool">
		    					<option value="${xdfSchool.dataValue }">${xdfSchool.name }</option>
		    				</c:forEach>  
					    </select>
					</div>
				</div>
				<div class="layui-form-item send" style="display:none;">
					<label class="layui-form-label">省份:</label>
					<div class="layui-input-inline">
						<select name="schoolProvince" id="schoolProvince" lay-filter="schoolProvinceId">
							<option value="">请选择</option>
							<c:forEach var="area" items="${areaInfo }">
		       	              <option value="${area.id }">${area.areaName }</option>
		                    </c:forEach>
	    			   </select>
					</div>
				</div>

				<div class="layui-form-item send" style="display:none;">
					<label class="layui-form-label">城市:</label>
					<div class="layui-input-inline">
						<select id="schoolCity" name="schoolCity" lay-filter="schoolCityId">
							<option value="">请选择</option>
	    			   </select>
					</div>
				</div>
				<div class="layui-form-item send" style="display:none;">
					<label class="layui-form-label">行政区:</label>
					<div class="layui-input-inline">
						<select id="schoolArea" name="schoolArea">
							<option value="">请选择</option>
	    			   </select>
					</div>
				</div>
				<div class="layui-form-item send" style="display:none;">
					<label class="layui-form-label">学校类型:</label>
					<div class="layui-input-inline">
						<select id="schoolType" name="schoolType" lay-filter="schoolType">
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

				<div class="layui-form-item send" style="display:none;">
					<label class="layui-form-label">学校名称:</label>
					<div class="layui-input-inline">
						<select id="schoolName" name="schoolName">
							<option value="">请选择</option>
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
		 <script type="text/javascript" src="<%=basePath %>/static/manager/js/user_add.js"></script>
		<script>
		</script>
	</body>
</html>