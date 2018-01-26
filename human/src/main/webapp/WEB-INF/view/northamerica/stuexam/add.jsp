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
			<div class="layui-form-item">
				<label class="layui-form-label">学生班号:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="stclassNo" name="stclassNo" value="" lay-verify="required"
						class="layui-input">
				</div>
				<label class="layui-form-label">考试时间:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input class="layui-input" placeholder="申请时间" id="time" lay-verify="required"
						readonly="readonly" name="time">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline ">
					<label class="layui-form-label ">考试类型:</label>
					<div class="layui-input-block" style="width:59%">
						<select id="type" name="type" lay-filter="type">
							<option value="随堂测">随堂测</option>
							<option value="词汇">词汇</option>
							<option value="阶段测试">阶段测试</option>
							<option value="模考">模考</option>
							<option value="实考">实考</option>
						</select>
					</div>
				</div>
				<div class="layui-inline " style=" margin-left: -4%; width: 42%;">
					<label class="layui-form-label ">阶段:</label>
					<div class="layui-input-block" style="width:60%">
						<select id="stage" name="stage" lay-filter="stage">
							<option value="预备">预备</option>
							<option value="基础">基础</option>
							<option value="强化">强化</option>
							<option value="冲刺">冲刺</option>
						</select>
					</div>
				</div>
			</div>
			<div class="layui-form-item show">
				<label class="layui-form-label">语法老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="yfTearcher" name="yfTearcher" value=""
						class="layui-input">
				</div>
				<label class="layui-form-label">听口老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="tkTearcher" name="tkTearcher" value=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item show">
				<label class="layui-form-label">读写老师:</label>
				<div class="layui-input-inline">
					<input type="text" id="dTearcher" name="dTearcher" value=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item hide">
				<label class="layui-form-label">阅读:</label>
				<div class="layui-input-inline">
					<input type="text" id="rTearcher" name="rTearcher" value=""
						class="layui-input">
				</div>
				<label class="layui-form-label">听力:</label>
				<div class="layui-input-inline">
					<input type="text" id="lTearcher" name="lTearcher" value=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item hide">
				<label class="layui-form-label">口语:</label>
				<div class="layui-input-inline">
					<input type="text" id="sTearcher" name="sTearcher" value=""
						class="layui-input">
				</div>
				<label class="layui-form-label">写作:</label>
				<div class="layui-input-inline">
					<input type="text" id="wTearcher" name="wTearcher" value=""
						class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-inline ">
					<label class="layui-form-label ">考试次数:</label>
					<div class="layui-input-block" style="width:59%">
						<select id="examName" name="examName" lay-filter="examName" value="${se.examName}">
							<c:forEach items="${examtime }" var="examtime">
	        				<option value="${examtime.name }" >${examtime.name }</option>
	        				</c:forEach>
						</select>
						</select>
					</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button id="data-manger" class="layui-btn" lay-submit=""
						lay-filter="demo1">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary" id="res">重置</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript"
	src="<%=basePath%>/static/northamerica/stuexam/add.js"></script>
</html>