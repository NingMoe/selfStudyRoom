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
		<%@include file="/WEB-INF/view/common/taglib.jsp" %>
</head>
	<body>
		<div class="alertFrom">
			<form class="layui-form" id="addForm" action="" method="post">
			<input type="hidden" name="schoolId" value="${college.schoolId }">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">院校省份</label>
					<div class="layui-input-inline">
						<select name="provinceId" id="provinceId" lay-verify="required" style="width: 150px;">
		    				<option value="">请选择</option>
		    				<c:forEach items="${areaInfo }" var="area">
    				            <option value="${area.id }"<c:if test="${area.id eq college.provinceId}">selected="selected"</c:if>>${area.areaName }</option>
    				        </c:forEach>
      					</select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">院校性质</label>
					<div class="layui-input-inline">
						<select name="type" id="type" style="width: 150px;" lay-verify="required">
		    				<option value="">请选择</option>
		    				<option value="1" <c:if test="${college.type eq '1'}">selected="selected"</c:if>>大学</option>
		    				<option value="2" <c:if test="${college.type eq '2'}">selected="selected"</c:if>>学院</option>
		    				<option value="3" <c:if test="${college.type eq '3'}">selected="selected"</c:if>>高等专科学校</option>
		    				<option value="4" <c:if test="${college.type eq '4'}">selected="selected"</c:if>>高等职业技术学校</option>
		    				<option value="5" <c:if test="${college.type eq '5'}">selected="selected"</c:if>>高等学校分校</option>
		    				<option value="6" <c:if test="${college.type eq '6'}">selected="selected"</c:if>>独立学院</option>
		    				<option value="7" <c:if test="${college.type eq '7'}">selected="selected"</c:if>>短期职业大学</option>
		    				<option value="8" <c:if test="${college.type eq '8'}">selected="selected"</c:if>>成人高等学校</option>
		    				<option value="9" <c:if test="${college.type eq '9'}">selected="selected"</c:if>>管理干部学院</option>
		    				<option value="10" <c:if test="${college.type eq '10'}">selected="selected"</c:if>>教育学院</option>
		    				<option value="11" <c:if test="${college.type eq '11'}">selected="selected"</c:if>>其它</option> 
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">院校分类</label>
					<div class="layui-input-inline">
						<select name="schoolProperty" id="schoolProperty" style="width: 150px;">
		    				<option value="">请选择</option> 
 	    				    <option value="1" <c:if test="${college.schoolProperty eq '1'}">selected="selected"</c:if>>综合</option> 
	    				    <option value="2" <c:if test="${college.schoolProperty eq '2'}">selected="selected"</c:if>>工科</option> 
	 	    				<option value="3" <c:if test="${college.schoolProperty eq '3'}">selected="selected"</c:if>>农林</option> 
		    				<option value="4" <c:if test="${college.schoolProperty eq '4'}">selected="selected"</c:if>>医药</option>
	 	    				<option value="5" <c:if test="${college.schoolProperty eq '5'}">selected="selected"</c:if>>师范</option>
	 	    				<option value="6" <c:if test="${college.schoolProperty eq '6'}">selected="selected"</c:if>>语言</option>
	 	    				<option value="7" <c:if test="${college.schoolProperty eq '7'}">selected="selected"</c:if>>财经</option>
		    				<option value="8" <c:if test="${college.schoolProperty eq '8'}">selected="selected"</c:if>>政法</option>
		    				<option value="9" <c:if test="${college.schoolProperty eq '9'}">selected="selected"</c:if>>体育</option>
	 	    				<option value="10" <c:if test="${college.schoolProperty eq '10'}">selected="selected"</c:if>>艺术</option>
	 	    				<option value="11" <c:if test="${college.schoolProperty eq '11'}">selected="selected"</c:if>>民族</option>
	 	    				<option value="12" <c:if test="${college.schoolProperty eq '12'}">selected="selected"</c:if>>军事</option>
		    				<option value="13" <c:if test="${college.schoolProperty eq '13'}">selected="selected"</c:if>>其它</option>	   
	      			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学历层次</label>
					<div class="layui-input-inline">
						<select name="educationalLevel" id="educationalLevel" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0"<c:if test="${college.educationalLevel eq '0'}">selected="selected"</c:if>>本科</option>
		    				<option value="1"<c:if test="${college.educationalLevel eq '1'}">selected="selected"</c:if>>高职（专科）</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
							
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">是否211</label>
					<div class="layui-input-inline">
						<select name="is211" id="is211" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0"<c:if test="${college.is211 eq '0'}">selected="selected"</c:if>>否</option>
		    				<option value="1"<c:if test="${college.is211 eq '1'}">selected="selected"</c:if>>是</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
			   </div>
				
			<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">是否985</label>
					<div class="layui-input-inline">
						<select name="is985" id="is985" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0"<c:if test="${college.is985 eq '0'}">selected="selected"</c:if>>否</option>
		    				<option value="1"<c:if test="${college.is985 eq '1'}">selected="selected"</c:if>>是</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
																			
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学校名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name" lay-verify="required" placeholder="请输入学校名称" autocomplete="off" class="layui-input" value="${college.name }">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学校官网地址</label>
					<div class="layui-input-inline">
						<input type="text" name="site" id="site" placeholder="请输入学校官网"  autocomplete="off" class="layui-input" value="${college.site }">
					</div>
				</div>
				
				<div class="layui-form-item ">
				    <label class="layui-form-label"style="width: 150px;">学校简介</label>
				    <div class="layui-input-inline">
				      <textarea name="info"  placeholder="请输入内容" class="layui-textarea" style="width: 400px;">${college.info }</textarea>
				    </div>
 				</div>								
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" lay-submit="" lay-filter="reMail">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
					</div>
				</div>
			</form>
		</div>		
	</body>
<script type="text/javascript" src="<%=basePath %>/static/basic/js/college/edit.js"></script>
</html>