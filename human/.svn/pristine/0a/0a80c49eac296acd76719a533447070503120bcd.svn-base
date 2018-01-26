<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增学员信息</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<%@include file="/WEB-INF/view/common/taglib.jsp" %>
	<script type="text/javascript" src="<%=basePath %>/static/continuation/js/configadd.js"></script>
</head>
	<body style="padding:10px;">
		<div class="alertFrom">
			<div class="layui-form">
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">部门:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<select name="manager_dept_code" id="manager_dept_code" style="width: 150px;">
								<option value="">请选择</option>
						      	<c:if test="${flag }">
							      	<c:forEach items="${ list}" var="dept">
							      		<option value="${dept.dataValue }">${dept.name }</option>
							      	</c:forEach>
							    </c:if>
						    </select>
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">原班财年:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<select name="old_fiscal_year" id="old_fiscal_year" style="width: 150px;">
								<option value="">请选择</option>
						      	<c:if test="${y_flag }">
							      	<c:forEach items="${ y_list}" var="f_year">
							      		<option value="${f_year.dataValue }">${f_year.name }</option>
							      	</c:forEach>
							    </c:if>
						    </select>
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">原班季度:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<select name="old_class_quarter" id="old_class_quarter" style="width: 150px;">
								<option value="">请选择</option>
							    <option value="1">春季</option>
							    <option value="2">暑假</option>
							    <option value="3">秋季</option>
							    <option value="4">寒假</option>
						    </select>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">续班财年:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<select name="new_fiscal_year" id="new_fiscal_year" style="width: 150px;">
								<option value="">请选择</option>
						      	<c:if test="${y_flag }">
							      	<c:forEach items="${ y_list}" var="f_year">
							      		<option value="${f_year.dataValue }">${f_year.name }</option>
							      	</c:forEach>
							    </c:if>
						    </select>
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">续班季度:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<select name="new_class_quarter" id="new_class_quarter" style="width: 150px;">
								<option value="">请选择</option>
							    <option value="1">春季</option>
							    <option value="2">暑假</option>
							    <option value="3">秋季</option>
							    <option value="4">寒假</option>
						    </select>
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">微信续班名称:</label>
						<div class="layui-input-inline">
							<input name="continue_name" id="continue_name" style="width: 160px;" type="text" placeholder="请输入微信续班名称" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">微信续班ID:</label>
						<div class="layui-input-inline">
							<input name="continue_id" id="continue_id" style="width: 160px;" type="text" placeholder="请输入微信续班ID" class="layui-input">
						</div>
					</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">升班期名称:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
							<input name="sbq_config_name" id="sbq_config_name" style="width: 160px;" type="text" placeholder="请输入升班期名称" class="layui-input">
						</div>
					</div>
					<div class="layui-inline" style="margin: 0 auto 10px auto">
						<label class="layui-form-label" style="width: 100px;">升班期ID:<span style="color: red">*</span></label>
						<div class="layui-input-inline">
						    <select name="sbq_id_add" id="sbq_id_add" style="width: 200px;">
								<option value="">请选择</option>
								<c:forEach items="${ sbq}" var="sbq" varStatus="status">
							    	<option data-nSchoolId="${sbq.nSchoolId }" data-sWindowPeriodName="${sbq.sWindowPeriodName}" data-sStageName="${sbq.sStageName}" data-sWindowPeriodId="${sbq.sWindowPeriodId}" data-sStageId="${sbq.sStageId}" value="${status.index }">${sbq.sWindowPeriodName } : ${sbq.sStageName }</option>
							    </c:forEach>
						    </select>
						</div>
					</div>
				</div>
			
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button id="continue_info_add" class="layui-btn">立即提交</button>
					</div>
				</div>
			</div>
		</div>
		<!-- layui.use -->
	</body>
</html>