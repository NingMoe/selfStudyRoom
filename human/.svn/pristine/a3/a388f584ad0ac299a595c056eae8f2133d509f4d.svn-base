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
	<body style="padding:20px;">
			<form class="layui-form" id="addForm">
				<input id="actid" type="hidden" name="actid" value="${actid }">
				<div class="layui-form-item">
					<label class="layui-form-label">拒绝原因</label>
					<div class="layui-input-inline">
    					<select name="refuseReason" id="refuseReason" lay-verify="required">
    					<c:forEach items="${reasons }" var="reason">
    						<option value="${reason.name }">${reason.name }</option>
    					</c:forEach>
      					</select>
    				</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" >
						<button class="layui-btn" type="button" lay-submit="" lay-filter="rf">确定</button>
					</div>
				</div>
			</form>
		<script type="text/javascript" src="<%=basePath %>/static/recruitment/seekerEntry/refuse.js"></script>
	</body>
</html>