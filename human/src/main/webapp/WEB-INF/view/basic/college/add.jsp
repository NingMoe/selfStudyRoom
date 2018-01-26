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
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">院校省份</label>
					<div class="layui-input-inline">
						<select name="provinceId" id="provinceId" lay-verify="required" style="width: 150px;">
		    				<option value="">请选择</option>
		    				<c:forEach items="${areaInfo }" var="area">
    				            <option value="${area.id }">${area.areaName }</option>
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
		    				<option value="1">大学</option>
		    				<option value="2">学院</option>
		    				<option value="3">高等专科学校</option>
		    				<option value="4">高等职业技术学校</option>
		    				<option value="5">高等学校分校</option>
		    				<option value="6">独立学院</option>
		    				<option value="7">短期职业大学</option>
		    				<option value="8">成人高等学校</option>
		    				<option value="9">管理干部学院</option>
		    				<option value="10">教育学院</option>
		    				<option value="11">其它</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">院校分类</label>
					<div class="layui-input-inline">
						<select name="schoolProperty" id="schoolProperty" style="width: 150px;">
		    				<option value="">请选择</option> 
 	    				    <option value="1">综合</option> 
	    				    <option value="2">工科</option> 
	 	    				<option value="3">农林</option> 
		    				<option value="4">医药</option>
	 	    				<option value="5">师范</option>
	 	    				<option value="6">语言</option>
	 	    				<option value="7">财经</option>
		    				<option value="8">政法</option>
		    				<option value="9">体育</option>
	 	    				<option value="10">艺术</option>
	 	    				<option value="11">民族</option>
	 	    				<option value="12">军事</option>
		    				<option value="13">其它</option>	   
	      			   </select>
					</div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学历层次</label>
					<div class="layui-input-inline">
						<select name="educationalLevel" id="educationalLevel" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0">本科</option>
		    				<option value="1">高职（专科）</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">是否211</label>
					<div class="layui-input-inline">
						<select name="is211" id="is211" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0">否</option>
		    				<option value="1">是</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">是否985</label>
					<div class="layui-input-inline">
						<select name="is985" id="is985" style="width: 150px;" lay-verify="required">
			    		    <option value="">请选择</option>
		    				<option value="0">否</option>
		    				<option value="1">是</option>  
	      			   </select>
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
																			
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学校名称</label>
					<div class="layui-input-inline">
						<input type="text" name="name" id="name" lay-verify="required" placeholder="请输入学校名称" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"><font color="red">*</font></div>
				</div>
				
				<div class="layui-form-item">
					<label class="layui-form-label" style="width: 150px;">学校官网地址</label>
					<div class="layui-input-inline">
						<input type="text" name="site" id="site" placeholder="请输入学校官网"  autocomplete="off" class="layui-input">
					</div>
				</div>
				
				<div class="layui-form-item ">
				    <label class="layui-form-label"style="width: 150px;">学校简介</label>
				    <div class="layui-input-inline">
				      <textarea name="info"  placeholder="请输入内容" class="layui-textarea" style="width: 400px;"></textarea>
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
<script type="text/javascript" src="<%=basePath %>/static/basic/js/college/add.js"></script>
</html>