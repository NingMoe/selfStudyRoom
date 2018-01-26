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
				<label class="layui-form-label">学生姓名:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="stName" name="stName" value="${dm.stName }"
						class="layui-input">
				</div>
				<label class="layui-form-label">学员班号:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="stclassNo" name="stclassNo"  
						value="${dm.stclassNo }" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" >
				<label class="layui-form-label">赠送课时:</label>
				<div class="layui-input-inline">
					<input type="text" id="sendClass" name="sendClass"  lay-verify="number"
						value="${dm.sendClass }" class="layui-input">
				</div>
				<label class="layui-form-label">剩余课时:</label>
				<div class="layui-input-inline">
					<input type="text" id="surplusClass" name="surplusClass" lay-verify="number"
						value="${dm.surplusClass }" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item ">
				<label class="layui-form-label">消耗情况:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="comsumSituation" id="comsumSituation" 
						lay-verify="comsumSituation" placeholder="" class="layui-textarea">${dm.comsumSituation}</textarea>
				</div>
			</div>
			<div class="layui-inline " style="margin: 0 auto 10px auto">
				<label class="layui-form-label ">是否用完:</label>
				<div class="layui-input-block">
					<select id="used" name="used" lay-filter="aihao">
						<option value="是">是</option>
						<option value="否">否</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
			<label class="layui-form-label">申请人:</label>
				<div class="layui-input-inline">
					<input type="text" id="applyName" name="applyName"
						value="${dm.applyName }" class="layui-input">
				</div>
				</div>
			<div class="layui-form-item">
				<label class="layui-form-label">申请时间:<font color="red">*</font></label>
					<div class="layui-input-inline">
						<input class="layui-input" placeholder="申请时间" id="applyDate" readonly="readonly"  lay-verify="required" name="applyDate">
					</div>
			</div>
			<div class="layui-form-item" >
   			    	<div class="layui-inline" style="margin: 0 auto 10px auto; width: 100%; margin-left: -2%!important;"  >
						<label class="layui-form-label" style="width: 100px;">赠送原因:</label>
						<div class="layui-input-block" style="width: 80%" id="class_type_checkbox" name="" >
							<input name="sendReason" checked="" type="radio"   value="转介绍"  title="转介绍" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="首次课失败"  title="首次课失败" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="超时"  title="转介绍" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="淘宝联名卡"  title="淘宝联名卡" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="投诉"  title="投诉" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="关系户"  title="关系户" lay-filter="sendReason">
							<input name="sendReason"  type="radio"  value="其他"  title="其他" lay-filter="sendReason">
					</div>
					<div class="layui-input-inline"  id="track" >
					<input type="text" id="sendReason" name="sendReason" lay-verify="required" 
						class="layui-input">
				</div>
			</div>
			</div>
			<div class="layui-form-item ">
				<label class="layui-form-label">备注:</label>
				<div class="layui-input-inline" style="width: 67% !important;">
					<textarea name="remark" id="remark" lay-verify="remark"
						placeholder="" class="layui-textarea">${dm.remark}</textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">OA流水号:<font color="red">*</font></label>
				<div class="layui-input-inline">
					<input type="text" id="number" name="number" 
						value="${dm.number}" class="layui-input">
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
	src="<%=basePath%>/static/datamanger/add.js"></script>
</html>