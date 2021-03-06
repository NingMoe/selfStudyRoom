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
<body>
	<div class="alertFrom">
		<form class="layui-form" id="addForm" action="" method="post">
		<input type="hidden" name="id" value="${se.id}">
			<div class="layui-form-item">
				<label class="layui-form-label">班号:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="stclassNo" name="stclassNo"  lay-verify="required"
						value="${se.stclassNo}" class="layui-input">
				</div>
				<label class="layui-form-label">考试时间:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input class="layui-input" lay-verify="required" placeholder="申请时间" id="time" readonly="readonly"   name="time" value="${se.time}">
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline ">
					<label class="layui-form-label ">考试类型:</label>
					<div class="layui-input-block" style="width:59%">
						<select id="type" name="type" lay-filter="type">
							<option value="随堂测">随堂测</option>
							<option value="词汇">词测</option>
							<option value="阶段测试">阶段测试</option>
							<option value="模考">模考</option>
							<option value="实考">实考</option>
						</select>
					</div>
				</div>
				<div class="layui-inline " style="    margin-left: -4%; width: 42%;" >
					<label class="layui-form-label ">阶段:</label>
					<div class="layui-input-block" style="width:60%">
						<select id="stage" name="stage" lay-filter="stage" onchange="choose('${se.stage}');" >
							<c:forEach items="${stage}" var="st">
	        				<option value="${st.name }" <c:if test="${se.stage eq st.name }">selected="selected"</c:if>>${st.name }</option>
	        				</c:forEach>
						</select>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item show" >
				<label class="layui-form-label">语法老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="yfTearcher" name="yfTearcher"  
						value="${se.yfTearcher}" class="layui-input">
				</div>
				<label class="layui-form-label">听口老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="tkTearcher" name="tkTearcher" 
						value="${se.tkTearcher}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item show" >
				<label class="layui-form-label">读写老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="dTearcher" name="dTearcher"  
						value="${se.dTearcher}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item hide" >
				<label class="layui-form-label">阅读:</label>
				<div class="layui-input-inline">
					<input type="text" id="rTearcher" name="rTearcher"  
						value="${se.rTearcher}" class="layui-input">
				</div>
				<label class="layui-form-label">听力:</label>
				<div class="layui-input-inline">
					<input type="text" id="lTearcher" name="lTearcher" 
						value="${se.lTearcher}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item hide" >
				<label class="layui-form-label">口语:</label>
				<div class="layui-input-inline">
					<input type="text" id="sTearcher" name="sTearcher"  
						value="${se.sTearcher}" class="layui-input">
				</div>
				<label class="layui-form-label">写作:</label>
				<div class="layui-input-inline">
					<input type="text" id="wTearcher" name="wTearcher" 
						value="${se.wTearcher}" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline ">
					<label class="layui-form-label ">考试次数:</label>
					<div class="layui-input-block" style="width:59%">
						<select id="examName" name="examName" lay-filter="examName ">
						<c:forEach items="${examtime }" var="exam">
	        				<option value="${exam.name }" <c:if test="${se.examName eq exam.name }">selected="selected"</c:if>>${exam.name }</option>
	        				</c:forEach>
						</select>
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
	src="<%=basePath%>/static/northamerica/stuexam/edit.js"></script>
</html>